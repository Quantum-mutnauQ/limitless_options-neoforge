package quatum.limitless_options_forge;

import com.mojang.logging.LogUtils;
import net.minecraft.Util;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.lwjgl.util.tinyfd.TinyFileDialogs;
import org.slf4j.Logger;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(LimitlessOptions_Forge.MODID)
public class LimitlessOptions_Forge {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "limitless_options_forge";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "limitless_options_forge" namespace



    public LimitlessOptions_Forge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.SPEC, "LimitlessOptions_Forge-client.toml");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            if (Util.OS.LINUX != Util.getPlatform()) {
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
            // Some client setup code
            //LOGGER.info("HELLO FROM CLIENT SETUP");
            //LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }

    }
}
