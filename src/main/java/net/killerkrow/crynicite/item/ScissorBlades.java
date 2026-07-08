package net.killerkrow.crynicite.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.killerkrow.crynicite.init.ModItems;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;

import java.util.List;

public class ScissorBlades extends SwordItem implements Vanishable {
    private final float attackDamage;
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    public ScissorBlades(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Item.Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
        this.attackDamage = (float) attackDamage + toolMaterial.getAttackDamage();
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Weapon modifier", (double) this.attackDamage, EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Weapon modifier", (double) attackSpeed, EntityAttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }

    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public ItemStack getRecipeRemainder(ItemStack stack) {
        return new ItemStack(this);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack heldItem = user.getStackInHand(hand);

        // CROUGH AND RIGHT CLICK
        if (user.isSneaking() && heldItem.hasNbt()) {
            NbtCompound tag = heldItem.getNbt();

            ItemStack item1 = new ItemStack(ModItems.CRYSEUM_SCISSORBLADES);
            ItemStack item2 = new ItemStack(ModItems.CINICITE_SCISSORBLADES);

            // Take your enchantments back pwease
            if (tag.contains("MainHandEnchants")) {
                item1.getOrCreateNbt().put("Enchantments", tag.getList("MainHandEnchants", 10));
            }
            if (tag.contains("OffHandEnchants")) {
                item2.getOrCreateNbt().put("Enchantments", tag.getList("OffHandEnchants", 10));
            }

            heldItem.decrement(1);
            if (!world.isClient) {

                // Get torn apart.
                ItemEntity itemEntity = new ItemEntity(
                        world,
                        user.getX(),
                        user.getY() + 1.0,
                        user.getZ(),
                        item1
                );
                ItemEntity itemEntity2 = new ItemEntity(
                        world,
                        user.getX(),
                        user.getY() + 1.0,
                        user.getZ(),
                        item2
                );

                // Spawns the items
                world.spawnEntity(itemEntity);
                world.spawnEntity(itemEntity2);

                world.playSound(null, user.getX(), user.getY(), user.getZ(),
                        SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.PLAYERS, 0.5F, 1.0F);
            }
            return TypedActionResult.success(heldItem);
        }
        return TypedActionResult.pass(heldItem);
    }

    // tooltip
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("tooltip.crynicite.scissorblades.tooltip").formatted(Formatting.DARK_PURPLE));
        } else {
            tooltip.add(Text.literal("[SHIFT]").formatted(Formatting.DARK_GRAY));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}