package quatum.limitless_options_forge.mixin;

import net.minecraft.client.gui.screens.OptionsScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import quatum.limitless_options_forge.Config;
import quatum.limitless_options_forge.gui.MinecraftOptionenButtons;

@OnlyIn(Dist.CLIENT)
@Mixin(OptionsScreen.class)
public class OptionsScreenMixin extends Screen {
    protected OptionsScreenMixin(Component p_96550_) {
        super(p_96550_);
    }

    @Inject(method = "init",at = @At("HEAD"))
    private void init(CallbackInfo ci){
        if(Config.SetOptionsButtonValue)
            this.addRenderableWidget(MinecraftOptionenButtons.createSet());

    }
}
