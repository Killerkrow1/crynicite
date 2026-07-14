package net.killerkrow.crynicite.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class ExploderBlock extends Block {
    public ExploderBlock(Settings settings) {
        super(settings);
    }

//    @Override
//    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
//        if (!world.isClient() && entity instanceof PlayerEntity) {
//            world.createExplosion(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 3.0f, World.ExplosionSourceType.BLOCK);
//            world.removeBlock(pos, false);
//        }
//    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);

        if (!world.isClient()) {
            boolean isNearFireOrLava = false;

            // Check all 6 adjacent directions
            for (Direction direction : Direction.values()) {
                BlockPos neighborPos = pos.offset(direction);
                BlockState neighborState = world.getBlockState(neighborPos);

                // Check for fire or lava blocks
                if (neighborState.isOf(Blocks.FIRE) ||
                        neighborState.isOf(Blocks.SOUL_FIRE) ||
                        neighborState.isOf(Blocks.LAVA)) { // You can also add flowing water if needed

                    isNearFireOrLava = true;
                    break;
                }
            }

            if (isNearFireOrLava) {
                // Trigger the explosion: world, entity (null for no specific instigator), x, y, z, radius, destruction type
                world.createExplosion(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 3.0f, World.ExplosionSourceType.BLOCK);
                world.removeBlock(pos, false);
            }
        }
    }
}