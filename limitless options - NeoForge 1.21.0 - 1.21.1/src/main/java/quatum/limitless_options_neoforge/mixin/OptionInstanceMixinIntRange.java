package quatum.limitless_options_neoforge.mixin;

import com.mojang.serialization.Codec;
import net.minecraft.client.OptionInstance;

import net.minecraft.client.Options;
import net.minecraft.client.gui.components.OptionsList;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;
@OnlyIn(Dist.CLIENT)
@Mixin(OptionInstance.IntRange.class)
public abstract class OptionInstanceMixinIntRange {
    @Shadow public abstract Optional<Integer> validateValue(Integer p_231645_);

    @Inject(method = "Lnet/minecraft/client/OptionInstance$IntRange;validateValue(Ljava/lang/Integer;)Ljava/util/Optional;", at = @At("HEAD"), cancellable = true)
        public void ValidateValue(Integer p_231645_, CallbackInfoReturnable<Optional<Integer>> cir) {
            cir.setReturnValue(Optional.of(p_231645_));

            cir.cancel();

        }
        @Inject(method = "Lnet/minecraft/client/OptionInstance$IntRange;codec()Lcom/mojang/serialization/Codec;",at = @At("HEAD"),cancellable = true)
        public void codec(CallbackInfoReturnable<Codec<Integer>> cir) {
            cir.setReturnValue(Codec.INT.stable());
            cir.cancel();
        }
    }