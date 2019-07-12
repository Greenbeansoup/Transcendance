package com.solidroyalty.transcendence.init;

import com.solidroyalty.transcendence.Constants;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(Constants.MOD_ID)
public class itemRegistry {

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> itemRegistryEvent) {
        itemRegistryEvent.getRegistry().registerAll(
                new BlockItem(TranscendenceBlockTypes.TRANSCENDING_TABLE, new Item.Properties().maxStackSize(64).group(ItemGroup.DECORATIONS)).setRegistryName(Constants.MOD_ID, "transcending_table")
        );
    }

}
