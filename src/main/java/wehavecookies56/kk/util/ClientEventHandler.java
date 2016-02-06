package wehavecookies56.kk.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSound;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreenServerList;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.client.GuiModList;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import wehavecookies56.kk.lib.Config;
import wehavecookies56.kk.lib.Music;
import wehavecookies56.kk.lib.Reference;

public class ClientEventHandler {
	Random rand = new Random();
	PositionedSound posSound;
	PositionedSound vanillaSound;
	ResourceLocation resLoc = new ResourceLocation(Reference.MODID, "");
	boolean[] played = { false };
	boolean[] bossBattlePlayed = { false };
	boolean[] battlePlayed = { false };
	boolean[] menuPlayed = { false, false, false, false, false, false, false, false, false };

	int interval = 100;

	int clientTick = 0;
	float volume = 1.0f;

	@SubscribeEvent (priority = EventPriority.HIGHEST)
	public void clientTick (ClientTickEvent event) {

		if(Config.EnableCustomMusic || Config.ForceEnableCustomMusic) {

			if (event.phase == Phase.END) if (!(Minecraft.getMinecraft().currentScreen instanceof GuiMainMenu || Minecraft.getMinecraft().currentScreen instanceof GuiModList || Minecraft.getMinecraft().currentScreen instanceof GuiScreenServerList || Minecraft.getMinecraft().currentScreen instanceof GuiMultiplayer || Minecraft.getMinecraft().currentScreen instanceof GuiLanguage || Minecraft.getMinecraft().currentScreen instanceof GuiSelectWorld)) {
				SoundManager soundManager = ReflectionHelper.getPrivateValue(SoundHandler.class, Minecraft.getMinecraft().getSoundHandler(), "sndManager", "field_147694_f");
				Map playingSounds = ReflectionHelper.getPrivateValue(SoundManager.class, soundManager, "playingSounds", "field_148629_h");
				Iterator iter = playingSounds.keySet().iterator();
				while (iter.hasNext()) {
					Object o = iter.next();
					if (playingSounds.get(o) instanceof PositionedSound) {
						if (((PositionedSound) playingSounds.get(o)).getSoundLocation().getResourcePath().contains("music.game") && ((PositionedSound) playingSounds.get(o)).getSoundLocation().getResourceDomain() == "minecraft")
							Minecraft.getMinecraft().getSoundHandler().stopSound(((PositionedSound) playingSounds.get(o)));
						
						this.posSound = (PositionedSound) playingSounds.get(o);
						this.resLoc = ReflectionHelper.getPrivateValue(PositionedSound.class, this.posSound, "positionedSoundLocation", "field_147664_a");
					}
				}

				if ("music.game".equals(resLoc.getResourcePath()) || "music.game.creative".equals(resLoc.getResourcePath())) {
					Minecraft.getMinecraft().getSoundHandler().stopSound(this.posSound);
					this.resLoc = new ResourceLocation(Reference.MODID, "");
				}
				// TODO implement this boss thing into this hard file
				if (!EventHandler.isBoss) {
					// Minecraft.getMinecraft().getSoundHandler().stopSound(Music.sinisterSundown);
					// this.bossBattlePlayed[0] = false;
				}

				if (!EventHandler.isHostiles) {
					clientTick++;
					if (clientTick >= 20) {
						Minecraft.getMinecraft().getSoundHandler().stopSound(Music.sinisterSundown);
						this.battlePlayed[0] = false;
						clientTick = 0;
					}

					// System.out.println("Lazy afternoons");
					/*
					 * if(!Minecraft.getMinecraft().getSoundHandler().
					 * isSoundPlaying(Music.lazyAfternoons) &&
					 * !"music.game".equals(this.resLoc.getResourcePath()) &&
					 * !"music.game.creative".equals(this.resLoc.getResourcePath
					 * ())) { if(!this.played[0]){
					 * Minecraft.getMinecraft().getSoundHandler().stopSound(
					 * Music.sinisterSundown);
					 * Minecraft.getMinecraft().getSoundHandler().playSound(
					 * Music.lazyAfternoons); for(int i = 0; i < this.played.length;
					 * i++){ this.played[i] = false; } //this.played[0] = true; } }
					 */

					/*
					 * if(this.day && !this.raining) { if(this.rand.nextInt(2) == 0
					 * && !this.played[0]) {
					 * Minecraft.getMinecraft().getSoundHandler().playSound(
					 * Sounds.posDay1); for(int i = 0; i < this.played.length; i++)
					 * { this.played[i] = false; } this.played[0] = true; } else
					 * if(!this.played[1]) {
					 * Minecraft.getMinecraft().getSoundHandler().playSound(
					 * Sounds.posDay2); for(int i = 0; i < this.played.length; i++)
					 * { this.played[i] = false; } this.played[1] = true; } }
					 * //Night if(!this.day && !this.raining) {
					 * if(this.rand.nextInt(2) == 0 && !this.played[2]) {
					 * Minecraft.getMinecraft().getSoundHandler().playSound(
					 * Sounds.posNight1); for(int i = 0; i < this.played.length;
					 * i++) { this.played[i] = false; } this.played[2] = true; }
					 * else if(!this.played[3]) {
					 * Minecraft.getMinecraft().getSoundHandler().playSound(
					 * Sounds.posNight2); for(int i = 0; i < this.played.length;
					 * i++) { this.played[i] = false; } this.played[3] = true; } }
					 * //Raining if(this.raining) { if(this.rand.nextInt(2) == 0 &&
					 * !this.played[4]) {
					 * Minecraft.getMinecraft().getSoundHandler().playSound(
					 * Music.song); for(int i = 0; i < this.played.length; i++) {
					 * this.played[i] = false; } this.played[4] = true; } else
					 * if(!this.played[5]) {
					 * Minecraft.getMinecraft().getSoundHandler().playSound(
					 * Music.song); for(int i = 0; i < this.played.length; i++) {
					 * this.played[i] = false; } this.played[5] = true; } }
					 */
				} else if (EventHandler.isHostiles) {
					if (!Minecraft.getMinecraft().getSoundHandler().isSoundPlaying(Music.sinisterSundown) && !Music.sinisterSundown.getSoundLocation().equals(this.resLoc) && !"music.game".equals(this.resLoc.getResourcePath()) && !"music.game.creative".equals(this.resLoc.getResourcePath())) if (!this.battlePlayed[0]) {
						if(!playingSounds.containsValue(Music.sinisterSundown)){
							try{Minecraft.getMinecraft().getSoundHandler().playDelayedSound(Music.sinisterSundown, 5);}catch(Exception e){}
						}
						for (int i = 0; i < this.battlePlayed.length; i++)
							this.battlePlayed[i] = false;
						this.battlePlayed[0] = true;
					}
				}
			} else {
				SoundManager soundManager = ReflectionHelper.getPrivateValue(SoundHandler.class, Minecraft.getMinecraft().getSoundHandler(), "sndManager", "field_147694_f");
				Map playingSounds = ReflectionHelper.getPrivateValue(SoundManager.class, soundManager, "playingSounds", "field_148629_h");
				Iterator iter = playingSounds.keySet().iterator();

				while (iter.hasNext()) {
					Object o = iter.next();
					if (playingSounds.get(o) instanceof PositionedSound) {
						if (((PositionedSound) playingSounds.get(o)).getSoundLocation().getResourcePath().equals("music.menu")) Minecraft.getMinecraft().getSoundHandler().stopSound(((PositionedSound) playingSounds.get(o)));
						this.posSound = (PositionedSound) playingSounds.get(o);
						this.resLoc = ReflectionHelper.getPrivateValue(PositionedSound.class, this.posSound, "positionedSoundLocation", "field_147664_a");
					}
				}

				if ("music.menu".equals(resLoc.getResourcePath())) {
					Minecraft.getMinecraft().getSoundHandler().stopSound(this.posSound);
					this.resLoc = new ResourceLocation(Reference.MODID, "");
				}
				if (!Minecraft.getMinecraft().getSoundHandler().isSoundPlaying(Music.dearlyBelovedDDD) && !Minecraft.getMinecraft().getSoundHandler().isSoundPlaying(Music.dearlyBelovedBBS) && !Minecraft.getMinecraft().getSoundHandler().isSoundPlaying(Music.dearlyBelovedKH1) && !Minecraft.getMinecraft().getSoundHandler().isSoundPlaying(Music.dearlyBelovedKH2) && !Minecraft.getMinecraft().getSoundHandler().isSoundPlaying(Music.dearlyBelovedReCoM) && !Minecraft.getMinecraft().getSoundHandler().isSoundPlaying(Music.dearlyBelovedCoM) && !Minecraft.getMinecraft().getSoundHandler().isSoundPlaying(Music.dearlyBelovedCoded) && !Minecraft.getMinecraft().getSoundHandler().isSoundPlaying(Music.dearlyBelovedDays) && !Minecraft.getMinecraft().getSoundHandler().isSoundPlaying(Music.dearlyBelovedChi) && !"music.menu".equals(this.resLoc.getResourcePath())) {

					int r = randomWithRange(0, 8);
					if (resLoc.getResourcePath().contains("music.menu")) Minecraft.getMinecraft().getSoundHandler().stopSound(this.posSound);
					Minecraft.getMinecraft().getSoundHandler().stopSound(this.posSound);
					if (r == 0 && !this.menuPlayed[0]) {
						if(!playingSounds.containsValue(Music.dearlyBelovedKH1))
							try{Minecraft.getMinecraft().getSoundHandler().playSound(Music.dearlyBelovedKH1);}catch(Exception e){}
						for (int i = 0; i < this.menuPlayed.length; i++)
							this.menuPlayed[i] = false;
						this.menuPlayed[0] = true;
						return;
					} else if (r == 1 && !this.menuPlayed[1]) {
						if(!playingSounds.containsValue(Music.dearlyBelovedKH2))
							try{Minecraft.getMinecraft().getSoundHandler().playSound(Music.dearlyBelovedKH2);}catch(Exception e){}
						for (int i = 0; i < this.menuPlayed.length; i++)
							this.menuPlayed[i] = false;
						this.menuPlayed[1] = true;
						return;
					} else if (r == 2 && !this.menuPlayed[2]) {
						if(!playingSounds.containsValue(Music.dearlyBelovedBBS))
							try{Minecraft.getMinecraft().getSoundHandler().playSound(Music.dearlyBelovedBBS);}catch(Exception e){}
						for (int i = 0; i < this.menuPlayed.length; i++)
							this.menuPlayed[i] = false;
						this.menuPlayed[2] = true;
						return;
					} else if (r == 3 && !this.menuPlayed[3]) {
						if(!playingSounds.containsValue(Music.dearlyBelovedDDD))
							try{Minecraft.getMinecraft().getSoundHandler().playSound(Music.dearlyBelovedDDD);}catch(Exception e){}
						for (int i = 0; i < this.menuPlayed.length; i++)
							this.menuPlayed[i] = false;
						this.menuPlayed[3] = true;
						return;
					} else if (r == 4 && !this.menuPlayed[4]) {
						if(!playingSounds.containsValue(Music.dearlyBelovedDays))
							try{Minecraft.getMinecraft().getSoundHandler().playSound(Music.dearlyBelovedDays);}catch(Exception e){}
						for (int i = 0; i < this.menuPlayed.length; i++)
							this.menuPlayed[i] = false;
						this.menuPlayed[4] = true;
						return;
					} else if (r == 5 && !this.menuPlayed[5]) {
						if(!playingSounds.containsValue(Music.dearlyBelovedCoded))
							try{Minecraft.getMinecraft().getSoundHandler().playSound(Music.dearlyBelovedCoded);}catch(Exception e){}
						for (int i = 0; i < this.menuPlayed.length; i++)
							this.menuPlayed[i] = false;
						this.menuPlayed[5] = true;
						return;
					} else if (r == 6 && !this.menuPlayed[6]) {
						if(!playingSounds.containsValue(Music.dearlyBelovedCoM))
							try{Minecraft.getMinecraft().getSoundHandler().playSound(Music.dearlyBelovedCoM);}catch(Exception e){}
						for (int i = 0; i < this.menuPlayed.length; i++)
							this.menuPlayed[i] = false;
						this.menuPlayed[6] = true;
						return;
					} else if (r == 7 && !this.menuPlayed[7]) {
						if(!playingSounds.containsValue(Music.dearlyBelovedReCoM))
							try{Minecraft.getMinecraft().getSoundHandler().playSound(Music.dearlyBelovedReCoM);}catch(Exception e){}
						for (int i = 0; i < this.menuPlayed.length; i++)
							this.menuPlayed[i] = false;
						this.menuPlayed[7] = true;
						return;
					} else if (r == 8 && !this.menuPlayed[8]) {
						if(!playingSounds.containsValue(Music.dearlyBelovedChi))
							try{Minecraft.getMinecraft().getSoundHandler().playSound(Music.dearlyBelovedChi);}catch(Exception e){}
						for (int i = 0; i < this.menuPlayed.length; i++)
							this.menuPlayed[i] = false;
						this.menuPlayed[8] = true;
						return;
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onRenderPlayer(RenderPlayerEvent.Post event){
		//		Minecraft mc = Minecraft.getMinecraft();
		//		float base = 0.0625f;
		//		ModelBiped main = event.renderer.getMainModel();
		//		ModelArmorStandArmor drive = new ModelArmorStandArmor();
		//
		//		GL11.glPushMatrix();
		//				
		//		//Body and arms
		//		mc.renderEngine.bindTexture(new ResourceLocation("kk:textures/armour/Valor_A.png"));
		//		
		//		//ModelBiped.copyModelAngles(main.bipedBody, drive.bipedBodyWear);
		//		//ModelBiped.copyModelAngles(main.bipedLeftArm, drive.bipedLeftArmwear);
		//		//ModelBiped.copyModelAngles(main.bipedRightArm, drive.bipedRightArmwear);
		//
		//		GL11.glRotatef(180f, 1.0f, 0, 0);
		//		
		//		//drive.render(event.entityPlayer, (float)event.x, (float)event.y, (float)event.z, event.entityPlayer.renderYawOffset, event.partialRenderTick, 1);
		//		drive.bipedBody.render(base);
		//		drive.bipedLeftArm.render(base);
		//		drive.bipedRightArm.render(base);
		//		System.out.println(event.z);
		//		//drive.bipedBody.offsetX = event.entityPlayer.renderOffsetX;
		//		//drive.bipedBody.offsetY = event.entityPlayer.renderOffsetY;
		//		//drive.bipedBody.offsetZ = event.entityPlayer.renderOffsetZ;
		//		//drive.bipedLeftArmwear.render(base);
		//		//drive.bipedRightArmwear.render(base);
		//		
		//		//Legs
		//		//mc.renderEngine.bindTexture(new ResourceLocation("kk:textures/armour/Valor_B.png"));
		//		
		//		//ModelBiped.copyModelAngles(main.bipedLeftLeg, drive.bipedLeftLegwear);
		//		//ModelBiped.copyModelAngles(main.bipedRightLeg, drive.bipedRightLegwear);
		//		
		//		//drive.bipedLeftLegwear.render(base);
		//		//drive.bipedRightLegwear.render(base);
		//
		//		GL11.glPopMatrix();
	}

	public static int randomWithRange (int min, int max) {
		int range = Math.abs(max - min) + 1;
		return (int) (Math.random() * range) + (min <= max ? min : max);
	}
}