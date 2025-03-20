package com.bankprice;

import net.runelite.api.events.WidgetLoaded;
import net.runelite.api.widgets.WidgetID;
import net.runelite.client.eventbus.Subscribe;

import javax.inject.Inject;

public class BankListener
{
    private final BankPriceManager bankPriceManager;

    @Inject
    public BankListener(BankPriceManager bankPriceManager)
    {
        this.bankPriceManager = bankPriceManager;
    }

    @Subscribe
    public void onWidgetLoaded(WidgetLoaded event)
    {
        if (event.getGroupId() == WidgetID.BANK_GROUP_ID)
        {
            BankPricePlugin.getInstance().sendChatMessage("<col=00ff00>Bank Opened! Updating prices...</col>");

            // Fetch and update item prices when the bank is opened
            bankPriceManager.updateBankPrices();
        }
    }
}
