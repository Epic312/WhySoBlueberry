package com.evoslab.why_so_blueberry.core;

import com.evoslab.why_so_blueberry.core.other.WhySoBlueberryCompat;
import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;
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
@Mod("why_so_blueberry")
@Mod.EventBusSubscriber(modid = "why_so_blueberry")
@SuppressWarnings("deprecation")
public class WhySoBlueberry
{
    public static final String MODID = "why_so_blueberry";
    public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MODID);

    public WhySoBlueberry() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        REGISTRY_HELPER.getDeferredBlockRegister().register(eventBus);
        REGISTRY_HELPER.getDeferredItemRegister().register(eventBus);

        MinecraftForge.EVENT_BUS.register(this);

        eventBus.addListener(this::commonSetup);
        DistExecutor.runWhenOn(Dist.CLIENT, ()->()-> {
            eventBus.addListener(this::clientSetup);
        });
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        DeferredWorkQueue.runLater(() -> {
            WhySoBlueberryCompat.registerFlammables();
            WhySoBlueberryCompat.registerCompostables();
        });
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        DeferredWorkQueue.runLater(WhySoBlueberryCompat::registerRenderLayers);
    }
}
