package fr.kerdanetyvan.ageofalloys.menu;

import fr.kerdanetyvan.ageofalloys.block.AlloyFoundryBlockEntity;
import fr.kerdanetyvan.ageofalloys.registry.ModBlocks;
import fr.kerdanetyvan.ageofalloys.registry.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

/**
 * The menu of the alloy foundry: its slots and their rules, on both sides. The server one is wired
 * to the real inventory, the client one is a copy that the server keeps up to date. The drawing is
 * done by the screen, which only exists on the client.
 */
public class AlloyFoundryMenu extends AbstractContainerMenu {
    // Indexes of the values the server sends to the client along with the slots.
    public static final int DATA_BURN_TIME = 0;
    public static final int DATA_BURN_DURATION = 1;
    public static final int DATA_PROGRESS = 2;
    public static final int DATA_PROGRESS_MAX = 3;
    public static final int DATA_COUNT = 4;

    private static final int FOUNDRY_SLOTS = AlloyFoundryBlockEntity.SLOT_COUNT;
    private static final int PLAYER_SLOTS_END = FOUNDRY_SLOTS + 36;

    private final ContainerLevelAccess access;
    private final ContainerData data;

    /** Client side: called by the menu type, with an empty copy that the server fills. */
    public AlloyFoundryMenu(int id, Inventory playerInventory, FriendlyByteBuf buf) {
        this(id, playerInventory, new ItemStackHandler(FOUNDRY_SLOTS), new SimpleContainerData(DATA_COUNT),
                ContainerLevelAccess.NULL);
    }

    /** Server side: wired to the real inventory of the foundry. */
    public AlloyFoundryMenu(int id, Inventory playerInventory, IItemHandler handler, ContainerData data,
                            ContainerLevelAccess access) {
        super(ModMenuTypes.ALLOY_FOUNDRY.get(), id);
        this.access = access;
        this.data = data;

        // The foundry slots come first (0 to 5): quick moving relies on this order.
        addSlot(new SlotItemHandler(handler, 0, 20, 17));
        addSlot(new SlotItemHandler(handler, 1, 38, 17));
        addSlot(new SlotItemHandler(handler, 2, 20, 35));
        addSlot(new SlotItemHandler(handler, 3, 38, 35));
        addSlot(new SlotItemHandler(handler, AlloyFoundryBlockEntity.FUEL_SLOT, 74, 53) {
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return AlloyFoundryBlockEntity.isFuel(stack);
            }
        });
        addSlot(new SlotItemHandler(handler, AlloyFoundryBlockEntity.OUTPUT_SLOT, 134, 35) {
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return false;
            }
        });

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
        for (int col = 0; col < 9; col++) {
            addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }

        addDataSlots(data);
    }

    public int getBurnTime() {
        return data.get(DATA_BURN_TIME);
    }

    public int getBurnDuration() {
        return data.get(DATA_BURN_DURATION);
    }

    public int getProgress() {
        return data.get(DATA_PROGRESS);
    }

    public int getProgressMax() {
        return data.get(DATA_PROGRESS_MAX);
    }

    /** Shift + click: foundry to bag; bag to the fuel slot if it burns, else to the inputs; never to the output. */
    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        Slot slot = slots.get(index);
        if (!slot.hasItem()) {
            return ItemStack.EMPTY;
        }
        ItemStack stack = slot.getItem();
        ItemStack original = stack.copy();

        if (index < FOUNDRY_SLOTS) {
            if (!moveItemStackTo(stack, FOUNDRY_SLOTS, PLAYER_SLOTS_END, true)) {
                return ItemStack.EMPTY;
            }
        } else if (AlloyFoundryBlockEntity.isFuel(stack)) {
            if (!moveItemStackTo(stack, AlloyFoundryBlockEntity.FUEL_SLOT, AlloyFoundryBlockEntity.FUEL_SLOT + 1,
                    false)) {
                return ItemStack.EMPTY;
            }
        } else if (!moveItemStackTo(stack, 0, AlloyFoundryBlockEntity.INPUT_SLOTS, false)) {
            return ItemStack.EMPTY;
        }

        if (stack.isEmpty()) {
            slot.setByPlayer(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }
        if (stack.getCount() == original.getCount()) {
            return ItemStack.EMPTY;
        }
        slot.onTake(player, stack);
        return original;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return stillValid(access, player, ModBlocks.ALLOY_FOUNDRY.get());
    }
}
