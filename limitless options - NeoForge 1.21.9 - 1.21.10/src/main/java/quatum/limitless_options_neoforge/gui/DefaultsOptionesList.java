package quatum.limitless_options_neoforge.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.sounds.SoundSource;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;


public class DefaultsOptionesList {

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
        list.add(options.invertMouseX());
        list.add(options.invertMouseY());
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
}
