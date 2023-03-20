package net.auxy.cwood.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.*;
import net.minecraft.world.timer.*;
import net.minecraft.world.updater.WorldUpdater;

import org.jetbrains.annotations.Nullable;


import java.util.stream.Stream;

public class FiltrationBlock extends Block implements Waterloggable {

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;


    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;


    public FiltrationBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState()
                .with(Properties.HORIZONTAL_FACING, Direction.NORTH)
                .with(WATERLOGGED, false));
    }


    private static final VoxelShape SHAPE_N = Stream.of(
            Block.createCuboidShape(0, 0, 0, 16, 10, 16),
            Block.createCuboidShape(0, 10, 0, 14, 12, 2),
            Block.createCuboidShape(14, 10, 0, 16, 12, 14),
            Block.createCuboidShape(2, 10, 14, 16, 12, 16),
            Block.createCuboidShape(0, 10, 2, 2, 12, 16),
            Block.createCuboidShape(1, 12, 15, 16, 13, 16),
            Block.createCuboidShape(0, 12, 0, 15, 13, 1),
            Block.createCuboidShape(15, 12, 0, 16, 13, 15),
            Block.createCuboidShape(0, 12, 1, 1, 13, 16),
            Block.createCuboidShape(1, 12, 1, 15, 13, 15)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.createCuboidShape(0, 0, 0, 16, 10, 16),
            Block.createCuboidShape(0, 10, 2, 2, 12, 16),
            Block.createCuboidShape(0, 10, 0, 14, 12, 2),
            Block.createCuboidShape(14, 10, 0, 16, 12, 14),
            Block.createCuboidShape(2, 10, 14, 16, 12, 16),
            Block.createCuboidShape(15, 12, 0, 16, 13, 15),
            Block.createCuboidShape(0, 12, 1, 1, 13, 16),
            Block.createCuboidShape(0, 12, 0, 15, 13, 1),
            Block.createCuboidShape(1, 12, 15, 16, 13, 16),
            Block.createCuboidShape(1, 12, 1, 15, 13, 15)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.createCuboidShape(0, 0, 0, 16, 10, 16),
            Block.createCuboidShape(2, 10, 14, 16, 12, 16),
            Block.createCuboidShape(0, 10, 2, 2, 12, 16),
            Block.createCuboidShape(0, 10, 0, 14, 12, 2),
            Block.createCuboidShape(14, 10, 0, 16, 12, 14),
            Block.createCuboidShape(0, 12, 0, 15, 13, 1),
            Block.createCuboidShape(1, 12, 15, 16, 13, 16),
            Block.createCuboidShape(0, 12, 1, 1, 13, 16),
            Block.createCuboidShape(15, 12, 0, 16, 13, 15),
            Block.createCuboidShape(1, 12, 1, 15, 13, 15)
            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();


    private static final VoxelShape SHAPE_E = Stream.of(
            Block.createCuboidShape(0, 0, 0, 16, 10, 16),
            Block.createCuboidShape(14, 10, 0, 16, 12, 14),
            Block.createCuboidShape(2, 10, 14, 16, 12, 16),
            Block.createCuboidShape(0, 10, 2, 2, 12, 16),
            Block.createCuboidShape(0, 10, 0, 14, 12, 2),
            Block.createCuboidShape(0, 12, 1, 1, 13, 16),
            Block.createCuboidShape(15, 12, 0, 16, 13, 15),
            Block.createCuboidShape(1, 12, 15, 16, 13, 16),
            Block.createCuboidShape(0, 12, 0, 15, 13, 1),
            Block.createCuboidShape(1, 12, 1, 15, 13, 15)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();


    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch (state.get(FACING)) {
            case NORTH:
                return SHAPE_N;

            case SOUTH:
                return SHAPE_S;

            case EAST:
                return SHAPE_E;

            case WEST:
                return SHAPE_W;

            default:
                return SHAPE_N;



        }
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite())
                .with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).getFluid() == Fluids.WATER);

    }



    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }




    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);

    }



    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }


}
