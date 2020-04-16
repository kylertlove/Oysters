package net.nerds.oysters.items.tools;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.nerds.oysters.items.armor.PearlyArmors;

public class PearlySword extends SwordItem {

    private int cooldown = 60;
    private int wait = 0;
    private ToolMaterial toolMaterial;
    private int riptideCooldown = 0;

    public PearlySword(ToolMaterial toolMaterial_1, int int_1, float float_1, Settings item$Settings_1) {
        super(toolMaterial_1, int_1, float_1, item$Settings_1);
        this.toolMaterial = toolMaterial_1;
    }

    @Override
    public void inventoryTick(ItemStack itemStack, World world, Entity entity, int i, boolean bool) {
        wait++;
        this.riptideCooldown++;
        if(wait > cooldown) {
            if(entity instanceof PlayerEntity) {
                boolean isInHand = ((PlayerEntity) entity).getEquippedStack(EquipmentSlot.MAINHAND).isItemEqualIgnoreDamage(itemStack);
                if(entity.isTouchingWaterOrRain() && isInHand) {
                    ((PlayerEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 90, 1, false, false));
                }
            }
            wait = 0;
        }
    }

    /**
     * If player is wearing all pearly diamond armor, then they can activate Riptide launch
     * @param world
     * @param playerEntity
     * @param hand
     * @return
     */
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        if(playerEntity.isTouchingWater() && this.toolMaterial.equals(PearlyTools.PearlyToolMaterial.PEARLY_DIAMOND) && riptideCooldown > 60) {
            ItemStack head = playerEntity.getEquippedStack(EquipmentSlot.HEAD);
            ItemStack chest = playerEntity.getEquippedStack(EquipmentSlot.CHEST);
            ItemStack legs = playerEntity.getEquippedStack(EquipmentSlot.LEGS);
            ItemStack feet = playerEntity.getEquippedStack(EquipmentSlot.FEET);
            if(
                    head.getItem() == PearlyArmors.pearlDiamondHelmet &&
                    chest.getItem() == PearlyArmors.pearlDiamondChest &&
                    legs.getItem() == PearlyArmors.pearlDiamondLegs &&
                    feet.getItem() == PearlyArmors.pearlDiamondBoots
              ) {
                launch(playerEntity, world);
                this.riptideCooldown = 0;
            }
        }
        return new TypedActionResult(ActionResult.PASS, playerEntity.getStackInHand(hand));
    }

    /**
     * Riptide launch
     * @param playerEntity
     * @param world
     */
    private void launch(PlayerEntity playerEntity, World world) {
        float float_1 = playerEntity.yaw;
        float float_2 = playerEntity.pitch;
        float float_3 = -MathHelper.sin(float_1 * 0.017453292F) * MathHelper.cos(float_2 * 0.017453292F);
        float float_4 = -MathHelper.sin(float_2 * 0.017453292F);
        float float_5 = MathHelper.cos(float_1 * 0.017453292F) * MathHelper.cos(float_2 * 0.017453292F);
        float float_6 = MathHelper.sqrt(float_3 * float_3 + float_4 * float_4 + float_5 * float_5);
        float float_7 = 3.0F * ((1.0F + (float)3) / 4.0F);
        float_3 *= float_7 / float_6;
        float_4 *= float_7 / float_6;
        float_5 *= float_7 / float_6;
        playerEntity.addVelocity((double)float_3, (double)float_4, (double)float_5);
        playerEntity.setPushCooldown(20);
        if (playerEntity.onGround) {
            float float_8 = 1.1999999F;
            playerEntity.move(MovementType.SELF, new Vec3d(0.0D, 1.1999999284744263D, 0.0D));
        }
    }
}
