package quatum.limitless_options_neoforge;

import com.mojang.logging.LogUtils;
import com.sun.jna.Memory;
import net.minecraft.util.Util;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.util.tinyfd.TinyFileDialogs;
import org.slf4j.Logger;
import quatum.limitless_options_neoforge.gui.OpionssetterScreen;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(LimitlessOptions_NeoForge.MODID)
public class LimitlessOptions_NeoForge {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "limitless_options_neoforge";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "limitless_options_forge" namespace

    public LimitlessOptions_NeoForge(IEventBus modEventBus, ModContainer modContainer) {

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC, "LimitlessOptions_NeoForge-Common.toml");

    }
}

