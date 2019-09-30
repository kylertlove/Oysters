package net.nerds.oysters.items;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.nerds.oysters.Oysters;

import java.util.List;

public class PearlNecklace extends Item {

    private int cooldown = 200;
    private int wait = 0;

    public PearlNecklace() {
        super(new Item.Settings().group(Oysters.oysterGroup).maxCount(1));
    }

    @Override
    public void inventoryTick(ItemStack itemStack, World world, Entity entity, int i, boolean b) {
        wait++;
        if(wait > cooldown){
            if(entity instanceof PlayerEntity) {
                ((PlayerEntity) entity).addPotionEffect(new StatusEffectInstance(StatusEffects.CONDUIT_POWER, 600, 0, false, false));
            }
            wait = 0;
        }
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack itemStack, World world, List<Text> list, TooltipContext tooltipContext) {
        list.add(new TranslatableText("oysters.tooltip.pearl-necklace").formatted(Formatting.AQUA));
    }
}
