package quatum.limitless_options_forge;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = LimitlessOptions_Forge.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

     private static final ForgeConfigSpec.BooleanValue Troll = BUILDER
            .comment("Enable oanly linux version")
            .define("onalyLinux",false);
    private static final ForgeConfigSpec.BooleanValue SliderFix = BUILDER
            .comment("Fix that the slider status go over the maximum")
            .define("SliderFix",true);
    private static final ForgeConfigSpec.BooleanValue RenderDistanzFix = BUILDER
            .comment("Allow render chunks over the 32 renderdistanz limit(this is a pre pre pre alpha and may brake the chunk loading)")
            .define("RenderDistanzFix",false);
    private static final ForgeConfigSpec.BooleanValue SetOptionsButton = BUILDER
            .comment("Shows the button in the Options gui")
            .define("SetOptionsButton",true);
    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static boolean troll = false;
    public static boolean SliderFixValue = true;
    public static boolean RenderDistanzFixValue = false;
    public static boolean SetOptionsButtonValue = true;

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void configload(final ModConfigEvent event){
        troll = Troll.get();
        SliderFixValue =SliderFix.get();
        RenderDistanzFixValue=RenderDistanzFix.get();
        SetOptionsButtonValue=SetOptionsButton.get();
    }


}
