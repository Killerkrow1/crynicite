package net.killerkrow.crynicite.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.killerkrow.crynicite.util.CryniciteToolMaterials;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.Vanishable;

public class GreatShovel extends ShovelItem implements Vanishable {
    public GreatShovel(CryniciteToolMaterials ToolMaterials, int attackDamage, float attackSpeed, FabricItemSettings settings) {
        super(ToolMaterials, attackDamage, attackSpeed, settings);
    }
}