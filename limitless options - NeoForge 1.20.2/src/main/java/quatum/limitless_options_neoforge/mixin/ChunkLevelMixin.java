package quatum.limitless_options_neoforge.mixin;

import net.minecraft.server.level.ChunkLevel;
import net.minecraft.world.level.chunk.ChunkStatus;
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
    @Inject(method = "<clinit>",at = @At("TAIL"))
    private static void setMaxLevel(CallbackInfo ci){
        if(Config.RenderDistanzFixValue)
            MAX_LEVEL = Byte.MAX_VALUE - 1 + ChunkStatus.maxDistance();
        else
            MAX_LEVEL = 33 + ChunkStatus.maxDistance();
    }
}
