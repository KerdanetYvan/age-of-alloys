package fr.kerdanetyvan.ageofalloys.event;

import fr.kerdanetyvan.ageofalloys.AgeOfAlloys;
import fr.kerdanetyvan.ageofalloys.content.AlloyArmorItem;
import fr.kerdanetyvan.ageofalloys.content.AlloySet;
import fr.kerdanetyvan.ageofalloys.content.SetBonus;
import fr.kerdanetyvan.ageofalloys.registry.ModArmorMaterials;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * The effects of the sets of alloy armor (see docs/design/alloy-traits.md): what the four pieces of an alloy do
 * together. {@link AlloySet} tells whether a creature wears a full set and {@link SetBonus} holds the numbers.
 * Not marked client-only: the effects must also run on a dedicated server.
 */
@Mod.EventBusSubscriber(modid = AgeOfAlloys.MOD_ID)
public final class AlloySetEffects {
    /** Duralumin: a fall hurts less. The multiplier of the damage is lowered before the game works the damage out. */
    @SubscribeEvent
    public static void onLivingFall(LivingFallEvent event) {
        if (AlloySet.isComplete(event.getEntity(), ModArmorMaterials.DURALUMIN)) {
            event.setDamageMultiplier(event.getDamageMultiplier() * SetBonus.DURALUMIN_FALL_DAMAGE_MULTIPLIER);
        }
    }

    /**
     * Steel: fire hurts less. The amount is lowered before the armor and the enchantments take their share,
     * which then apply to what is left.
     */
    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (event.getSource().is(DamageTypeTags.IS_FIRE)
                && AlloySet.isComplete(event.getEntity(), ModArmorMaterials.STEEL)) {
            event.setAmount(event.getAmount() * SetBonus.STEEL_FIRE_DAMAGE_MULTIPLIER);
        }
    }

    /**
     * Bronze: the pieces mend slowly. The time is counted with the time the player has been playing, so there is
     * nothing to save: logging in again starts the count over, which hardly matters over three minutes.
     */
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END || !event.side.isServer()) {
            return;
        }
        Player player = event.player;
        if (player.tickCount == 0 || player.tickCount % SetBonus.BRONZE_REPAIR_INTERVAL_TICKS != 0
                || !AlloySet.isComplete(player, ModArmorMaterials.BRONZE)) {
            return;
        }
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.getType() != EquipmentSlot.Type.ARMOR) {
                continue;
            }
            ItemStack stack = player.getItemBySlot(slot);
            if (stack.getItem() instanceof AlloyArmorItem && stack.isDamaged()) {
                stack.setDamageValue(stack.getDamageValue() - 1);
            }
        }
    }

    private AlloySetEffects() {
    }
}
