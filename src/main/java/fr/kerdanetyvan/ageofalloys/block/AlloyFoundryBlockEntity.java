package fr.kerdanetyvan.ageofalloys.block;

import fr.kerdanetyvan.ageofalloys.menu.AlloyFoundryMenu;
import fr.kerdanetyvan.ageofalloys.recipe.AlloyIngredient;
import fr.kerdanetyvan.ageofalloys.recipe.AlloyingRecipe;
import fr.kerdanetyvan.ageofalloys.registry.ModBlockEntities;
import fr.kerdanetyvan.ageofalloys.registry.ModRecipeTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
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
import net.minecraftforge.items.wrapper.RecipeWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The memory of an alloy foundry: its six slots and its fire. It uses Forge (inventory
 * capabilities), so it lives in the block package and not in content.
 */
public class AlloyFoundryBlockEntity extends BlockEntity implements MenuProvider {
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
                return isFuel(stack);
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

    // The inventory seen as a vanilla container, to ask the recipe manager which recipe matches. Its cache
    // remembers the last recipe found, so the tick does not search the whole list 20 times per second.
    private final Container recipeInputs = new RecipeWrapper(inventory);
    private final RecipeManager.CachedCheck<Container, AlloyingRecipe> recipeCheck =
            RecipeManager.createCheck(ModRecipeTypes.ALLOYING.get());

    /** Ticks left on the fuel being burnt. */
    private int burnTime;
    /** Total duration of the fuel being burnt, for the flame of the interface. */
    private int burnDuration;
    /** Ticks of melting done on the current batch. */
    private int progress;
    /** Ticks the current recipe takes (0 when there is nothing to melt). */
    private int progressMax;

    /** The numbers the server keeps in sync with the client while the menu is open (flame, progress bar). */
    private final ContainerData data = new ContainerData() {
        @Override
        public int get(int index) {
            switch (index) {
                case AlloyFoundryMenu.DATA_BURN_TIME:
                    return burnTime;
                case AlloyFoundryMenu.DATA_BURN_DURATION:
                    return burnDuration;
                case AlloyFoundryMenu.DATA_PROGRESS:
                    return progress;
                case AlloyFoundryMenu.DATA_PROGRESS_MAX:
                    return progressMax;
                default:
                    return 0;
            }
        }

        @Override
        public void set(int index, int value) {
            // Unused on the server: only the client receives values.
        }

        @Override
        public int getCount() {
            return AlloyFoundryMenu.DATA_COUNT;
        }
    };

    public AlloyFoundryBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ALLOY_FOUNDRY.get(), pos, state);
    }

    /** Whether an item burns, so it may go in the fuel slot. */
    public static boolean isFuel(ItemStack stack) {
        return ForgeHooks.getBurnTime(stack, RecipeType.SMELTING) > 0;
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

    /** Runs 20 times per second on the server. */
    public static void serverTick(Level level, BlockPos pos, BlockState state, AlloyFoundryBlockEntity foundry) {
        if (foundry.burnTime > 0) {
            foundry.burnTime--;
        }

        AlloyingRecipe recipe = foundry.recipeCheck.getRecipeFor(foundry.recipeInputs, level).orElse(null);
        boolean canWork = recipe != null && foundry.hasRoomFor(recipe);

        if (foundry.burnTime == 0 && canWork) {
            ItemStack fuel = foundry.inventory.getStackInSlot(FUEL_SLOT);
            int duration = fuel.isEmpty() ? 0 : ForgeHooks.getBurnTime(fuel, RecipeType.SMELTING);
            if (duration > 0) {
                foundry.burnDuration = duration;
                foundry.burnTime = duration;
                fuel.shrink(1);
            }
        }

        if (foundry.burnTime > 0 && canWork) {
            foundry.progressMax = recipe.time();
            foundry.progress++;
            if (foundry.progress >= foundry.progressMax) {
                foundry.melt(recipe);
                foundry.progress = 0;
            }
        } else if (foundry.progress > 0) {
            // Nothing to melt, no fire or no room: the progress goes back down, like a furnace.
            foundry.progress = Math.max(foundry.progress - 2, 0);
        }

        boolean burning = foundry.burnTime > 0;
        if (state.getValue(AlloyFoundryBlock.LIT) != burning) {
            level.setBlock(pos, state.setValue(AlloyFoundryBlock.LIT, burning), 3);
        }
        foundry.setChanged();
    }

    /** Whether the result fits in the output slot: it is empty, or holds the same item with room left. */
    private boolean hasRoomFor(AlloyingRecipe recipe) {
        ItemStack output = inventory.getStackInSlot(OUTPUT_SLOT);
        ItemStack result = recipe.result();
        if (output.isEmpty()) {
            return true;
        }
        return ItemStack.isSameItemSameTags(output, result)
                && output.getCount() + result.getCount() <= output.getMaxStackSize();
    }

    /** Takes a whole batch of ingredients from the inputs and puts the result in the output. */
    private void melt(AlloyingRecipe recipe) {
        for (AlloyIngredient ingredient : recipe.alloyIngredients()) {
            int left = ingredient.count();
            for (int slot = 0; slot < INPUT_SLOTS && left > 0; slot++) {
                ItemStack stack = inventory.getStackInSlot(slot);
                if (ingredient.accepts(stack)) {
                    left -= inventory.extractItem(slot, Math.min(left, stack.getCount()), false).getCount();
                }
            }
        }
        ItemStack output = inventory.getStackInSlot(OUTPUT_SLOT);
        if (output.isEmpty()) {
            inventory.setStackInSlot(OUTPUT_SLOT, recipe.result().copy());
        } else {
            output.grow(recipe.result().getCount());
            inventory.setStackInSlot(OUTPUT_SLOT, output);
        }
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("block.ageofalloys.alloy_foundry");
    }

    @Override
    public AbstractContainerMenu createMenu(int id, @NotNull Inventory playerInventory, @NotNull Player player) {
        return new AlloyFoundryMenu(id, playerInventory, inventory, data, ContainerLevelAccess.create(level, worldPosition));
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
        tag.putInt("Progress", progress);
        tag.putInt("ProgressMax", progressMax);
    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        inventory.deserializeNBT(tag.getCompound("Inventory"));
        burnTime = tag.getInt("BurnTime");
        burnDuration = tag.getInt("BurnDuration");
        progress = tag.getInt("Progress");
        progressMax = tag.getInt("ProgressMax");
    }

    /** Throws the whole content into the world, when the foundry is broken. */
    public void dropContents(Level level, BlockPos pos) {
        for (int slot = 0; slot < SLOT_COUNT; slot++) {
            Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), inventory.getStackInSlot(slot));
        }
    }
}
