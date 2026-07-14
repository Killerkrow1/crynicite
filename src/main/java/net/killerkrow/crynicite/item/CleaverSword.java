package net.killerkrow.crynicite.item;

import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CleaverSword extends AxeItem implements Vanishable {

    public CleaverSword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Item.Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }


    @Override
    public ItemStack getRecipeRemainder(ItemStack stack) {
        return new ItemStack(this);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        ItemStack helmet = target.getEquippedStack(EquipmentSlot.HEAD);
        ItemStack chestplate = target.getEquippedStack(EquipmentSlot.CHEST);
        ItemStack leggings = target.getEquippedStack(EquipmentSlot.LEGS);
        ItemStack boots = target.getEquippedStack(EquipmentSlot.FEET);

        damageArmor(target, helmet, 2);
        damageArmor(target, chestplate, 2);
        damageArmor(target, leggings, 2);
        damageArmor(target, boots, 2);

        double dx = attacker.getX() - target.getX();
        double dz = attacker.getZ() - target.getZ();

        target.takeKnockback(0.5F, dx, dz);

        return super.postHit(stack, target, attacker);
    }

    private void damageArmor(LivingEntity target, ItemStack armorStack, int amount) {
        if (!armorStack.isEmpty()) {
            armorStack.damage(amount, target, (entity) -> {
                entity.sendEquipmentBreakStatus(EquipmentSlot.HEAD);
            });
        }
    }

    @Override
    public float getMiningSpeedMultiplier(net.minecraft.item.ItemStack stack, BlockState state) {
        if (state.isIn(BlockTags.AXE_MINEABLE)) {
            return -8.0F;
        }
        return super.getMiningSpeedMultiplier(stack, state);
    }


    // tooltip
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("tooltip.crynicite.cleaversword.tooltip").formatted(Formatting.DARK_PURPLE));
        } else {
            tooltip.add(Text.literal("[SHIFT]").formatted(Formatting.DARK_GRAY));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}