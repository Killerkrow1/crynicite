package net.killerkrow.crynicite.init.mass;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
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
        ModWorldGeneration.generateModWorldGen();
        ServerTickEvents.END_WORLD_TICK.register(PullTaskTracker::tick);
    }
}
