package com.davigj.sticks_and_stones.common.blocks;

import com.davigj.sticks_and_stones.core.registry.SticksAndStonesBlocks;
import com.davigj.sticks_and_stones.core.registry.SticksAndStonesItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class PebbleBlock extends Block implements IWaterLoggable {
    public static final IntegerProperty PEBBLES = BlockStateProperties.PICKLES_1_4;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    protected static final VoxelShape ONE_SHAPE = Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 6.0D, 10.0D);
    protected static final VoxelShape TWO_SHAPE = Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 6.0D, 13.0D);
    protected static final VoxelShape THREE_SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 6.0D, 14.0D);
    protected static final VoxelShape FOUR_SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 7.0D, 14.0D);
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);

    public PebbleBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(PEBBLES, Integer.valueOf(1)).with(WATERLOGGED, Boolean.valueOf(false)));
//        this.setDefaultState(this.stateContainer.getBaseState().with(WATERLOGGED, Boolean.FALSE));
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        return hasEnoughSolidSide(worldIn, pos.down(), Direction.UP);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
    }

    @Override
    @Nonnull
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        Vector3d offset = state.getOffset(worldIn, pos);
        return SHAPE.withOffset(offset.x, offset.y, offset.z);
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos,
                                boolean isMoving) {
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
        if (!this.isValidPosition(state, worldIn, pos)) {
            worldIn.destroyBlock(pos, true);
        } else if (state.get(WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }
    }

    @Override
    @Nonnull
    public Block.OffsetType getOffsetType() {
        return Block.OffsetType.XZ;
    }

    @Override
    @Nonnull
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
                                             Hand handIn, BlockRayTraceResult hit) {
        if (!player.isCrouching()) {
            worldIn.removeBlock(pos, true);
            spawnAsEntity(worldIn, pos, new ItemStack(SticksAndStonesItems.PEBBLE_BLOCK.get(), 1));
            worldIn.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_GRAVEL_BREAK, SoundCategory.BLOCKS, 0.4F, 1.4F + worldIn.rand.nextFloat() * 0.4F);
            player.swingArm(handIn);
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }
}