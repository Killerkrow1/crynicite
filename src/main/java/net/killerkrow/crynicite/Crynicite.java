package net.killerkrow.crynicite;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.killerkrow.crynicite.init.mass.*;
import net.killerkrow.crynicite.init.*;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Crynicite implements ModInitializer {
	public static final String MOD_ID = "crynicite";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		MassInit.massInit();

		UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
			var blockState = world.getBlockState(hitResult.getBlockPos());
			var block = blockState.getBlock();

			if ((block == Blocks.END_PORTAL_FRAME)
					&& player.getStackInHand(hand).isOf(ModItems.CRYNICITE_INGOT)) {

				if (!world.isClient()) {

					var pos = hitResult.getBlockPos().up();

					var false_portal = new ItemStack(ModBlocks.END_PORTAL_FRAME, 1);

					Block.dropStack(world, pos, false_portal);
				}
				return ActionResult.SUCCESS;
			}

			return ActionResult.PASS;
		});
	}

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}
}
