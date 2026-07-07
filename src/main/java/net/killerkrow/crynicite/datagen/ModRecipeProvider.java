package net.killerkrow.crynicite.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.killerkrow.crynicite.Crynicite;
import net.killerkrow.crynicite.init.ModBlocks;
import net.killerkrow.crynicite.init.ModItems;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

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


        ShapelessRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.CRYNICITE_INGOT)
                .input(ModItems.CINICITE_CRYSTAL, 4)
                .input(ModItems.CRYSEUM_INGOT, 4)
                .criterion(hasItem(ModItems.CRYSEUM_INGOT), conditionsFromItem(ModItems.CRYSEUM_INGOT))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "crynicite_ingot"));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.CRYSEUM_INGOT,4)
                .input(ModBlocks.CRYSEUM_BLOCK)
                .criterion(hasItem(ModBlocks.CRYSEUM_BLOCK), conditionsFromItem(ModBlocks.CRYSEUM_BLOCK))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "cryseum_block_to"));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModBlocks.CINICITE_BLOCK)
                .input(ModItems.CINICITE_CRYSTAL, 9)
                .criterion(hasItem(ModItems.CINICITE_CRYSTAL), conditionsFromItem(ModItems.CINICITE_CRYSTAL))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "cinicite_block_from"));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.CINICITE_CRYSTAL,9)
                .input(ModBlocks.CINICITE_BLOCK)
                .criterion(hasItem(ModItems.CINICITE_CRYSTAL), conditionsFromItem(ModItems.CINICITE_CRYSTAL))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "cinicite_block_to"));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.CRYNICITE_INGOT,4)
                .input(ModBlocks.CYNICITE_BLOCK)
                .criterion(hasItem(ModItems.CRYNICITE_INGOT), conditionsFromItem(ModItems.CRYNICITE_INGOT))
                .offerTo(exporter, new Identifier(Crynicite.MOD_ID, "crynicite_block_to"));
    }
}
