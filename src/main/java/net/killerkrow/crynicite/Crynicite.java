package net.killerkrow.crynicite;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.killerkrow.crynicite.init.*;
import net.killerkrow.crynicite.util.PullTaskTracker;
import net.killerkrow.crynicite.world.gen.ModWorldGeneration;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
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
		ModLootTableModifiers.modifyLootTables();
		ModParticles.registerParticles();
		ModEnchantments.registerModEnchantments();

		ModWorldGeneration.generateModWorldGen();
		ServerTickEvents.END_WORLD_TICK.register(PullTaskTracker::tick);

		UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
			var blockState = world.getBlockState(hitResult.getBlockPos());
			var block = blockState.getBlock();

			if ((block == Blocks.END_PORTAL_FRAME)
					&& player.getStackInHand(hand).isOf(Items.BONE_MEAL)) {

				if (!world.isClient()) {
					if (!player.getAbilities().creativeMode) {
						player.getStackInHand(hand).decrement(0);
					}

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
