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

     private static final ForgeConfigSpec.IntValue Max_Int = BUILDER
            .comment("Limit the max Integer value")
            .defineInRange("MaxInt", Integer.MAX_VALUE, 1, Integer.MAX_VALUE);
     private static final ForgeConfigSpec.IntValue Min_Int = BUILDER
            .comment("Limit the min Integer value")
            .defineInRange("MinInt", Integer.MIN_VALUE, Integer.MIN_VALUE, -1);
    private static final ForgeConfigSpec.DoubleValue Max_Double = BUILDER
            .comment("Limit the max Double value")
            .defineInRange("MaxDouble", Double.MAX_VALUE, 1, Double.MAX_VALUE);
    private static final ForgeConfigSpec.DoubleValue Min_Double = BUILDER
            .comment("Limit the min Double value")
            .defineInRange("MinDouble", 0 - Double.MAX_VALUE, 0 - Double.MAX_VALUE, -1);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static int max_int;
    public static int min_int;
    public static double max_double;
    public static double min_double;

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void configload(ModConfigEvent.Loading event){
        max_int = Max_Int.get();
        min_int = Min_Int.get();
        max_double = Max_Double.get();
        min_double = Min_Double.get();
    }
}
