package net.killerkrow.crynicite.enchantments;

import net.killerkrow.crynicite.init.ModItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class ChainedEnchantment extends Enchantment {
    public ChainedEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.isOf(ModItems.AMETRINE_DUAL_BLADES);
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }
}