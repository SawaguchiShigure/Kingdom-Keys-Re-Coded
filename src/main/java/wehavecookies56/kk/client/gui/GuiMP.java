package wehavecookies56.kk.client.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import wehavecookies56.kk.lib.Constants;
import wehavecookies56.kk.lib.Reference;

public class GuiMP extends GuiScreen {

	int guiWidth = 142;
	int guiHeight = 12;
	int noborderguiwidth = 140;

	@SubscribeEvent
	public void onRenderOverlayPost(RenderGameOverlayEvent event){
		if(event.type == RenderGameOverlayEvent.ElementType.TEXT){

			Minecraft mc = Minecraft.getMinecraft();
			EntityPlayer player = mc.thePlayer;

			mc.renderEngine.bindTexture(new ResourceLocation(Reference.MODID, "textures/gui/mpbar.png"));

			int screenWidth = event.resolution.getScaledWidth();
			int screenHeight = event.resolution.getScaledHeight();

			float oneMP = (noborderguiwidth / player.getMaxHealth());
			int currHealth = noborderguiwidth - (int) (oneMP * player.getHealth());
			float scale = 0.65f;
			switch(mc.gameSettings.guiScale){
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
			GL11.glTranslatef((screenWidth - guiWidth*scale) - 10*scale, (screenHeight - guiHeight*scale) - 8*scale, 0);
			GL11.glScalef(scale, scale, scale);
			//BG
			this.drawTexturedModalRect(-25, 4, 0, 0, guiWidth, guiHeight);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glTranslatef((screenWidth - noborderguiwidth*scale) + (currHealth * scale) - 10*scale, (screenHeight - guiHeight*scale) - 8*scale, 0);
			GL11.glScalef(scale, scale, scale);
			//FG
			this.drawTexturedModalRect(-25, 3, 0, 11, (noborderguiwidth - currHealth) - 2, guiHeight);
			GL11.glPopMatrix();
		}
	}
}