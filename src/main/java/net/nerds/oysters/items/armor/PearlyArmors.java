package net.nerds.oysters.items.armor;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Lazy;
import net.nerds.oysters.items.OysterItemManager;

import java.util.function.Supplier;

public class PearlyArmors {

    public static PearlyIronArmor pearlIronHelmet = new PearlyIronArmor(EquipmentSlot.HEAD, OysterItemManager.getGroup());
    public static PearlyIronArmor pearlIronChest = new PearlyIronArmor(EquipmentSlot.CHEST, OysterItemManager.getGroup());
    public static PearlyIronArmor pearlIronLegs = new PearlyIronArmor(EquipmentSlot.LEGS, OysterItemManager.getGroup());
    public static PearlyIronArmor pearlIronBoots = new PearlyIronArmor(EquipmentSlot.FEET, OysterItemManager.getGroup());

    public static PearlyDiamondArmor pearlDiamondHelmet = new PearlyDiamondArmor(EquipmentSlot.HEAD, OysterItemManager.getGroup());
    public static PearlyDiamondArmor pearlDiamondChest = new PearlyDiamondArmor(EquipmentSlot.CHEST, OysterItemManager.getGroup());
    public static PearlyDiamondArmor pearlDiamondLegs = new PearlyDiamondArmor(EquipmentSlot.LEGS, OysterItemManager.getGroup());
    public static PearlyDiamondArmor pearlDiamondBoots = new PearlyDiamondArmor(EquipmentSlot.FEET, OysterItemManager.getGroup());

    public enum PearlyArmorMaterial implements ArmorMaterial {
        IRON("pearly_iron_material", 20, new int[]{3, 6, 7, 8}, 20, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F, () -> {
            return Ingredient.ofItems(OysterItemManager.pearlyIronIngot);
        }),
        DIAMOND("pearly_diamond_material", 40, new int[]{4, 7, 9, 4}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 4.0F, () -> {
            return Ingredient.ofItems(OysterItemManager.pearlyDiamond);
        });

        private static final int[] BASE_DURABILITY = new int[]{13, 15, 16, 11};
        private final String name;
        private final int durabilityMultiplier;
        private final int[] protectionAmounts;
        private final int enchantability;
        private final SoundEvent equipSound;
        private final float toughness;
        private final Lazy<Ingredient> repairIngredientSupplier;

        PearlyArmorMaterial(String name, int durabilityMultiplier, int[] protecc, int ench, SoundEvent soundEvent, float tough, Supplier<Ingredient> ingredientSupplier) {
            this.name = name;
            this.durabilityMultiplier = durabilityMultiplier;
            this.protectionAmounts = protecc;
            this.enchantability = ench;
            this.equipSound = soundEvent;
            this.toughness = tough;
            this.repairIngredientSupplier = new Lazy(ingredientSupplier);
        }

        @Override
        public int getDurability(EquipmentSlot equipmentSlot_1) {
            return BASE_DURABILITY[equipmentSlot_1.getEntitySlotId()] * this.durabilityMultiplier;
        }

        @Override
        public int getProtectionAmount(EquipmentSlot equipmentSlot_1) {
            return this.protectionAmounts[equipmentSlot_1.getEntitySlotId()];
        }

        @Override
        public int getEnchantability() {
            return this.enchantability;
        }

        @Override
        public SoundEvent getEquipSound() {
            return this.equipSound;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return (Ingredient)this.repairIngredientSupplier.get();
        }

        @Override
        @Environment(EnvType.CLIENT)
        public String getName() {
            return this.name;
        }

        @Override
        public float getToughness() {
            return this.toughness;
        }
    }
}
