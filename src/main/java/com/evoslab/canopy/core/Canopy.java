package com.evoslab.canopy.core;

import com.evoslab.canopy.core.other.CanopyCompat;
import com.evoslab.canopy.core.registry.CanopyEffects;
import com.minecraftabnormals.abnormals_core.core.util.registry.RegistryHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("canopy")
@Mod.EventBusSubscriber(modid = "canopy")
@SuppressWarnings("deprecation")
public class Canopy
{
    public static final String MODID = "canopy";
    public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MODID);

    public Canopy() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);

        REGISTRY_HELPER.register(eventBus);

        CanopyEffects.EFFECTS.register(eventBus);


        eventBus.addListener(this::commonSetup);
        DistExecutor.runWhenOn(Dist.CLIENT, ()->()-> {
            eventBus.addListener(this::clientSetup);
        });
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        DeferredWorkQueue.runLater(() -> {
            CanopyCompat.registerFlammables();
            CanopyCompat.registerCompostables();
        });
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        DeferredWorkQueue.runLater(CanopyCompat::registerRenderLayers);
    }
}
