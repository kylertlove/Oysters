package net.nerds.oysters.items.tools;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.world.World;

public class PearlyShovel extends ShovelItem {

    private int cooldown = 60;
    private int wait = 0;

    public PearlyShovel(ToolMaterial toolMaterial, float float_1, float float_2, Settings settings) {
        super(toolMaterial, float_1, float_2, settings);
    }

    @Override
    public void inventoryTick(ItemStack itemStack, World world, Entity entity, int i, boolean bool) {
        wait++;
        if(wait > cooldown) {
            if(entity instanceof PlayerEntity) {
                boolean isInHand = ((PlayerEntity) entity).getEquippedStack(EquipmentSlot.MAINHAND).isItemEqualIgnoreDamage(itemStack);
                if(entity.isTouchingWaterOrRain() && isInHand) {
                    ((PlayerEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 90, 1, false, false));
                }
            }
            wait = 0;
        }
    }
}
