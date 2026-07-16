package net.killerkrow.crynicite.mixin;

import net.killerkrow.crynicite.Crynicite;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;

@Mixin(ModelLoader.class)
public abstract class ModelLoaderMixin {
    @Shadow
    protected abstract void addModel(ModelIdentifier modelId);

    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/model/ModelLoader;addModel(Lnet/minecraft/client/util/ModelIdentifier;)V", ordinal = 3, shift = At.Shift.AFTER))
    public void addItem(BlockColors blockColors, Profiler profiler, Map<Identifier, JsonUnbakedModel> jsonUnbakedModels, Map<Identifier, List<ModelLoader.SourceTrackedData>> blockStates, CallbackInfo ci) {
        this.addModel(new ModelIdentifier(Crynicite.MOD_ID, "cleaversword_held", "inventory"));
        this.addModel(new ModelIdentifier(Crynicite.MOD_ID, "greathammer_held", "inventory"));
        this.addModel(new ModelIdentifier(Crynicite.MOD_ID, "katana_held", "inventory"));
        this.addModel(new ModelIdentifier(Crynicite.MOD_ID, "scissorblades_blue_held", "inventory"));
        this.addModel(new ModelIdentifier(Crynicite.MOD_ID, "scissorblades_gold_held", "inventory"));
        this.addModel(new ModelIdentifier(Crynicite.MOD_ID, "scissorblades_full_held", "inventory"));
        this.addModel(new ModelIdentifier(Crynicite.MOD_ID, "glaive_held", "inventory"));
        this.addModel(new ModelIdentifier(Crynicite.MOD_ID, "dual_blades_held", "inventory"));
    }
}