package net.killerkrow.crynicite.entities;

import net.killerkrow.crynicite.init.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PyriteChunkEntity extends ThrownItemEntity {
    public PyriteChunkEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public PyriteChunkEntity(World world, double x, double y, double z) {
        super(ModEntities.PYRITE_CHUNK_ENTITY, x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.PYRITE_CHUNK;
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient()) {
            this.getWorld().sendEntityStatus(this, (byte)3);
            BlockPos impactPos = this.getBlockPos();
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    for (int z = -1; z <= 1; z++) {
                        BlockPos targetPos = impactPos.add(x, y, z);
                        if (this.getWorld().getBlockState(targetPos).isAir()) {
                            this.getWorld().setBlockState(targetPos, net.minecraft.block.Blocks.FIRE.getDefaultState());
                        }
                    }
                }
            }
            this.discard();
        }
    }

    @Override
    public void tick() {
        super.tick();
        // Restricts heavy logic or block modifications to the server side
        if (this.getWorld().isClient()) {
            return;
        }
        // Check for the shooter
//        Entity owner = this.getOwner();
//        if (owner == null || owner.isRemoved()) {
//            // Handles the missing owner safely or it will poof the item
//            this.discard();
//        }
    }
}