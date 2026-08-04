package net.killerkrow.crynicite.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.killerkrow.crynicite.util.CryniciteToolMaterials;
import net.minecraft.item.SwordItem;
import net.minecraft.item.Vanishable;

public class Cleaver extends SwordItem implements Vanishable {
    public Cleaver(CryniciteToolMaterials ToolMaterials, int attackDamage, float attackSpeed, FabricItemSettings settings) {
        super(ToolMaterials, attackDamage, attackSpeed, settings);
    }
}