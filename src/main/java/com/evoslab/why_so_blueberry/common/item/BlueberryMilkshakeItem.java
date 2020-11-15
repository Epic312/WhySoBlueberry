package com.evoslab.why_so_blueberry.common.item;

import com.google.common.collect.ImmutableList;
import com.minecraftabnormals.neapolitan.common.item.DrinkItem;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collections;

public class BlueberryMilkshakeItem extends DrinkItem {

    public BlueberryMilkshakeItem(Properties builder) {
        super(builder);
    }

    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entity) {
        doEffect(entity);

        return super.onItemUseFinish(stack, worldIn, entity);
    }

    public ActionResultType itemInteractionForEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
        if (entity.world.isRemote) {
            return ActionResultType.PASS;
        } else {
            entity.world.playSound((PlayerEntity)null, entity.func_233580_cy_(), SoundEvents.ENTITY_WANDERING_TRADER_DRINK_MILK, SoundCategory.NEUTRAL, 1.0F, 1.0F);
            if (player instanceof ServerPlayerEntity) {
                ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)player;
                CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
                serverplayerentity.addStat(Stats.ITEM_USED.get(this));
            }

            doEffect(entity);

            if (!player.abilities.isCreativeMode) {
                stack.shrink(1);
                ItemStack itemstack = new ItemStack(Items.GLASS_BOTTLE);
                if (!player.inventory.addItemStackToInventory(itemstack)) {
                    player.dropItem(itemstack, false);
                }
            }

            return ActionResultType.SUCCESS;
        }
    }

    public static void doEffect(LivingEntity entity) {
        ImmutableList<EffectInstance> effects = ImmutableList.copyOf(entity.getActivePotionEffects());

        if (effects.size() > 0) {
            for (EffectInstance e : effects) {
                entity.removePotionEffect(e.getPotion());
                if (e.getAmplifier() > 0) {entity.addPotionEffect(new EffectInstance(e.getPotion(), e.getDuration(), e.getAmplifier() - 1));}
            }
        }
    }

    public int getUseDuration(ItemStack stack) {
        return 40;
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    public SoundEvent getDrinkSound() {
        return SoundEvents.ITEM_HONEY_BOTTLE_DRINK;
    }

    public SoundEvent getEatSound() {
        return SoundEvents.ITEM_HONEY_BOTTLE_DRINK;
    }
}

