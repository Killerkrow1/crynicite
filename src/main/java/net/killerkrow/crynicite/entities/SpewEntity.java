package net.killerkrow.crynicite.entities;

import net.killerkrow.crynicite.init.ModEntities;
import net.killerkrow.crynicite.init.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class SpewEntity extends ThrownItemEntity {
    public SpewEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public SpewEntity(World world, double x, double y, double z) {
        super(ModEntities.SPEW_ENTITY, x, y, z, world);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (this.getWorld().isClient()) {
            return;
        }

        Entity targetEntity = entityHitResult.getEntity();
        Entity owner = this.getOwner();

        if (targetEntity == owner) {
            return;
        }

        // Deals the damage
        float damageAmount = 4.0f;
        targetEntity.damage(this.getDamageSources().generic(), damageAmount);

        ((ServerWorld) this.getWorld()).spawnParticles(
                ParticleTypes.CRIT,
                this.getX(), this.getY(), this.getZ(),
                5, 0.5, 0.5, 0.5, 0.0
        );

        this.getWorld().playSound(
                null, this.getX(), this.getY(), this.getZ(),
                SoundEvents.ENTITY_ARROW_HIT, SoundCategory.NEUTRAL,
                0.3f, 1.0f
        );

        this.discard();
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        if (this.getWorld().isClient()) {
            return;
        }
        this.discard();
    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.OBLITUS_STEEL;
    }

    @Override
    protected float getGravity() {
        return 0.0f;
    }
}