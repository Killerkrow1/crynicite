package net.killerkrow.crynicite.init;

import net.killerkrow.crynicite.Crynicite;
import net.killerkrow.crynicite.effect.HeartStrainEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEffects {

    public static final StatusEffect HEARTSTRAIN = new HeartStrainEffect();

    public static void registerEffects() {
        Registry.register(Registries.STATUS_EFFECT, new Identifier(Crynicite.MOD_ID, "heartstrain"), HEARTSTRAIN);
    }
}