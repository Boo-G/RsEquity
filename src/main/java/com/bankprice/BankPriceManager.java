package com.bankprice;

import java.util.HashMap;

// 📌 BankPriceManager.java (Stores purchase prices in memory)
// Retrieves and updates item prices.
public class BankPriceManager
{
    private final HashMap<Integer, Integer> purchasePrices = new HashMap<>();

    public void storePurchasePrice(int itemId, int price)
    {
        purchasePrices.put(itemId, price);
    }

    public int getPurchasePrice(int itemId)
    {
        return purchasePrices.getOrDefault(itemId, -1);
    }
}
