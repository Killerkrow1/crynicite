package net.killerkrow.crynicite.mixin;

import net.killerkrow.crynicite.item.CleaverSword;
import net.killerkrow.crynicite.item.GreatHammer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class SwordDisableShieldMixin {

    @Shadow public abstract boolean isBlocking();

    @Inject(method = "damage", at = @At("HEAD"))
    private void onDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (source.getAttacker() instanceof PlayerEntity player) {
            ItemStack heldItem = player.getMainHandStack();

            if (heldItem.getItem() instanceof GreatHammer) {
                LivingEntity target = (LivingEntity) (Object) this;
                if (target.isBlocking()) {
                    target.disablesShield();
                }
            }
            if (heldItem.getItem() instanceof CleaverSword) {
                LivingEntity target = (LivingEntity) (Object) this;
                if (target.isBlocking()) {
                    target.disablesShield();
                }
            }
        }
    }
}