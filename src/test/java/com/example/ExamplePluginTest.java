package com.example;

import com.bankprice.BankPricePlugin;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ExamplePluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(BankPricePlugin.class);
		RuneLite.main(args);
	}
}