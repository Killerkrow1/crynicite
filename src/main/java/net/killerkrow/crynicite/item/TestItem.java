package net.killerkrow.crynicite.item;

import net.killerkrow.crynicite.entities.HeartStrainEntity;
import net.killerkrow.crynicite.entities.SpewEntity;
import net.killerkrow.crynicite.init.ModEffects;
import net.killerkrow.crynicite.init.ModItems;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
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
        return stack.isOf(ModItems.CRYSEUM_CORE) ||
                stack.isOf(ModItems.CINICITE_CORE) ||
                stack.isOf(ModItems.OBLITUS_CORE) ||
                stack.isOf(ModItems.AMETRINE_CORE) ||
                stack.isOf(ModItems.PYRITE_CORE);
    }

    private void triggerAbility(World world, PlayerEntity user, NbtCompound storedNbt) {
        ItemStack storedStack = ItemStack.fromNbt(storedNbt);
        if (storedStack.isOf(ModItems.CINICITE_CORE)) {
            Vec3d startPos = user.getPos().add(0, user.getStandingEyeHeight() - 0.2, 0);
            Vec3d lookDir = user.getRotationVector();

            Vec3d spreadDir = new Vec3d(-lookDir.z, 0, lookDir.x).normalize();

            for (int i = -3; i <= 3; i++) {
                Vec3d spawnPos = startPos.add(spreadDir.multiply(i * 0.4));

                ThrownItemEntity throwableEntity = new SpewEntity(world, user.getX(), user.getY()+1.5 - 0.0D, user.getZ());
                throwableEntity.setPos(spawnPos.x, spawnPos.y, spawnPos.z);
                throwableEntity.setVelocity(lookDir.x * 1.5, lookDir.y * 1.5, lookDir.z * 1.5, 1.5f, 0.0f);
                throwableEntity.setOwner(user);

                world.spawnEntity(throwableEntity);
            }

            world.playSound(null, user.getX(), user.getY(), user.getZ(),
                    SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F);

            user.sendMessage(Text.literal("Cinicite Shatter Shard Ability Activated."), true);

        } else if (storedStack.isOf(ModItems.CRYSEUM_CORE)) {
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
            user.sendMessage(Text.literal("Cryseum Repulse Ability Activated."), true);

        } else if (storedStack.isOf(ModItems.OBLITUS_CORE)) {
            Vec3d startPos = user.getPos().add(0, user.getStandingEyeHeight() - 0.2, 0);
            Vec3d lookDir = user.getRotationVector();

            Vec3d spreadDir = new Vec3d(-lookDir.z, 0, lookDir.x).normalize();

            for (int i = -3; i <= 3; i++) {
                Vec3d spawnPos = startPos.add(spreadDir.multiply(i * 0.4));

                ThrownItemEntity throwableEntity = new HeartStrainEntity(world, user.getX(), user.getY()+1.5 - 0.0D, user.getZ());
                throwableEntity.setPos(spawnPos.x, spawnPos.y, spawnPos.z);
                throwableEntity.setVelocity(lookDir.x * 1.5, lookDir.y * 1.5, lookDir.z * 1.5, 1.5f, 0.0f);
                throwableEntity.setOwner(user);

                world.spawnEntity(throwableEntity);
            }

            user.sendMessage(Text.literal("Oblitus Heartstrain Ability Activated."), true);
        } else if (storedStack.isOf(ModItems.PYRITE_CORE)) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 600, 0));

            BlockPos playerPos = user.getBlockPos();
            int radius = 2;

            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    for (int y = -1; y <= 1; y++) {
                        BlockPos targetPos = playerPos.add(x, y, z);
                        if (world.isAir(targetPos) && world.getBlockState(targetPos.down()).isOpaque()) {
                            world.setBlockState(targetPos, Blocks.FIRE.getDefaultState());
                        }
                    }
                }
            }

            Vec3d velocity = user.getVelocity();
            double horizontalSpeed = Math.sqrt(velocity.x * velocity.x + velocity.z * velocity.z);

            if (horizontalSpeed > 0.01) {
                double boostMultiplier = 2.5;
                double motionX = (velocity.x / horizontalSpeed) * boostMultiplier;
                double motionZ = (velocity.z / horizontalSpeed) * boostMultiplier;

                user.setVelocity(motionX, velocity.y, motionZ);
                user.velocityModified = true;
            }

            user.sendMessage(Text.literal("Pyrite Dash Ability Activated."), true);
        } else if (storedStack.isOf(ModItems.AMETRINE_CORE)) {
            user.addStatusEffect(new StatusEffectInstance(ModEffects.SPIRITED, 120, 0,
                    false, false, false));

            user.sendMessage(Text.literal("Ametrine Spirit Ability Activated."), true);
        }
        user.getItemCooldownManager().set(this, 100);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        NbtCompound nbt = stack.getNbt();
        if (Screen.hasShiftDown()) {
            if (nbt != null && nbt.contains("StoredItem")) {
                ItemStack stored = ItemStack.fromNbt(nbt.getCompound("StoredItem"));
                tooltip.add(Text.literal("Engraved Item: ").append(stored.getName()));
            } else {
                tooltip.add(Text.literal("Engraved Item: NONE"));
            }
        } else {
            tooltip.add(Text.literal("[SHIFT]").formatted(Formatting.DARK_GRAY));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient()) {
            NbtCompound nbt = stack.getOrCreateNbt();
            nbt.putBoolean("Unbreakable", true);
        }
    }
}