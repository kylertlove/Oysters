package net.nerds.oysters.items.tools;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.tag.BlockTags;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class PearlyShears extends ShearsItem {

    public PearlyShears(Settings settings) {
        super(settings);
    }

    @SuppressWarnings("SimplifiableConditionalExpression")
    @Override
    public boolean postMine(ItemStack itemStack, World world, BlockState blockState, BlockPos blockPos, LivingEntity livingEntity) {
        if (!world.isClient) {
            itemStack.damage(1, livingEntity, (entity) -> entity.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        }
        Block block = blockState.getBlock();
            if(block == Blocks.TALL_SEAGRASS) {
                ItemScatterer.spawn(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(), new ItemStack(Blocks.SEAGRASS, 2));
            }
            if(     (block.matches(BlockTags.CORAL_BLOCKS) || block.matches(BlockTags.CORAL_PLANTS)
                    || block == Blocks.SEAGRASS || block == Blocks.TALL_SEAGRASS
                    || block.matches(BlockTags.WOOL) || block.matches(BlockTags.LEAVES)
                    || block.matches(BlockTags.CORALS) || block.matches(BlockTags.WALL_CORALS))) {
                ItemScatterer.spawn(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(), new ItemStack(block));
                return true;
            }
        return true;
    }

    @Override
    public float getMiningSpeed(ItemStack itemStack, BlockState blockState) {
        Block block = blockState.getBlock();
        if(block.matches(BlockTags.CORAL_BLOCKS)) {
            return 100.0f;
        }
        if (block != Blocks.COBWEB && !blockState.matches(BlockTags.LEAVES)
                && !blockState.matches(BlockTags.WALL_CORALS) && !blockState.matches(BlockTags.CORALS)
                && !blockState.matches(BlockTags.CORAL_PLANTS)) {
            return (block.matches(BlockTags.WOOL)) ? 8.0F : super.getMiningSpeed(itemStack, blockState);
        } else {
            return 15.0F;
        }
    }

    @Override
    public boolean isEnchantable(ItemStack itemStack_1) {
        return false;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack itemStack, World world, List<Text> list, TooltipContext tooltipContext) {
        list.add(new TranslatableText("oysters.tooltip.shears").formatted(Formatting.AQUA));
    }
}
