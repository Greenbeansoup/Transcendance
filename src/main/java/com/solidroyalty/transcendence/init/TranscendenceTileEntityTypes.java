package com.solidroyalty.transcendence.init;

import com.solidroyalty.transcendence.Constants;
import com.solidroyalty.transcendence.containers.TranscendingTableContainer;
import com.solidroyalty.transcendence.tile_entities.TranscendingTableTileEntity;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Constants.MOD_ID)
public class TranscendenceTileEntityTypes {



    @ObjectHolder(Constants.MOD_ID + ":transcending_table")
    public static TileEntityType<TranscendingTableTileEntity> TRANSCENDING_TABLE_TILE_ENTITY;

    @ObjectHolder(Constants.MOD_ID + ":transcending_table")
    public static ContainerType<TranscendingTableContainer> TRANSCENDING_TABLE_CONTAINER;
}
