package com.evoslab.canopy.common.block;

import com.evoslab.canopy.common.item.BlueberryMilkshakeItem;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.CakeBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import java.util.Iterator;

public class BlueberryCakeBlock extends CakeBlock {
    private Food food;

    public BlueberryCakeBlock(Food food, AbstractBlock.Properties properties) {
        super(properties);
        this.food = food;
    }

    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.isRemote) {
            ItemStack itemstack = player.getHeldItem(handIn);
            if (this.eatSlice(worldIn, pos, state, player).isSuccessOrConsume()) {
                return ActionResultType.SUCCESS;
            }

            if (itemstack.isEmpty()) {
                return ActionResultType.CONSUME;
            }
        }

        return this.eatSlice(worldIn, pos, state, player);
    }

    private ActionResultType eatSlice(IWorld world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!player.canEat(false)) {
            return ActionResultType.PASS;
        } else {
            player.addStat(Stats.EAT_CAKE_SLICE);
            player.getFoodStats().addStats(this.food.getHealing(), this.food.getSaturation());
            int bites = (Integer)state.get(BITES);

            BlueberryMilkshakeItem.doEffect(player);

            Iterator effectIter = this.food.getEffects().iterator();

            while(effectIter.hasNext()) {
                Pair<EffectInstance, Float> pair = (Pair)effectIter.next();
                if (!world.isRemote() && pair.getFirst() != null && world.getRandom().nextFloat() < (Float)pair.getSecond()) {
                    player.addPotionEffect(new EffectInstance((EffectInstance)pair.getFirst()));
                }
            }

            if (bites < 6) {
                world.setBlockState(pos, (BlockState)state.with(BITES, bites + 1), 3);
            } else {
                world.removeBlock(pos, false);
            }

            return ActionResultType.SUCCESS;
        }
    }
}
