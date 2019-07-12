package com.transcendence.init;

import com.transcendence.Constants;
import com.transcendence.blocks.TranscendingTableBlock;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(Constants.MOD_ID)
public class BlockRegistry {

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> blockRegistryEvent) {
        blockRegistryEvent.getRegistry().registerAll(
                new TranscendingTableBlock().setRegistryName(Constants.MOD_ID, "transcending_table")
        );
    }
}
