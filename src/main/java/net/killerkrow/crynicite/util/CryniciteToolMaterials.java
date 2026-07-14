package net.killerkrow.crynicite.util;

import net.fabricmc.yarn.constants.MiningLevels;
import net.killerkrow.crynicite.init.ModItems;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;

import java.util.function.Supplier;

public enum CryniciteToolMaterials implements ToolMaterial {
            CRYSEUM(MiningLevels.NETHERITE, 2031, 9.0F, 4.0F, 15,
                    () -> Ingredient.ofItems(ModItems.CRYSEUM_INGOT)),
            CINICITE(MiningLevels.DIAMOND, 1561, 8.0F, 3.0F, 10,
                    () -> Ingredient.ofItems(ModItems.CINICITE_CRYSTAL)),
            CRYNICITE(MiningLevels.DIAMOND, 1561, 8.0F, 3.0F, 10,
                    () -> Ingredient.ofItems(ModItems.CRYNICITE_INGOT));

    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Lazy<Ingredient> repairIngredient;

    CryniciteToolMaterials(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = new Lazy<>(repairIngredient);
    }

    @Override
    public int getDurability() {
        return this.itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return this.miningLevel;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}