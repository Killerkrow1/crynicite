package net.killerkrow.crynicite.item;

import net.killerkrow.crynicite.init.ModEnchantments;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Glaive extends SwordItem implements Vanishable {
    public Glaive(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Item.Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 72000; // Standard maximum bow/charging duration
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        // Check if item is on cooldown (4 seconds = 80 ticks)
        if (user.getItemCooldownManager().isCoolingDown(this)) {
            return TypedActionResult.fail(stack);
        }

        int level = EnchantmentHelper.getLevel(ModEnchantments.LAUNCH, stack);
        if (level > 0) {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(stack);
        }

        return TypedActionResult.pass(stack);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!(user instanceof PlayerEntity player)) return;

        int level = EnchantmentHelper.getLevel(ModEnchantments.LAUNCH, stack);
        if (level > 0) {
            // The charge time/time held
            int ticksCharged = this.getMaxUseTime(stack) - remainingUseTicks;

            // TThe Formula: distance scales with the time charged, then modified by level of enchantment, and capped at 12 blocks
            // 1 second of full charge is the cap per level factor
            float chargeFactor = Math.min((float) ticksCharged / 20.0f, 1.0f);

            // 12 max blocks. 1 block = 1 unit of velocity for initial impulse
            float targetBlocks = Math.min(12.0f, chargeFactor * (4.0f * level));

            if (!world.isClient()) {
                Vec3d lookDir = player.getRotationVector();
                Vec3d velocity = new Vec3d(lookDir.x, 0.0, lookDir.z).normalize().multiply(targetBlocks * 1.35); // Adjustt this if needed
                player.addVelocity(velocity.x, 0.2, velocity.z); //Need a small upwards boost yk
                player.velocityModified = true;

                // Countdown
                player.getItemCooldownManager().set(this, 80);
            }
        }
    }

    // tooltip
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("tooltip.crynicite.glaive.tooltip").formatted(Formatting.DARK_PURPLE));
        } else {
            tooltip.add(Text.literal("[SHIFT]").formatted(Formatting.DARK_GRAY));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}