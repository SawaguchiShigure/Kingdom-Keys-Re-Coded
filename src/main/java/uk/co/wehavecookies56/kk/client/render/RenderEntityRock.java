package uk.co.wehavecookies56.kk.client.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import uk.co.wehavecookies56.kk.client.model.ModelFlyingHeart;
import uk.co.wehavecookies56.kk.client.model.ModelRock;
import uk.co.wehavecookies56.kk.common.entity.EntityFlyingHeart;
import uk.co.wehavecookies56.kk.common.entity.projectiles.EntityRock;
import uk.co.wehavecookies56.kk.common.lib.Reference;

public class RenderEntityRock extends Render implements IRenderFactory<EntityRock> {

	ModelBase model;

	public RenderEntityRock(RenderManager renderManager) {
		super(renderManager);
		shadowSize = 1;
		this.model = new ModelRock();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation(Reference.MODID, "models/entity/rock.png");
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch) {
		GL11.glPushMatrix();
		{
			GL11.glTranslated(x, y, z);

			GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 128.0F, 128.0F);

			GL11.glScalef(0.01f, 0.01f, 0.01f);

			float ticks = entity.ticksExisted;
			if (ticks < 10) // Growing
				GL11.glScalef(ticks * 0.001f, ticks * 0.001f, ticks * 0.001f);
			else if (ticks > EntityRock.MAX_TICKS - 5 && !entity.isDead) // Disappearing
				GL11.glScalef((EntityRock.MAX_TICKS - ticks) * 0.001f, (EntityRock.MAX_TICKS - ticks) * 0.001f, (EntityRock.MAX_TICKS - ticks) * 0.001f);
			else // Static size
				GL11.glScalef(0.01f, 0.01f, 0.01f);

			bindEntityTexture(entity);

			// GL11.glScalef(-1.0F, -1.0F, 1.0F);
			this.model.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

			GL11.glPopAttrib();
		}
		GL11.glPopMatrix();
	}

	@Override
	public Render<? super EntityRock> createRenderFor(RenderManager manager) {
		return new RenderEntityRock(manager);
	}

}
