package net.killerkrow.crynicite.mixin;

import net.killerkrow.crynicite.Crynicite;
import net.killerkrow.crynicite.init.ModItems;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    @ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
    public BakedModel useItem(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded,
                              MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

        if (stack.isOf(ModItems.CRYNICITE_CLEAVERSWORD) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).Crynicite$getModels().getModelManager().getModel(new ModelIdentifier(Crynicite.MOD_ID,
                    "cleaversword_held", "inventory"));
        }
        if (stack.isOf(ModItems.CRYSEUM_GREATHAMMER) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).Crynicite$getModels().getModelManager().getModel(new ModelIdentifier(Crynicite.MOD_ID,
                    "greathammer_held", "inventory"));
        }
        if (stack.isOf(ModItems.CINICITE_KATANA) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).Crynicite$getModels().getModelManager().getModel(new ModelIdentifier(Crynicite.MOD_ID,
                    "katana_held", "inventory"));
        }
        if (stack.isOf(ModItems.CINICITE_SCISSORBLADES) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).Crynicite$getModels().getModelManager().getModel(new ModelIdentifier(Crynicite.MOD_ID,
                    "scissorblades_blue_held", "inventory"));
        }
        if (stack.isOf(ModItems.CRYSEUM_SCISSORBLADES) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).Crynicite$getModels().getModelManager().getModel(new ModelIdentifier(Crynicite.MOD_ID,
                    "scissorblades_gold_held", "inventory"));
        }
        if (stack.isOf(ModItems.CRYNICITE_SCISSORBLADES) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).Crynicite$getModels().getModelManager().getModel(new ModelIdentifier(Crynicite.MOD_ID,
                    "scissorblades_full_held", "inventory"));
        }
        if (stack.isOf(ModItems.AMETRINE_GLAIVE) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).Crynicite$getModels().getModelManager().getModel(new ModelIdentifier(Crynicite.MOD_ID,
                    "glaive_held", "inventory"));
        }
        if (stack.isOf(ModItems.AMETRINE_DUAL_BLADES) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).Crynicite$getModels().getModelManager().getModel(new ModelIdentifier(Crynicite.MOD_ID,
                    "dual_blades_held", "inventory"));
        }

        if (stack.isOf(ModItems.OBLITUS_CLEAVER) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).Crynicite$getModels().getModelManager().getModel(new ModelIdentifier(Crynicite.MOD_ID,
                    "cleaver_held", "inventory"));
        }
        if (stack.isOf(ModItems.OBLITUS_GREATAXE) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).Crynicite$getModels().getModelManager().getModel(new ModelIdentifier(Crynicite.MOD_ID,
                    "greataxe_held", "inventory"));
        }
        if (stack.isOf(ModItems.OBLITUS_GREATPICKAXE) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).Crynicite$getModels().getModelManager().getModel(new ModelIdentifier(Crynicite.MOD_ID,
                    "greatpickaxe_held", "inventory"));
        }
        if (stack.isOf(ModItems.OBLITUS_GREATSHOVEL) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).Crynicite$getModels().getModelManager().getModel(new ModelIdentifier(Crynicite.MOD_ID,
                    "greatshovel_held", "inventory"));
        }
        if (stack.isOf(ModItems.OBLITUS_GREATSWORD) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).Crynicite$getModels().getModelManager().getModel(new ModelIdentifier(Crynicite.MOD_ID,
                    "greatsword_held", "inventory"));
        }
        if (stack.isOf(ModItems.OBLITUS_RIPPERSWORD) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).Crynicite$getModels().getModelManager().getModel(new ModelIdentifier(Crynicite.MOD_ID,
                    "rippersword_held", "inventory"));
        }
        if (stack.isOf(ModItems.OBLITUS_SCISSORBLADES_FULL) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).Crynicite$getModels().getModelManager().getModel(new ModelIdentifier(Crynicite.MOD_ID,
                    "oblitus_scissorblades_full_held", "inventory"));
        }
        if (stack.isOf(ModItems.OBLITUS_SCISSORBLADES_HALF) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).Crynicite$getModels().getModelManager().getModel(new ModelIdentifier(Crynicite.MOD_ID,
                    "oblitus_scissorblades_half_held", "inventory"));
        }

        return value;
    }
}