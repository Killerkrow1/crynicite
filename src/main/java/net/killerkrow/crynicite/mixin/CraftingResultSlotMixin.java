package net.killerkrow.crynicite.mixin;

import net.killerkrow.crynicite.init.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.CraftingResultSlot;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CraftingResultSlot.class)
public class CraftingResultSlotMixin {
    @Inject(method = "onTakeItem", at = @At("HEAD"))
    private void onTakeItem(PlayerEntity player, ItemStack stack, CallbackInfo ci) {
        if (!player.getWorld().isClient() && stack.isOf(ModItems.CRYNICITE_SCISSORBLADES)) {
            player.sendMessage(Text.translatable("message.crynicite.crynicite_scissorblades"), false);
        }
    }
}