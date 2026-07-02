package quatum.limitless_options_neoforge.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.sounds.SoundSource;
import java.util.ArrayList;
import java.util.List;


public class DefaultsOptionesList {

    public static List<OptionInstance<?>> OnScreenOptions = addDefaults();

    public static List<OptionInstance<?>> addDefaults() {
        List<OptionInstance<?>> list = new ArrayList<>();
        Options options = Minecraft.getInstance().options;

        list.add(options.renderDistance());
        list.add(options.simulationDistance());
        list.add(options.entityDistanceScaling());
        list.add(options.framerateLimit());
        list.add(options.preferredGraphicsBackend());
        list.add(options.graphicsPreset());
        list.add(options.cloudStatus());
        list.add(options.cloudRange());
        list.add(options.weatherRadius());
        list.add(options.biomeBlendRadius());
        list.add(options.mipmapLevels());
        list.add(options.maxAnisotropyBit());
        list.add(options.textureFiltering());
        list.add(options.particles());
        list.add(options.entityShadows());
        list.add(options.ambientOcclusion());
        list.add(options.improvedTransparency());
        list.add(options.vignette());
        list.add(options.cutoutLeaves());
        list.add(options.gamma());
        list.add(options.screenEffectScale());
        list.add(options.fovEffectScale());
        list.add(options.darknessEffectScale());
        list.add(options.glintSpeed());
        list.add(options.glintStrength());
        list.add(options.damageTiltStrength());
        list.add(options.fov());
        list.add(options.guiScale());
        list.add(options.menuBackgroundBlurriness());
        list.add(options.textBackgroundOpacity());
        list.add(options.panoramaSpeed());
        list.add(options.darkMojangStudiosBackground());
        list.add(options.hideLightningFlash());
        list.add(options.hideSplashTexts());
        list.add(options.highContrast());
        list.add(options.highContrastBlockOutline());

        list.add(options.sensitivity());
        list.add(options.mouseWheelSensitivity());
        list.add(options.rawMouseInput());
        list.add(options.allowCursorChanges());
        list.add(options.invertMouseX());
        list.add(options.invertMouseY());
        list.add(options.discreteMouseScroll());
        list.add(options.autoJump());
        list.add(options.rotateWithMinecart());
        list.add(options.toggleCrouch());
        list.add(options.toggleSprint());
        list.add(options.toggleAttack());
        list.add(options.toggleUse());
        list.add(options.sprintWindow());
        list.add(options.mainHand());
        list.add(options.attackIndicator());

        list.add(options.chatVisibility());
        list.add(options.chatOpacity());
        list.add(options.chatLineSpacing());
        list.add(options.chatScale());
        list.add(options.chatWidth());
        list.add(options.chatHeightUnfocused());
        list.add(options.chatHeightFocused());
        list.add(options.chatDelay());
        list.add(options.notificationDisplayTime());
        list.add(options.chatColors());
        list.add(options.chatLinks());
        list.add(options.chatLinksPrompt());
        list.add(options.backgroundForChatOnly());
        list.add(options.hideMatchedNames());
        list.add(options.showAutosaveIndicator());
        list.add(options.onlyShowSecureChat());
        list.add(options.saveChatDrafts());
        list.add(options.operatorItemsTab());
        list.add(options.autoSuggestions());
        list.add(options.narrator());
        list.add(options.narratorHotkey());

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
        list.add(options.getSoundSourceOptionInstance(SoundSource.UI));
        list.add(options.soundDevice());
        list.add(options.directionalAudio());
        list.add(options.showSubtitles());

        list.add(options.fullscreen());
        list.add(options.exclusiveFullscreen());
        list.add(options.bobView());
        list.add(options.telemetryOptInExtra());
        list.add(options.reducedDebugInfo());
        list.add(options.inGameNotification());
        list.add(options.realmsNotifications());
        list.add(options.allowServerListing());
        list.add(options.sharePresence());
        list.add(options.forceUnicodeFont());
        list.add(options.japaneseGlyphVariants());
        list.add(options.enableVsync());
        list.add(options.inactivityFpsLimit());
        list.add(options.prioritizeChunkUpdates());
        list.add(options.chunkSectionFadeInTime());

        return list;
    }
}
