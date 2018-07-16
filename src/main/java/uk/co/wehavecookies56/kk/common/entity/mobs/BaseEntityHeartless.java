package uk.co.wehavecookies56.kk.common.entity.mobs;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import uk.co.wehavecookies56.kk.common.core.helper.EntityHelper.MobType;
import uk.co.wehavecookies56.kk.common.entity.EntityFlyingHeart;
import uk.co.wehavecookies56.kk.common.item.base.ItemRealKeyblade;

public class BaseEntityHeartless extends EntityMob {

	public BaseEntityHeartless(World worldIn) {
		super(worldIn);
	}

	@Override
	protected boolean isValidLightLevel() {
		BlockPos pos = new BlockPos(posX, posY, posZ);
		IBlockState state = world.getBlockState(pos);
		int l = state.getLightValue(world, pos);
		return l <= 7;
	}

	@Override
	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1000.0D);
	}

	@Override
	protected boolean canDespawn() {
		return true;
	}

	@Override
	public boolean isAIDisabled() {
		return false;
	}

	@Override
	public boolean getCanSpawnHere() {
		return true;
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);
		if (this instanceof IKHMob) {
			if (((IKHMob) this).getType() == MobType.HEARTLESS_EMBLEM) {
				if (cause.getTrueSource() instanceof EntityPlayer) {
					EntityPlayer player = (EntityPlayer) cause.getTrueSource();
					if ((player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() instanceof ItemRealKeyblade) || (player.getHeldItemOffhand() != null && player.getHeldItemOffhand().getItem() instanceof ItemRealKeyblade)) {
						if (!world.isRemote) {
							EntityFlyingHeart heart = new EntityFlyingHeart(this.world);
							heart.setPosition(this.posX, this.posY + 1, this.posZ);
							world.spawnEntity(heart);
						}
					}
				}
			}
		}
	}
}
