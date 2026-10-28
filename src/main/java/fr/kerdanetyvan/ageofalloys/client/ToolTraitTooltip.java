package fr.kerdanetyvan.ageofalloys.client;

import fr.kerdanetyvan.ageofalloys.AgeOfAlloys;
import fr.kerdanetyvan.ageofalloys.content.ToolTrait;
import fr.kerdanetyvan.ageofalloys.registry.ModTiers;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.DiggerItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Adds the trait to the tooltip of the tools and weapons of the alloys, in blue like the attribute lines of the
 * game. A tool has no set, so its trait is always active. The lines of the other traits (reach, knockback, stun)
 * join this class as they come. {@code value = Dist.CLIENT} makes Forge skip this class on a dedicated server.
 */
@Mod.EventBusSubscriber(modid = AgeOfAlloys.MOD_ID, value = Dist.CLIENT)
public final class ToolTraitTooltip {
    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        List<Component> tooltip = event.getToolTip();
        if (event.getItemStack().getItem() instanceof DiggerItem tool) {
            if (tool.getTier() == ModTiers.DURALUMIN) {
                addTrait(tooltip, "duralumin.mining", ToolTrait.displayedPercent(ToolTrait.DURALUMIN_MINING_MULTIPLIER));
            } else if (tool.getTier() == ModTiers.STEEL) {
                addTrait(tooltip, "steel.mining", ToolTrait.displayedPercent(ToolTrait.STEEL_STONE_MINING_MULTIPLIER));
            }
        }
    }

    private static void addTrait(List<Component> tooltip, String key, int value) {
        tooltip.add(Component.translatable("tooltip." + AgeOfAlloys.MOD_ID + ".trait." + key, value)
                .withStyle(ChatFormatting.BLUE));
    }

    private ToolTraitTooltip() {
    }
}
