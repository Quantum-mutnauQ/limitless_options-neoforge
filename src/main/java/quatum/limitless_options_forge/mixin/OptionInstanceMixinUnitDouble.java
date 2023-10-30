package quatum.limitless_options_forge.mixin;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import net.minecraft.client.OptionInstance;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;
@OnlyIn(Dist.CLIENT)
@Mixin(OptionInstance.UnitDouble.class)
    public  class OptionInstanceMixinUnitDouble {
        @Inject(method = "Lnet/minecraft/client/OptionInstance$UnitDouble;validateValue(Ljava/lang/Double;)Ljava/util/Optional;", at = @At("HEAD"), cancellable = true)
        public void ValidateValue(Double p_231747_, CallbackInfoReturnable<Optional<Double>> cir) {
            cir.setReturnValue(Optional.of(p_231747_));
            cir.cancel();
        }
@Inject(method = "Lnet/minecraft/client/OptionInstance$UnitDouble;codec()Lcom/mojang/serialization/Codec;",at = @At("HEAD"),cancellable = true)
    public void codec(CallbackInfoReturnable<Codec<Double>> cir) {
        cir.setReturnValue(Codec.either(Codec.doubleRange(Double.MIN_VALUE, Double.MAX_VALUE), Codec.BOOL).xmap((p_231743_) -> {
            return p_231743_.map((p_231760_) -> {
                return p_231760_;
            }, (p_231745_) -> {
                return p_231745_ ? Double.MAX_VALUE :Double.MIN_VALUE;
            });
        }, Either::left));
    }
    }