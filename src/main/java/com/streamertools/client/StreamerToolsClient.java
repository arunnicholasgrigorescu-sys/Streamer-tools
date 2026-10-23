package com.streamertools.client;

import com.streamertools.client.screen.StreamerToolsScreen;
import com.streamertools.StreamerToolsMod;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class StreamerToolsClient implements ClientModInitializer {
    private static final KeyBinding OPEN_MENU = KeyBindingHelper.registerKeyBinding(
            new KeyBinding(
                    "key.streamer_tools.open_menu",
                    InputUtil.Type.KEYSYM,
                    GLFW.GLFW_KEY_RIGHT_SHIFT,
                    "key.category.streamer_tools"
            )
    );

    @Override
    public void onInitializeClient() {
        StreamerToolsMod.LOGGER.info("Streamer Tools client initialized");

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (OPEN_MENU.wasPressed()) {
                MinecraftClient.getInstance().setScreen(new StreamerToolsScreen());
            }
        });
    }
}
