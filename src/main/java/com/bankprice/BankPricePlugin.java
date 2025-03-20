package com.bankprice;

import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.chat.QueuedMessage;
import net.runelite.client.chat.ChatMessageType;
import javax.inject.Inject;

@PluginDescriptor(
    name = "Bank Price Tracker",
    description = "Shows purchase prices of items in your bank",
    tags = {"bank", "price", "ge"}
)

// 📌 BankPricePlugin.java (Entry point)
// Registers all event listeners.
// Initializes the overlay.
// Handles plugin startup and shutdown.
public class BankPricePlugin extends Plugin
{
    @Inject
    private BankPriceManager bankPriceManager;

    @Inject
    private OverlayManager overlayManager;

    @Inject
    private BankPriceOverlay overlay;

	@Inject
    private ChatMessageManager chatMessageManager;

    public void sendChatMessage(String message)
    {
        chatMessageManager.queue(QueuedMessage.builder()
            .type(ChatMessageType.GAMEMESSAGE)
            .runeLiteFormattedMessage(message)
            .build());
    }

    @Override
    protected void startUp()
    {
        overlayManager.add(overlay);
    }

    @Override
    protected void shutDown()
    {
        overlayManager.remove(overlay);
    }
}
