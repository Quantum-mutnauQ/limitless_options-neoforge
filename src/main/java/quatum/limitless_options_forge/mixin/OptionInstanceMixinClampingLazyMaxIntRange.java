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
import java.util.function.Function;

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
            cir.setReturnValue(Codec.INT.stable());

    }
    }