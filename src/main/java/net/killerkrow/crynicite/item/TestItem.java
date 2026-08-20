package net.killerkrow.crynicite.item;

import net.killerkrow.crynicite.entities.HeartStrainEntity;
import net.killerkrow.crynicite.entities.SpewEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class TestItem extends SwordItem {
    public TestItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack swordStack = user.getStackInHand(hand);
        NbtCompound nbt = swordStack.getOrCreateNbt();

        if (!world.isClient()) {
            if (user.isSneaking()) {
                // Takes the item frfr
                if (nbt.contains("StoredItem")) {
                    NbtCompound storedNbt = nbt.getCompound("StoredItem");
                    ItemStack extracted = ItemStack.fromNbt(storedNbt);
                    nbt.remove("StoredItem");

                    if (!user.giveItemStack(extracted)) {
                        user.dropItem(extracted, false);
                    }
                    world.playSound(null, user.getX(), user.getY(), user.getZ(),
                            SoundEvents.ITEM_BUNDLE_REMOVE_ONE, SoundCategory.PLAYERS, 0.8f, 1.0f);
                    return TypedActionResult.success(swordStack);
                }
            } else {
                // Inserts the item frfr
                if (!nbt.contains("StoredItem")) {
                    ItemStack handStack = user.getStackInHand(hand == Hand.MAIN_HAND ? Hand.OFF_HAND : Hand.MAIN_HAND);
                    if (isValidGem(handStack)) {
                        ItemStack singleGem = handStack.copy();
                        singleGem.setCount(1);

                        NbtCompound storedNbt = new NbtCompound();
                        singleGem.writeNbt(storedNbt);
                        nbt.put("StoredItem", storedNbt);

                        handStack.decrement(1);
                        world.playSound(null, user.getX(), user.getY(), user.getZ(),
                                SoundEvents.ITEM_BUNDLE_INSERT, SoundCategory.PLAYERS, 0.8f, 1.0f);
                        return TypedActionResult.success(swordStack);
                    }
                } else {
                    // Will trigger the ability based on the stored item
                    triggerAbility(world, user, nbt.getCompound("StoredItem"));
                    return TypedActionResult.success(swordStack);
                }
            }
        }
        return TypedActionResult.pass(swordStack);
    }

    private boolean isValidGem(ItemStack stack) {
        return stack.isOf(Items.DIAMOND) ||
                stack.isOf(Items.GOLD_INGOT) ||
                stack.isOf(Items.EMERALD);
    }

    private void triggerAbility(World world, PlayerEntity user, NbtCompound storedNbt) {
        ItemStack storedStack = ItemStack.fromNbt(storedNbt);
        if (storedStack.isOf(Items.DIAMOND)) {
            Vec3d startPos = user.getPos().add(0, user.getStandingEyeHeight() - 0.2, 0);
            Vec3d lookDir = user.getRotationVector();

            Vec3d spreadDir = new Vec3d(-lookDir.z, 0, lookDir.x).normalize();

            for (int i = -3; i <= 3; i++) {
                Vec3d spawnPos = startPos.add(spreadDir.multiply(i * 0.4));

                ThrownItemEntity throwableEntity = new SpewEntity(world, user.getX(), user.getY()+1.5 - 0.0D, user.getZ());
                throwableEntity.setPos(spawnPos.x, spawnPos.y, spawnPos.z);
                throwableEntity.setVelocity(lookDir.x * 1.5, lookDir.y * 1.5, lookDir.z * 1.5, 1.5f, 0.0f);

                world.spawnEntity(throwableEntity);
            }

            world.playSound(null, user.getX(), user.getY(), user.getZ(),
                    SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F);

            user.getItemCooldownManager().set(this, 100);
            user.sendMessage(Text.literal("Projectile Ability Activated."), true);

        } else if (storedStack.isOf(Items.GOLD_INGOT)) {
            // Casually theives from the Paradigm Mod :3
            double radius = 10.0D;
            Box boundingBox = user.getBoundingBox().expand(radius);

            List<Entity> entities = world.getOtherEntities(user, boundingBox, entity -> entity instanceof LivingEntity);

            for (Entity entity : entities) {
                Vec3d pushDirection = entity.getPos().subtract(user.getPos()).normalize();

                pushDirection = pushDirection.add(0, 0.5D, 0);

                entity.addVelocity(pushDirection.multiply(2.0D));

                entity.velocityModified = true;
            }
            user.getItemCooldownManager().set(this, 100);
            user.sendMessage(Text.literal("Push Away Ability Activated."), true);

        } else if (storedStack.isOf(Items.EMERALD)) {
            Vec3d startPos = user.getPos().add(0, user.getStandingEyeHeight() - 0.2, 0);
            Vec3d lookDir = user.getRotationVector();

            Vec3d spreadDir = new Vec3d(-lookDir.z, 0, lookDir.x).normalize();

            for (int i = -3; i <= 3; i++) {
                Vec3d spawnPos = startPos.add(spreadDir.multiply(i * 0.4));

                ThrownItemEntity throwableEntity = new HeartStrainEntity(world, user.getX(), user.getY()+1.5 - 0.0D, user.getZ());
                throwableEntity.setPos(spawnPos.x, spawnPos.y, spawnPos.z);
                throwableEntity.setVelocity(lookDir.x * 1.5, lookDir.y * 1.5, lookDir.z * 1.5, 1.5f, 0.0f);

                world.spawnEntity(throwableEntity);
            }

            user.getItemCooldownManager().set(this, 100);
            user.sendMessage(Text.literal("Heartstrain Ability Activated."), true);
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        NbtCompound nbt = stack.getNbt();
        if (nbt != null && nbt.contains("StoredItem")) {
            ItemStack stored = ItemStack.fromNbt(nbt.getCompound("StoredItem"));
            tooltip.add(Text.literal("Engraved Item: ").append(stored.getName()));
        } else {
            tooltip.add(Text.literal("Engraved Item: NONE"));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}