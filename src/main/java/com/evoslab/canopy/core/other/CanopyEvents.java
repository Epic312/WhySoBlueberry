package com.evoslab.canopy.core.other;

import com.evoslab.canopy.core.Canopy;
import com.evoslab.canopy.core.registry.CanopyEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.DamagingProjectileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Canopy.MODID)
public class CanopyEvents {

    @SubscribeEvent
    public static void onProjectileHit(ProjectileImpactEvent event) {
        if (event.getRayTraceResult().getType() == RayTraceResult.Type.ENTITY) {
            Entity entity = event.getEntity();
            World world = entity.getEntityWorld();
            LivingEntity target = world.getClosestEntityWithinAABB(LivingEntity.class, EntityPredicate.DEFAULT, null, entity.getPosX(), entity.getPosY(), entity.getPosZ(), AxisAlignedBB.fromVector(event.getRayTraceResult().getHitVec()));

            if (target != null && target.getActivePotionEffect(CanopyEffects.BLUEBERRY.get()) != null) {
                if (entity.getEntityWorld().isRemote()) {
                    entity.setVelocity(entity.getMotion().getX() * -1.0D, entity.getMotion().getY() * -1.0D, entity.getMotion().getZ() * -1.0D);
                }
                else {
                    entity.setMotion(entity.getMotion().mul(-1.0D, -1.0D, -1.0D));
                }
                if (entity instanceof DamagingProjectileEntity) {
                    ((DamagingProjectileEntity) entity).accelerationX*=-1.0;
                    ((DamagingProjectileEntity) entity).accelerationY*=-1.0;
                    ((DamagingProjectileEntity) entity).accelerationZ*=-1.0;
                }
                event.setCanceled(true);
            }
        }
    }
}