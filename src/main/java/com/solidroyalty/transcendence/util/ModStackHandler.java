package com.solidroyalty.transcendence.util;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class ModStackHandler extends ItemStackHandler {

    public ModStackHandler(int size) {
        super(size);
    }

    @Override
    protected int getStackLimit(int slot, @Nonnull ItemStack stack) {
        return 1;
    }

    @Override
    public int getSlotLimit(int slot) {
        return 1;
    }
}
