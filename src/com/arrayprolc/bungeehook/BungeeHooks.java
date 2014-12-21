package com.arrayprolc.bungeehook;

import java.util.HashMap;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class BungeeHooks {
	static Main plugin;
	public static HashMap<String, Integer> players = new HashMap<String, Integer>();

	public static void sendPlayerToServer(String name, Player p){
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
		out.writeUTF(name);
		p.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
	}
	@SuppressWarnings("deprecation")
	public static void sendPlayerToServer(String name, OfflinePlayer p){
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("ConnectOther");
		out.writeUTF(p.getName());
		out.writeUTF(name);//This is another edit 2
		Bukkit.getOnlinePlayers()[0].sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
	}

	public int getPlayerCount(String server){
		return 1;

	}


}
