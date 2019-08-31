package net.nerds.oysters.items.tools;

import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.nerds.oysters.Oysters;
import net.nerds.oysters.items.OysterItemManager;

public class PearlyTools {

    public static PearlyAxe pearlyIronAxe = new PearlyAxe(PearlyToolMaterial.PEARLY_IRON, 6.0F, -3.1F, OysterItemManager.getGroup());
    public static PearlyShovel pearlyIronShovel = new PearlyShovel(PearlyToolMaterial.PEARLY_IRON, 1.5F, -3.0F, OysterItemManager.getGroup());
    public static PearlyPickaxe pearlyIronPickaxe = new PearlyPickaxe(PearlyToolMaterial.PEARLY_IRON, 1, -2.8F, OysterItemManager.getGroup());
    public static PearlySword pearlyIronSword = new PearlySword(PearlyToolMaterial.PEARLY_IRON, 3, -2.4F, OysterItemManager.getGroup());
    public static PearlyShears pearlyShears = new PearlyShears(new Item.Settings().group(Oysters.oysterGroup).maxDamage(512));
    public static PearlyAxe pearlyDiamondAxe = new PearlyAxe(PearlyToolMaterial.PEARLY_DIAMOND, 5.0F, -3.0F, OysterItemManager.getGroup());
    public static PearlySword pearlyDiamondSword = new PearlySword(PearlyToolMaterial.PEARLY_DIAMOND, 3, -2.4F, OysterItemManager.getGroup());
    public static PearlyShovel pearlyDiamondShovel = new PearlyShovel(PearlyToolMaterial.PEARLY_DIAMOND, 1.5F, -3.0F, OysterItemManager.getGroup());
    public static PearlyPickaxe pearlyDiamondPickaxe = new PearlyPickaxe(PearlyToolMaterial.PEARLY_DIAMOND, 1, -2.8F, OysterItemManager.getGroup());

    private enum PearlyToolMaterial implements ToolMaterial {

        PEARLY_IRON(625, 6.5f, 2, 2, 20, OysterItemManager.pearlyIronIngot),
        PEARLY_DIAMOND(2048, 9.5f, 6, 3, 25, OysterItemManager.pearlyDiamond);

        int durability;
        float miningSpeed;
        float attackDamage;
        int miningLevel;
        int enchantability;
        Item ingrediant;

        PearlyToolMaterial(int durability, float miningSpeed, float attackDamage, int miningLevel, int enchantability, Item ingrediant) {
            this.durability = durability;
            this.miningSpeed = miningSpeed;
            this.attackDamage = attackDamage;
            this.miningLevel = miningLevel;
            this.enchantability = enchantability;
            this.ingrediant = ingrediant;
        }

        @Override
        public int getDurability() {
            return durability;
        }

        @Override
        public float getMiningSpeed() {
            return miningSpeed;
        }

        @Override
        public float getAttackDamage() {
            return attackDamage;
        }

        @Override
        public int getMiningLevel() {
            return miningLevel;
        }

        @Override
        public int getEnchantability() {
            return enchantability;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.ofItems(ingrediant);
        }
    }
}
