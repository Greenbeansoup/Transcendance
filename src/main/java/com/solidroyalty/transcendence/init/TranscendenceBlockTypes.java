package com.solidroyalty.transcendence.init;

import com.solidroyalty.transcendence.Constants;
import com.solidroyalty.transcendence.blocks.TranscendingTableBlock;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Constants.MOD_ID)
public class TranscendenceBlockTypes {

    @ObjectHolder(Constants.MOD_ID + ":transcending_table")
    public static TranscendingTableBlock TRANSCENDING_TABLE;
}
