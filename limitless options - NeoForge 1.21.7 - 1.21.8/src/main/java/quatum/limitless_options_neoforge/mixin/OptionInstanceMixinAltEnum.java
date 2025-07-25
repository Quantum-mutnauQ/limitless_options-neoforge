package quatum.limitless_options_neoforge.mixin;

import net.minecraft.client.OptionInstance;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(OptionInstance.AltEnum.class)
    public class OptionInstanceMixinAltEnum<T> {
        @Inject(method = "Lnet/minecraft/client/OptionInstance$AltEnum;validateValue(Ljava/lang/Object;)Ljava/util/Optional;", at = @At("HEAD"), cancellable = true)
        void ValidateValue(T p_231570_, CallbackInfoReturnable<Optional<T>> cir) {
            cir.setReturnValue(Optional.of(p_231570_));
            cir.cancel();
        }
    }