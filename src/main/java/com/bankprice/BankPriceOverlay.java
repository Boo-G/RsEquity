package com.bankprice;

import net.runelite.api.Client;
import net.runelite.api.ItemContainer;
import net.runelite.api.InventoryID;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.TextComponent;

import javax.inject.Inject;
import java.awt.*;
import java.util.Map;

public class BankPriceOverlay extends Overlay
{
    private final Client client;
    private final BankPriceManager bankPriceManager;

    @Inject
    public BankPriceOverlay(Client client, BankPriceManager bankPriceManager)
    {
        this.client = client;
        this.bankPriceManager = bankPriceManager;

        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_WIDGETS);
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        // Get the bank's items container
        ItemContainer bank = client.getItemContainer(InventoryID.BANK);
        if (bank == null)
        {
            return null; // No bank open
        }

        // Get the bank widget and its dynamic children (items)
        Widget bankWidget = client.getWidget(12, 5); // Bank item container widget (might need adjustments for your client version)
        if (bankWidget == null || bankWidget.getDynamicChildren() == null)
        {
            return null; // No bank items
        }

        // Get stored prices
        Map<Integer, Integer> storedPrices = bankPriceManager.getStoredPrices();

        // Iterate over each bank item
        for (Widget itemWidget : bankWidget.getDynamicChildren()) // This should give the items in the bank widget
        {
            // Check if item has valid ID
            int itemId = itemWidget.getItemId();
            if (itemId == -1) continue;

            int price = storedPrices.getOrDefault(itemId, -1);

            if (price > 0)
            {
                // Create text overlay for the price
                TextComponent textComponent = new TextComponent();
                textComponent.setText(price + " gp");
                textComponent.setColor(Color.YELLOW);

                // Convert RuneLite's Point to java.awt.Point
                Point point = new Point(itemWidget.getCanvasLocation().getX(), itemWidget.getCanvasLocation().getY() - 10);

                textComponent.setPosition(point);

                textComponent.render(graphics);
            }
        }

        return null;
    }
}
