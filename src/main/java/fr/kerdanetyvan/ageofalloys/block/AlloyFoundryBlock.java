package fr.kerdanetyvan.ageofalloys.block;

import fr.kerdanetyvan.ageofalloys.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

/**
 * The alloy foundry. It has a front that faces the player when it is placed, a lit state,
 * and a block entity that holds its slots and its fire.
 */
public class AlloyFoundryBlock extends HorizontalDirectionalBlock implements EntityBlock {
    /** Whether the foundry is burning: a second property of the state, next to the direction. */
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    public AlloyFoundryBlock(BlockBehaviour.Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(LIT, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new AlloyFoundryBlockEntity(pos, state);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
                                 BlockHitResult hit) {
        // Opening a menu is decided by the server: the client only gets told to show the screen.
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }
        if (level.getBlockEntity(pos) instanceof AlloyFoundryBlockEntity foundry) {
            NetworkHooks.openScreen((ServerPlayer) player, foundry, pos);
        }
        return InteractionResult.CONSUME;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide || type != ModBlockEntities.ALLOY_FOUNDRY.get()) {
            return null;
        }
        return (tickLevel, pos, tickState, blockEntity) ->
                AlloyFoundryBlockEntity.serverTick(tickLevel, pos, tickState, (AlloyFoundryBlockEntity) blockEntity);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        // Only when the block really goes away, not when only its state changes (LIT).
        if (!state.is(newState.getBlock()) && level.getBlockEntity(pos) instanceof AlloyFoundryBlockEntity foundry) {
            foundry.dropContents(level, pos);
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
}
