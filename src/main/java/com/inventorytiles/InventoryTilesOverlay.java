package com.inventorytiles;

import javax.inject.Inject;

import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.api.Client;
import net.runelite.api.widgets.Widget;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.BasicStroke;
import java.awt.Color;

public class InventoryTilesOverlay extends Overlay
{
    private final Client client;
    private final InventoryTilesConfig config;

    @Inject
    public InventoryTilesOverlay(Client client, InventoryTilesConfig config)
    {
        this.client = client;
        this.config = config;
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_WIDGETS);
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        //Check if inventory is open
        Widget inventoryWidget = client.getWidget(149, 0);
        if (inventoryWidget == null || inventoryWidget.isHidden())
        {
            return null;
        }

        //Math to determine size and position of inventory slots
        int slotWidth = 36;
        int slotHeight = 32;
        int padX = 6;
        int padY = 4;
        int columns = 4;

        Rectangle inventory = inventoryWidget.getBounds();

        //Configure graphics settings for tiles
        graphics.setStroke(new BasicStroke(1));
        Color drawColor = new Color(
                config.tileColor().getRed(),
                config.tileColor().getGreen(),
                config.tileColor().getBlue(),
                config.tileOpacity()
        );
        graphics.setColor(drawColor);

        //Iterate through every inventory slot and draw a tile according to config
        for (int i = 0; i < 28; i++)
        {
            //Determine position of each inventory slot
            int row = i / columns;
            int col = i % columns;
            int x = inventory.x + 16 + (slotWidth + padX) * col;
            int y = inventory.y + 8 + (slotHeight + padY) * row;

            //Draw tile on inventory slot according to graphics settings
            if (config.drawMode() == InventoryTilesConfig.DrawMode.OUTLINE)
            {
                graphics.drawRect(x, y, slotWidth, slotHeight);
            }
            else
            {
                graphics.fillRect(x, y, slotWidth, slotHeight);
            }
        }

        return null;
    }
}