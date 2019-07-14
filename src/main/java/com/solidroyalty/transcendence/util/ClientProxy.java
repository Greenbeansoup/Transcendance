package com.solidroyalty.transcendence.util;

import com.solidroyalty.transcendence.init.TranscendenceTileEntityTypes;
import com.solidroyalty.transcendence.screens.TranscendingTableScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ClientProxy implements IProxy {

    @Override
    public void init() {
        ScreenManager.registerFactory(TranscendenceTileEntityTypes.TRANSCENDING_TABLE_CONTAINER, TranscendingTableScreen::new);
    }

    @Override
    public World getClientWorld() {
        return Minecraft.getInstance().world;
    }

    @Override
    public PlayerEntity getClientPlayer() {
        return Minecraft.getInstance().player;
    }
}
