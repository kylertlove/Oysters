package net.nerds.oysters.oysters;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.state.property.Properties;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.Direction;
import net.nerds.oysters.Oysters;
import net.nerds.oysters.Utils.OysterConfigValues;

import java.util.Iterator;

public class OysterEntity extends BlockEntity implements Tickable, SidedInventory {

    public DefaultedList<ItemStack> inventory;
    private OysterBreed oysterBreed;
    private long ticksElapased = 0;
    private long tickCheck;

    public OysterEntity(BlockEntityType<?> blockEntityType, OysterBreed oysterBreed) {
        super(blockEntityType);
        inventory = DefaultedList.create(1, ItemStack.EMPTY);
        this.oysterBreed = oysterBreed;
        tickCheck = Oysters.oystersConfig.getProperty(OysterConfigValues.BASE_OYSTER_TIME);
    }

    @Override
    public void tick() {
        if (tickCheck <= ticksElapased) {
            spawnPearl();
            ticksElapased = 0;
        } else {
            ticksElapased++;
        }
    }

    private void spawnPearl() {
        if (!world.isClient && world.getBlockState(this.pos).get(Properties.WATERLOGGED)) {
            ItemStack itemStack = new ItemStack(oysterBreed.getOysterPearl());
            if (inventory.get(0).isEmpty()) {
                inventory.set(0, itemStack);
                markDirty();
            } else if (inventory.get(0).isItemEqual(itemStack) &&
                    (inventory.get(0).getCount() + itemStack.getCount() <= itemStack.getMaxCount()) &&
                    itemStack.isStackable()) {
                inventory.set(0, new ItemStack(itemStack.getItem(), itemStack.getCount() + inventory.get(0).getCount()));
                markDirty();
            }
        }
    }

    @Override
    public void fromTag(CompoundTag nbt) {
        super.fromTag(nbt);
        this.inventory = DefaultedList.create(1, ItemStack.EMPTY);
        Inventories.fromTag(nbt, this.inventory);
    }

    @Override
    public CompoundTag toTag(CompoundTag nbt) {
        super.toTag(nbt);
        Inventories.toTag(nbt, this.inventory);
        return nbt;
    }

    @Override
    public int[] getInvAvailableSlots(Direction direction) {
        return new int[1];
    }

    @Override
    public boolean canInsertInvStack(int i, ItemStack itemStack, Direction direction) {
        return false;
    }

    @Override
    public boolean canExtractInvStack(int i, ItemStack itemStack, Direction direction) {
        if (direction == Direction.DOWN && i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public int getInvSize() {
        return inventory.size();
    }

    @Override
    public boolean isInvEmpty() {
        Iterator var1 = this.inventory.iterator();
        ItemStack itemStack_1;
        do {
            if (!var1.hasNext()) {
                return true;
            }
            itemStack_1 = (ItemStack) var1.next();
        } while (itemStack_1.isEmpty());
        return false;
    }

    @Override
    public ItemStack getInvStack(int i) {
        return inventory.get(i);
    }

    @Override
    public ItemStack takeInvStack(int i, int i1) {
        return Inventories.splitStack(this.inventory, i, i1);
    }

    @Override
    public ItemStack removeInvStack(int i) {
        return Inventories.removeStack(inventory, i);
    }

    @Override
    public void setInvStack(int i, ItemStack itemStack) {
        inventory.set(i, itemStack);
        this.markDirty();
    }

    @Override
    public boolean canPlayerUseInv(PlayerEntity playerEntity) {
        if (this.world.getBlockEntity(this.pos) != this) {
            return false;
        } else {
            return playerEntity.squaredDistanceTo((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }

    @Override
    public void clear() {
        inventory.clear();
    }
}
