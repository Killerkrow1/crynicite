package net.killerkrow.crynicite.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class RaycastHelper {
    public static LivingEntity getTargetEntity(PlayerEntity player, double maxDistance) {
        World world = player.getWorld();
        Vec3d eyePosition = player.getCameraPosVec(1.0F);
        Vec3d rotation = player.getRotationVec(1.0F);
        Vec3d endPosition = eyePosition.add(rotation.x * maxDistance, rotation.y * maxDistance, rotation.z * maxDistance);

        Box box = player.getBoundingBox().stretch(rotation.multiply(maxDistance)).expand(1.0D, 1.0D, 1.0D);

        EntityHitResult hitResult = ProjectileUtil.raycast(
                player, eyePosition, endPosition, box,
                entity -> entity instanceof LivingEntity && entity != player, maxDistance * maxDistance
        );

        if (hitResult != null && hitResult.getEntity() instanceof LivingEntity livingEntity) {
            return livingEntity;
        }
        return null;
    }
}