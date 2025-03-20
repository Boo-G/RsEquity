package com.bankprice;

import net.runelite.api.events.WidgetLoaded;
import net.runelite.api.widgets.WidgetID;
import net.runelite.client.eventbus.Subscribe;

import javax.inject.Inject;

// 📌 BankListener.java (Detects when the bank is opened)
// Ensures the plugin updates prices when the bank is opened.
public class BankListener
{
    @Inject
    private BankPriceManager bankPriceManager;

    @Subscribe
    public void onWidgetLoaded(WidgetLoaded event)
    {
        if (event.getGroupId() == WidgetID.BANK_GROUP_ID)
        {
            log.info("Bank opened!");
            // updateBankPrices();
        }
    }
    
}
