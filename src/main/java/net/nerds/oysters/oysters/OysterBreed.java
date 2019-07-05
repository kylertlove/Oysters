package net.nerds.oysters.oysters;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.nerds.oysters.Oysters;
import net.nerds.oysters.pearls.OysterPearl;

public enum OysterBreed {
    PLAIN("plain_oyster", "plain_oyster_container", "plain_pearl"),
    BONE_MEAL("bone_meal_oyster", "bone_meal_oyster_container", "bone_meal_pearl"),
    COAL("coal_oyster", "coal_oyster_container", "coal_pearl"),
    IRON("iron_oyster", "iron_oyster_container", "iron_pearl"),
    GOLD("gold_oyster", "gold_oyster_container", "gold_pearl"),
    DIAMOND("diamond_oyster", "diamond_oyster_container", "diamond_pearl"),
    LAPIS("lapis_oyster", "lapis_oyster_container", "lapis_pearl"),
    EMERALD("emerald_oyster", "emerald_oyster_container", "emerald_pearl");

    private Identifier identifier;
    private Identifier containerIdentifier;
    private OysterBlock oysterBlock;
    private Item oysterBlockItem;
    private OysterPearl oysterPearl;

    OysterBreed(String name, String containerName, String pearlName) {
        identifier = new Identifier(Oysters.MODID, name);
        containerIdentifier = new Identifier(Oysters.MODID, containerName);
        oysterBlock = new OysterBlock();
        oysterBlockItem = new BlockItem(getOysterBlock(), new Item.Settings().group(Oysters.oysterGroup).maxCount(8));
        oysterPearl = new OysterPearl(pearlName);
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
}
