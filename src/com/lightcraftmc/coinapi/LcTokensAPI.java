package com.lightcraftmc.coinapi;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Listener;

import com.lightcraftmc.coin.Multiplier;
import com.lightcraftmc.hub.main.Main;

public class LcTokensAPI implements Listener {
	
	private static Main plugin;
	
	public static void initialize(){
		LcTokensAPI.plugin = Main.getInstance();
	}
	
	public static void givePoints(OfflinePlayer p, int i) {
		i = i * Multiplier.token(p);
		plugin.getConfig().set(p.getUniqueId() + ".Tokens",
				plugin.getConfig().getInt(p.getUniqueId() + ".Tokens", 0) + i);
		plugin.saveFile();
		//String i2 = Integer.toString(i);
		//com.lightcraftmc.mysql.MySQL.givePoints(p, i2);
	}

	public static void takePoints(OfflinePlayer p, int i) {
		i = i * Multiplier.token(p);
		plugin.getConfig().set(p.getUniqueId() + ".Tokens",
				plugin.getConfig().getInt(p.getUniqueId() + ".Tokens", 0) - i);
		plugin.saveFile();
		//String i2 = Integer.toString(i);
		//com.lightcraftmc.mysql.MySQL.takePoints(p, i2);
	}

	public static boolean hasEnough(OfflinePlayer p, int i) {
		i = i * Multiplier.token(p);
		if (plugin.getConfig().getInt(p.getUniqueId() + ".Tokens") >= i)
			return true;
		return false;
	}
	public static int balancePoints(OfflinePlayer p) {
		int a;
		try {
			a = plugin.getConfig().getInt(p.getUniqueId() + ".Tokens");
		}catch(Exception exc) {
			a = 0;
		}
		return a;
	}
}