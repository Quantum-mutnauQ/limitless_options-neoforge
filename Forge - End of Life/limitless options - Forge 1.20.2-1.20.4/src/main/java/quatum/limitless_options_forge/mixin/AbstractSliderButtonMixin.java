package quatum.limitless_options_forge.mixin;

import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import quatum.limitless_options_forge.Config;

@Mixin(AbstractSliderButton.class)
public abstract class AbstractSliderButtonMixin{
    byte clicks=0;
    long time=0;
    double lastX=0;
    double lastY=0;
    @Shadow protected abstract void setValueFromMouse(double p_93586_);

    @Shadow protected double value;

    @Shadow protected abstract void setValue(double p_93612_);

    @Shadow protected abstract void applyValue();

    @Shadow protected abstract void updateMessage();

    int x=0;
    int width =0;
    @ModifyArg(method = "renderWidget",at = @At(value = "INVOKE",target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Lnet/minecraft/resources/ResourceLocation;IIII)V",ordinal = 0),index = 1)
    public int setX(int p_281513_){
            x=p_281513_;
            return p_281513_;
    }
    @ModifyArg(method = "renderWidget",at = @At(value = "INVOKE",target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Lnet/minecraft/resources/ResourceLocation;IIII)V",ordinal = 0),index = 3)
    public int setWidth(int p_281513_){
        width=p_281513_;
        return p_281513_;
    }

@ModifyArg(method = "renderWidget",at = @At(value = "INVOKE",target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Lnet/minecraft/resources/ResourceLocation;IIII)V",ordinal = 1),index = 1)
    public int fixOutOfRange(int p_281513_){
    if (Config.SliderFixValue == true)
        return Mth.clamp(p_281513_,x, x+width-8);
    else
        return p_281513_;
    }
}
