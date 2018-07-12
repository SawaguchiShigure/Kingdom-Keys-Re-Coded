package uk.co.wehavecookies56.kk.client.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import uk.co.wehavecookies56.kk.client.sound.ModSounds;
import uk.co.wehavecookies56.kk.common.capability.ModCapabilities;
import uk.co.wehavecookies56.kk.common.core.handler.MainConfig;
import uk.co.wehavecookies56.kk.common.lib.Constants;
import uk.co.wehavecookies56.kk.common.lib.Reference;

public class GuiHP extends GuiScreen {

	int guiWidth = 173;
	int hpBarWidth;
	int guiHeight = 10;
	int noborderguiwidth = 171;

	int counter = 0;

	@SubscribeEvent
	public void onRenderOverlayPost(RenderGameOverlayEvent event) {
		if (!MainConfig.displayGUI())
			return;
		if (!Minecraft.getMinecraft().player.getCapability(ModCapabilities.PLAYER_STATS, null).getHudMode())
			return;
		if (event.getType().equals(ElementType.HEALTH) && event.isCancelable())
			if (!MainConfig.client.hud.EnableHeartsOnHUD)
				event.setCanceled(true);
		if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {

			Minecraft mc = Minecraft.getMinecraft();
			EntityPlayer player = mc.player;

			mc.renderEngine.bindTexture(new ResourceLocation(Reference.MODID, "textures/gui/hpbar.png"));

			int screenWidth = event.getResolution().getScaledWidth();
			int screenHeight = event.getResolution().getScaledHeight();

			float oneHeart = (noborderguiwidth / player.getMaxHealth());
			int currHealth = noborderguiwidth - (int) (oneHeart * player.getHealth());
			float scale = 0.65f;

			hpBarWidth = (int) ((int) player.getHealth() * 1.5);

			int hpBarMaxWidth = (int) ((int) player.getMaxHealth() * 1.5);

			switch (mc.gameSettings.guiScale) {
			case Constants.SCALE_AUTO:
				scale = 0.85f;
				break;
			case Constants.SCALE_NORMAL:
				scale = 0.85f;
				break;
			default:
				scale = 0.65f;
				break;
			}

			GL11.glPushMatrix();
			{
				GL11.glTranslatef((screenWidth - hpBarMaxWidth * scale) - 5 * scale, (screenHeight - guiHeight * scale) + 1 * scale, 0);
				GL11.glScalef(scale, scale, scale);
				drawHPBarBack(0, 0, hpBarMaxWidth, scale);
			}
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			{
				GL11.glTranslatef((screenWidth - (hpBarWidth) * scale) - 5 * scale, (screenHeight - (guiHeight) * scale) + 1 * scale, 0);
				GL11.glScalef(scale, scale, scale);
				drawHPBarTop(0, 0, (int) Math.ceil(hpBarWidth), scale, player);
			}
			GL11.glPopMatrix();
		}
	}

	public void drawHPBarBack(int posX, int posY, int width, float scale) {
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Reference.MODID, "textures/gui/hpbar.png"));
		GL11.glPushMatrix();
		{
			GL11.glPushMatrix();
			{
				GL11.glTranslatef(scale * posX, scale * posY, 0);
				GL11.glScalef(scale, scale, 0);
				drawTexturedModalRect(0, 0, 0, 0, 2, 11);
			}
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			{
				GL11.glTranslatef((posX + 2) * scale, posY * scale, 0);
				GL11.glScalef(width, scale, 0);
				drawTexturedModalRect(0, 0, 2, 0, 1, 11);
			}
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			{
				GL11.glTranslatef((posX + 2) * scale + width, scale * posY, 0);
				GL11.glScalef(scale, scale, 0);
				drawTexturedModalRect(0, 0, 3, 0, 2, 11);
			}
			GL11.glPopMatrix();
		}
		GL11.glPopMatrix();

	}

	public void drawHPBarTop(int posX, int posY, int width, float scale, EntityPlayer player) {
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Reference.MODID, "textures/gui/hpbar.png"));
		GL11.glPushMatrix();
		{
			GL11.glTranslatef((posX + 2) * scale, (posY + 2) * scale, 0);
			GL11.glScalef(width, scale, 0);
			int v = player.getHealth() >= player.getMaxHealth() / 5 ? 12 : 22;
			drawTexturedModalRect(0, 0, 2, v, 1, 6);
		}
		GL11.glPopMatrix();

	}

	public void drawHPBarTopRed(int posX, int posY, int width, float scale) {
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Reference.MODID, "textures/gui/hpbar.png"));
		GL11.glPushMatrix();
		{
			GL11.glPushMatrix();
			{
				GL11.glTranslatef(scale * (posX + 2), scale * (posY + 2), 0);
				GL11.glScalef(scale, scale, 0);
				drawTexturedModalRect(0, 0, 2, 22, 2, 6);
			}
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			{
				GL11.glTranslatef((posX + 4) * scale, (posY + 2) * scale, 0);
				GL11.glScalef(width, scale, 0);
				drawTexturedModalRect(0, 0, 3, 22, 1, 6);
			}
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			{
				GL11.glTranslatef((posX + 4) * scale + width, scale * (posY + 2), 0);
				GL11.glScalef(scale, scale, 0);
				drawTexturedModalRect(0, 0, 4, 22, 2, 6);
			}
			GL11.glPopMatrix();
		}
		GL11.glPopMatrix();

	}

}
