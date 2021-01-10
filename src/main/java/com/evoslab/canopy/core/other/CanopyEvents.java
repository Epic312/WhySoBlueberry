package com.evoslab.canopy.core.other;

import com.evoslab.canopy.core.Canopy;
import com.evoslab.canopy.core.registry.CanopyEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.DamagingProjectileEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Canopy.MODID)
public class CanopyEvents {

    @SubscribeEvent
    public static void onProjectileHit(ProjectileImpactEvent event) {
        if (event.getRayTraceResult().getType() == RayTraceResult.Type.ENTITY) {
            Entity entity = event.getEntity();
            PlayerEntity target = entity.getEntityWorld().getClosestPlayer(entity, 3.0D);
            boolean flag = true;
            if (entity instanceof AbstractArrowEntity) {
                flag = ((AbstractArrowEntity)entity).getPierceLevel() == 0;
            }
            if (flag && target != null && target.getActivePotionEffect(CanopyEffects.BLUEBERRY.get()) != null) {
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