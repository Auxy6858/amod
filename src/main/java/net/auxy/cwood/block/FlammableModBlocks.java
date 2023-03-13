package net.auxy.cwood.block;

import net.auxy.cwood.Cwood;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;

public class FlammableModBlocks {
    public static void registerFlammableBlocks() {
        FlammableBlockRegistry registry = FlammableBlockRegistry.getDefaultInstance();


        registry.add(ModBlocks.RED_PLANKS, 5, 20);
    }
}
