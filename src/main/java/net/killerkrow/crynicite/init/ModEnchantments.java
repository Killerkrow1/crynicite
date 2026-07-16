package net.killerkrow.crynicite.init;

import net.killerkrow.crynicite.Crynicite;
import net.killerkrow.crynicite.enchantments.ChainedEnchantment;
import net.killerkrow.crynicite.enchantments.LaunchEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchantments {
    public static final Enchantment LAUNCH = register("launch",
            new LaunchEnchantment());
    public static final Enchantment CHAINED = register("chained",
            new ChainedEnchantment());

    private static Enchantment register(String name, Enchantment enchantment) {
        return Registry.register(Registries.ENCHANTMENT, new Identifier(Crynicite.MOD_ID, name), enchantment);
    }

    public static void registerModEnchantments() {
        Crynicite.LOGGER.info("Registering Enchantments for " + Crynicite.MOD_ID);
    }
}