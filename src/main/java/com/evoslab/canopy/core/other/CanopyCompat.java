package com.evoslab.canopy.core.other;

import com.evoslab.canopy.core.registry.CanopyBlocks;
import com.evoslab.canopy.core.registry.CanopyItems;
import com.minecraftabnormals.abnormals_core.core.util.DataUtil;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class CanopyCompat {
    public static void registerCompostables() {
        DataUtil.registerCompostable(CanopyItems.BLUEBERRY_PIPS.get(), 0.3F);
        DataUtil.registerCompostable(CanopyItems.BLUEBERRIES.get(), 0.3F);
        DataUtil.registerCompostable(CanopyItems.PINK_BLUEBERRIES.get(), 0.65F);

        DataUtil.registerCompostable(CanopyBlocks.BLUEBERRY_BASKET.get(), 1.0F);
        DataUtil.registerCompostable(CanopyBlocks.PINK_BLUEBERRY_BASKET.get(), 1.0F);
    }

    public static void registerFlammables() {
        DataUtil.registerFlammable(CanopyBlocks.BLUEBERRY_BUSH.get(), 60, 100);
        DataUtil.registerFlammable(CanopyBlocks.BLUEBERRY_BASKET.get(), 5, 20);
        DataUtil.registerFlammable(CanopyBlocks.PINK_BLUEBERRY_BASKET.get(), 5, 20);
    }

    public static void registerRenderLayers() {
        RenderTypeLookup.setRenderLayer(CanopyBlocks.BLUEBERRY_BUSH.get(), RenderType.getCutout());
    }
}
