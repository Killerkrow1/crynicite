package net.killerkrow.crynicite.entities;

import net.killerkrow.crynicite.init.ModBlocks;
import net.killerkrow.crynicite.init.ModItems;
import net.killerkrow.crynicite.init.ModParticles;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PyriteSmokeBombEntity extends ThrownItemEntity {
    public PyriteSmokeBombEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public PyriteSmokeBombEntity(World world, double x, double y, double z) {
        super(ModEntities.PYRITE_SMOKE_BOMB_ENTITY, x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.PYRITE_SMOKE_BOMB;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        this.discard();
        spawnSmoke();
        generateDiamondSphere(this.getBlockPos(), 2);
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
            super.onBlockHit(blockHitResult);
            this.discard();
            spawnSmoke();
            generateDiamondSphere(this.getBlockPos(), 2);
    }

    private void generateDiamondSphere(BlockPos center, int radius) {
        if (!this.getWorld().isClient()) {
            World world = this.getWorld();
            int r2 = radius * radius;

            for (int x = -radius; x <= radius; x++) {
                for (int y = -radius; y <= radius; y++) {
                    for (int z = -radius; z <= radius; z++) {
                        if (x * x + y * y + z * z <= r2) {
                            BlockPos pos = center.add(x, y, z);
                            if (world.getBlockState(pos).isAir()) {
                                world.setBlockState(pos, ModBlocks.EXPLODE_ME.getDefaultState());
                            }
                        }
                    }
                }
            }
        }
    }

    private void spawnSmoke() {
        if (!this.getWorld().isClient()) {
            ((net.minecraft.server.world.ServerWorld) this.getWorld()).spawnParticles(
                    ModParticles.PYRITE_PARTICLE,
                    this.getX(), this.getY(), this.getZ(),
                    100,
                    1.5, 1.5, 1.5, // Spread
                    0.05 // Speed
            );
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