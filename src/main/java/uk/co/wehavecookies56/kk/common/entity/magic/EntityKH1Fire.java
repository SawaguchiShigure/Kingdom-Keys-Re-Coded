package uk.co.wehavecookies56.kk.common.entity.magic;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import uk.co.wehavecookies56.kk.client.core.handler.InputHandler;
import uk.co.wehavecookies56.kk.common.lib.Reference;
import uk.co.wehavecookies56.kk.common.network.packet.PacketDispatcher;
import uk.co.wehavecookies56.kk.common.network.packet.client.SpawnBlizzardParticles;
import uk.co.wehavecookies56.kk.common.network.packet.client.SpawnKH1FireParticles;

public class EntityKH1Fire extends EntityThrowable {
	private static final ResourceLocation resourceLocation = new ResourceLocation(Reference.MODID, "textures/entity/fire.png");
	public EntityLivingBase shootingEntity;

	public EntityKH1Fire (World world) {
		super(world);
	}

	public EntityKH1Fire (World world, EntityLivingBase entity) {
		super(world, entity);
		shootingEntity = entity;


	}

	public EntityKH1Fire (World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	protected float getGravityVelocity () {
		return 0.0F;
	}

	@Override
	public void onUpdate () {
		if (shootingEntity == null) return;
		if (shootingEntity instanceof EntityPlayer)
		{
			if (!worldObj.isRemote) 
				PacketDispatcher.sendToAllAround(new SpawnKH1FireParticles(this, 1), (EntityPlayer)shootingEntity, 64.0D);
			if(InputHandler.lockOn != null)
			{
				EntityLiving target = (EntityLiving)InputHandler.lockOn;
				setThrowableHeading(target.posX - this.posX, target.posY - this.posY + target.height, target.posZ - this.posZ, 1.5f, 0);	
			}
		}else{
			if (!worldObj.isRemote) 
				PacketDispatcher.sendToAllAround(new SpawnKH1FireParticles(this,1), dimension, this.posX, this.posY, this.posZ, 64D);
		}
		if (ticksExisted > 60) setDead();
		super.onUpdate();
	}

	@Override
	protected void onImpact (RayTraceResult movingObject) {
		if (!this.worldObj.isRemote) {
			boolean flag;

			if (movingObject.entityHit != null) {
				flag = movingObject.entityHit != null;
				if (flag) {
					applyEnchantments(this.shootingEntity, movingObject.entityHit);

					if (!movingObject.entityHit.isImmuneToFire()) movingObject.entityHit.setFire(5);
					if (shootingEntity instanceof EntityPlayer)
						movingObject.entityHit.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) shootingEntity), DamageCalculation.getMagicDamage((EntityPlayer) shootingEntity, 1));
					else
						movingObject.entityHit.attackEntityFrom(DamageSource.causeMobDamage(shootingEntity), 5);
				}
			} else {
				flag = true;

				if (this.shootingEntity != null && this.shootingEntity instanceof EntityPlayer) flag = this.worldObj.getGameRules().getBoolean("mobGriefing");

				if (flag) {
					BlockPos blockpos = movingObject.getBlockPos().offset(movingObject.sideHit);

					if (this.worldObj.isAirBlock(blockpos)) this.worldObj.setBlockState(blockpos, Blocks.FIRE.getDefaultState());
				}
			}

			setDead();
		}
	}

}
