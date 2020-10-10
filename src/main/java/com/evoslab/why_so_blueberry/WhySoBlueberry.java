package com.evoslab.why_so_blueberry;

import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

import static com.evoslab.why_so_blueberry.WhySoBlueberry.MODID;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("why_so_blueberry")
@Mod.EventBusSubscriber(modid = MODID)
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

    }

    private void clientSetup(final FMLClientSetupEvent event) {

    }
}
