package quatum.limitless_options_neoforge.mixin;

import net.minecraft.server.level.ChunkLevel;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import quatum.limitless_options_neoforge.Config;

@Mixin(ChunkLevel.class)
public class ChunkLevelMixin {
    @Mutable @Shadow @Final
    public static int MAX_LEVEL;
    @Shadow @Final public static int RADIUS_AROUND_FULL_CHUNK;

    @Inject(method = "<clinit>",at = @At("TAIL"))
    private static void setMaxLevel(CallbackInfo ci){
        if(Config.RenderDistanzFixValue)
            MAX_LEVEL = Byte.MAX_VALUE - 1 + RADIUS_AROUND_FULL_CHUNK;
        else
            MAX_LEVEL = 33 + RADIUS_AROUND_FULL_CHUNK;
    }
}
