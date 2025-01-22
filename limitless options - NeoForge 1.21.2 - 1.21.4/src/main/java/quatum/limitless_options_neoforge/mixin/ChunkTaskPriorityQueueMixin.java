package quatum.limitless_options_neoforge.mixin;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.longs.Long2ObjectLinkedOpenHashMap;
import net.minecraft.server.level.ChunkTaskPriorityQueue;
import net.minecraft.world.level.ChunkPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import quatum.limitless_options_neoforge.Config;

import java.util.List;
import java.util.Optional;

@Mixin(ChunkTaskPriorityQueue.class)
public class ChunkTaskPriorityQueueMixin<T> {
    @Shadow private volatile int topPriorityQueueIndex;
    @Shadow @Final private List<Long2ObjectLinkedOpenHashMap<List<Runnable>>> queuesPerPriority;
    @Mutable
    @Shadow @Final
    public static int PRIORITY_LEVEL_COUNT;

    @Inject(method = "submit",at = @At("HEAD"),cancellable = true)
    protected void submit(Runnable p_371364_, long p_140537_, int p_140538_, CallbackInfo ci) {
        if (p_140538_ >= PRIORITY_LEVEL_COUNT){
            ci.cancel(); return;}
    }

    @Inject(method = "resortChunkTasks",at = @At("HEAD"),cancellable = true)
    protected void resortChunkTasks(int p_140522_, ChunkPos p_140523_, int p_140524_, CallbackInfo ci) {
        if (p_140524_ >= PRIORITY_LEVEL_COUNT){
            ci.cancel(); return;}


    }
    @Inject(method = "<clinit>",at = @At(value = "HEAD"))
    private static void fix(CallbackInfo ci){
        if(Config.RenderDistanzFixValue)
        PRIORITY_LEVEL_COUNT = 35;
    }
}
