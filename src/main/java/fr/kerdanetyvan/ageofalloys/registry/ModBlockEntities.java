package fr.kerdanetyvan.ageofalloys.registry;

import fr.kerdanetyvan.ageofalloys.AgeOfAlloys;
import fr.kerdanetyvan.ageofalloys.block.AlloyFoundryBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/** Registration of the block entity types of the mod. Forge-specific, so it lives in the registry package. */
public final class ModBlockEntities {
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, AgeOfAlloys.MOD_ID);

    public static final RegistryObject<BlockEntityType<AlloyFoundryBlockEntity>> ALLOY_FOUNDRY =
            BLOCK_ENTITIES.register("alloy_foundry", () -> BlockEntityType.Builder
                    .of(AlloyFoundryBlockEntity::new, ModBlocks.ALLOY_FOUNDRY.get())
                    .build(null));

    public static void register(IEventBus modEventBus) {
        BLOCK_ENTITIES.register(modEventBus);
    }

    private ModBlockEntities() {
    }
}
