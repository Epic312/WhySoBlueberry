package com.evoslab.why_so_blueberry.core.other;

import com.evoslab.why_so_blueberry.core.registry.WhySoBlueberryBlocks;
import com.evoslab.why_so_blueberry.core.registry.WhySoBlueberryItems;
import com.teamabnormals.abnormals_core.core.utils.DataUtils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class WhySoBlueberryCompat {
    public static void registerCompostables() {
        DataUtils.registerCompostable(WhySoBlueberryItems.BLUEBERRY_PIPS.get(), 0.3F);
        DataUtils.registerCompostable(WhySoBlueberryItems.BLUEBERRIES.get(), 0.3F);
        DataUtils.registerCompostable(WhySoBlueberryItems.PINK_BLUEBERRIES.get(), 0.65F);

        DataUtils.registerCompostable(WhySoBlueberryBlocks.BLUEBERRY_BASKET.get(), 1.0F);
        DataUtils.registerCompostable(WhySoBlueberryBlocks.PINK_BLUEBERRY_BASKET.get(), 1.0F);
    }

    public static void registerFlammables() {
        DataUtils.registerFlammable(WhySoBlueberryBlocks.BLUEBERRY_BUSH.get(), 60, 100);
        DataUtils.registerFlammable(WhySoBlueberryBlocks.BLUEBERRY_BASKET.get(), 5, 20);
        DataUtils.registerFlammable(WhySoBlueberryBlocks.PINK_BLUEBERRY_BASKET.get(), 5, 20);
    }

    public static void registerRenderLayers() {
        RenderTypeLookup.setRenderLayer(WhySoBlueberryBlocks.BLUEBERRY_BUSH.get(), RenderType.getCutout());
    }
}
