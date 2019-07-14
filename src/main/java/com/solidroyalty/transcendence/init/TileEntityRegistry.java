package com.solidroyalty.transcendence.init;

import com.google.common.base.Preconditions;
import com.solidroyalty.transcendence.Constants;
import com.solidroyalty.transcendence.tile_entities.TranscendingTableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(Constants.MOD_ID)
public class TileEntityRegistry {

    @SubscribeEvent
    public static void registerTileEntityTypes(@Nonnull final RegistryEvent.Register<TileEntityType<?>> event) {
        event.getRegistry().registerAll(
                setup(TileEntityType.Builder.create(TranscendingTableTileEntity::new, TranscendenceBlockTypes.TRANSCENDING_TABLE).build(null), "transcending_table")
        );
    }

    @Nonnull
    private static <T extends IForgeRegistryEntry<T>> T setup(@Nonnull final T entry, @Nonnull final String name) {
        Preconditions.checkNotNull(name, "Name to assign to entry cannot be null!");
        return setup(entry, new ResourceLocation(Constants.MOD_ID, name));
    }

    @Nonnull
    private static <T extends IForgeRegistryEntry<T>> T setup(@Nonnull final T entry, @Nonnull final ResourceLocation registryName) {
        Preconditions.checkNotNull(entry, "Entry cannot be null!");
        Preconditions.checkNotNull(registryName, "Registry name to assign to entry cannot be null!");
        entry.setRegistryName(registryName);
        return entry;
    }

}
