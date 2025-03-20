package com.bankprice;

import java.io.*;
import java.util.HashMap;

// 📌 ItemPriceStorage.java (Saves and loads prices)
// Ensures data persists across sessions.
public class ItemPriceStorage
{
    private static final String FILE_PATH = "item_prices.dat";

    public static void savePrices(HashMap<Integer, Integer> prices) throws IOException
    {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH)))
        {
            out.writeObject(prices);
        }
    }

    @SuppressWarnings("unchecked")
    public static HashMap<Integer, Integer> loadPrices() throws IOException, ClassNotFoundException
    {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH)))
        {
            return (HashMap<Integer, Integer>) in.readObject();
        }
    }
}
