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
import net.minecraft.nbt.NbtList;
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

public class RightScissorBlades extends SwordItem implements Vanishable {
    private final float attackDamage;
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    public RightScissorBlades(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Item.Settings settings) {
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
        ItemStack mainHand = user.getStackInHand(hand);
        ItemStack mainHand2 = user.getStackInHand(hand);
        ItemStack offHand = user.getStackInHand(Hand.OFF_HAND);
        ItemStack offHand2 = user.getStackInHand(Hand.OFF_HAND);

        // HOLD THE RIGHT STUFF, AND DON'T SNEAK
        if (offHand.isOf(ModItems.CRYSEUM_SCISSORBLADES) && !user.isSneaking()) {
            ItemStack fusedItem = new ItemStack(ModItems.CRYNICITE_SCISSORBLADES);
            NbtCompound fusedNbt = new NbtCompound();

            // Enchants get storeeeeeddddddddddd
            NbtList mainEnchants = mainHand.getEnchantments();
            NbtList offEnchants = offHand.getEnchantments();

            fusedNbt.put("MainHandEnchants", offEnchants);
            fusedNbt.put("OffHandEnchants", mainEnchants);
            fusedItem.setNbt(fusedNbt);

            // Here take new item
            mainHand.decrement(1);
            offHand.decrement(1);
            if (!world.isClient) {
                ItemEntity itemEntity = new ItemEntity(
                        world,
                        user.getX(),
                        user.getY() + 1.0,
                        user.getZ(),
                        fusedItem
                );

                // Spawns the item
                world.spawnEntity(itemEntity);

                world.playSound(null, user.getX(), user.getY(), user.getZ(),
                        SoundEvents.BLOCK_ANVIL_USE, SoundCategory.PLAYERS, 0.5F, 1.0F);
            }
            return TypedActionResult.success(mainHand);
        }
        return TypedActionResult.pass(mainHand);
    }

    // tooltip
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("tooltip.crynicite.rightscissor.tooltip").formatted(Formatting.DARK_PURPLE));
        } else {
            tooltip.add(Text.literal("[SHIFT]").formatted(Formatting.DARK_GRAY));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}