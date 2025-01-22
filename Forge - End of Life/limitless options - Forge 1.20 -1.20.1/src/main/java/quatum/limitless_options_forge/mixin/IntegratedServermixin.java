package quatum.limitless_options_forge.mixin;

import net.minecraft.client.server.IntegratedServer;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import quatum.limitless_options_forge.Config;

@Mixin(IntegratedServer.class)
public class IntegratedServermixin {

    @Redirect(method = "tickServer", at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(II)I"))
    private int redirectClamp(int a, int b) {
        if(Config.RenderDistanzFixValue)
            return b;
        return Math.max(a, b);
    }
}
