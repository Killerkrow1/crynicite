package net.killerkrow.crynicite.init;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.killerkrow.crynicite.Crynicite;
import net.killerkrow.crynicite.item.*;
import net.killerkrow.crynicite.util.CryniciteArmorMaterials;
import net.killerkrow.crynicite.util.CryniciteToolMaterials;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item CRYSEUM_INGOT = registerItem("cryseum_ingot",
            new Item(new FabricItemSettings()));
    public static final Item CINICITE_CRYSTAL = registerItem("cinicite_crystal",
            new Item(new FabricItemSettings()));
    public static final Item CRYNICITE_INGOT = registerItem("crynicite_ingot",
            new Item(new FabricItemSettings()));


    public static final Item CRYSEUM_GREATHAMMER = registerItem("cryseum_greathammer",
            new GreatHammer(CryniciteToolMaterials.CRYSEUM, 7, -3.4f, new FabricItemSettings()));
    public static final Item CINICITE_KATANA = registerItem("cinicite_katana",
            new Katana(CryniciteToolMaterials.CINICITE, 3, -2.2f, new FabricItemSettings()));
    public static final Item CRYNICITE_CLEAVERSWORD = registerItem("crynicite_cleaversword",
            new CleaverSword(CryniciteToolMaterials.CRYNICITE, 7, -3.0f, new FabricItemSettings()));

    public static final Item CRYNICITE_SCISSORBLADES = registerItem("crynicite_scissorblades",
            new ScissorBlades(CryniciteToolMaterials.CRYNICITE, 8, -3.1f, new FabricItemSettings()));
    public static final Item CRYSEUM_SCISSORBLADES = registerItem("cryseum_scissorblades",
            new LeftScissorBlades(CryniciteToolMaterials.CRYSEUM, 5, -3.0f, new FabricItemSettings()));
    public static final Item CINICITE_SCISSORBLADES = registerItem("cinicite_scissorblades",
            new RightScissorBlades(CryniciteToolMaterials.CINICITE, 5, -2.8f, new FabricItemSettings()));


    public static final Item PYRITE_CHUNK = registerItem("pyrite_chunk",
            new PyriteChunk(new FabricItemSettings()));
    public static final Item PYRITE_AND_STEEL = registerItem("pyrite_and_steel",
            new PyriteSteel(new FabricItemSettings()));
    public static final Item PYRITE_SMOKE_BOMB = registerItem("pyrite_smoke_bomb",
            new PyriteSmokeBomb(new FabricItemSettings().maxCount(16)));
    public static final Item PYRITE_INGOT = registerItem("pyrite_ingot",
            new Item(new FabricItemSettings()));
    public static final Item RAW_PYRITE = registerItem("raw_pyrite",
            new Item(new FabricItemSettings()));


    public static final Item CITRINE_CRYSTAL = registerItem("citrine_crystal",
            new Item(new FabricItemSettings()));
    public static final Item AMETRINE_CRYSTAL = registerItem("ametrine_crystal",
            new Ametrine(new FabricItemSettings()));
    public static final Item AMETRINE_GLAIVE = registerItem("ametrine_glaive",
            new Glaive(CryniciteToolMaterials.AMETRINE, 5, -2.8f, new FabricItemSettings()));
    public static final Item AMETRINE_DUAL_BLADES = registerItem("ametrine_dual_blades",
            new DualBlades(CryniciteToolMaterials.AMETRINE, 4, -2.5f, new FabricItemSettings()));


    public static final Item OBLITUS_STEEL = registerItem("oblitus_steel",
            new Item(new FabricItemSettings()));

    public static final Item OBLITUS_CLEAVER = registerItem("oblitus_cleaver",
            new Cleaver(CryniciteToolMaterials.OBLITUS, 5, -3.0f, new FabricItemSettings().fireproof()));
    public static final Item OBLITUS_GREATAXE = registerItem("oblitus_greataxe",
            new GreatAxe(CryniciteToolMaterials.OBLITUS, 6, -3.1f, new FabricItemSettings().fireproof()));
    public static final Item OBLITUS_GREATPICKAXE = registerItem("oblitus_greatpickaxe",
            new GreatPickaxe(CryniciteToolMaterials.OBLITUS, 2, -2.8f, new FabricItemSettings().fireproof()));
    public static final Item OBLITUS_GREATSHOVEL = registerItem("oblitus_greatshovel",
            new GreatShovel(CryniciteToolMaterials.OBLITUS, 2, -3.0f, new FabricItemSettings().fireproof()));
    public static final Item OBLITUS_GREATSWORD = registerItem("oblitus_greatsword",
            new GreatSword(CryniciteToolMaterials.OBLITUS, 4, -2.7f, new FabricItemSettings().fireproof()));
    public static final Item OBLITUS_RIPPERSWORD = registerItem("oblitus_rippersword",
            new Rippersword(CryniciteToolMaterials.OBLITUS, 5, -3.2f, new FabricItemSettings().fireproof()));
    public static final Item OBLITUS_SCISSORBLADES_FULL = registerItem("oblitus_scissorblades_full",
            new OblitusScissorBlades(CryniciteToolMaterials.OBLITUS, 6, -3.0f, new FabricItemSettings().fireproof()));
    public static final Item OBLITUS_SCISSORBLADES_HALF = registerItem("oblitus_scissorblades_half",
            new OblitusScissorBlade(CryniciteToolMaterials.OBLITUS, 5, -2.8f, new FabricItemSettings().fireproof()));

    public static final Item OBLITUS_HELMET = registerItem("oblitus_helmet",
            new ArmorItem(CryniciteArmorMaterials.OBLITUS, ArmorItem.Type.HELMET, new FabricItemSettings().fireproof()));
    public static final Item OBLITUS_CHESTPLATE = registerItem("oblitus_chestplate",
            new ArmorItem(CryniciteArmorMaterials.OBLITUS, ArmorItem.Type.CHESTPLATE, new FabricItemSettings().fireproof()));
    public static final Item OBLITUS_LEGGINGS = registerItem("oblitus_leggings",
            new ArmorItem(CryniciteArmorMaterials.OBLITUS, ArmorItem.Type.LEGGINGS, new FabricItemSettings().fireproof()));
    public static final Item OBLITUS_BOOTS = registerItem("oblitus_boots",
            new ArmorItem(CryniciteArmorMaterials.OBLITUS, ArmorItem.Type.BOOTS, new FabricItemSettings().fireproof()));


    public static final Item TEST_ITEM = registerItem("eversor",
            new TestItem(ToolMaterials.DIAMOND, 3, -2.4f, new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Crynicite.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Crynicite.LOGGER.info("Registering Mod Items for " + Crynicite.MOD_ID);
    }
}
