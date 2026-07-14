package net.killerkrow.crynicite.init;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.killerkrow.crynicite.Crynicite;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticles {
    public static final DefaultParticleType PYRITE_PARTICLE =
            registerParticle("pyrite_particle", FabricParticleTypes.simple());

    private static DefaultParticleType registerParticle(String name, DefaultParticleType particleType) {
        return Registry.register(Registries.PARTICLE_TYPE, new Identifier(Crynicite.MOD_ID, name), particleType);
    }

    public static void registerParticles() {
        Crynicite.LOGGER.info("Registering Particles for " + Crynicite.MOD_ID);
    }
}