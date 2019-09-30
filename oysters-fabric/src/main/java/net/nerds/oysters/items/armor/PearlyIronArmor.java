package net.nerds.oysters.items.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;

public class PearlyIronArmor extends ArmorItem {

    public PearlyIronArmor(EquipmentSlot equipmentSlot, Settings settings) {
        super(PearlyArmors.PearlyArmorMaterial.IRON, equipmentSlot, settings);
    }
}
