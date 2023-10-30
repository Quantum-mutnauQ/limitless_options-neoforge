package quatum.limitless_options_forge.mixin;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import net.minecraft.client.OptionInstance;
import net.minecraft.util.ExtraCodecs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;
@OnlyIn(Dist.CLIENT)
@Mixin(OptionInstance.ClampingLazyMaxIntRange.class)
public class OptionInstanceMixinClampingLazyMaxIntRange {
        @Inject(method = "Lnet/minecraft/client/OptionInstance$ClampingLazyMaxIntRange;validateValue(Ljava/lang/Integer;)Ljava/util/Optional;",at = @At("HEAD"), cancellable = true)
        void ValidateValue(Integer p_231590_, CallbackInfoReturnable<Optional<Integer>> cir){
            cir.setReturnValue(Optional.of(p_231590_));
            cir.cancel();
        }
        @Inject(method = "Lnet/minecraft/client/OptionInstance$ClampingLazyMaxIntRange;codec()Lcom/mojang/serialization/Codec;",at = @At("HEAD"),cancellable = true)
    public void codec(CallbackInfoReturnable<Codec<Integer>> cir) {
            cir.setReturnValue( ExtraCodecs.validate(Codec.INT, (p_276098_) -> {
            int i = Integer.MAX_VALUE;
            return p_276098_.compareTo(Integer.MIN_VALUE) >= 0 && p_276098_.compareTo(i) <= 0 ? DataResult.success(p_276098_) : DataResult.error(() -> {
                return "Value " + p_276098_ + " outside of range [" + Integer.MIN_VALUE + ":" + i + "]";
            }, p_276098_);
        }));
    }
    }