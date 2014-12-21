package me.mike1665.commands;

import me.mike1665.Main.Main;
import me.mike1665.wardrobe.WardrobeManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.lightcraftmc.fusebox.strings.MessageType;
import net.lightcraftmc.fusebox.strings.StringManager;

public class UnlockAllArmor {
	
	static Main plugin;

	public static void setup() {
		plugin = Main.getInstance();
	}

	public static boolean onCommand(CommandSender sender, Command cmd,
			String label, String[] a) {
		if(sender instanceof Player){
			Player p = (Player)sender;
			if(!p.isOp()) return false;
			}
		if (cmd.getName().equalsIgnoreCase("unlockallarmor") && sender.isOp()) {
			if (a.length < 1) {
				sender.sendMessage(ChatColor.DARK_RED + "/unlockallarmor {playername}");
			}
				OfflinePlayer p2 = Bukkit.getOfflinePlayer(a[0]);
				
				//Leather Armor
				WardrobeManager.unlockArmor(p2, Material.LEATHER_HELMET);
				WardrobeManager.unlockArmor(p2, Material.LEATHER_CHESTPLATE);
				WardrobeManager.unlockArmor(p2, Material.LEATHER_HELMET);
				WardrobeManager.unlockArmor(p2, Material.LEATHER_LEGGINGS);
				
				//Chain Armor
				WardrobeManager.unlockArmor(p2, Material.CHAINMAIL_HELMET);
				WardrobeManager.unlockArmor(p2, Material.CHAINMAIL_CHESTPLATE);
				WardrobeManager.unlockArmor(p2, Material.CHAINMAIL_HELMET);
				WardrobeManager.unlockArmor(p2, Material.CHAINMAIL_LEGGINGS);
				
				//Iron Armor
				WardrobeManager.unlockArmor(p2, Material.IRON_HELMET);
				WardrobeManager.unlockArmor(p2, Material.IRON_CHESTPLATE);
				WardrobeManager.unlockArmor(p2, Material.IRON_HELMET);
				WardrobeManager.unlockArmor(p2, Material.IRON_LEGGINGS);
				
				//Gold Armor
				WardrobeManager.unlockArmor(p2, Material.GOLD_HELMET);
				WardrobeManager.unlockArmor(p2, Material.GOLD_CHESTPLATE);
				WardrobeManager.unlockArmor(p2, Material.GOLD_HELMET);
				WardrobeManager.unlockArmor(p2, Material.GOLD_LEGGINGS);
				
				//Diamond Armor
				WardrobeManager.unlockArmor(p2, Material.DIAMOND_HELMET);
				WardrobeManager.unlockArmor(p2, Material.DIAMOND_CHESTPLATE);
				WardrobeManager.unlockArmor(p2, Material.DIAMOND_HELMET);
				WardrobeManager.unlockArmor(p2, Material.DIAMOND_LEGGINGS);
				
				if (p2.isOnline()) {
					p2.getPlayer().sendMessage(StringManager.getPrefix(MessageType.SUCCESS) + "Successfully unlocked all Wardrobe contents!");
				}
				
				else if (!p2.isOnline()) {
					return true;
				}
			}
		return false;
	}

}
