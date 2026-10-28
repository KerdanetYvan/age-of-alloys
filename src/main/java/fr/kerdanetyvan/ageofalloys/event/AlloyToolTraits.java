package fr.kerdanetyvan.ageofalloys.event;

import fr.kerdanetyvan.ageofalloys.AgeOfAlloys;
import fr.kerdanetyvan.ageofalloys.content.ToolTrait;
import fr.kerdanetyvan.ageofalloys.registry.ModTiers;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * The traits of the tools and weapons of the alloys (see docs/design/alloy-traits.md), one event at a time.
 * Not marked client-only: the mining speed is worked out on both sides, and must be the same on both.
 */
@Mod.EventBusSubscriber(modid = AgeOfAlloys.MOD_ID)
public final class AlloyToolTraits {
    /**
     * Mining speed. Duralumin tools are faster on rock and on every ore, but not on deepslate itself; steel tools
     * are faster on stone and on deepslate. The speed already worked out is multiplied, so the bonus adds to
     * Efficiency and to Haste instead of replacing them. Only the right tool for the block gets it.
     */
    @SubscribeEvent
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        ItemStack stack = event.getEntity().getMainHandItem();
        BlockState state = event.getState();
        if (!(stack.getItem() instanceof DiggerItem tool) || !stack.isCorrectToolForDrops(state)) {
            return;
        }
        if (tool.getTier() == ModTiers.DURALUMIN) {
            boolean rock = state.is(BlockTags.BASE_STONE_OVERWORLD) && !state.is(Blocks.DEEPSLATE);
            if (rock || state.is(Tags.Blocks.ORES)) {
                event.setNewSpeed(event.getNewSpeed() * ToolTrait.DURALUMIN_MINING_MULTIPLIER);
            }
        } else if (tool.getTier() == ModTiers.STEEL && state.is(BlockTags.BASE_STONE_OVERWORLD)) {
            event.setNewSpeed(event.getNewSpeed() * ToolTrait.STEEL_STONE_MINING_MULTIPLIER);
        }
    }

    private AlloyToolTraits() {
    }
}
