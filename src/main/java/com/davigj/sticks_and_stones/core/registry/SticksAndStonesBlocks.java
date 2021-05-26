package com.davigj.sticks_and_stones.core.registry;

import com.davigj.sticks_and_stones.common.blocks.CobbleBlock;
import com.davigj.sticks_and_stones.common.blocks.PebbleBlock;
import com.minecraftabnormals.abnormals_core.core.util.registry.BlockSubRegistryHelper;
import com.davigj.sticks_and_stones.core.SticksAndStonesMod;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SticksAndStonesMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SticksAndStonesBlocks {
	public static final BlockSubRegistryHelper HELPER = SticksAndStonesMod.REGISTRY_HELPER.getBlockSubHelper();

	public static final RegistryObject<Block> PEBBLE_BLOCK = HELPER.createBlockNoItem("pebble_block", () -> new PebbleBlock(
			Block.Properties.from(Blocks.DIRT).doesNotBlockMovement().notSolid().sound(SoundType.STONE)
					.zeroHardnessAndResistance()));

	public static final RegistryObject<Block> COBBLE_BLOCK = HELPER.createBlockNoItem("cobble_block", () -> new CobbleBlock(
			Block.Properties.from(Blocks.DIRT).sound(SoundType.STONE)
					.zeroHardnessAndResistance()));
}
