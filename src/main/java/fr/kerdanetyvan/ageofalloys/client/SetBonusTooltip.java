package fr.kerdanetyvan.ageofalloys.client;

import fr.kerdanetyvan.ageofalloys.AgeOfAlloys;
import fr.kerdanetyvan.ageofalloys.content.AlloyArmorItem;
import fr.kerdanetyvan.ageofalloys.content.AlloyArmorMaterial;
import fr.kerdanetyvan.ageofalloys.content.AlloySet;
import fr.kerdanetyvan.ageofalloys.content.SetBonus;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Adds the set bonus to the tooltip of a piece of alloy armor: green when the player wears the four pieces,
 * grey otherwise. It reads what the player wears, so it lives on the client side and not in the item.
 * {@code value = Dist.CLIENT} makes Forge skip this class on a dedicated server.
 */
@Mod.EventBusSubscriber(modid = AgeOfAlloys.MOD_ID, value = Dist.CLIENT)
public final class SetBonusTooltip {
    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        if (!(event.getItemStack().getItem() instanceof AlloyArmorItem piece)) {
            return;
        }
        AlloyArmorMaterial material = piece.alloyMaterial();
        // The player is missing while the creative tab is built at the start of the game: nothing is worn then.
        Player player = event.getEntity();
        int worn = player == null ? 0 : AlloySet.piecesWorn(player, material);
        boolean active = worn == AlloySet.PIECES;

        String key = "tooltip." + AgeOfAlloys.MOD_ID + ".set." + material.alloyId();
        List<Component> tooltip = event.getToolTip();
        tooltip.add(Component.empty());
        tooltip.add(Component.translatable(key + ".title", worn)
                .withStyle(active ? ChatFormatting.GREEN : ChatFormatting.GRAY));
        tooltip.add(Component.literal("  ")
                .append(Component.translatable(key + ".effect", SetBonus.displayedValue(material.alloyId())))
                .withStyle(active ? ChatFormatting.GREEN : ChatFormatting.DARK_GRAY));
    }

    private SetBonusTooltip() {
    }
}
