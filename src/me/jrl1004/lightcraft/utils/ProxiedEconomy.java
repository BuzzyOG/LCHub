package me.jrl1004.lightcraft.utils;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class ProxiedEconomy {
	private static ProxiedEconomy instance;

	public enum Currency
	{
		Tokens,
		Coins;
	}

	public ProxiedEconomy() {
		instance = this;
		Bukkit.getMessenger().registerOutgoingPluginChannel(Main.instance, "BungeeCord");
	}

	public static ProxiedEconomy getInstance() {
		return instance;
	}

	public void requestPlayerBalance(Player player, Currency c) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Economy");
		out.writeUTF("BALANCE");
		out.writeUTF(c.toString());
		out.writeUTF(player.getUniqueId().toString());
		player.sendPluginMessage(Main.instance, "BungeeCord", out.toByteArray());
	}

	public void resetPlayerBalance(Player player, Currency c) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Economy");
		out.writeUTF("RESET");
		out.writeUTF(c.toString());
		out.writeUTF(player.getUniqueId().toString());
		player.sendPluginMessage(Main.instance, "BungeeCord", out.toByteArray());
	}

	public void increasePlayerBalance(Player player, Currency c, Long amount) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Economy");
		out.writeUTF("ADD");
		out.writeUTF(c.toString());
		out.writeUTF(player.getUniqueId().toString());
		out.writeLong(amount);
		player.sendPluginMessage(Main.instance, "BungeeCord", out.toByteArray());
	}

	public void decreasePlayerBalance(Player player, Currency c, Long amount) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Economy");
		out.writeUTF("TAKE");
		out.writeUTF(c.toString());
		out.writeUTF(player.getUniqueId().toString());
		out.writeLong(amount);
		player.sendPluginMessage(Main.instance, "BungeeCord", out.toByteArray());
	}

	public void setPlayerBalance(Player player, Currency c, Long amount) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Economy");
		out.writeUTF("SET");
		out.writeUTF(c.toString());
		out.writeUTF(player.getUniqueId().toString());
		out.writeLong(amount);
		player.sendPluginMessage(Main.instance, "BungeeCord", out.toByteArray());
	}

}
