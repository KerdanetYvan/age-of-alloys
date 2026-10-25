package fr.kerdanetyvan.ageofalloys.block;

import fr.kerdanetyvan.ageofalloys.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Containers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RangedWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The memory of an alloy foundry: its six slots and its fire. It uses Forge (inventory
 * capabilities), so it lives in the block package and not in content.
 */
public class AlloyFoundryBlockEntity extends BlockEntity {
    public static final int INPUT_SLOTS = 4;
    public static final int FUEL_SLOT = 4;
    public static final int OUTPUT_SLOT = 5;
    public static final int SLOT_COUNT = 6;

    private final ItemStackHandler inventory = new ItemStackHandler(SLOT_COUNT) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            if (slot == OUTPUT_SLOT) {
                return false;
            }
            if (slot == FUEL_SLOT) {
                return ForgeHooks.getBurnTime(stack, RecipeType.SMELTING) > 0;
            }
            return true;
        }
    };

    // What a hopper sees depending on the side it touches: each one is a window on the same inventory.
    private final LazyOptional<IItemHandler> allSlots = LazyOptional.of(() -> inventory);
    private final LazyOptional<IItemHandler> fromTop =
            LazyOptional.of(() -> new RangedWrapper(inventory, 0, INPUT_SLOTS));
    private final LazyOptional<IItemHandler> fromSides =
            LazyOptional.of(() -> new RangedWrapper(inventory, FUEL_SLOT, FUEL_SLOT + 1));
    private final LazyOptional<IItemHandler> fromBottom =
            LazyOptional.of(() -> new RangedWrapper(inventory, OUTPUT_SLOT, SLOT_COUNT));

    /** Ticks left on the fuel being burnt. */
    private int burnTime;
    /** Total duration of the fuel being burnt, for the flame of the interface. */
    private int burnDuration;

    public AlloyFoundryBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ALLOY_FOUNDRY.get(), pos, state);
    }

    public ItemStackHandler getInventory() {
        return inventory;
    }

    public int getBurnTime() {
        return burnTime;
    }

    public int getBurnDuration() {
        return burnDuration;
    }

    /** Whether at least one input slot holds something. Temporary rule: #88 replaces it with "a recipe matches". */
    private boolean hasInput() {
        for (int slot = 0; slot < INPUT_SLOTS; slot++) {
            if (!inventory.getStackInSlot(slot).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /** Runs 20 times per second on the server. */
    public static void serverTick(Level level, BlockPos pos, BlockState state, AlloyFoundryBlockEntity foundry) {
        if (foundry.burnTime > 0) {
            foundry.burnTime--;
        }

        if (foundry.burnTime == 0 && foundry.hasInput()) {
            ItemStack fuel = foundry.inventory.getStackInSlot(FUEL_SLOT);
            int duration = fuel.isEmpty() ? 0 : ForgeHooks.getBurnTime(fuel, RecipeType.SMELTING);
            if (duration > 0) {
                foundry.burnDuration = duration;
                foundry.burnTime = duration;
                fuel.shrink(1);
            }
        }

        boolean burning = foundry.burnTime > 0;
        if (state.getValue(AlloyFoundryBlock.LIT) != burning) {
            level.setBlock(pos, state.setValue(AlloyFoundryBlock.LIT, burning), 3);
        }
        foundry.setChanged();
    }

    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap != ForgeCapabilities.ITEM_HANDLER) {
            return super.getCapability(cap, side);
        }
        if (side == null) {
            return allSlots.cast();
        }
        return switch (side) {
            case UP -> fromTop.cast();
            case DOWN -> fromBottom.cast();
            default -> fromSides.cast();
        };
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        allSlots.invalidate();
        fromTop.invalidate();
        fromSides.invalidate();
        fromBottom.invalidate();
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("Inventory", inventory.serializeNBT());
        tag.putInt("BurnTime", burnTime);
        tag.putInt("BurnDuration", burnDuration);
    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        inventory.deserializeNBT(tag.getCompound("Inventory"));
        burnTime = tag.getInt("BurnTime");
        burnDuration = tag.getInt("BurnDuration");
    }

    /** Throws the whole content into the world, when the foundry is broken. */
    public void dropContents(Level level, BlockPos pos) {
        for (int slot = 0; slot < SLOT_COUNT; slot++) {
            Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), inventory.getStackInSlot(slot));
        }
    }
}
