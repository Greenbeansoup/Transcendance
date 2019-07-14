package com.solidroyalty.transcendence.init;

import com.solidroyalty.transcendence.Constants;
import com.solidroyalty.transcendence.Transcendence;
import com.solidroyalty.transcendence.containers.TranscendingTableContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ContainerRegistry {

    @SubscribeEvent
    public static void registerContainers(final RegistryEvent.Register<ContainerType<?>> event) {
        event.getRegistry().register(IForgeContainerType.create((windowId, inv, data) -> {
            BlockPos pos = data.readBlockPos();
            return new TranscendingTableContainer(windowId, pos, Transcendence.proxy.getClientWorld(),  inv, Transcendence.proxy.getClientPlayer());
        }).setRegistryName(Constants.MOD_ID, "transcending_table"));
    }

}
