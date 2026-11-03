package net.killerkrow.crynicite.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.killerkrow.crynicite.init.ModItems;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.AMETRINE_CORE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CRYSEUM_CORE, Models.GENERATED);
        itemModelGenerator.register(ModItems.PYRITE_CORE, Models.GENERATED);
        itemModelGenerator.register(ModItems.OBLITUS_CORE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CINICITE_CORE, Models.GENERATED);
    }
}