package net.killerkrow.crynicite.mixin;

import net.killerkrow.crynicite.init.ModItems;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractFurnaceBlockEntity.class)
public class FurnaceExplosionMixin {

    @Inject(method = "setStack", at = @At("HEAD"))
    private void onSetStack(int slot, ItemStack stack, CallbackInfo ci) {
        AbstractFurnaceBlockEntity furnace = (AbstractFurnaceBlockEntity) (Object) this;
        World world = furnace.getWorld();

        // BY MY ITEM
        if (world == null || world.isClient() || !stack.isOf(ModItems.RAW_PYRITE)) {
            return;
        }

        BlockPos pos = furnace.getPos();
        stack.decrement(1);

        // Bye furnace
        world.removeBlock(pos, false);

        world.createExplosion(
                null,
                pos.getX() + 0.5,
                pos.getY() + 0.5,
                pos.getZ() + 0.5,
                4.0F,
                true,
                World.ExplosionSourceType.BLOCK
        );
    }
}