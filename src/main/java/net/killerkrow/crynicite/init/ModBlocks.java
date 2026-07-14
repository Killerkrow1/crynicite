package net.killerkrow.crynicite.init;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.killerkrow.crynicite.Crynicite;
import net.killerkrow.crynicite.util.EnderPortalBlock;
import net.killerkrow.crynicite.util.ExploderBlock;
import net.killerkrow.crynicite.util.PyriteFire;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block CRYSEUM_BLOCK = registerBlock("cryseum_block",
            new Block(FabricBlockSettings.copyOf(Blocks.NETHERITE_BLOCK)));
    public static final Block CINICITE_BLOCK = registerBlock("cinicite_block",
            new Block(FabricBlockSettings.copyOf(Blocks.NETHERITE_BLOCK)));
    public static final Block CYNICITE_BLOCK = registerBlock("cynicite_block",
            new Block(FabricBlockSettings.copyOf(Blocks.NETHERITE_BLOCK)));

    public static final Block PYRITE_ORE = registerBlock("pyrite_ore",
            new Block(FabricBlockSettings.copyOf(Blocks.COAL_ORE)));
    public static final Block DEEPSLATE_PYRITE_ORE = registerBlock("deepslate_pyrite_ore",
            new Block(FabricBlockSettings.copyOf(Blocks.COAL_ORE)));
    public static final Block NETHER_PYRITE_ORE = registerBlock("nether_pyrite_ore",
            new Block(FabricBlockSettings.copyOf(Blocks.COAL_ORE)));
    public static final Block PYRITE_BLOCK = registerBlock("pyrite_block",
            new Block(FabricBlockSettings.copyOf(Blocks.COAL_ORE)));

    public static final Block PYRITE_FIRE = registerBlock("pyrite_fire",
            new PyriteFire(AbstractBlock.Settings.create()
                    .noCollision().breakInstantly().replaceable().dropsNothing().luminance(state -> 15).sounds(BlockSoundGroup.WOOL)));
    public static final Block EXPLODE_ME = registerBlock("explode_me",
            new ExploderBlock(FabricBlockSettings.copyOf(Blocks.TNT).collidable(false).hardness(0)
                    .resistance(0).breakInstantly().burnable().dropsNothing().nonOpaque()));

    public static final Block END_PORTAL_FRAME = registerBlock("end_portal_frame",
            new EnderPortalBlock(FabricBlockSettings.copyOf(Blocks.END_PORTAL).luminance((state) -> 1).hardness(55).requiresTool().nonOpaque().collidable(true)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Crynicite.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(Crynicite.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        Crynicite.LOGGER.info("Registering ModBlocks for " + Crynicite.MOD_ID);
    }
}
