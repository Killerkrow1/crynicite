package net.killerkrow.crynicite.init;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.killerkrow.crynicite.Crynicite;
import net.killerkrow.crynicite.item.*;
import net.killerkrow.util.CryniciteToolMaterials;
import net.minecraft.item.Item;
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
            new GreatHammer(CryniciteToolMaterials.CRYSEUM, 9, -3.2f, new FabricItemSettings()));
    public static final Item CINICITE_KATANA = registerItem("cinicite_katana",
            new Katana(CryniciteToolMaterials.CINICITE, 4, -2.2f, new FabricItemSettings()));
    public static final Item CRYNICITE_CLEAVERSWORD = registerItem("crynicite_cleaversword",
            new CleaverSword(CryniciteToolMaterials.CRYNICITE, 9, -3.0f, new FabricItemSettings()));

    public static final Item CRYNICITE_SCISSORBLADES = registerItem("crynicite_scissorblades",
            new ScissorBlades(CryniciteToolMaterials.CRYNICITE, 8, -3.1f, new FabricItemSettings()));
    public static final Item CRYSEUM_SCISSORBLADES = registerItem("cryseum_scissorblades",
            new LeftScissorBlades(CryniciteToolMaterials.CRYSEUM, 6, -3.0f, new FabricItemSettings()));
    public static final Item CINICITE_SCISSORBLADES = registerItem("cinicite_scissorblades",
            new RightScissorBlades(CryniciteToolMaterials.CINICITE, 5, -2.8f, new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Crynicite.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Crynicite.LOGGER.info("Registering Mod Items for " + Crynicite.MOD_ID);
    }
}
