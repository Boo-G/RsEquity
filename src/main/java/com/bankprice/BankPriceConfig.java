package com.bankprice;

import net.runelite.client.config.*;

@ConfigGroup("bankprice")

// 📌 BankPriceConfig.java (Config settings)
// Allows users to enable/disable features.
public interface BankPriceConfig extends Config
{
    @ConfigItem(
        keyName = "showPrices",
        name = "Show Purchase Prices",
        description = "Display item purchase prices in the bank"
    )
    default boolean showPrices()
    {
        return true;
    }
}
