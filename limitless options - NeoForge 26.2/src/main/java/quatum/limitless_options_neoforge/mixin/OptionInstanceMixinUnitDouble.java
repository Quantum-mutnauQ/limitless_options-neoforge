package quatum.limitless_options_neoforge.mixin;

import com.mojang.serialization.Codec;
import net.minecraft.client.OptionInstance;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(OptionInstance.UnitDouble.class)
    public  class OptionInstanceMixinUnitDouble {
        @Inject(method = "Lnet/minecraft/client/OptionInstance$UnitDouble;validateValue(Ljava/lang/Double;)Ljava/util/Optional;", at = @At("HEAD"), cancellable = true)
        public void ValidateValue(Double p_231747_, CallbackInfoReturnable<Optional<Double>> cir) {
            cir.setReturnValue(Optional.of(p_231747_));
            cir.cancel();
        }
    @Inject(method = "Lnet/minecraft/client/OptionInstance$UnitDouble;codec()Lcom/mojang/serialization/Codec;",at = @At("HEAD"),cancellable = true)
    public void codec(CallbackInfoReturnable<Codec<Double>> cir) {
        cir.setReturnValue(Codec.DOUBLE.stable());
    }
    }