package net.nerds.oysters.oysters;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.nerds.oysters.Oysters;
import net.nerds.oysters.pearls.OysterPearl;
import net.nerds.oysters.pearls.OysterPearlManager;

public enum OysterBreed {
    BLEMISHED("blemished_oyster", "blemished_oyster_container", "blemished_pearl", null, null),
    CLEAN("clean_oyster", "clean_oyster_container", "clean_pearl", OysterPearlManager.CLEAN_PEARL, null),
    FLAWLESS("flawless_oyster", "flawless_oyster_container", "flawless_pearl", OysterPearlManager.FLAWLESS_PEARL, null),
    BONE_MEAL("bone_meal_oyster", "bone_meal_oyster_container", "bone_meal_pearl", Items.BONE_MEAL, OysterTier.BLEMISHED),
    COAL("coal_oyster", "coal_oyster_container", "coal_pearl", Items.COAL, OysterTier.BLEMISHED),
    WOOD("wood_oyster", "wood_oyster_container", "wood_pearl", Items.OAK_LOG, OysterTier.BLEMISHED),
    DIRT("dirt_oyster", "dirt_oyster_container", "dirt_pearl", Items.DIRT, OysterTier.BLEMISHED),
    STONE("stone_oyster", "stone_oyster_container", "stone_pearl", Items.STONE, OysterTier.BLEMISHED),
    IRON("iron_oyster", "iron_oyster_container", "iron_pearl", Items.IRON_INGOT, OysterTier.CLEAN),
    GOLD("gold_oyster", "gold_oyster_container", "gold_pearl", Items.GOLD_INGOT, OysterTier.FLAWLESS),
    DIAMOND("diamond_oyster", "diamond_oyster_container", "diamond_pearl", Items.DIAMOND, OysterTier.FLAWLESS),
    LAPIS("lapis_oyster", "lapis_oyster_container", "lapis_pearl", Items.LAPIS_LAZULI, OysterTier.CLEAN),
    EMERALD("emerald_oyster", "emerald_oyster_container", "emerald_pearl", Items.EMERALD, OysterTier.FLAWLESS),
    REDSTONE("redstone_oyster", "redstone_oyster_container", "redstone_pearl", Items.REDSTONE, OysterTier.CLEAN),
    GLOWSTONE("glowstone_oyster", "glowstone_oyster_container", "glowstone_pearl", Items.GLOWSTONE_DUST, OysterTier.CLEAN),
    ENDER_PEARL("ender_pearl_oyster", "ender_pearl_oyster_container", "ender_pearl_pearl", Items.ENDER_PEARL, OysterTier.FLAWLESS);

    private Identifier identifier;
    private Identifier containerIdentifier;
    private OysterBlock oysterBlock;
    private OysterBlockItem oysterBlockItem;
    private OysterPearl oysterPearl;
    private Item resourceItem;
    private OysterBreed.OysterTier oysterTier;

    OysterBreed(String name, String containerName, String pearlName, Item resourceItem, OysterBreed.OysterTier tier) {
        identifier = new Identifier(Oysters.MODID, name);
        containerIdentifier = new Identifier(Oysters.MODID, containerName);
        oysterBlock = new OysterBlock();
        oysterBlockItem = new OysterBlockItem(getOysterBlock(), new Item.Settings().group(Oysters.oysterGroup).maxCount(8), tier);
        oysterPearl = name.equalsIgnoreCase("clean_oyster") ? OysterPearlManager.CLEAN_PEARL :
                name.equalsIgnoreCase("flawless_oyster") ? OysterPearlManager.FLAWLESS_PEARL : new OysterPearl(pearlName);
        this.resourceItem = resourceItem;
        this.oysterTier = tier;
    }

    public String getName() { return identifier.getPath(); }

    public Identifier getIdentifier() {
        return identifier;
    }

    public OysterBlock getOysterBlock() {
        return oysterBlock;
    }

    public Identifier getContainerIdentifier() {
        return containerIdentifier;
    }

    public Item getOysterBlockItem() { return oysterBlockItem; }

    public OysterPearl getOysterPearl() {
        return oysterPearl;
    }

    public Item getResourceItem() {
        return this.resourceItem;
    }

    public OysterBreed.OysterTier getOysterTier() {
        return this.oysterTier;
    }

    /**
     * The tier at which the resource oyster can be mutated with
     */
    public enum OysterTier {
        BLEMISHED, CLEAN, FLAWLESS
    }
}
