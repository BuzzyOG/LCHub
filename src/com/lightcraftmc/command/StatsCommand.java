package com.lightcraftmc.command;

import me.mike1665.coinapi.LcCoinsAPI;
import me.mike1665.coinapi.LcTokensAPI;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.arrayprolc.rank.RankManager;
import com.lightcraftmc.hub.main.Main;

public class StatsCommand {

	static Main plugin;

	public static void setup() {
		plugin = Main.getInstance();
	}

	public static boolean command(CommandSender sender, Command cmd,
			String label, String[] a) {
		if(!(sender instanceof Player)) return false;
		Player player = (Player) sender;
		if (label.equalsIgnoreCase("stats")) {
			int a1 = LcTokensAPI.balancePoints(player);
			int b1 = LcCoinsAPI.balancePoints(player);
			player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "" + ChatColor.STRIKETHROUGH + "------------------------------------------");
			player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + ">>  " + ChatColor.GRAY + "Name: " + ChatColor.LIGHT_PURPLE + player.getName().toString());
			player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + ">>  " + ChatColor.GRAY + "Rank: " + ChatColor.LIGHT_PURPLE + RankManager.getRank(player).toString());
			player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + ">>  " + ChatColor.GRAY + "Tokens: " + ChatColor.LIGHT_PURPLE + a1);
			player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + ">>  " + ChatColor.GRAY + "Coins: " + ChatColor.LIGHT_PURPLE + b1);
			player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "" + ChatColor.STRIKETHROUGH + "------------------------------------------");

		}
		return false;
	}

}