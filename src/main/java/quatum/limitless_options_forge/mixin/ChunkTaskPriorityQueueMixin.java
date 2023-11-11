package quatum.limitless_options_forge.mixin;

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

import java.util.List;
import java.util.Optional;

@Mixin(ChunkTaskPriorityQueue.class)
public class ChunkTaskPriorityQueueMixin<T> {
    @Mutable
    @Shadow @Final
    public static int PRIORITY_LEVEL_COUNT;
    @Shadow
    private volatile int firstQueue;
    @Shadow @Final
    private List<Long2ObjectLinkedOpenHashMap<List<Optional<T>>>> taskQueue;

    @Inject(method = "submit",at = @At("HEAD"),cancellable = true)
    protected void submit(Optional<T> p_140536_, long p_140537_, int p_140538_, CallbackInfo ci) {
        if (p_140538_ >= PRIORITY_LEVEL_COUNT){
            ci.cancel(); return;}
        this.taskQueue.get(p_140538_).computeIfAbsent(p_140537_, (p_140545_) -> {
            return Lists.newArrayList();
        }).add(p_140536_);
        this.firstQueue = Math.min(this.firstQueue, p_140538_);
        ci.cancel();
    }

    @Inject(method = "resortChunkTasks",at = @At("HEAD"),cancellable = true)
    protected void resortChunkTasks(int p_140522_, ChunkPos p_140523_, int p_140524_, CallbackInfo ci) {
        if (p_140524_ >= PRIORITY_LEVEL_COUNT){
            ci.cancel(); return;}
        if (p_140522_ < PRIORITY_LEVEL_COUNT) {
            Long2ObjectLinkedOpenHashMap<List<Optional<T>>> long2objectlinkedopenhashmap = this.taskQueue.get(p_140522_);
            List<Optional<T>> list = long2objectlinkedopenhashmap.remove(p_140523_.toLong());
            if (p_140522_ == this.firstQueue) {
                while (this.hasWork() && this.taskQueue.get(this.firstQueue).isEmpty()) {
                    ++this.firstQueue;
                }
            }

            if (list != null && !list.isEmpty()) {
                this.taskQueue.get(p_140524_).computeIfAbsent(p_140523_.toLong(), (p_140547_) -> {
                    return Lists.newArrayList();
                }).addAll(list);
                this.firstQueue = Math.min(this.firstQueue, p_140524_);
            }

        }
    }
    @Inject(method = "<clinit>",at = @At(value = "HEAD"))
    private static void fix(CallbackInfo ci){
        PRIORITY_LEVEL_COUNT = 35;
    }
        @Shadow
        public boolean hasWork(){throw new AssertionError();}
}
