package net.killerkrow.crynicite.init;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.killerkrow.crynicite.Crynicite;
import net.killerkrow.crynicite.entities.*;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<PyriteChunkEntity> PYRITE_CHUNK_ENTITY = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Crynicite.MOD_ID, "pyrite_chunk_projectile"),
            FabricEntityTypeBuilder.<PyriteChunkEntity>create(SpawnGroup.MISC, PyriteChunkEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f)).build());

    public static final EntityType<PyriteSmokeBombEntity> PYRITE_SMOKE_BOMB_ENTITY = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Crynicite.MOD_ID, "pyrite_smoke_bomb_projectile"),
            FabricEntityTypeBuilder.<PyriteSmokeBombEntity>create(SpawnGroup.MISC, PyriteSmokeBombEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f)).build());

    public static final EntityType<SpewEntity> SPEW_ENTITY = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Crynicite.MOD_ID, "spew_entity"),
            FabricEntityTypeBuilder.<SpewEntity>create(SpawnGroup.MISC, SpewEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f)).build());

    public static final EntityType<HeartStrainEntity> HEARTSTRAIN_ENTITY = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Crynicite.MOD_ID, "heartstrain_entity"),
            FabricEntityTypeBuilder.<HeartStrainEntity>create(SpawnGroup.MISC, HeartStrainEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f)).build());

    public static void registerModEntities() {
        Crynicite.LOGGER.info("Registering Mod Entities for " + Crynicite.MOD_ID);
    }
}