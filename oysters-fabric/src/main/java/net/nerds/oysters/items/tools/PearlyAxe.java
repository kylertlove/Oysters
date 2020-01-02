package net.nerds.oysters.items.tools;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.world.World;

public class PearlyAxe extends AxeItem {

    private int cooldown = 60;
    private int wait = 0;

    public PearlyAxe(ToolMaterial toolMaterial_1, float float_1, float float_2, Settings item$Settings_1) {
        super(toolMaterial_1, float_1, float_2, item$Settings_1);
    }

    @Override
    public void inventoryTick(ItemStack itemStack, World world, Entity entity, int i, boolean bool) {
        wait++;
        if(wait > cooldown) {
            if(entity instanceof PlayerEntity) {
                boolean isInHand = ((PlayerEntity) entity).getEquippedStack(EquipmentSlot.MAINHAND).isItemEqualIgnoreDamage(itemStack);
                if(entity.isInsideWaterOrRain() && isInHand) {
                    ((PlayerEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 90, 1, false, false));
                }
            }
            wait = 0;
        }
    }
}
