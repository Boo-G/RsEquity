package com.bankprice;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ItemPriceStorage
{
    private static final String FILE_PATH = "item_prices.dat";

    /**
     * Saves item purchase prices to a file.
     */
    public static void savePrices(Map<Integer, Integer> prices)
    {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH)))
        {
            out.writeObject(prices);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            BankPricePlugin.getInstance().sendChatMessage("<col=ff0000>Error saving item prices.</col>");
        }
    }

    /**
     * Loads item purchase prices from a file.
     */
    @SuppressWarnings("unchecked")
    public static Map<Integer, Integer> loadPrices()
    {
        File file = new File(FILE_PATH);
        if (!file.exists())
        {
            return new HashMap<>(); // Return empty if no previous data
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH)))
        {
            return (Map<Integer, Integer>) in.readObject();
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
            BankPricePlugin.getInstance().sendChatMessage("<col=ff0000>Error loading item prices.</col>");
            return new HashMap<>();
        }
    }
}
