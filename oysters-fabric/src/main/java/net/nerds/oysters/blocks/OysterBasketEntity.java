package net.nerds.oysters.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Direction;
import net.nerds.oysters.Oysters;
import net.nerds.oysters.Utils.OysterBreedUtility;
import net.nerds.oysters.Utils.OysterConfigValues;
import net.nerds.oysters.oysters.OysterBlockItem;
import net.nerds.oysters.oysters.OysterBreed;
import net.nerds.oysters.pearls.OysterPearl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;

public class OysterBasketEntity extends BlockEntity implements Tickable, SidedInventory {

    private int maxStorage = 47;
    public DefaultedList<ItemStack> inventory;
    private long pearlGenCounter = 0;
    private double pearlGenCheck;
    private long pearlMutateCheck;
    private long tickBreedCounter = 0;
    private float canBreedChance;

    public OysterBasketEntity(BlockEntityType blockEntityType) {
        super(blockEntityType);
        inventory = DefaultedList.ofSize(maxStorage, ItemStack.EMPTY);
        pearlGenCheck = Oysters.oystersConfig.getProperty(OysterConfigValues.BASE_BASKET_PEARL_GEN_TIME);
        pearlMutateCheck = Oysters.oystersConfig.getProperty(OysterConfigValues.BASE_BASKET_MUTATE_TIME);
        canBreedChance = Oysters.oystersConfig.getProperty(OysterConfigValues.MUTATION_CHANCE);
    }

    @Override
    public void tick() {
        if (pearlGenCounter >= getPearlGenTimer()) {
            generateOyster();
            pearlGenCounter = 0;
        }
        if (tickBreedCounter >= pearlMutateCheck) {
            attemptToBreedOysters();
            tickBreedCounter = 0;
        }
        pearlGenCounter++;
        tickBreedCounter++;
    }

    //more oysters means faster pearl generation
    private double getPearlGenTimer() {
        if(inventory.get(0).getCount() > 0) {
            //tickcheck / (count * .6) so that its not a straight cut-in-half time for each oyster added
            return pearlGenCheck / ((double)inventory.get(0).getCount() * .6);
        }
        return pearlGenCheck;
    }

    private void generateOyster() {
        if (!world.isClient && world.getBlockState(this.pos).get(Properties.WATERLOGGED)) {
            if (!inventory.get(0).isEmpty()) {
                Optional<OysterBreed> oysterBreed = OysterBreedUtility.getBreedByBlockItem(inventory.get(0).getItem());
                if(!oysterBreed.isPresent()) {
                    return;
                }
                ItemStack itemStack = new ItemStack(oysterBreed.get().getOysterPearl());
                addItemToInventory(itemStack);
            }
        }
    }

    private void addItemToInventory(ItemStack itemStack) {
        //loop through inventory looking for space. start at 2 to avoid breeding slots
        for (int i = 2; i < inventory.size(); i++) {
            if (inventory.get(i).isEmpty()) {
                inventory.set(i, itemStack);
                markDirty();
                break;
            } else if (inventory.get(i).isItemEqual(itemStack) &&
                    (inventory.get(i).getCount() + itemStack.getCount() <= itemStack.getMaxCount()) &&
                    itemStack.isStackable()) {
                inventory.set(i, new ItemStack(itemStack.getItem(), itemStack.getCount() + inventory.get(i).getCount()));
                markDirty();
                break;
            }
        }
    }

    private void attemptToBreedOysters() {
        if (!world.isClient && world.getBlockState(this.pos).get(Properties.WATERLOGGED)) {
            //there are oysters and there is more than one
            if (!inventory.get(0).isEmpty() && inventory.get(0).getCount() > 1) {
                //pray to rng gods for blessings
                if (rngForBreeding(inventory.get(0).getCount())) {
                    //get the Oyster Breed from the spawning inventory
                    Optional<OysterBreed> oysterBreed = OysterBreedUtility.getBreedByBlockItem(inventory.get(0).getItem());
                    if(!oysterBreed.isPresent()) {
                        return;
                    }
                    //get the tier of the oyster you are attempting to breed/mutate
                    OysterBreed.OysterTier breedingTier = OysterBreedUtility.getTierOfBreedForMutation(oysterBreed.get());
                    //is this attempting to mutate the oyster. And if it is, is it the right tiered oyster for that resource
                    if (!inventory.get(1).isEmpty() && OysterBreedUtility.isRightTierForBreeding(breedingTier, inventory.get(1).getItem())) {
                        //there is a shell and a resource... get the new shell of that resource
                        OysterBreed newBreed = Arrays.stream(OysterBreed.values())
                                .filter(oyster -> oyster.getResourceItem() == inventory.get(1).getItem())
                                .findFirst().get();
                        //add new oyster to basket
                        addItemToInventory(new ItemStack(newBreed.getOysterBlockItem()));
                        //remove a resource item
                        if (!inventory.get(1).isEmpty()) {
                            inventory.get(1).decrement(1);
                        }
                        //add the same breed if you are not trying to mutate
                    } else if (inventory.get(0).getCount() > 1 && inventory.get(1).isEmpty()) {
                        addItemToInventory(new ItemStack(oysterBreed.get().getOysterBlockItem()));
                    }
                } else {
                    //rng gods failed you, remove a resource item
                    if (!inventory.get(1).isEmpty()) {
                        inventory.get(1).decrement(1);
                    }
                }
            }
        }
    }

    private boolean rngForBreeding(int oysterCount) {
        float range = world.random.nextInt(100);
        //breed chance + additional oysters = ~ 4 - 10 % chance of breeding
        return (this.canBreedChance + (oysterCount - 2)) >= range;
    }

    @Override
    public void fromTag(BlockState state, CompoundTag nbt) {
        super.fromTag(state, nbt);
        inventory = DefaultedList.ofSize(maxStorage, ItemStack.EMPTY);
        Inventories.fromTag(nbt, this.inventory);
    }

    @Override
    public CompoundTag toTag(CompoundTag nbt) {
        super.toTag(nbt);
        Inventories.toTag(nbt, this.inventory);
        return nbt;
    }

    @Override
    public int size() {
        return inventory.size();
    }

    @Override
    public boolean isEmpty() {
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
    public ItemStack getStack(int i) {
        return inventory.get(i);
    }

    @Override
    public ItemStack removeStack(int i, int i1) {
        return Inventories.splitStack(this.inventory, i, i1);
    }

    @Override
    public ItemStack removeStack(int i) {
        return Inventories.removeStack(inventory, i);
    }

    @Override
    public void setStack(int i, ItemStack itemStack) {
        inventory.set(i, itemStack);
        this.markDirty();
    }

    @Override
    public boolean canPlayerUse(PlayerEntity playerEntity) {
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

    @Override
    public int[] getAvailableSlots(Direction direction) {
        int[] arr = new int[maxStorage];
        for (int i = 0; i < maxStorage; i++) {
            arr[i] = i;
        }
        return arr;
    }

    @Override
    public boolean canInsert(int i, ItemStack itemStack, Direction direction) {
        return (i == 0 && itemStack.getItem() instanceof OysterBlockItem) ||
                (i > 1 && itemStack.getItem() instanceof OysterBlockItem) ||
                (i > 1 && itemStack.getItem() instanceof OysterPearl) ||
                (i == 1 && OysterBreedUtility.isAResource(itemStack.getItem()));
    }

    @Override
    public boolean canExtract(int i, ItemStack itemStack, Direction direction) {
        if (direction == Direction.DOWN && i > 1) {
            return true;
        }
        return false;
    }
}