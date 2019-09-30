package net.nerds.oysters.items;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nerds.oysters.Oysters;
import net.nerds.oysters.items.armor.PearlyArmors;
import net.nerds.oysters.items.tools.PearlyTools;

public class OysterItemManager {

    public static PearlNecklace pearlNecklace = new PearlNecklace();
    public static Item pearlyIronIngot = new Item(getGroup());
    public static Item pearlyDiamond = new Item(getGroup());

    public static void init() {
        //items
        Registry.register(Registry.ITEM, new Identifier(Oysters.MODID, "pearl_necklace"), pearlNecklace);
        Registry.register(Registry.ITEM, new Identifier(Oysters.MODID, "pearly_iron_ingot"), pearlyIronIngot);
        Registry.register(Registry.ITEM, new Identifier(Oysters.MODID, "pearly_diamond"), pearlyDiamond);

        //tools
        Registry.register(Registry.ITEM, new Identifier(Oysters.MODID, "pearly_diamond_sword"), PearlyTools.pearlyDiamondSword);
        Registry.register(Registry.ITEM, new Identifier(Oysters.MODID, "pearly_diamond_shovel"), PearlyTools.pearlyDiamondShovel);
        Registry.register(Registry.ITEM, new Identifier(Oysters.MODID, "pearly_diamond_axe"), PearlyTools.pearlyDiamondAxe);
        Registry.register(Registry.ITEM, new Identifier(Oysters.MODID, "pearly_diamond_pickaxe"), PearlyTools.pearlyDiamondPickaxe);
        Registry.register(Registry.ITEM, new Identifier(Oysters.MODID, "pearly_iron_sword"), PearlyTools.pearlyIronSword);
        Registry.register(Registry.ITEM, new Identifier(Oysters.MODID, "pearly_iron_shovel"), PearlyTools.pearlyIronShovel);
        Registry.register(Registry.ITEM, new Identifier(Oysters.MODID, "pearly_iron_axe"), PearlyTools.pearlyIronAxe);
        Registry.register(Registry.ITEM, new Identifier(Oysters.MODID, "pearly_iron_pickaxe"), PearlyTools.pearlyIronPickaxe);
        Registry.register(Registry.ITEM, new Identifier(Oysters.MODID, "pearly_shears"), PearlyTools.pearlyShears);

        //armor
        Registry.register(Registry.ITEM, new Identifier(Oysters.MODID, "pearly_iron_helmet"), PearlyArmors.pearlIronHelmet);
        Registry.register(Registry.ITEM, new Identifier(Oysters.MODID, "pearly_iron_chestplate"), PearlyArmors.pearlIronChest);
        Registry.register(Registry.ITEM, new Identifier(Oysters.MODID, "pearly_iron_leggings"), PearlyArmors.pearlIronLegs);
        Registry.register(Registry.ITEM, new Identifier(Oysters.MODID, "pearly_iron_boots"), PearlyArmors.pearlIronBoots);
        Registry.register(Registry.ITEM, new Identifier(Oysters.MODID, "pearly_diamond_helmet"), PearlyArmors.pearlDiamondHelmet);
        Registry.register(Registry.ITEM, new Identifier(Oysters.MODID, "pearly_diamond_chestplate"), PearlyArmors.pearlDiamondChest);
        Registry.register(Registry.ITEM, new Identifier(Oysters.MODID, "pearly_diamond_leggings"), PearlyArmors.pearlDiamondLegs);
        Registry.register(Registry.ITEM, new Identifier(Oysters.MODID, "pearly_diamond_boots"), PearlyArmors.pearlDiamondBoots);
    }

    public static Item.Settings getGroup() {
        return new Item.Settings().group(Oysters.oysterGroup);
    }
}
