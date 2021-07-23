package com.davigj.sticks_and_stones.core.other;

import com.davigj.sticks_and_stones.core.registry.SticksAndStonesBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class SticksAndStonesClient {
    public static void registerRenderLayers() {
        RenderTypeLookup.setRenderLayer(SticksAndStonesBlocks.PEBBLE_BLOCK.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(SticksAndStonesBlocks.COBBLE_BLOCK.get(), RenderType.getCutout());

    }
}
