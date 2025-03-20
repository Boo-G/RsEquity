package com.bankprice;

import net.runelite.api.events.GrandExchangeOfferChanged;
import net.runelite.api.GrandExchangeOffer;
import net.runelite.api.GrandExchangeOfferState;
import net.runelite.client.eventbus.Subscribe;

import javax.inject.Inject;

public class GrandExchangeListener
{
    private final BankPriceManager bankPriceManager;

    @Inject
    public GrandExchangeListener(BankPriceManager bankPriceManager)
    {
        this.bankPriceManager = bankPriceManager;
    }

    @Subscribe
    public void onGrandExchangeOfferChanged(GrandExchangeOfferChanged event)
    {
        GrandExchangeOffer offer = event.getOffer();

        // Only store the price if the item was fully bought
        if (offer.getState() == GrandExchangeOfferState.BOUGHT)
        {
            int itemId = offer.getItemId();
            int totalSpent = offer.getSpent();
            int quantityBought = offer.getQuantitySold();

            if (quantityBought > 0)
            {
                int pricePerItem = totalSpent / quantityBought;
                bankPriceManager.storePurchasePrice(itemId, pricePerItem);

                BankPricePlugin.getInstance().sendChatMessage(
                    "<col=ff9800>GE Purchase:</col> Bought " + quantityBought + "x " + itemId +
                    " for " + pricePerItem + " gp each."
                );
            }
        }
    }
}
