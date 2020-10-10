package com.evoslab.why_so_blueberry.registry;

import com.evoslab.why_so_blueberry.WhySoBlueberry;
import com.minecraftabnormals.neapolitan.common.item.MilkshakeItem;
import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WhySoBlueberry.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WhySoBlueberryItems {
    public static final RegistryHelper HELPER = WhySoBlueberry.REGISTRY_HELPER;

    //public static final RegistryObject<Item> BLUEBERRY_PIPS =
    public static final RegistryObject<Item> BLUEBERRIES = HELPER.createItem("blueberries", ()->new Item(new Item.Properties().food(Foods.BLUEBERRIES).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> BLUEBERRY_ICE_CREAM = HELPER.createItem("blueberry_ice_cream", ()->new SoupItem(new Item.Properties().food(Foods.BLUEBERRY_ICE_CREAM).containerItem(Items.BOWL).maxStackSize(1).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> BLUEBERRY_MILKSHAKE = HELPER.createItem("blueberry_milkshake", ()->new MilkshakeItem(null, new Item.Properties().food(Foods.BLUEBERRY_MILKSHAKE).maxStackSize(16).group(ItemGroup.FOOD)));

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
    }
}
