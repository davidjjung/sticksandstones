package com.davigj.sticks_and_stones.common.items;

import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ToolItem;

import java.util.Set;

public class ChopperItem extends ToolItem {

    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet();

    public ChopperItem(float attackDamageIn, float attackSpeedIn, IItemTier tier, Set<Block> effectiveBlocksIn, Properties builderIn) {
        super(attackDamageIn, attackSpeedIn, tier, effectiveBlocksIn, builderIn);
    }
}
