package net.killerkrow.crynicite.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.killerkrow.crynicite.init.ModBlocks;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.CRYSEUM_BLOCK);
        addDrop(ModBlocks.CINICITE_BLOCK);
        addDrop(ModBlocks.CYNICITE_BLOCK);
    }
}