package net.nerds.oysters.oysters;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.nerds.oysters.Oysters;
import net.nerds.oysters.pearls.OysterPearl;

public enum OysterBreed {
    PLAIN("plain_oyster", "plain_oyster_container", "plain_pearl", null),
    BONE_MEAL("bone_meal_oyster", "bone_meal_oyster_container", "bone_meal_pearl", Items.BONE_MEAL),
    COAL("coal_oyster", "coal_oyster_container", "coal_pearl", Items.COAL),
    IRON("iron_oyster", "iron_oyster_container", "iron_pearl", Items.IRON_INGOT),
    GOLD("gold_oyster", "gold_oyster_container", "gold_pearl", Items.GOLD_INGOT),
    DIAMOND("diamond_oyster", "diamond_oyster_container", "diamond_pearl", Items.DIAMOND),
    LAPIS("lapis_oyster", "lapis_oyster_container", "lapis_pearl", Items.LAPIS_LAZULI),
    EMERALD("emerald_oyster", "emerald_oyster_container", "emerald_pearl", Items.EMERALD);

    private Identifier identifier;
    private Identifier containerIdentifier;
    private OysterBlock oysterBlock;
    private OysterBlockItem oysterBlockItem;
    private OysterPearl oysterPearl;
    private Item resourceItem;

    OysterBreed(String name, String containerName, String pearlName, Item resourceItem) {
        identifier = new Identifier(Oysters.MODID, name);
        containerIdentifier = new Identifier(Oysters.MODID, containerName);
        oysterBlock = new OysterBlock();
        oysterBlockItem = new OysterBlockItem(getOysterBlock(), new Item.Settings().group(Oysters.oysterGroup).maxCount(8));
        oysterPearl = new OysterPearl(pearlName);
        this.resourceItem = resourceItem;
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
}
