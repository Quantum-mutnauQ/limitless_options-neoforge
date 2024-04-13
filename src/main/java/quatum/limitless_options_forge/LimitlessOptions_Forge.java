package quatum.limitless_options_forge;

import com.mojang.logging.LogUtils;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;
import org.lwjgl.util.tinyfd.TinyFileDialogs;
import org.slf4j.Logger;
import quatum.limitless_options_forge.gui.OpionssetterScreen;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(LimitlessOptions_Forge.MODID)
public class LimitlessOptions_Forge {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "limitless_options_forge";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "limitless_options_forge" namespace

    public static List<OptionInstance<?>> OnScreenOptions = addDefaults();

    public static List<OptionInstance<?>> addDefaults(){
        List<OptionInstance<?>> list= new ArrayList();
        var options = Minecraft.getInstance().options;
        list.add(options.gamma());
        list.add(options.guiScale());
        list.add(options.renderDistance());
        list.add(options.simulationDistance());
        list.add(options.fov());
        list.add(options.autoJump());
        list.add(options.operatorItemsTab());
        list.add(options.autoSuggestions());
        list.add(options.chatColors());
        list.add(options.chatLinks());
        list.add(options.chatLinksPrompt());
        list.add(options.enableVsync());
        list.add(options.entityShadows());
        list.add(options.forceUnicodeFont());
        list.add(options.discreteMouseScroll());
        list.add(options.invertYMouse());
        list.add(options.realmsNotifications());
        list.add(options.reducedDebugInfo());
        list.add(options.showSubtitles());
        list.add(options.directionalAudio());
        list.add(options.touchscreen());
        list.add(options.fullscreen());
        list.add(options.bobView());
        list.add(options.toggleCrouch());
        list.add(options.toggleSprint());
        list.add(options.darkMojangStudiosBackground());
        list.add(options.hideLightningFlash());
        list.add(options.mouseWheelSensitivity());
        list.add(options.screenEffectScale());
        list.add(options.fovEffectScale());
        list.add(options.darknessEffectScale());
        list.add(options.glintSpeed());
        list.add(options.glintStrength());
        list.add(options.damageTiltStrength());
        list.add(options.highContrast());
        list.add(options.entityDistanceScaling());
        list.add(options.particles());
        list.add(options.graphicsMode());
        list.add(options.ambientOcclusion());
        list.add(options.prioritizeChunkUpdates());
        list.add(options.biomeBlendRadius());
        list.add(options.cloudStatus());
        list.add(options.chatVisibility());
        list.add(options.chatOpacity());
        list.add(options.chatLineSpacing());
        list.add(options.textBackgroundOpacity());
        list.add(options.backgroundForChatOnly());
        list.add(options.chatHeightFocused());
        list.add(options.chatDelay());
        list.add(options.chatHeightUnfocused());
        list.add(options.chatScale());
        list.add(options.chatWidth());
        list.add(options.notificationDisplayTime());
        list.add(options.mipmapLevels());
        list.add(options.mainHand());
        list.add(options.attackIndicator());
        list.add(options.narrator());
        list.add(options.mouseWheelSensitivity());
        list.add(options.rawMouseInput());
        list.add(options.hideMatchedNames());
        list.add(options.showAutosaveIndicator());
        list.add(options.allowServerListing());
        list.add(options.onlyShowSecureChat());
        list.add(options.panoramaSpeed());
        list.add(options.telemetryOptInExtra());
        list.add(options.getSoundSourceOptionInstance(SoundSource.MASTER));
        list.add(options.getSoundSourceOptionInstance(SoundSource.MUSIC));
        list.add(options.getSoundSourceOptionInstance(SoundSource.RECORDS));
        list.add(options.getSoundSourceOptionInstance(SoundSource.WEATHER));
        list.add(options.getSoundSourceOptionInstance(SoundSource.BLOCKS));
        list.add(options.getSoundSourceOptionInstance(SoundSource.HOSTILE));
        list.add(options.getSoundSourceOptionInstance(SoundSource.NEUTRAL));
        list.add(options.getSoundSourceOptionInstance(SoundSource.PLAYERS));
        list.add(options.getSoundSourceOptionInstance(SoundSource.AMBIENT));
        list.add(options.getSoundSourceOptionInstance(SoundSource.VOICE));
        return list;
    }

    public LimitlessOptions_Forge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC, "LimitlessOptions_Forge-Common.toml");
        
        if (FMLLoader.getDist().isClient()) {
            ModList.get().getModContainerById("minecraft").get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,() -> new ConfigScreenHandler.ConfigScreenFactory((minecraft, screen) -> {
                return new OpionssetterScreen(screen);
            }));
        }
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
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
            // Some client setup code
            //LOGGER.info("HELLO FROM CLIENT SETUP");
            //LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }

    }
}
