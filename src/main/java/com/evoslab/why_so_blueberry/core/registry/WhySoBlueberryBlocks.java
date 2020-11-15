package com.evoslab.why_so_blueberry.core.registry;

import com.evoslab.why_so_blueberry.common.block.BlueberryBushBlock;
import com.evoslab.why_so_blueberry.common.block.BlueberryCakeBlock;
import com.evoslab.why_so_blueberry.core.WhySoBlueberry;
import com.minecraftabnormals.neapolitan.common.block.FlavoredCakeBlock;
import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WhySoBlueberry.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WhySoBlueberryBlocks {
    public static final RegistryHelper HELPER = WhySoBlueberry.REGISTRY_HELPER;

    //TODO: Blueberry bush
    //TODO: Blueberry cake

    public static final RegistryObject<Block> BLUEBERRY_ICE_CREAM_BLOCK = HELPER.createBlock("blueberry_ice_cream_block", ()->new Block(Properties.BLUEBERRY_ICE_CREAM_BLOCK), ItemGroup.BUILDING_BLOCKS);

    public static final RegistryObject<Block> BLUEBERRY_BUSH = HELPER.createBlockNoItem("blueberry_bush", ()->new BlueberryBushBlock(Properties.BLUEBERRY_BUSH));

    public static final RegistryObject<Block> BLUEBERRY_CAKE = HELPER.createBlockNoItem("blueberry_cake", ()->new BlueberryCakeBlock(WhySoBlueberryItems.Foods.BLUEBERRY_CAKE, Properties.BLUEBERRY_CAKE));

    public static final RegistryObject<Block> BLUEBERRY_BASKET = HELPER.createCompatBlock("quark", "blueberry_basket", ()->new Block(Properties.BLUEBERRY_BASKET), ItemGroup.DECORATIONS);
    public static final RegistryObject<Block> PINK_BLUEBERRY_BASKET = HELPER.createCompatBlock("quark", "pink_blueberry_basket", ()->new Block(Properties.PINK_BLUEBERRY_BASKET), ItemGroup.DECORATIONS);

    static class Properties {
        public static final AbstractBlock.Properties BLUEBERRY_ICE_CREAM_BLOCK = AbstractBlock.Properties.create(Material.SNOW_BLOCK, MaterialColor.LAPIS).harvestTool(ToolType.SHOVEL).hardnessAndResistance(0.2F).sound(SoundType.SNOW);

        public static final AbstractBlock.Properties BLUEBERRY_BUSH = AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0F).sound(SoundType.CROP);

        public static final AbstractBlock.Properties BLUEBERRY_CAKE = AbstractBlock.Properties.create(Material.CAKE, MaterialColor.LAPIS).hardnessAndResistance(0.5F).sound(SoundType.CLOTH);

        public static final AbstractBlock.Properties BLUEBERRY_BASKET = AbstractBlock.Properties.create(Material.WOOD, MaterialColor.LAPIS).hardnessAndResistance(1.5F).sound(SoundType.WOOD);
        public static final AbstractBlock.Properties PINK_BLUEBERRY_BASKET = AbstractBlock.Properties.create(Material.WOOD, MaterialColor.PINK).hardnessAndResistance(1.5F).sound(SoundType.WOOD);
    }
}
