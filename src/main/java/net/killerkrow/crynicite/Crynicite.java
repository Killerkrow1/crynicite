package net.killerkrow.crynicite;

import net.fabricmc.api.ModInitializer;
import net.killerkrow.crynicite.init.*;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Crynicite implements ModInitializer {
	public static final String MOD_ID = "crynicite";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModBlocks.registerModBlocks();
	}

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}
}
