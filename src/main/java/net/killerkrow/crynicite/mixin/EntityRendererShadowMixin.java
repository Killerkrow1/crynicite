package net.killerkrow.crynicite.mixin;

import net.killerkrow.crynicite.init.ModEffects;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderDispatcher.class)
public class EntityRendererShadowMixin<T extends Entity> {

    @Inject(
            method = "renderShadow",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void hideCustomEffectShadow(
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            Entity entity,
            float opacity,
            float tickDelta,
            net.minecraft.world.WorldView world,
            float radius,
            CallbackInfo ci
    ) {
        if (entity instanceof LivingEntity livingEntity) {
            if (livingEntity.hasStatusEffect(ModEffects.SPIRITED)) {
                ci.cancel();
            }
        }
    }
}