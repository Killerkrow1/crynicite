package net.killerkrow.crynicite.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.killerkrow.crynicite.Crynicite;
import net.killerkrow.crynicite.init.ModBlocks;
import net.killerkrow.crynicite.init.ModItems;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CRYSEUM_INGOT, 2)
                .pattern(" G ")
                .pattern("GNG")
                .pattern(" G ")
                .input('G',Items.GOLD_INGOT)
                .input('N',Items.NETHERITE_INGOT)
                .criterion(hasItem(Items.NETHERITE_INGOT), conditionsFromItem(Items.NETHERITE_INGOT))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "cryseum_ingot"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CINICITE_CRYSTAL, 2)
                .pattern(" G ")
                .pattern("GNG")
                .pattern(" G ")
                .input('G',Items.LAPIS_LAZULI)
                .input('N',Items.AMETHYST_SHARD)
                .criterion(hasItem(Items.LAPIS_LAZULI), conditionsFromItem(Items.LAPIS_LAZULI))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "cinicite_crystal"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.CRYSEUM_BLOCK, 1)
                .pattern("GG ")
                .pattern("GG ")
                .pattern("   ")
                .input('G',ModItems.CRYSEUM_INGOT)
                .criterion(hasItem(ModItems.CRYSEUM_INGOT), conditionsFromItem(ModItems.CRYSEUM_INGOT))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "cryseum_block_from"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.CYNICITE_BLOCK, 1)
                .pattern("GG ")
                .pattern("GG ")
                .pattern("   ")
                .input('G',ModItems.CRYNICITE_INGOT)
                .criterion(hasItem(ModItems.CRYNICITE_INGOT), conditionsFromItem(ModItems.CRYNICITE_INGOT))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "cynicite_block_from"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CRYSEUM_GREATHAMMER, 1)
                .pattern("BIB")
                .pattern(" S ")
                .pattern(" S ")
                .input('S',Items.STICK)
                .input('I',ModItems.CRYSEUM_INGOT)
                .input('B',ModBlocks.CRYSEUM_BLOCK)
                .criterion(hasItem(ModBlocks.CRYSEUM_BLOCK), conditionsFromItem(ModBlocks.CRYSEUM_BLOCK))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "cryseum_greathammer"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CINICITE_KATANA, 1)
                .pattern("  C")
                .pattern(" C ")
                .pattern("S  ")
                .input('S',Items.STICK)
                .input('C',ModItems.CINICITE_CRYSTAL)
                .criterion(hasItem(ModItems.CINICITE_CRYSTAL), conditionsFromItem(ModItems.CINICITE_CRYSTAL))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "cinicite_katana"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CRYNICITE_CLEAVERSWORD, 1)
                .pattern(" CR")
                .pattern(" CY")
                .pattern(" S ")
                .input('S',Items.STICK)
                .input('C',ModItems.CINICITE_CRYSTAL)
                .input('R',ModItems.CRYSEUM_INGOT)
                .input('Y',ModItems.CRYNICITE_INGOT)
                .criterion(hasItem(ModItems.CRYSEUM_INGOT), conditionsFromItem(ModItems.CRYSEUM_INGOT))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "crynicite_cleaversword"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CRYNICITE_SCISSORBLADES, 1)
                .pattern("I B")
                .pattern(" C ")
                .pattern("B I")
                .input('C',ModItems.CRYNICITE_INGOT)
                .input('I',ModItems.CINICITE_CRYSTAL)
                .input('B',ModItems.CRYSEUM_INGOT)
                .criterion(hasItem(ModItems.CRYNICITE_INGOT), conditionsFromItem(ModItems.CRYNICITE_INGOT))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "crynicite_scissorblades"));


        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CRYNICITE_INGOT)
                .input(ModItems.CINICITE_CRYSTAL, 4)
                .input(ModItems.CRYSEUM_INGOT, 4)
                .criterion(hasItem(ModItems.CRYSEUM_INGOT), conditionsFromItem(ModItems.CRYSEUM_INGOT))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "crynicite_ingot"));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CRYSEUM_INGOT,4)
                .input(ModBlocks.CRYSEUM_BLOCK)
                .criterion(hasItem(ModBlocks.CRYSEUM_BLOCK), conditionsFromItem(ModBlocks.CRYSEUM_BLOCK))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "cryseum_block_to"));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.CINICITE_BLOCK)
                .input(ModItems.CINICITE_CRYSTAL, 9)
                .criterion(hasItem(ModItems.CINICITE_CRYSTAL), conditionsFromItem(ModItems.CINICITE_CRYSTAL))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "cinicite_block_from"));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CINICITE_CRYSTAL,9)
                .input(ModBlocks.CINICITE_BLOCK)
                .criterion(hasItem(ModItems.CINICITE_CRYSTAL), conditionsFromItem(ModItems.CINICITE_CRYSTAL))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "cinicite_block_to"));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CRYNICITE_INGOT,4)
                .input(ModBlocks.CYNICITE_BLOCK)
                .criterion(hasItem(ModItems.CRYNICITE_INGOT), conditionsFromItem(ModItems.CRYNICITE_INGOT))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "crynicite_block_to"));

