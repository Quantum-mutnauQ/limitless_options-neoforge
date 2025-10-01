package quatum.limitless_options_neoforge.mixin;

import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import quatum.limitless_options_neoforge.Config;
import quatum.limitless_options_neoforge.SlyderFixType;

@Mixin(AbstractSliderButton.class)
public abstract class AbstractSliderButtonMixin{
    @Shadow protected double value;

    @Shadow protected abstract void applyValue();

    @Shadow protected abstract void updateMessage();

    int x=0;
    int width =0;
    @ModifyArg(method = "renderWidget",at = @At(value = "INVOKE",target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/ResourceLocation;IIIII)V",ordinal = 0),index = 2)
    public int setX(int p_281513_){
        x=p_281513_;
        return p_281513_;
    }
    @ModifyArg(method = "renderWidget",at = @At(value = "INVOKE",target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/ResourceLocation;IIIII)V",ordinal = 0),index = 4)
    public int setWidth(int p_281513_){
        width=p_281513_;
        return p_281513_;
    }

    @ModifyArg(method = "renderWidget",at = @At(value = "INVOKE",target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/ResourceLocation;IIIII)V",ordinal = 1),index = 2)
    public int fixOutOfRange(int p_281513_){
        if (Config.SliderFixValue == SlyderFixType.FIX_CLAMP_SLIDER|| Config.SliderFixValue == SlyderFixType.FIX_CLAMP_AND_OVERSHOOT_SLIDER)
            return Mth.clamp(p_281513_,x, x+width-8);
        else
            return p_281513_;
    }
    @Inject(method = "setValue",at = @At("HEAD"),cancellable = true)
    private void setVariableFix(double p_93612_, CallbackInfo ci){
        double d0 = this.value;

        if(Config.SliderFixValue == SlyderFixType.FIX_OVERSHOOT_SLIDER || Config.SliderFixValue == SlyderFixType.FIX_CLAMP_AND_OVERSHOOT_SLIDER)
            this.value=p_93612_;
        else
            this.value = Mth.clamp(p_93612_, 0.0, 1.0);
        if (d0 != this.value) {
            this.applyValue();
        }

        this.updateMessage();
        ci.cancel();
    }
}
