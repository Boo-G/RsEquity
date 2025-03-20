package com.bankprice;

import net.runelite.api.events.GrandExchangeOfferChanged;
import net.runelite.api.GrandExchangeOffer;
import net.runelite.api.GrandExchangeOfferState;
import net.runelite.client.eventbus.Subscribe;

import javax.inject.Inject;


// 📌 GrandExchangeListener.java (Stores item purchase prices)
// Captures Grand Exchange transactions.
public class GrandExchangeListener
{
    @Inject
    private BankPriceManager bankPriceManager;

    @Subscribe
    public void onGrandExchangeOfferChanged(GrandExchangeOfferChanged event)
    {
        GrandExchangeOffer offer = event.getOffer();
        
        if (offer.getState() == GrandExchangeOfferState.BOUGHT)
        {
            int itemId = offer.getItemId();
            int pricePerItem = offer.getSpent() / offer.getQuantitySold();
    
            bankPriceManager.storePurchasePrice(itemId, pricePerItem);
            
            // Print purchase info in chat
            plugin.sendChatMessage("<col=ff9800>GE Purchase:</col> Bought item " + itemId + " for " + pricePerItem + " gp each.");
        }
    }
    
    
}
