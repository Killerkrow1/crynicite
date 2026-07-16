package net.killerkrow.crynicite;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.killerkrow.crynicite.entities.ModEntities;
import net.killerkrow.crynicite.init.ModBlocks;
import net.killerkrow.crynicite.init.ModParticles;
import net.killerkrow.crynicite.util.PyriteParticle;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class CryniciteClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EXPLODE_ME, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CITRINE_BLOCK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.AMETRINE_BLOCK, RenderLayer.getTranslucent());

        EntityRendererRegistry.register(ModEntities.PYRITE_CHUNK_ENTITY, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.PYRITE_SMOKE_BOMB_ENTITY, FlyingItemEntityRenderer::new);

        ParticleFactoryRegistry.getInstance().register(ModParticles.PYRITE_PARTICLE, PyriteParticle.Factory::new);
    }
}
