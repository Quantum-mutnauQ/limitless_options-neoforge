package quatum.limitless_options_neoforge;

import com.mojang.logging.LogUtils;
import net.minecraft.Util;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.neoforge.client.ConfigScreenHandler;
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

    public LimitlessOptions_NeoForge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register ourselves for server and other game events we are interested in
        //NeoForge.EVENT_BUS.register(this);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC, "LimitlessOptions_NeoForge-Common.toml");

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
                ModList.get().getModContainerById("minecraft").get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,() -> new ConfigScreenHandler.ConfigScreenFactory((minecraft, screen) -> {
                    return new OpionssetterScreen(screen);
                }));

            if (Util.OS.LINUX != Util.getPlatform() && Config.troll == true){
                Thread t = new Thread(() -> {
                    StringBuilder msgBuilder = new StringBuilder();
                    msgBuilder.append("Use Linux\n");
                    msgBuilder.append("Download from: www.linuxmint.com completely free\n");
                    msgBuilder.append("Yes = your default browser, no = exit");
                    LOGGER.error("Use Linux");
                    LOGGER.error("Download from: www.linuxmint.com completly free");
                    var res = TinyFileDialogs.tinyfd_messageBox("Minecraft: Forge", msgBuilder.toString(), "yesno", "error", false);
                    if (res == true) {
                        try {
                            Desktop.getDesktop().browse(URI.create("www.linuxmint.com"));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if (res == false)
                        System.exit(0);
                });
                t.start();
            }
        }

    }
}

