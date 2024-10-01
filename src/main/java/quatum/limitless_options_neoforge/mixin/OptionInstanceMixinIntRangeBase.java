package quatum.limitless_options_neoforge.mixin;

import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import quatum.limitless_options_neoforge.Config;
import quatum.limitless_options_neoforge.SlyderFixType;

//@Interface(iface = "net.minecraft.client.OptionInstance$IntRangeBase")
@Mixin(targets = "net.minecraft.client.OptionInstance$IntRangeBase")
public interface OptionInstanceMixinIntRangeBase {
    @Shadow
    int minInclusive();

    @Shadow
    int maxInclusive();
    @Inject(method = "fromSliderValue(D)Ljava/lang/Integer;",at = @At("HEAD"),cancellable = true)
    default void fromSliderValue(double p_231656_, CallbackInfoReturnable<Integer> cir) {
        if (Config.SliderFixValue == SlyderFixType.FALSE)
            return;
            
        if (p_231656_ == 1.0) {
            p_231656_ = 0.9999899864196777;
        }

        cir.setReturnValue(Mth.floor(Mth.map(p_231656_, 0.0, 1.0, (double)this.minInclusive(), (double)this.maxInclusive() + 1.0)));
        cir.cancel();
    }
}
