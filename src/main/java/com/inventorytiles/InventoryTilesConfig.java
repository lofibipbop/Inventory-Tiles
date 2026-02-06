package com.inventorytiles;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

import java.awt.Color;

@ConfigGroup("inventory-tiles")
public interface InventoryTilesConfig extends Config
{
	enum DrawMode
	{
		OUTLINE,
		FILL
	}

	@ConfigItem(
		keyName = "drawMode",
		name = "Draw Mode",
		description = "Choose whether to outline or fill tiles"
	)

	default DrawMode drawMode()
	{
		return DrawMode.OUTLINE;
	}

	@ConfigItem(
		keyName = "tileColor",
		name = "Tile Color",
		description = "Set tile color"
	)

	default Color tileColor()
	{
		return new Color(255, 255, 255, 60);
	}

	@ConfigItem(
		keyName = "tileOpacity",
		name = "Tile Opacity",
		description = "Set tile opacity"
	)

	default int tileOpacity()
	{
		return 60;
	}
}
