package com.arrayprolc.command;
import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;

import com.arrayprolc.rank.RankFile;
import com.arrayprolc.rank.RankManager;
import com.arrayprolc.rank.ServerRank;

public class ArrayCommandHandler {

	static Main plugin;
	
	public static void setup(Main instance){
		plugin = instance;
	}
	
	public static boolean command(CommandSender sender, Command cmd, String label, String[] a){
		if(label.equalsIgnoreCase("g-reward")){
			if(sender instanceof Player) return false;
			Player p = Bukkit.getPlayer(a[0]);
			int amount = Integer.parseInt(a[2]);
			switch(a[1].toLowerCase()){
			case "coin": {
				me.mike1665.coinapi.LcCoinsAPI.givePoints(p, amount);
				me.mike1665.coinapi.ApiEvent.scoreboard(p);
				return true;
			}
			case "token":
				me.mike1665.coinapi.LcTokensAPI.givePoints(p, amount);
				me.mike1665.coinapi.ApiEvent.scoreboard(p);
				return true;
			}
		}
		if(label.equalsIgnoreCase("sheeplol") && ((Player)sender).isOp()){
			try{((Player)sender).getPassenger().eject();
			((Player)sender).eject(); }catch(Exception ex){}
			for(Entity e :  ((Player)sender).getNearbyEntities(5,  5,  5)){
				if(e instanceof Sheep){
					 ((Player)sender).setPassenger(e);
					 return false;
				}
			}
		}
		if(label.equalsIgnoreCase("rank")){
			if(sender instanceof Player){
				if(!((Player)sender).isOp()) return false;
			}
			Player p = Bukkit.getPlayer(a[0]);
			ServerRank r = RankManager.getRankFromString(a[1]);
			RankManager.setRank(p, r);
			
		}
		if(label.equalsIgnoreCase("test")){
			RankFile.saveToFile(new String[] {"test" });
			
		}
		return false;
	}
	

	
}
