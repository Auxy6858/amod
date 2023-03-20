package net.auxy.cwood.block;

import net.auxy.cwood.block.custom.FiltrationBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.auxy.cwood.Cwood;
import net.auxy.cwood.item.ModItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;



public class ModBlocks {




    // ORE BLOCKS

    public static final Block JADE_ORE = registerBlock("jade_ore", new ExperienceDroppingBlock(FabricBlockSettings.of(Material.STONE).strength(4.0f).requiresTool(), UniformIntProvider.create(2, 6)), ItemGroups.NATURAL);




    // COLOURED BLOCKS
    public static final Block RED_PLANKS = registerBlock("red_planks", new Block(FabricBlockSettings.of(Material.WOOD, MapColor.RED).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD)), ItemGroups.COLORED_BLOCKS);
    public static final Block RED_WOOD_STAIRS = registerBlock("red_wood_stairs", new ModStairsBlock(ModBlocks.RED_PLANKS.getDefaultState(), FabricBlockSettings.of(Material.WOOD, MapColor.RED).strength(2.0f,3.0f).sounds(BlockSoundGroup.WOOD)), ItemGroups.COLORED_BLOCKS);
    public static final Block RED_WOOD_SLAB = registerBlock("red_wood_slab", new ModSlabBlock(FabricBlockSettings.of(Material.WOOD, MapColor.RED).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD)), ItemGroups.COLORED_BLOCKS);
    public static final Block RED_WOOD_FENCE = registerBlock("red_wood_fence", new ModFenceBlock(AbstractBlock.Settings.of(Material.WOOD, RED_PLANKS.getDefaultMapColor()).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD)), ItemGroups.COLORED_BLOCKS);
    public static final Block RED_WOOD_FENCE_GATE = registerBlock("red_wood_fence_gate", new ModFenceGateBlock(AbstractBlock.Settings.of(Material.WOOD, RED_PLANKS.getDefaultMapColor()).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD), SoundEvents.BLOCK_FENCE_GATE_CLOSE, SoundEvents.BLOCK_FENCE_GATE_OPEN), ItemGroups.COLORED_BLOCKS);
    public static final Block RED_WOOD_DOOR = registerBlock("red_wood_door", new ModDoorBlock(AbstractBlock.Settings.of(Material.WOOD, RED_PLANKS.getDefaultMapColor()).strength(3.0f).sounds(BlockSoundGroup.WOOD).nonOpaque(), SoundEvents.BLOCK_WOODEN_DOOR_CLOSE, SoundEvents.BLOCK_WOODEN_DOOR_OPEN), ItemGroups.COLORED_BLOCKS);
    public static final Block WHITE_PLANKS = registerBlock("white_planks", new Block(FabricBlockSettings.of(Material.WOOD, MapColor.WHITE).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD)), ItemGroups.COLORED_BLOCKS);

    // NATURE BLOCKS

    public static final Block GRAINY_SAND = registerBlock("grainy_sand", new SandBlock(14406560, AbstractBlock.Settings.of(Material.AGGREGATE, MapColor.PALE_YELLOW).strength(0.5f).sounds(BlockSoundGroup.SAND)), ItemGroups.NATURAL);

    // FUNCTIONAL BLOCKS


    public static final Block FILTRATION_BLOCK = registerBlock("filtration_block", new FiltrationBlock(FabricBlockSettings.of(Material.WOOD, MapColor.BROWN).sounds(BlockSoundGroup.STONE)), ItemGroups.FUNCTIONAL);


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





