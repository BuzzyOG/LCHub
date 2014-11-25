package me.mike1665.coinapi;

import me.mike1665.Main.Main;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Listener;

public class LcTokensAPI implements Listener {
	
	private static Main plugin;
	
	public static void initialize(Main plugin){
		LcTokensAPI.plugin = plugin;
	}
	
	public static void givePoints(OfflinePlayer p, int i) {
		plugin.getConfig().set(p.getName() + ".Tokens",
				plugin.getConfig().getInt(p.getName() + ".Tokens", 0) + i);
		plugin.saveFile();
	}

	public static void takePoints(OfflinePlayer p, int i) {
		plugin.getConfig().set(p.getName() + ".Tokens",
				plugin.getConfig().getInt(p.getName() + ".Tokens", 0) - i);
		plugin.saveFile();
	}

	public static boolean hasEnough(OfflinePlayer p, int i) {
		if (plugin.getConfig().getInt(p.getName() + ".Tokens") >= i)
			return true;
		return false;
	}
	public static int balancePoints(OfflinePlayer p) {
		int a = plugin.getConfig().getInt(p.getName() + ".Tokens");
		return a;
	}
}