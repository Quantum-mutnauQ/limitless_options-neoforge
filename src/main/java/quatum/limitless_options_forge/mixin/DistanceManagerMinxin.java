package quatum.limitless_options_forge.mixin;

import net.minecraft.server.level.DistanceManager;
import net.minecraft.server.level.FullChunkStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(DistanceManager.class)
public class DistanceManagerMinxin {
    @ModifyArg(
            method = "<init>",
            at = @At(value = "INVOKE",target = "Lnet/minecraft/server/level/DistanceManager$PlayerTicketTracker;<init>(Lnet/minecraft/server/level/DistanceManager;I)V"))
    private int changePlayerTicketManagerArg(int originalArg) {
        // Hier kannst du das Argument ändern, bevor es an den Konstruktor übergeben wird
        return Byte.MAX_VALUE -2;
    }
}