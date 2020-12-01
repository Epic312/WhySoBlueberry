package com.evoslab.why_so_blueberry.core.other;

import com.evoslab.why_so_blueberry.core.WhySoBlueberry;
import com.evoslab.why_so_blueberry.core.registry.WhySoBlueberryEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.DamagingProjectileEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WhySoBlueberry.MODID)
public class WhySoBlueberryEvents {

    @SubscribeEvent
    public static void onProjectileHit(ProjectileImpactEvent event) {
        if (event.getRayTraceResult().getType() == RayTraceResult.Type.ENTITY) {
            Entity entity = event.getEntity();
            PlayerEntity target = entity.getEntityWorld().getClosestPlayer(entity, 3.0D);
            if (target != null && target.getActivePotionEffect(WhySoBlueberryEffects.BLUEBERRY.get()) != null) {
                entity.setVelocity(entity.getMotion().x * -1.0D, entity.getMotion().y * -1.0D, entity.getMotion().z * -1.0D);
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