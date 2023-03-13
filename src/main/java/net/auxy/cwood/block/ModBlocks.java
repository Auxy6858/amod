package net.auxy.cwood.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.auxy.cwood.Cwood;
import net.auxy.cwood.item.ModItemGroup;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;



public class ModBlocks {
    public static final Block JADE_ORE = registerBlock("jade_ore", new ExperienceDroppingBlock(FabricBlockSettings.of(Material.STONE).strength(4.0f).requiresTool(), UniformIntProvider.create(2, 6)), ModItemGroup.Amod);
    public static final Block RED_PLANKS = registerBlock("red_planks", new Block(AbstractBlock.Settings.of(Material.WOOD, MapColor.RED).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD)), ModItemGroup.Amod);
    public static final Block WHITE_PLANKS = registerBlock("white_planks", new Block(AbstractBlock.Settings.of(Material.WOOD, MapColor.WHITE).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD)), ModItemGroup.Amod);
    public static final Block RED_WOOD_STAIRS = registerBlock("red_wood_stairs", new StairsBlock(RED_PLANKS.getDefaultState(), AbstractBlock.Settings.of(Material.WOOD, MapColor.RED).strength(2.0f,3.0f).sounds(BlockSoundGroup.WOOD)), ModItemGroup.Amod);











    private static Item registerBlockItem(String name, Block block, ItemGroup group) {
        Item item = Registry.register(Registries.ITEM, new Identifier(Cwood.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
        return item;
    }

    private static Block registerBlock(String name, Block block, ItemGroup group) {
        registerBlockItem(name, block, group);
        return Registry.register(Registries.BLOCK, new Identifier(Cwood.MOD_ID, name), block);
    }




    public static void registerModBlocks() {Cwood.LOGGER.info("Registering Mod Blocks for " + Cwood.MOD_ID);}
}





