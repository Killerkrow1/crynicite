package net.killerkrow.crynicite.effect;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class HeartStrainEffect extends StatusEffect {
    public HeartStrainEffect() {
        super(StatusEffectCategory.HARMFUL, 0x4B0082);
        this.addAttributeModifier(
                EntityAttributes.GENERIC_MAX_HEALTH,
                "12345678-1234-1234-1234-123456789abc",
                -3.0,
                EntityAttributeModifier.Operation.ADDITION
        );
    }

    @Override
    public double adjustModifierAmount(int amplifier, EntityAttributeModifier modifier) {
        // Each level like doubles it I think, I can't remember
        return modifier.getValue() * (amplifier + 1);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return false;
    }
}