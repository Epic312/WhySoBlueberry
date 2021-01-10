package com.evoslab.canopy.core.registry;

import com.evoslab.canopy.common.potion.BlueberryEffect;
import com.evoslab.canopy.core.Canopy;
import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class CanopyEffects {
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, Canopy.MODID);

    public static final RegistryObject<Effect> BLUEBERRY = EFFECTS.register("blueberry", () -> new BlueberryEffect());
}
