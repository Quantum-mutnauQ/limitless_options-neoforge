package quatum.limitless_options_forge.gui;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;

import net.minecraft.client.gui.screens.OptionsScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;

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