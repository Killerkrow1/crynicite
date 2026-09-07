package net.killerkrow.crynicite.mixin;

import net.killerkrow.crynicite.item.*;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntityRenderer.class)
public class TwoHandedGripMixin {
    @Inject(method = "getArmPose", at = @At("HEAD"), cancellable = true)
    private static void crynicite$getArmPoseDR(AbstractClientPlayerEntity player, Hand hand,
                                               CallbackInfoReturnable<BipedEntityModel.ArmPose> cir) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.getItem() instanceof ScissorBlades) {
            if (!player.isUsingItem()) {
                cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_CHARGE);
            }
        }
        if (itemStack.getItem() instanceof GreatHammer) {
            if (!player.isUsingItem()) {
                cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_CHARGE);
            }
        }
        if (itemStack.getItem() instanceof OblitusScissorBlades) {
            if (!player.isUsingItem()) {
                cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_CHARGE);
            }
        }
        if (itemStack.getItem() instanceof Cleaver) {
            if (!player.isUsingItem()) {
                cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_CHARGE);
            }
        }
        if (itemStack.getItem() instanceof GreatAxe) {
            if (!player.isUsingItem()) {
                cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_CHARGE);
            }
        }
        if (itemStack.getItem() instanceof GreatShovel) {
            if (!player.isUsingItem()) {
                cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_CHARGE);
            }
        }
        if (itemStack.getItem() instanceof GreatPickaxe) {
            if (!player.isUsingItem()) {
                cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_CHARGE);
            }
        }
        if (itemStack.getItem() instanceof GreatSword) {
            if (!player.isUsingItem()) {
                cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_CHARGE);
            }
        }
        if (itemStack.getItem() instanceof Rippersword) {
            if (!player.isUsingItem()) {
                cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_CHARGE);
            }
        }
        if (itemStack.getItem() instanceof TestItem) {
            if (!player.isUsingItem()) {
                cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_CHARGE);
            }
        }
    }
}
