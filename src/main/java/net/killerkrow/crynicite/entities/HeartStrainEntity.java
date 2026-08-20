package net.killerkrow.crynicite.entities;

import net.killerkrow.crynicite.init.ModEffects;
import net.killerkrow.crynicite.init.ModEntities;
import net.killerkrow.crynicite.init.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class HeartStrainEntity extends ThrownItemEntity {
    public HeartStrainEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public HeartStrainEntity(World world, double x, double y, double z) {
        super(ModEntities.SPEW_ENTITY, x, y, z, world);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (this.getWorld().isClient()) return;

        Entity target = entityHitResult.getEntity();

        float damageAmount = 4.0f;
        target.damage(this.getDamageSources().generic(), damageAmount);

        if (target instanceof LivingEntity living) {
            living.addStatusEffect(new StatusEffectInstance(ModEffects.HEARTSTRAIN, 100, 1));
        }
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