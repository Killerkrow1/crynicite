package net.killerkrow.crynicite.init;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.killerkrow.crynicite.Crynicite;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup CRYNICITE_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Crynicite.MOD_ID, "cryseum_ingot"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.crynicite"))
                    .icon(() -> new ItemStack(ModItems.CRYNICITE_INGOT)).entries((displayContext, entries) -> {

                        entries.add(ModItems.CRYSEUM_INGOT);
                        entries.add(ModItems.CINICITE_CRYSTAL);
                        entries.add(ModItems.CRYNICITE_INGOT);

                        entries.add(ModItems.CRYSEUM_GREATHAMMER);
                        entries.add(ModItems.CINICITE_KATANA);
                        entries.add(ModItems.CRYNICITE_CLEAVERSWORD);
                        entries.add(ModItems.CRYNICITE_SCISSORBLADES);
                        entries.add(ModItems.CRYSEUM_SCISSORBLADES);
                        entries.add(ModItems.CINICITE_SCISSORBLADES);

                        entries.add(ModBlocks.CRYSEUM_BLOCK);
                        entries.add(ModBlocks.CINICITE_BLOCK);
                        entries.add(ModBlocks.CYNICITE_BLOCK);

                        entries.add(ModBlocks.PYRITE_ORE);
                        entries.add(ModBlocks.DEEPSLATE_PYRITE_ORE);
                        entries.add(ModBlocks.NETHER_PYRITE_ORE);
                        entries.add(ModBlocks.PYRITE_BLOCK);
                        entries.add(ModItems.RAW_PYRITE);
                        entries.add(ModItems.PYRITE_CHUNK);
                        entries.add(ModItems.PYRITE_INGOT);
                        entries.add(ModItems.PYRITE_SMOKE_BOMB);

                    }).build());

    public static void registerItemGroups() {
        Crynicite.LOGGER.info("Registering Item Groups for " + Crynicite.MOD_ID);
    }
}