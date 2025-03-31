package quatum.limitless_options_neoforge.gui;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class MinecraftOptionenButtons {


	public static Button createSet(){
		Button button = Button.builder(Component.translatable("limitlessoptiones.screen.Options.button"), p_93751_ -> onPress()).pos(3,3).size(70,15).build();
		button.active=true;
		button.visible=true;
		return button;
	}
	private static void onPress(){
	Minecraft.getInstance().setScreen(new OpionssetterScreen(Minecraft.getInstance().screen));

	}

}
