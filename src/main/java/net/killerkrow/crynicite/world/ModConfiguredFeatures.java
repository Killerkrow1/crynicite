package net.killerkrow.crynicite.world;

import net.killerkrow.crynicite.Crynicite;
import net.killerkrow.crynicite.init.ModBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_KEY = registerKey("overworld_pyrite_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> NETHER_ORE_KEY = registerKey("nether_pyrite_ore");

    public static void boostrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplacables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplacables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherReplacables = new TagMatchRuleTest(BlockTags.BASE_STONE_NETHER);

        List<OreFeatureConfig.Target> overworldPyriteOres =
                List.of(OreFeatureConfig.createTarget(stoneReplacables, ModBlocks.PYRITE_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.DEEPSLATE_PYRITE_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> netherPyriteOres =
                List.of(OreFeatureConfig.createTarget(netherReplacables, ModBlocks.NETHER_PYRITE_ORE.getDefaultState()));

        register(context, ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldPyriteOres, 8));
        register(context, NETHER_ORE_KEY, Feature.ORE, new OreFeatureConfig(netherPyriteOres, 8));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(Crynicite.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}