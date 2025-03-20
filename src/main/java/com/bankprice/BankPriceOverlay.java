package com.bankprice;

import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.TextComponent;

import javax.inject.Inject;
import java.awt.*;

// 📌 BankPriceOverlay.java (Displays item purchase prices)
// Draws the price above each item in the bank.
public class BankPriceOverlay extends Overlay
{
    private final BankPriceManager bankPriceManager;

    @Inject
    public BankPriceOverlay(BankPriceManager bankPriceManager)
    {
        this.bankPriceManager = bankPriceManager;
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_WIDGETS);
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        // Iterate over bank items and display prices
        return null;
    }
}
