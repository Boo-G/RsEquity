package com.bankprice;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.Getter;
import net.runelite.api.Client;
import net.runelite.api.ChatMessageType;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.chat.QueuedMessage;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@PluginDescriptor(
    name = "Bank Price Tracker",
    description = "Displays purchase prices of bank items",
    tags = {"bank", "price", "grand exchange"}
)
public class BankPricePlugin extends Plugin
{
    @Getter
    private static BankPricePlugin instance;  // Store a reference to this plugin

    @Inject
    private Client client;

    @Inject
    private ChatMessageManager chatMessageManager;

    @Inject
    private OverlayManager overlayManager;

    @Inject
    private EventBus eventBus;

    @Inject
    private BankPriceOverlay bankPriceOverlay;

    @Inject
    private BankPriceManager bankPriceManager;

    @Inject
    private GrandExchangeListener geListener;

    @Inject
    private BankListener bankListener;

    @Override
    protected void startUp()
    {
        instance = this;  // Assign reference for other classes to use

        // Register event listeners
        eventBus.register(geListener);
        eventBus.register(bankListener);

        // Add overlay to RuneLite UI
        overlayManager.add(bankPriceOverlay);

        sendChatMessage("<col=00ff00>Bank Price Tracker Enabled!</col>");
    }

    @Override
    protected void shutDown()
    {
        instance = null;

        // Unregister event listeners
        eventBus.unregister(geListener);
        eventBus.unregister(bankListener);

        // Remove overlay from UI
        overlayManager.remove(bankPriceOverlay);

        sendChatMessage("<col=ff0000>Bank Price Tracker Disabled!</col>");
    }

    public static BankPricePlugin getInstance()
    {
        return instance;
    }

    public void sendChatMessage(String message)
    {
        if (client != null && chatMessageManager != null)
        {
            chatMessageManager.queue(QueuedMessage.builder()
                .type(ChatMessageType.GAMEMESSAGE)
                .runeLiteFormattedMessage(message)
                .build());
        }
    }

    @Provides
    BankPriceConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(BankPriceConfig.class);
    }
}
