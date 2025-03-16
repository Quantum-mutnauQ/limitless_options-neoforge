package quatum.limitless_options_neoforge.mixin;

import net.minecraft.server.level.DistanceManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import quatum.limitless_options_neoforge.Config;

@Mixin(DistanceManager.class)
public class DistanceManagerMinxin {

    @ModifyArg(
            method = "<init>",
            at = @At(value = "INVOKE",target = "Lnet/minecraft/server/level/DistanceManager$PlayerTicketTracker;<init>(Lnet/minecraft/server/level/DistanceManager;I)V"))
    private int changePlayerTicketManagerArg(int originalArg) {
        if(Config.RenderDistanzFixValue)
            return Byte.MAX_VALUE - 2;
        return originalArg;
    }
}