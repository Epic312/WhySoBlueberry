package com.evoslab.why_so_blueberry.core.registry;

import com.evoslab.why_so_blueberry.common.potion.BlueberryEffect;
import com.evoslab.why_so_blueberry.core.WhySoBlueberry;
import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class WhySoBlueberryEffects {
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, WhySoBlueberry.MODID);

    public static final RegistryObject<Effect> BLUEBERRY = EFFECTS.register("blueberry", () -> new BlueberryEffect());
}
