package net.auxy.cwood.item;

import net.auxy.cwood.Cwood;
import net.auxy.cwood.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup  {
    public static ItemGroup Amod;

    public static void registerItemGroups() {
        Amod = FabricItemGroup.builder(new Identifier(Cwood.MOD_ID, "jade"))
                .displayName(Text.translatable("itemgroup.jade"))
                .icon(() -> new ItemStack(ModBlocks.WHITE_PLANKS)).build();

    }
}
