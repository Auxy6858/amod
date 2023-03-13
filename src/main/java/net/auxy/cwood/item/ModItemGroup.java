package net.auxy.cwood.item;

import net.auxy.cwood.Cwood;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static ItemGroup JADE;

    public static void registerItemGroups() {
        JADE = FabricItemGroup.builder(new Identifier(Cwood.MOD_ID, "jade"))
                .displayName(Text.translatable("itemgroup.jade"))
                .icon(() -> new ItemStack(ModItems.JADE)).build();

    }
}
