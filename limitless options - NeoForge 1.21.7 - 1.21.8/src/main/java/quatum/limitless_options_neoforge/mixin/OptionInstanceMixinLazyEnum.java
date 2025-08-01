package quatum.limitless_options_neoforge.mixin;

import net.minecraft.client.OptionInstance;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(OptionInstance.LazyEnum.class)
    public  class OptionInstanceMixinLazyEnum<T>{
     @Inject(method = "Lnet/minecraft/client/OptionInstance$LazyEnum;validateValue(Ljava/lang/Object;)Ljava/util/Optional;", at = @At("HEAD"),cancellable = true)
    public void ValidateValue(T p_231689_, CallbackInfoReturnable<Optional<T>> cir){
        cir.setReturnValue(Optional.of(p_231689_));
        cir.cancel();
    }
    }