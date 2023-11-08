package quatum.limitless_options_forge.mixin;

import net.minecraft.world.level.chunk.ChunkStatus;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.server.level.ChunkLevel.class)
public class ChunkLevelMixin {
    @Mutable
    @Shadow() @Final
    private static int MAX_LEVEL;
    /*

    @Inject(method = "generationStatus", at = @At("HEAD"),cancellable = true)
    private static void generationStatus(int p_287738_, CallbackInfoReturnable<ChunkStatus> cir){
        cir.setReturnValue(p_287738_ < (Byte.MAX_VALUE - 1) ? ChunkStatus.FULL : ChunkStatus.getStatusAroundFullChunk(p_287738_ - (Byte.MAX_VALUE - 1)));
    }
    @Inject(method = "byStatus(Lnet/minecraft/world/level/chunk/ChunkStatus;)I", at = @At("HEAD"),cancellable = true)
    private static void byStatus(ChunkStatus p_287771_, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(Byte.MAX_VALUE - 1 + ChunkStatus.getDistance(p_287771_));
    }
    @Inject(method = "fullStatus", at = @At("HEAD"),cancellable = true)
    private static void fullStatus(int p_287750_, CallbackInfoReturnable<FullChunkStatus> cir) {
        if (p_287750_ <= Byte.MAX_VALUE - 3) {
            cir.setReturnValue(FullChunkStatus.ENTITY_TICKING);
        } else if (p_287750_ <= Byte.MAX_VALUE - 2) {
            cir.setReturnValue(FullChunkStatus.BLOCK_TICKING);
        } else {
            cir.setReturnValue(p_287750_ <= (Byte.MAX_VALUE - 1) ? FullChunkStatus.FULL : FullChunkStatus.INACCESSIBLE);
        }
    }
    @Inject(method = "byStatus(Lnet/minecraft/server/level/FullChunkStatus;)I", at = @At("HEAD"),cancellable = true)
    private static void byStatus(FullChunkStatus p_287601_, CallbackInfoReturnable<Integer> cir) {
        int i;
        switch (p_287601_) {
            case INACCESSIBLE:
                i = MAX_LEVEL;
                break;
            case FULL:
                i = Byte.MAX_VALUE - 1;
                break;
            case BLOCK_TICKING:
                i = Byte.MAX_VALUE - 2;
                break;
            case ENTITY_TICKING:
                i = Byte.MAX_VALUE - 3;
                break;
            default:
                throw new IncompatibleClassChangeError();
        }

        cir.setReturnValue(i);
    }
    @Inject(method = "isEntityTicking", at = @At("HEAD"),cancellable = true)
    private static void isEntityTicking(int p_287767_, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(p_287767_ <= Byte.MAX_VALUE - 3);
    }

    @Inject(method = "isBlockTicking", at = @At("HEAD"),cancellable = true)
    private static void isBlockTicking(int p_287696_, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(p_287696_ <= Byte.MAX_VALUE - 2);
    }*/
    @Inject(method = "<clinit>",at = @At(value = "TAIL"))
    private static void fix(CallbackInfo ci){
        MAX_LEVEL = Byte.MAX_VALUE - 1 + ChunkStatus.maxDistance();
    }
}
