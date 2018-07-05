package uk.co.wehavecookies56.kk.client.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import uk.co.wehavecookies56.kk.client.core.handler.InputHandler;
import uk.co.wehavecookies56.kk.common.capability.ModCapabilities;
import uk.co.wehavecookies56.kk.common.lib.Constants;
import uk.co.wehavecookies56.kk.common.lib.Reference;

public class GuiLockOn extends GuiScreen {

	int guiWidth = 256;
	int guiHeight = 256;

	int hpGuiWidth = 173;
	float hpBarWidth;
	int BarHP;
	int hpBars;
	int currentBar;

	int hpGuiHeight = 10;
	int noborderguiwidth = 171;

	int max = 23;
	int i = max;
	int multiplier = 4;
	
	float scale;

	@SubscribeEvent
	public void onRenderOverlayPost(RenderGameOverlayEvent event) {

		if (!Minecraft.getMinecraft().player.getCapability(ModCapabilities.PLAYER_STATS, null).getHudMode())
			return;
		if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
			Entity target = InputHandler.lockOn;
			if (target == null)
				return;
			Minecraft mc = Minecraft.getMinecraft();
			EntityPlayer player = mc.player;
			float reduction = 4;

			mc.renderEngine.bindTexture(new ResourceLocation(Reference.MODID, "textures/gui/lockon/lock on frame" + i / multiplier + ".png"));

			i--;

			if (i <= 0)
				i = max * multiplier;

			int screenWidth = event.getResolution().getScaledWidth();
			int screenHeight = event.getResolution().getScaledHeight();

			scale = 0.75F;
			/*switch (mc.gameSettings.guiScale) {
			case Constants.SCALE_AUTO:
				scale = 0.85f;
				break;
			case Constants.SCALE_NORMAL:
				scale = 0.85f;
				break;
			default:
				scale = 0.65f;
				break;
			}*/
			GL11.glPushMatrix();
			GL11.glTranslatef((screenWidth / 2) - (guiWidth / 2) * scale / reduction, (screenHeight / 2) - (guiHeight / 2) * scale / reduction, 0);
			GL11.glScalef(scale / reduction, scale / reduction, scale / reduction);
			// BG
			if (target != null) {
				this.drawTexturedModalRect(0, 0, 0, 0, guiWidth, guiHeight);
			}

			GL11.glPopMatrix();
			mc.renderEngine.bindTexture(new ResourceLocation(Reference.MODID, "textures/gui/hpbar.png"));

			GL11.glPushMatrix();

			if (target != null)// TODO && player has scan ability
			{
				this.drawString(mc.fontRenderer, target.getName(), screenWidth - mc.fontRenderer.getStringWidth(target.getName()), 15, 0xFFFFFF);
				drawHPBar(event, (EntityLivingBase) target);
			}

			GL11.glScalef(scale, scale, scale);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glPopMatrix();
		}
	}

	public void drawHPBar(RenderGameOverlayEvent event, EntityLivingBase target) {
		int screenWidth = event.getResolution().getScaledWidth();
		int screenHeight = event.getResolution().getScaledHeight();
		if (!Minecraft.getMinecraft().player.getCapability(ModCapabilities.PLAYER_STATS, null).getHudMode())
			return;
		if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {

			Minecraft mc = Minecraft.getMinecraft();
			mc.renderEngine.bindTexture(new ResourceLocation(Reference.MODID, "textures/gui/hpbar.png"));

			float oneHeart = (noborderguiwidth / target.getMaxHealth());
			int currHealth = noborderguiwidth - (int) (oneHeart * target.getHealth());
			//float scale = 0.65f;

			BarHP = 25;

			// If the max health is not divisible by BarHP reduce it
			while (target.getMaxHealth() % BarHP != 0) {
				BarHP--;
				if (BarHP == 0) {// If it's not divisible by 0 set it to the entity health
					BarHP = (int) target.getMaxHealth();
					break;
				}

			}

			// Number of HP bars (returns 1 more but it gets removed after)
			hpBars = (int) target.getMaxHealth() / BarHP;
			// Current HP Bar
			currentBar = (int) target.getHealth() / BarHP;

			if (target.getHealth() % BarHP != 0) // If there is module it will have it's hp bar
				currentBar++;

			int oneBar = (int) (target.getMaxHealth() / hpBars);

			if (target.getMaxHealth() == target.getHealth()) {
				hpBarWidth = oneBar * 10;
			} else {
				hpBarWidth = (float) (Math.ceil(target.getHealth() % oneBar) * 10);
			}

			//Background HP width
			int hpBarMaxWidth = (int) (target.getMaxHealth() * 10 / hpBars);

			/*switch (mc.gameSettings.guiScale) {
			case Constants.SCALE_AUTO:
				scale = 0.85f;
				break;
			case Constants.SCALE_NORMAL:
				scale = 0.85f;
				break;
			default:
				scale = 0.70f;
				break;
			}*/

			GL11.glPushMatrix();
			{
				GL11.glTranslatef((screenWidth - hpBarMaxWidth * scale) - 2 * scale * 2, 1, 0);
				GL11.glScalef(scale, scale, scale);
				drawHPBarBack(0, 0, hpBarMaxWidth, scale);
			}
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			{
				GL11.glTranslatef((screenWidth - (hpBarWidth) * scale) - 2 * scale * 2, 1, 0);
				GL11.glScalef(scale, scale, scale);
				drawHPBarTop(0, 0, (int) Math.ceil(hpBarWidth), scale);
			}
			GL11.glPopMatrix();
		}
	}

	public void drawHPBarBack(int posX, int posY, int width, float scale) {
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Reference.MODID, "textures/gui/hpbar.png"));
		GL11.glPushMatrix();
		{
			// Left Margin
			GL11.glPushMatrix();
			{
				GL11.glTranslatef(scale * posX, scale * posY, 0);
				GL11.glScalef(scale, scale, 0);
				drawTexturedModalRect(0, 0, 0, 0, 2, 10);
			}
			GL11.glPopMatrix();

			// Background
			GL11.glPushMatrix();
			{
				GL11.glTranslatef((posX + 2) * scale, posY * scale, 0);
				GL11.glScalef(width, scale, 0);
				drawTexturedModalRect(0, 0, 2, 0, 1, 10);
			}
			GL11.glPopMatrix();

			// Right Margin
			GL11.glPushMatrix();
			{
				GL11.glTranslatef((posX + 2) * scale + width, scale * posY, 0);
				GL11.glScalef(scale, scale, 0);
				drawTexturedModalRect(0, 0, 3, 0, 2, 10);
			}
			GL11.glPopMatrix();

			// HP Icon
			GL11.glPushMatrix();
			{
				GL11.glTranslatef(posX + width - 14, posY + 7, 0);
				GL11.glScalef(scale, scale, 0);
				drawTexturedModalRect(1, 0, 0, 32, 23, 12);
			}
			GL11.glPopMatrix();

			// HP Bars
			for (int i = 0; i < hpBars - 1; i++) {
				GL11.glPushMatrix();
				{
					GL11.glTranslatef(posX + width - 14 - (11 * (i + 1)), posY + 7, 0);
					GL11.glScalef(scale, scale, 0);
					drawTexturedModalRect(0, 0, 0, 46, 17, 12);
				}
				GL11.glPopMatrix();
			}
		}
		GL11.glPopMatrix();

	}

	public void drawHPBarTop(int posX, int posY, int width, float scale) {
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Reference.MODID, "textures/gui/hpbar.png"));
		GL11.glPushMatrix();
		{
			// HP Bar
			GL11.glPushMatrix();
			{
				GL11.glTranslatef((posX + 2) * scale, (posY + 2) * scale, 0);
				GL11.glScalef(width, scale, 0);
				drawTexturedModalRect(0, 0, 2, 12, 1, 6);
			}
			GL11.glPopMatrix();

			// HP Bars
			for (int i = 0; i < currentBar - 1; i++) {
				GL11.glPushMatrix();
				{
					GL11.glTranslatef(posX + width - 14 - (11 * (i + 1)), posY + 7, 0);
					GL11.glScalef(scale, scale, 0);
					drawTexturedModalRect(2, 2, 2, 62, 16, 12);
				}
				GL11.glPopMatrix();
			}
		}
		GL11.glPopMatrix();
	}

}
