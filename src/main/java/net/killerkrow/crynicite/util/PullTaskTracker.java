package net.killerkrow.crynicite.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

import java.util.HashMap;
import java.util.Map;

public class PullTaskTracker {
    private static final Map<LivingEntity, PullSession> activePulls = new HashMap<>();

    public static void startPulling(ServerWorld world, PlayerEntity user, LivingEntity target) {
        activePulls.put(target, new PullSession(user, target, 160));
    }

    public static void tick(ServerWorld world) {
        activePulls.entrySet().removeIf(entry -> {
            LivingEntity target = entry.getKey();
            PullSession session = entry.getValue();

            // The conditions to stop: the target is not with us anymore, the user is removed, your time runs out, or the target is within 1 block
            if (!target.isAlive() || !session.user.isAlive() || session.ticksLeft <= 0) {
                return true;
            }

            double distanceSq = target.squaredDistanceTo(session.user);
            if (distanceSq <= 1.0D * 1.0D) { // ARE YOU WITHIN A BLOCK?
                return true;
            }

            // Pull logic: calculate vector towards the user and apply velocity
            Vec3d direction = session.user.getPos().subtract(target.getPos()).normalize();
            target.setVelocity(direction.multiply(0.35)); // Pull speed adjuster
            target.velocityModified = true;

            session.ticksLeft--;
            return false;
        });
    }

    private static class PullSession {
        final PlayerEntity user;
        final LivingEntity target;
        int ticksLeft;

        PullSession(PlayerEntity user, LivingEntity target, int ticksLeft) {
            this.user = user;
            this.target = target;
            this.ticksLeft = ticksLeft;
        }
    }
}