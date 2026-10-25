package fr.kerdanetyvan.ageofalloys;

import com.mojang.logging.LogUtils;
import fr.kerdanetyvan.ageofalloys.datagen.DataGenerators;
import fr.kerdanetyvan.ageofalloys.registry.ModBlockEntities;
import fr.kerdanetyvan.ageofalloys.registry.ModBlocks;
import fr.kerdanetyvan.ageofalloys.registry.ModCreativeTabs;
import fr.kerdanetyvan.ageofalloys.registry.ModItems;
import fr.kerdanetyvan.ageofalloys.registry.ModMenuTypes;
import fr.kerdanetyvan.ageofalloys.registry.ModTiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

/**
 * Entry point of the mod. Forge finds this class through the {@link Mod} annotation,
 * whose value must match the {@code modId} declared in META-INF/mods.toml.
 */
@Mod(AgeOfAlloys.MOD_ID)
public class AgeOfAlloys {
    public static final String MOD_ID = "ageofalloys";
    public static final Logger LOGGER = LogUtils.getLogger();

    public AgeOfAlloys(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModItems.register(modEventBus);
        ModCreativeTabs.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(DataGenerators::gatherData);

        LOGGER.info("Age of Alloys is loading");
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(ModTiers::register);
        LOGGER.info("Age of Alloys: common setup (client and server)");
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        LOGGER.info("Age of Alloys: client setup (client only)");
    }
}
