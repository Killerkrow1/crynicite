package net.killerkrow.crynicite.init.mass;

import net.killerkrow.crynicite.init.*;

public class MassInit {
    public static void massInit() {
        ModItems.registerModItems();
        ModItemGroups.registerItemGroups();
        ModBlocks.registerModBlocks();
        ModLootTableModifiers.modifyLootTables();
        ModParticles.registerParticles();
        ModEnchantments.registerModEnchantments();
        ModEntities.registerModEntities();
        ModEffects.registerEffects();
    }
}
