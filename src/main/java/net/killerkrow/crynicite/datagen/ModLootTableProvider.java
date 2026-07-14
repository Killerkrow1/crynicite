package net.killerkrow.crynicite.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.killerkrow.crynicite.init.ModBlocks;
import net.killerkrow.crynicite.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.CRYSEUM_BLOCK);
        addDrop(ModBlocks.CINICITE_BLOCK);
        addDrop(ModBlocks.CYNICITE_BLOCK);
        addDrop(ModBlocks.END_PORTAL_FRAME);
        addDrop(ModBlocks.PYRITE_BLOCK);

        addDrop(ModBlocks.PYRITE_ORE, copperLikeOreDrops(ModBlocks.PYRITE_ORE, ModItems.RAW_PYRITE));
        addDrop(ModBlocks.DEEPSLATE_PYRITE_ORE, copperLikeOreDrops(ModBlocks.DEEPSLATE_PYRITE_ORE, ModItems.RAW_PYRITE));
        addDrop(ModBlocks.NETHER_PYRITE_ORE, copperLikeOreDrops(ModBlocks.NETHER_PYRITE_ORE, ModItems.RAW_PYRITE));
    }

    public LootTable.Builder copperLikeOreDrops(Block drop, Item item) {
        return BlockLootTableGenerator.dropsWithSilkTouch(drop, (LootPoolEntry.Builder)this.applyExplosionDecay(drop,
                ((LeafEntry.Builder)
                        ItemEntry.builder(item)
                                .apply(SetCountLootFunction
                                        .builder(UniformLootNumberProvider
                                                .create(2.0f, 5.0f))))
                        .apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))));
    }
}