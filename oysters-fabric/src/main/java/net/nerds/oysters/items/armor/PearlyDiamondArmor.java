package net.nerds.oysters.items.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;

public class PearlyDiamondArmor extends ArmorItem {

    public PearlyDiamondArmor(EquipmentSlot equipmentSlot, Settings settings) {
        super(PearlyArmors.PearlyArmorMaterial.DIAMOND, equipmentSlot, settings);
    }
}
