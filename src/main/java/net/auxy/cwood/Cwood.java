package net.auxy.cwood;

import net.auxy.cwood.block.ModBlocks;
import net.auxy.cwood.item.ModItemGroup;
import net.auxy.cwood.item.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;







public class Cwood implements ModInitializer {


	public static final String MOD_ID = "cwood";
	public static final Logger LOGGER = LoggerFactory.getLogger("cwood");

	@Override
	public void onInitialize() {
		ModItemGroup.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();


		LOGGER.info("Hello Fabric world!");
	}
}
