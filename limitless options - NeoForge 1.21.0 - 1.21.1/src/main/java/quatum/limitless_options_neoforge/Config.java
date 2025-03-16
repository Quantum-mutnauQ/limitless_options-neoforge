package quatum.limitless_options_neoforge;


import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@EventBusSubscriber(modid = LimitlessOptions_NeoForge.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

     private static final ModConfigSpec.BooleanValue Troll = BUILDER
            .comment("Enable oanly linux version")
            .define("onalyLinux",false);
    private static final ModConfigSpec.EnumValue<SlyderFixType> SliderFix = BUILDER
            .comment("Fix that the slider status go over the maximum")
            .defineEnum("SliderFix",SlyderFixType.FIX_CLAMP_SLIDER);
    private static final ModConfigSpec.BooleanValue RenderDistanzFix = BUILDER
            .comment("Allow render chunks over the 32 renderdistanz limit(this is a pre pre pre alpha and may brake the chunk loading)")
            .define("RenderDistanzFix",false);
    private static final ModConfigSpec.BooleanValue SetOptionsButton = BUILDER
            .comment("Shows the button in the Options gui")
            .define("SetOptionsButton",true);
    static final ModConfigSpec SPEC = BUILDER.build();

    public static boolean troll = false;
    public static SlyderFixType SliderFixValue =SlyderFixType.FIX_CLAMP_SLIDER;
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
