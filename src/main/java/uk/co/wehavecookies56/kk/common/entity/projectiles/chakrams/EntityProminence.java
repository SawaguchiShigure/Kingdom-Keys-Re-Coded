package uk.co.wehavecookies56.kk.common.entity.projectiles.chakrams;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IThrowableEntity;
import uk.co.wehavecookies56.kk.common.KingdomKeys;
import uk.co.wehavecookies56.kk.common.capability.ModCapabilities;

public class EntityProminence extends EntityChakram {
    EntityPlayer player;

    public EntityProminence(World world) {
        super(world);
    }

    public EntityProminence(World world, EntityLivingBase entity) {
        super(world, entity);
        this.player = (EntityPlayer) entity;
    }

    public EntityProminence(World world, double x, double y, double z) {
        super(world, x, y, z);
    }
}
