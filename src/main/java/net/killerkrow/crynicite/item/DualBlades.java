package net.killerkrow.crynicite.item;

import net.killerkrow.crynicite.init.ModEnchantments;
import net.killerkrow.crynicite.util.PullTaskTracker;
import net.killerkrow.crynicite.util.RaycastHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DualBlades extends SwordItem implements Vanishable {
    public DualBlades(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Item.Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient()) {
            NbtCompound nbt = stack.getOrCreateNbt();
            nbt.putBoolean("Unbreakable", true);
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        ItemStack offstack = user.getOffHandStack();

        // Please have my enchantment
        int enchantmentLevel = EnchantmentHelper.getLevel(ModEnchantments.CHAINED, stack);
        int enchantmentLeveloff = EnchantmentHelper.getLevel(ModEnchantments.CHAINED, offstack);
        if (enchantmentLevel > 0 && !world.isClient()) {
            // Be an enttity I'm looking at
            LivingEntity target = RaycastHelper.getTargetEntity(user, 16.0D); // max range

            if (target != null) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 160, 1));
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 160, 1));

                // GET PULLED
                PullTaskTracker.startPulling((ServerWorld) world, user, target);

                user.getItemCooldownManager().set(this, 100);
                return TypedActionResult.success(stack, world.isClient());
            }
        }
        if (enchantmentLeveloff > 0 && !world.isClient()) {
            // Be an enttity I'm looking at
            LivingEntity target = RaycastHelper.getTargetEntity(user, 16.0D); // max range

            if (target != null) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 160, 1));
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 160, 1));

                // GET PULLED
                PullTaskTracker.startPulling((ServerWorld) world, user, target);

                user.getItemCooldownManager().set(this, 100);
                return TypedActionResult.success(stack, world.isClient());
            }
        }
        return TypedActionResult.pass(stack);
    }

    // tooltip
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("tooltip.crynicite.dualblades.tooltip").formatted(Formatting.DARK_PURPLE));
        } else {
            tooltip.add(Text.literal("[SHIFT]").formatted(Formatting.DARK_GRAY));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}