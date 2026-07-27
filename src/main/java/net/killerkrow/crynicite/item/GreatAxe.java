package net.killerkrow.crynicite.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.killerkrow.crynicite.util.CryniciteToolMaterials;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Vanishable;

public class GreatAxe extends AxeItem implements Vanishable {
    public GreatAxe(CryniciteToolMaterials ToolMaterials, int attackDamage, float attackSpeed, FabricItemSettings settings) {
        super(ToolMaterials, attackDamage, attackSpeed, settings);
    }
}