package net.killerkrow.crynicite.mixin;

import net.killerkrow.crynicite.init.ModBlocks;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractFireBlock.class)
public class AbstractFireBlockMixin {

    @Inject(method = "getState", at = @At("HEAD"), cancellable = true)
    private static void getGildedFireState(BlockView world, BlockPos pos, CallbackInfoReturnable<BlockState> cir) {
        BlockState blockBelow = world.getBlockState(pos.down());

        if (blockBelow.isOf(ModBlocks.PYRITE_BLOCK)) {
            cir.setReturnValue(ModBlocks.PYRITE_FIRE.getDefaultState());
        }
    }
}