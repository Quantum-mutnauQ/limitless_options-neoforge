package quatum.limitless_options_neoforge.mixin;

import net.minecraft.server.level.ChunkMap;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import quatum.limitless_options_neoforge.Config;

@Mixin(ChunkMap.class)
public class ChunkMapMixin{
    @Mutable
    @Shadow @Final
    private static int MIN_VIEW_DISTANCE;
    @Mutable
    @Shadow @Final
    public static int MAX_VIEW_DISTANCE;

    @Redirect(method = "setServerViewDistance", at = @At(value = "INVOKE", target = "net/minecraft/util/Mth.clamp(III)I"))
    private int redirectClamp(int p_140168_, int min, int max) {
        if(Config.RenderDistanzFixValue)
            return Mth.clamp(p_140168_, 1, Byte.MAX_VALUE - 2);
        return Mth.clamp(p_140168_, 2, 32);
    }
    @Inject(method = "<clinit>",at = @At("TAIL"))
    private static void ViewdistancesFix(CallbackInfo ci){
        if(Config.RenderDistanzFixValue){
        MIN_VIEW_DISTANCE = 1;
        MAX_VIEW_DISTANCE = Byte.MAX_VALUE -2;
    }}
}
