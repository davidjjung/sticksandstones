package com.davigj.sticks_and_stones.core.registry;

import com.minecraftabnormals.abnormals_core.core.util.registry.ItemSubRegistryHelper;
import com.davigj.sticks_and_stones.core.SticksAndStonesMod;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SticksAndStonesMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SticksAndStonesItems {
	public static final ItemSubRegistryHelper HELPER = SticksAndStonesMod.REGISTRY_HELPER.getItemSubHelper();

	public static final RegistryObject<Item> PEBBLE_BLOCK = HELPER.createItem("pebble_block", () -> new BlockItem(
			SticksAndStonesBlocks.PEBBLE_BLOCK.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
}