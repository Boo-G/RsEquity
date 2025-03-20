package com.bankprice;

import net.runelite.api.Client;
import net.runelite.api.InventoryID;
import net.runelite.api.Item;
import net.runelite.api.ItemContainer;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

public class BankPriceManager
{
    private final Map<Integer, Integer> purchasePrices = new HashMap<>(); // Stores item ID -> Purchase Price
    private final Client client;

    @Inject
    public BankPriceManager(Client client)
    {
        this.client = client;
    }

    /**
     * Stores the purchase price of an item when bought from the GE.
     */
    public void storePurchasePrice(int itemId, int price)
    {
        purchasePrices.put(itemId, price);
        BankPricePlugin.getInstance().sendChatMessage("Stored price: " + price + " gp for item ID " + itemId);
    }

    /**
     * Gets the stored purchase price of an item.
     */
    public int getPurchasePrice(int itemId)
    {
        return purchasePrices.getOrDefault(itemId, -1);
    }

    /**
     * Updates the bank item prices when the bank is opened.
     */
    public void updateBankPrices()
    {
        ItemContainer bank = client.getItemContainer(InventoryID.BANK);
        if (bank == null)
        {
            BankPricePlugin.getInstance().sendChatMessage("<col=ff0000>Failed to load bank items.</col>");
            return;
        }

        for (Item item : bank.getItems())
        {
            int itemId = item.getId();
            int storedPrice = getPurchasePrice(itemId);

            if (storedPrice != -1)
            {
                BankPricePlugin.getInstance().sendChatMessage(
                    "Item " + itemId + " price: " + storedPrice + " gp"
                );
            }
        }
    }

    /**
     * Returns all stored purchase prices (for overlays).
     */
    public Map<Integer, Integer> getStoredPrices()
    {
        return purchasePrices;
    }
}
