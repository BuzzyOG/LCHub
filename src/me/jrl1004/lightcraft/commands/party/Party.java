package me.jrl1004.lightcraft.commands.party;

import java.util.HashSet;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;

public class Party {
	private OfflinePlayer host;
	public final String name;
	private final HashSet<OfflinePlayer> players;
	private final HashSet<OfflinePlayer> invites;
	private boolean locked;

	public Party(String name, OfflinePlayer host) {
		this.name = name;
		this.players = new HashSet<OfflinePlayer>();
		this.invites = new HashSet<OfflinePlayer>();
		locked = true;
		this.host = host;
	}

	public void addToParty(OfflinePlayer player) {
		if (invites.contains(player))
			invites.remove(player);
		players.add(player);
	}

	public boolean hasPlayer(OfflinePlayer player) {
		if(players.size() == 0) return false;
		for(OfflinePlayer p : players) {
			if(p.getUniqueId().equals(player.getUniqueId())) return true;
		}
		return false;
	}

	public boolean isInvited(OfflinePlayer player) {
		return invites.contains(player);
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public boolean isLocked() {
		return locked;
	}

	public OfflinePlayer getHost() {
		return this.host;
	}

	public void setHost(OfflinePlayer host) {
		this.host = host;
	}

	public void removePlayer(OfflinePlayer player) {
		if (players.contains(player))
			players.remove(player);
		if (invites.contains(player))
			invites.remove(player);
	}

	protected void wipeData() {
		players.clear();
		invites.clear();
		setHost(null);
		setLocked(true);
	}

	public void setInvited(OfflinePlayer invitee) {
		invites.add(invitee);
		if (invitee.isOnline())
			invitee.getPlayer().sendMessage(ChatColor.AQUA + "You have been invited to the party: " + name);
	}
}
