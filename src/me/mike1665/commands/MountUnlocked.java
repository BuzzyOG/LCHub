package me.mike1665.commands;

import java.util.UUID;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.arrayprolc.strings.MessageType;
import com.arrayprolc.strings.StringManager;

public class MountUnlocked {

	static Main plugin;

	public static void setup(Main instance) {
		plugin = instance;
	}

	@SuppressWarnings("deprecation")
	public static boolean onCommand(CommandSender sender, Command cmd,
			String label, String[] a) {
		if(sender instanceof Player){
			Player p = (Player)sender;
			if(!p.isOp()) return false;
			}
		if (cmd.getName().equalsIgnoreCase("unlockmount")) {
			if (a.length == 2) {
				if (a[0].equalsIgnoreCase("angelmount")) {
					OfflinePlayer recieved = Bukkit.getOfflinePlayer(a[1]);
					UUID uuid = Bukkit.getOfflinePlayer(a[1]).getUniqueId();
					plugin.getConfig().set(uuid + ".AngelMount", true);
					plugin.saveConfig();
					if (recieved.isOnline()) {
						Player onlineplyr = recieved.getPlayer();
						onlineplyr.sendMessage(StringManager.getPrefix(MessageType.SUCCESS)
								+ "Enjoy your new Angel Mount!");
						} else if (!recieved.isOnline()) {
							return true;
						}
					}
				}

		}/* else {
			sender.sendMessage(StringManager.getPrefix(MessageType.ERROR)
					+ "Something Failed");
		}*/

		return false;
	}

	/*
	 * if (a[0] == "angelmount") { UUID uuid =
	 * Bukkit.getPlayer(a[1]).getUniqueId(); plugin.getConfig().set(uuid +
	 * ".AngelMount", true); plugin.saveConfig();
	 * player.sendMessage("Mount unlocked!");
	 */

}
