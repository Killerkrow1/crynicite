package net.killerkrow.crynicite.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.killerkrow.crynicite.util.CryniciteToolMaterials;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.Vanishable;

public class GreatPickaxe extends PickaxeItem implements Vanishable {
    public GreatPickaxe(CryniciteToolMaterials ToolMaterials, int attackDamage, float attackSpeed, FabricItemSettings settings) {
        super(ToolMaterials, attackDamage, attackSpeed, settings);
    }
}