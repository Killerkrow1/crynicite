package net.killerkrow.crynicite.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class SpiritedEffect extends StatusEffect {
    public SpiritedEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0x26FFC5);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return false;
    }
}