package fr.kerdanetyvan.ageofalloys;

import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

/**
 * Entry point of the mod. Forge finds this class through the {@link Mod} annotation,
 * whose value must match the {@code modId} declared in META-INF/mods.toml.
 */
@Mod(AgeOfAlloys.MOD_ID)
public class AgeOfAlloys {
    public static final String MOD_ID = "ageofalloys";
    public static final Logger LOGGER = LogUtils.getLogger();

    public AgeOfAlloys() {
        LOGGER.info("Age of Alloys is loading");
    }
}
