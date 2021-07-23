package com.davigj.sticks_and_stones.core.other;

import com.davigj.sticks_and_stones.core.SticksAndStonesMod;
import com.davigj.sticks_and_stones.core.registry.SticksAndStonesItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@EventBusSubscriber(modid = SticksAndStonesMod.MOD_ID)
public class SticksAndStonesEvents {
    Logger LOGGER = LogManager.getLogger(SticksAndStonesMod.MOD_ID);

    public SticksAndStonesEvents() {
    }

    @SubscribeEvent
    public static void onEntityInteract (PlayerInteractEvent.EntityInteractSpecific event) {
        ItemStack stack = event.getItemStack();
        Entity entity = event.getTarget();
        Hand hand = event.getHand();
        PlayerEntity player = event.getPlayer();
        if ((stack.getItem() == SticksAndStonesItems.COBBLE_BLOCK.get() && entity instanceof PigEntity && !((PigEntity) entity).isChild())) {
            player.playSound(SoundEvents.BLOCK_GLASS_PLACE, 1.0F, 1.0F);
            player.swingArm(hand);
            // i'M wOrKiNg oN iT
        }
    }

    @SubscribeEvent
    public void onRightClick(PlayerInteractEvent.RightClickBlock event) {
        doTheShovelThingHomie(event, ToolType.SHOVEL, Blocks.DIRT, Blocks.GRASS_PATH);
    }

    public static void doTheShovelThingHomie(PlayerInteractEvent.RightClickBlock event, ToolType tool, Block target, Block result) {
        PlayerEntity player = event.getPlayer();
        BlockPos pos = event.getPos();
        Direction facing = event.getFace();
        World world = event.getWorld();
        Hand hand = event.getHand();
        ItemStack itemstack = player.getHeldItem(hand);
        BlockState state = world.getBlockState(pos);

        if(!itemstack.getItem().getToolTypes(itemstack).contains(tool) || itemstack.getDestroySpeed(state) < 1.0F)
            return;

        if(facing != null && player.canPlayerEdit(pos.offset(facing), facing, itemstack)) {
            Block block = state.getBlock();

            if(facing != Direction.DOWN && world.getBlockState(pos.up()).getMaterial() == Material.AIR && block == target) {
                BlockState pathState = result.getDefaultState();
                world.playSound(player, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);

                if(!world.isRemote) {
                    world.setBlockState(pos, pathState, 11);
                }

                event.setCanceled(true);
                event.setCancellationResult(ActionResultType.SUCCESS);
            }
        }
    }

//    @SubscribeEvent
//    public static void rightClickBlock(PlayerInteractEvent.RightClickBlock event) {
//        World world = event.getWorld();
//        PlayerEntity player = event.getPlayer();
//        ItemStack itemstack = event.getItemStack();
//        Item item = itemstack.getItem();
//        BlockPos pos = event.getPos();
//        Hand handIn = player.getActiveHand();
//        BlockState state = world.getBlockState(pos);
//        ItemEntity itemDrop = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(SticksAndStonesItems.STONE_SHOVEL_HEAD.get()));
//        if (!player.isCrouching() && item == SticksAndStonesItems.COBBLE_BLOCK.get()) {
//            Block block = state.getBlock();
//            world.removeBlock(pos, true);
//            world.addEntity(itemDrop);
//            world.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_STONE_BREAK, SoundCategory.BLOCKS, 1.0F, 0.9F + world.rand.nextFloat() * 0.4F);
////            player.swingArm(handIn);
////            return ActionResultType.SUCCESS;
//        }
////        return ActionResultType.PASS;
//    }
}