//        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PYRITE_AND_STEEL)
//                .input(ModItems.RAW_PYRITE)
//                .input(Items.IRON_NUGGET)
//                .criterion(hasItem(ModItems.RAW_PYRITE), conditionsFromItem(ModItems.RAW_PYRITE))
//                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "pyrite_and_steel"));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.PYRITE_BLOCK)
                .input(ModItems.RAW_PYRITE, 9)
                .criterion(hasItem(ModItems.RAW_PYRITE), conditionsFromItem(ModItems.RAW_PYRITE))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "pyrite_block_to"));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RAW_PYRITE, 9)
                .input(ModBlocks.PYRITE_BLOCK)
                .criterion(hasItem(ModBlocks.PYRITE_BLOCK), conditionsFromItem(ModBlocks.PYRITE_BLOCK))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "pyrite_block_from"));


        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PYRITE_CHUNK, 1)
                .pattern("CC ")
                .pattern("CC ")
                .pattern("   ")
                .input('C',ModItems.RAW_PYRITE)
                .criterion(hasItem(ModItems.RAW_PYRITE), conditionsFromItem(ModItems.RAW_PYRITE))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "pyrite_chunk"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PYRITE_SMOKE_BOMB, 1)
                .pattern("BHB")
                .pattern("HCH")
                .pattern("BHB")
                .input('C',ModItems.PYRITE_CHUNK)
                .input('H',Items.COAL)
                .input('B',Items.GUNPOWDER)
                .criterion(hasItem(ModItems.PYRITE_CHUNK), conditionsFromItem(ModItems.PYRITE_CHUNK))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "pyrite_smoke_bomb"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CITRINE_CRYSTAL, 2)
                .pattern("BHB")
                .pattern("HBH")
                .pattern("BHB")
                .input('H',Items.GOLD_INGOT)
                .input('B',Items.QUARTZ)
                .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "citrine_crystal"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.AMETRINE_CRYSTAL, 2)
                .pattern("BHB")
                .pattern("HBH")
                .pattern("BHB")
                .input('H',ModItems.CITRINE_CRYSTAL)
                .input('B',Items.AMETHYST_SHARD)
                .criterion(hasItem(ModItems.CITRINE_CRYSTAL), conditionsFromItem(ModItems.CITRINE_CRYSTAL))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "ametrine_crystal"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.AMETRINE_DUAL_BLADES, 2)
                .pattern("A A")
                .pattern("A A")
                .pattern("S S")
                .input('A',ModItems.AMETRINE_CRYSTAL)
                .input('S',Items.STICK)
                .criterion(hasItem(ModItems.AMETRINE_CRYSTAL), conditionsFromItem(ModItems.AMETRINE_CRYSTAL))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "ametrine_dual_blades"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.AMETRINE_GLAIVE)
                .pattern("  A")
                .pattern(" S ")
                .pattern("S  ")
                .input('A',ModItems.AMETRINE_CRYSTAL)
                .input('S',Items.STICK)
                .criterion(hasItem(ModItems.AMETRINE_CRYSTAL), conditionsFromItem(ModItems.AMETRINE_CRYSTAL))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "ametrine_glaive"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.AMETRINE_BLOCK)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .input('A',ModItems.AMETRINE_CRYSTAL)
                .criterion(hasItem(ModItems.AMETRINE_CRYSTAL), conditionsFromItem(ModItems.AMETRINE_CRYSTAL))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "ametrine_block_to"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.CITRINE_BLOCK)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .input('A',ModItems.CITRINE_CRYSTAL)
                .criterion(hasItem(ModItems.CITRINE_CRYSTAL), conditionsFromItem(ModItems.CITRINE_CRYSTAL))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "citrine_block_to"));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.AMETRINE_CRYSTAL, 9)
                .input(ModBlocks.AMETRINE_BLOCK)
                .criterion(hasItem(ModBlocks.AMETRINE_BLOCK), conditionsFromItem(ModBlocks.AMETRINE_BLOCK))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "ametrine_block_from"));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CITRINE_CRYSTAL, 9)
                .input(ModBlocks.CITRINE_BLOCK)
                .criterion(hasItem(ModBlocks.CITRINE_BLOCK), conditionsFromItem(ModBlocks.CITRINE_BLOCK))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "citrine_block_from"));



        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.OBLITUS_GREATSHOVEL, 1)
                .pattern(" O ")
                .pattern(" T ")
                .pattern(" S ")
                .input('O',ModItems.OBLITUS_STEEL)
                .input('T',Items.NETHERITE_SHOVEL)
                .input('S',Items.STICK)
                .criterion(hasItem(ModItems.OBLITUS_STEEL), conditionsFromItem(ModItems.OBLITUS_STEEL))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "oblitus_greatshovel"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.OBLITUS_GREATSWORD, 1)
                .pattern(" O ")
                .pattern(" O ")
                .pattern(" S ")
                .input('O',ModItems.OBLITUS_STEEL)
                .input('S',Items.NETHERITE_SWORD)
                .criterion(hasItem(ModItems.OBLITUS_STEEL), conditionsFromItem(ModItems.OBLITUS_STEEL))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "oblitus_greatsword"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.OBLITUS_RIPPERSWORD, 1)
                .pattern("  O")
                .pattern(" OO")
                .pattern("T  ")
                .input('O',ModItems.OBLITUS_STEEL)
                .input('T',Items.NETHERITE_SWORD)
                .criterion(hasItem(ModItems.OBLITUS_STEEL), conditionsFromItem(ModItems.OBLITUS_STEEL))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "oblitus_rippersword"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.OBLITUS_SCISSORBLADES_FULL, 1)
                .pattern("O O")
                .pattern(" N ")
                .pattern("OSO")
                .input('O',ModItems.OBLITUS_STEEL)
                .input('S',Items.NETHERITE_SWORD)
                .input('N',Items.NETHERITE_INGOT)
                .criterion(hasItem(ModItems.OBLITUS_STEEL), conditionsFromItem(ModItems.OBLITUS_STEEL))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "oblitus_scissorblades"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.OBLITUS_HELMET, 1)
                .pattern("OOO")
                .pattern("ONO")
                .pattern("   ")
                .input('O',ModItems.OBLITUS_STEEL)
                .input('N',Items.NETHERITE_INGOT)
                .criterion(hasItem(ModItems.OBLITUS_STEEL), conditionsFromItem(ModItems.OBLITUS_STEEL))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "oblitus_helmet"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.OBLITUS_CHESTPLATE, 1)
                .pattern("O O")
                .pattern("ONO")
                .pattern("OOO")
                .input('O',ModItems.OBLITUS_STEEL)
                .input('N',Items.NETHERITE_INGOT)
                .criterion(hasItem(ModItems.OBLITUS_STEEL), conditionsFromItem(ModItems.OBLITUS_STEEL))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "oblitus_chestplate"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.OBLITUS_LEGGINGS, 1)
                .pattern("OOO")
                .pattern("O O")
                .pattern("N N")
                .input('O',ModItems.OBLITUS_STEEL)
                .input('N',Items.NETHERITE_INGOT)
                .criterion(hasItem(ModItems.OBLITUS_STEEL), conditionsFromItem(ModItems.OBLITUS_STEEL))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "oblitus_leggings"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.OBLITUS_BOOTS, 1)
                .pattern("   ")
                .pattern("O O")
                .pattern("N N")
                .input('O',ModItems.OBLITUS_STEEL)
                .input('N',Items.NETHERITE_INGOT)
                .criterion(hasItem(ModItems.OBLITUS_STEEL), conditionsFromItem(ModItems.OBLITUS_STEEL))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "oblitus_boots"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.OBLITUS_CLEAVER, 1)
                .pattern(" OO")
                .pattern(" SO")
                .pattern(" S ")
                .input('O',ModItems.OBLITUS_STEEL)
                .input('S',Items.STICK)
                .criterion(hasItem(ModItems.OBLITUS_STEEL), conditionsFromItem(ModItems.OBLITUS_STEEL))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "oblitus_cleaver"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.OBLITUS_GREATAXE, 1)
                .pattern("OOO")
                .pattern("OAO")
                .pattern(" S ")
                .input('O',ModItems.OBLITUS_STEEL)
                .input('S',Items.STICK)
                .input('A',Items.NETHERITE_AXE)
                .criterion(hasItem(ModItems.OBLITUS_STEEL), conditionsFromItem(ModItems.OBLITUS_STEEL))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "oblitus_greataxe"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.OBLITUS_GREATPICKAXE, 1)
                .pattern("OOO")
                .pattern(" A ")
                .pattern(" S ")
                .input('O',ModItems.OBLITUS_STEEL)
                .input('S',Items.STICK)
                .input('A',Items.NETHERITE_PICKAXE)
                .criterion(hasItem(ModItems.OBLITUS_STEEL), conditionsFromItem(ModItems.OBLITUS_STEEL))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "oblitus_greatpickaxe"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.TEST_ITEM, 1)
                .pattern(" IC")
                .pattern("ODO")
                .pattern("AO ")
                .input('O',ModItems.OBLITUS_STEEL)
                .input('D',ModItems.CRYNICITE_INGOT)
                .input('I',ModItems.CINICITE_CRYSTAL)
                .input('C',ModItems.CRYSEUM_INGOT)
                .input('A',ModItems.CRYNICITE_CLEAVERSWORD)
                .criterion(hasItem(ModItems.OBLITUS_STEEL), conditionsFromItem(ModItems.OBLITUS_STEEL))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "eversore"));




        ItemConvertible cookedPyriteItem = ModItems.RAW_PYRITE;
        ItemConvertible cookedPyriteIngot = ModItems.PYRITE_INGOT;
        ItemConvertible overworld = ModBlocks.PYRITE_ORE;
        ItemConvertible deepslate = ModBlocks.DEEPSLATE_PYRITE_ORE;
        ItemConvertible nether = ModBlocks.NETHER_PYRITE_ORE;

        // Smelting
        offerSmelting(exporter, List.of(overworld), RecipeCategory.MISC, cookedPyriteItem, 1f,
                200, "normal_pyrite_smelting");
        offerSmelting(exporter, List.of(deepslate), RecipeCategory.MISC, cookedPyriteItem, 1f,
                200, "deepslate_pyrite_smelting");
        offerSmelting(exporter, List.of(nether), RecipeCategory.MISC, cookedPyriteItem, 1f,
                200, "nether_pyrite_smelting");

        offerSmelting(exporter, List.of(cookedPyriteItem), RecipeCategory.MISC, cookedPyriteIngot, 1f,
                200, "pyrite_ingot_smelting");

        // Blasting
        offerBlasting(exporter, List.of(overworld), RecipeCategory.MISC, cookedPyriteItem, 1f,
                100, "normal_pyrite_blasting");
        offerBlasting(exporter, List.of(deepslate), RecipeCategory.MISC, cookedPyriteItem, 1f,
                100, "deepslate_pyrite_blasting");
        offerBlasting(exporter, List.of(nether), RecipeCategory.MISC, cookedPyriteItem, 1f,
                100,  "nether_pyrite_blasting");
    }
}