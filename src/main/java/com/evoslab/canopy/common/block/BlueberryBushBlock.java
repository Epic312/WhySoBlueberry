package com.evoslab.canopy.common.block;

import com.evoslab.canopy.core.registry.CanopyItems;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.RavagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.DimensionType;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

@SuppressWarnings("deprecated")
public class BlueberryBushBlock extends BushBlock implements IPlantable, IGrowable {
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 6);
    public static final EnumProperty<BlueberryType> TYPE = EnumProperty.create("type", BlueberryType.class);
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[] {
            Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 5.0D, 14.0D),
            Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 7.0D, 14.0D),
            Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D),
            Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D),
            Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D),
            Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D)
    };

    public BlueberryBushBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(AGE, 0).with(TYPE, BlueberryType.NONE));
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        int age = state.get(AGE);
        boolean fullyGrown = age == this.getMaxAge();
        if (!fullyGrown && player.getHeldItem(handIn).getItem() == Items.BONE_MEAL) {
            return ActionResultType.PASS;
        } else if (fullyGrown) {
            int blueberryCount = 1 + worldIn.rand.nextInt(2);
            Item blueberry = state.get(TYPE) == BlueberryType.PINK ? CanopyItems.PINK_BLUEBERRIES.get() : CanopyItems.BLUEBERRIES.get();
            spawnAsEntity(worldIn, pos, new ItemStack(blueberry, blueberryCount));
            worldIn.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.rand.nextFloat() * 0.4F);
            worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(2)).with(TYPE, BlueberryType.NONE), 2);
            return ActionResultType.func_233537_a_(worldIn.isRemote);
        } else {
            return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
        }
    }

    protected int getBonemealAgeIncrease(World worldIn) {
        return MathHelper.nextInt(worldIn.rand, 2, 5);
    }

    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        super.tick(state, worldIn, pos, rand);
        if (!worldIn.isAreaLoaded(pos, 1))
            return;
        if (worldIn.getLightSubtracted(pos, 0) >= 13) {
            int age = this.getAge(state);
            int maxAgeForPos = worldIn.getBlockState(pos.down()).isIn(Blocks.COARSE_DIRT) ? 3 : this.getMaxAge();
            int growthChance = !worldIn.isRaining() ? 7 : 5;
            if (age < maxAgeForPos) {
                if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt(growthChance) == 0)) {
                    if (age != 3) {
                        worldIn.setBlockState(pos, this.withAge(age + 1), 2);
                    } else {
                        worldIn.setBlockState(pos, this.withAge(age + 1).with(TYPE, BlueberryType.BLUE), 2);
                    }
                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
                }
            }
        }
    }

    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if (worldIn.rand.nextInt(15) == 0) {
            if (entityIn.lastTickPosX != entityIn.getPosX() || entityIn.lastTickPosZ != entityIn.getPosZ()) {
                double d0 = Math.abs(entityIn.getPosX() - entityIn.lastTickPosX);
                double d1 = Math.abs(entityIn.getPosZ() - entityIn.lastTickPosZ);
                if (d0 >= (double) 0.003F || d1 >= (double) 0.003F) {
                    worldIn.playSound((PlayerEntity) null, pos, SoundEvents.BLOCK_GRASS_STEP, SoundCategory.BLOCKS, 1.5F, 0.8F + worldIn.rand.nextFloat() * 0.4F);
                }
            }
        }
        if (entityIn instanceof RavagerEntity && net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(worldIn, entityIn)) {
            worldIn.destroyBlock(pos, true, entityIn);
        }
        super.onEntityCollision(state, worldIn, pos, entityIn);
    }

    protected IItemProvider getSeedsItem() {
        return CanopyItems.BLUEBERRY_PIPS.get();
    }

    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
        return new ItemStack(this.getSeedsItem());
    }

    protected int getAge(BlockState state) {
        return state.get(this.getAgeProperty());
    }

    public BlockState withAge(int age) {
        return this.getDefaultState().with(this.getAgeProperty(), Integer.valueOf(age));
    }

    public boolean isMaxAge(BlockState state) {
        return state.get(this.getAgeProperty()) >= this.getMaxAge();
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE, TYPE);
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE_BY_AGE[state.get(this.getAgeProperty())];
    }

    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    public int getMaxAge() {
        return 5;
    }

    @Override
    public boolean canGrow(IBlockReader block, BlockPos pos, BlockState state, boolean isClient) {
        return !this.isMaxAge(state);
    }

    @Override
    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        int age = Math.min(this.getAge(state) + this.getBonemealAgeIncrease(worldIn), this.getMaxAge());
        if (age != 5 && age != 4) {
            worldIn.setBlockState(pos, this.withAge(age), 2);
        } else {
            worldIn.setBlockState(pos, this.withAge(age).with(TYPE, BlueberryType.BLUE), 2);
        }
    }

    public static enum BlueberryType implements IStringSerializable {
        NONE("none"), BLUE("blue"), PINK("pink");

        private final String name;

        private BlueberryType(String name) {
            this.name = name;
        }

        @Override
        public String getString() {
            return this.name;
        }
    }
}
