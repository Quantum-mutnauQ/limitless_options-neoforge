package quatum.limitless_options_forge.gui;

import com.mojang.blaze3d.platform.Monitor;
import com.mojang.blaze3d.platform.VideoMode;
import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.GraphicsStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GpuWarnlistManager;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Optional;

@OnlyIn(Dist.CLIENT)
public class OpionssetterScreen extends Screen {
    Screen lastScreen;
    Options options= Minecraft.getInstance().options;
    private ValuesList list;
    private final GpuWarnlistManager gpuWarnlistManager;
    private final int oldMipmaps;


    public OpionssetterScreen(Screen p_96806_) {
        super(Component.translatable("limitlessoptiones.screen.Options.title"));
        this.lastScreen = p_96806_;
        this.gpuWarnlistManager = Minecraft.getInstance().getGpuWarnlistManager();
        this.gpuWarnlistManager.resetWarnings();
        if (options.graphicsMode().get() == GraphicsStatus.FABULOUS) {
            this.gpuWarnlistManager.dismissWarning();
        }

        this.oldMipmaps = options.mipmapLevels().get();
    }

    protected void init() {
        this.list = new ValuesList(this.minecraft, this.width, this.height, 32, this.height - 32, 25);
        int i = -1;
        Window window = this.minecraft.getWindow();
        Monitor monitor = window.findBestMonitor();
        int j;
        if (monitor == null) {
            j = -1;
        } else {
            Optional<VideoMode> optional = window.getPreferredFullscreenVideoMode();
            j = optional.map(monitor::getVideoModeIndex).orElse(-1);
        }
        this.list.add(DefaultsOptionesList.OnScreenOptions,font);

        this.addWidget(this.list);
        this.addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, (p_280842_) -> {
            this.minecraft.options.save();
            window.changeFullscreenVideoMode();
            this.minecraft.setScreen(this.lastScreen);
        }).bounds(this.width / 2 - 100, this.height - 27, 200, 20).build());
    }

    public void removed() {
        if (this.options.mipmapLevels().get() != this.oldMipmaps) {
            this.minecraft.updateMaxMipLevel(this.options.mipmapLevels().get());
            this.minecraft.delayTextureReload();
        }

        super.removed();
    }

    public boolean mouseClicked(double p_96809_, double p_96810_, int p_96811_) {
        int i = this.options.guiScale().get();
        if (super.mouseClicked(p_96809_, p_96810_, p_96811_)) {
            if (this.options.guiScale().get() != i) {
                this.minecraft.resizeDisplay();
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean mouseScrolled(double p_278332_, double p_278334_, double p_278285_, double p_300252_) {
        if (Screen.hasControlDown()) {
            OptionInstance<Integer> $$4 = this.options.guiScale();
            int $$5 = (Integer)$$4.get() + (int)Math.signum(p_300252_);
            if ($$5 != 0) {
                $$4.set($$5);
                if ((Integer)$$4.get() == $$5) {
                    this.minecraft.resizeDisplay();
                    return true;
                }
            }

            return false;
        } else {
            return super.mouseScrolled(p_278332_, p_278334_, p_278285_, p_300252_);
        }
    }

    public void render(GuiGraphics p_282311_, int p_283219_, int p_282352_, float p_283266_) {
        this.basicListRender(p_282311_, this.list, p_283219_, p_282352_, p_283266_);
    }
    protected void basicListRender(GuiGraphics p_282011_, ValuesList p_281793_, int p_281640_, int p_281598_, float p_281558_) {
        super.render(p_282011_, p_281640_, p_281598_, p_281558_);
        p_281793_.render(p_282011_, p_281640_, p_281598_, p_281558_);
        p_282011_.drawCenteredString(this.font, this.title, this.width / 2, 20, 16777215);
    }
}
