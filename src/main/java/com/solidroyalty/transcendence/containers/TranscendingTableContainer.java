package com.solidroyalty.transcendence.containers;

import com.solidroyalty.transcendence.init.TranscendenceBlockTypes;
import com.solidroyalty.transcendence.init.TranscendenceTileEntityTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class TranscendingTableContainer extends Container {

    private final IInventory tableInventory = new Inventory(1) {
        /**
         * For tile entities, ensures the chunk containing the tile entity is saved to disk later - the game won't think
         * it hasn't changed and skip it.
         */
        public void markDirty() {
            super.markDirty();
            TranscendingTableContainer.this.onCraftMatrixChanged(this);
        }
    };

    private TileEntity tileEntity;
    private PlayerEntity playerEntity;
    private IItemHandler playerInventory;
    private IWorldPosCallable worldPosCallable;

    public TranscendingTableContainer(int windowId, BlockPos pos, World world, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        super(TranscendenceTileEntityTypes.TRANSCENDING_TABLE_CONTAINER, windowId);
        tileEntity = world.getTileEntity(pos);
        this.playerEntity = playerEntity;
        this.playerInventory = new InvWrapper(playerInventory);
        this.worldPosCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());

        this.addSlot(new Slot(this.tableInventory, 0, 25, 15) {//Position the container inventory slots
            /**
             * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
             */
            public boolean isItemValid(ItemStack stack) {
                return true;
            }

            /**
             * Returns the maximum stack size for a given slot (usually the same as getInventoryStackLimit(), but 1 in the
             * case of armor slots)
             */
            public int getSlotStackLimit() {
                return 1;
            }
        });
        this.addSlot(new Slot(this.tableInventory, 1, 25, 54) {// id, x, y: Position the container inventory slots

            /**
             * Returns the maximum stack size for a given slot (usually the same as getInventoryStackLimit(), but 1 in the
             * case of armor slots)
             */
            public int getSlotStackLimit() {
                return 1;
            }

            /**
             * This slot should never allow the player to put stuff in it.
             * I sure hope this still allows the block to put stuff in it.
             */
            public boolean isItemValid(ItemStack stack) { return false; }
        });
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(worldPosCallable, this.playerEntity, TranscendenceBlockTypes.TRANSCENDING_TABLE);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {//Shift click handling

        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index == 0) {
                if (!this.mergeItemStack(itemstack1, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (index == 1) {
                if (!this.mergeItemStack(itemstack1, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (this.inventorySlots.get(0).getHasStack() || !this.inventorySlots.get(0).isItemValid(itemstack1)) {
                    return ItemStack.EMPTY;
                }

                if (itemstack1.hasTag()) { // Forge: Fix MC-17431
                    ((Slot)this.inventorySlots.get(0)).putStack(itemstack1.split(1));
                } else if (!itemstack1.isEmpty()) {
                    this.inventorySlots.get(0).putStack(new ItemStack(itemstack1.getItem()));
                    itemstack1.shrink(1);
                }
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }

    @Override
    public void onContainerClosed(PlayerEntity playerIn) {
        super.onContainerClosed(playerIn);
        this.worldPosCallable.consume((var1, var2) -> {//TODO: find out what var1 and var2 actually are and maybe name them better
            this.clearContainer(playerIn, playerIn.world, this.tableInventory);
        });
    }
}
