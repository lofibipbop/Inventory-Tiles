package com.inventorytiles;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
	name = "Inventory Tiles",
	description = "Shows tiles on each inventory slot",
	tags={"inventory", "tiles", "grid"}
)
public class InventoryTilesPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private InventoryTilesConfig config;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private InventoryTilesOverlay overlay;

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
	}

	@Provides
	InventoryTilesConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(InventoryTilesConfig.class);
	}

}
