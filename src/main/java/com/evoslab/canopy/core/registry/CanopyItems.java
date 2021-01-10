package com.evoslab.canopy.core.registry;

import com.evoslab.canopy.common.item.BlueberryMilkshakeItem;
import com.evoslab.canopy.core.Canopy;
import com.minecraftabnormals.abnormals_core.core.util.registry.ItemSubRegistryHelper;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Canopy.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CanopyItems {
    public static final ItemSubRegistryHelper HELPER = Canopy.REGISTRY_HELPER.getItemSubHelper();

    public static final RegistryObject<Item> BLUEBERRY_PIPS = HELPER.createItem("blueberry_pips", ()->new BlockNamedItem(CanopyBlocks.BLUEBERRY_BUSH.get(), new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<Item> BLUEBERRIES = HELPER.createItem("blueberries", ()->new Item(new Item.Properties().food(Foods.BLUEBERRIES).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> BLUEBERRY_ICE_CREAM = HELPER.createItem("blueberry_ice_cream", ()->new SoupItem(new Item.Properties().food(Foods.BLUEBERRY_ICE_CREAM).containerItem(Items.BOWL).maxStackSize(1).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> BLUEBERRY_MILKSHAKE = HELPER.createItem("blueberry_milkshake", ()->new BlueberryMilkshakeItem(new Item.Properties().food(Foods.BLUEBERRY_MILKSHAKE).maxStackSize(16).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> BLUEBERRY_MUFFIN = HELPER.createItem("blueberry_muffin", ()->new Item(new Item.Properties().food(Foods.BLUEBERRY_MUFFIN).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> BLUEBERRY_PIE = HELPER.createItem("blueberry_pie", ()->new Item(new Item.Properties().food(Foods.BLUEBERRY_PIE).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> BLUEBERRY_TURNOVERS = HELPER.createItem("blueberry_turnovers", ()->new Item(new Item.Properties().food(Foods.BLUEBERRY_TURNOVERS).group(ItemGroup.FOOD)));

    public static final RegistryObject<Item> BLUEBERRY_CAKE = HELPER.createItem("blueberry_cake", ()->new BlockItem(CanopyBlocks.BLUEBERRY_CAKE.get(), new Item.Properties().group(ItemGroup.FOOD).maxStackSize(1)));

    public static final RegistryObject<Item> PINK_BLUEBERRIES = HELPER.createItem("pink_blueberries", ()->new Item(new Item.Properties().food(Foods.PINK_BLUEBERRIES).group(ItemGroup.FOOD)));

    static class Foods {
        // temporary food values, will tweak in the future
        public static final Food BLUEBERRIES = new Food.Builder().hunger(3).saturation(0.03F).build();
        public static final Food PINK_BLUEBERRIES = new Food.Builder().hunger(5).saturation(0.04F).build();
        public static final Food BLUEBERRY_ICE_CREAM = new Food.Builder().hunger(6).saturation(0.42F).build();
        public static final Food BLUEBERRY_MILKSHAKE = new Food.Builder().hunger(3).saturation(0.6F).build();
        public static final Food BLUEBERRY_MUFFIN = new Food.Builder().hunger(5).saturation(0.04F).build();
        public static final Food BLUEBERRY_PIE = new Food.Builder().hunger(4).saturation(0.6F).build();
        public static final Food BLUEBERRY_TURNOVERS = new Food.Builder().hunger(5).saturation(0.03F).build();

        public static final Food BLUEBERRY_CAKE = (new Food.Builder()).hunger(1).saturation(0.1F).build();
    }
}
