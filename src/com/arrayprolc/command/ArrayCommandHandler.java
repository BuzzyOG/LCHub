package com.arrayprolc.command;

import java.lang.reflect.Method;

import me.mike1665.Main.Main;
import net.minecraft.server.v1_8_R1.EntityTypes;

import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.material.Dye;

import com.arrayprolc.menu.Menu;
import com.arrayprolc.rank.RankManager;
import com.arrayprolc.rank.ServerRank;
import com.arrayprolc.speedways.CustomEntityType;
import com.arrayprolc.tools.ItemTools;

public class ArrayCommandHandler implements Listener
{

	static Main plugin;
	public static ArrayCommandHandler instance2;
	public static void setup(Main instance)
	{
		plugin = instance;
		instance2 = new ArrayCommandHandler();
		Bukkit.getPluginManager().registerEvents(instance2, instance);
	}

	public static boolean command(CommandSender sender, Command cmd, String label, String[] a)
	{
		if (label.equalsIgnoreCase("g-reward"))
		{
			if (sender instanceof Player)
				return false;
			Player p = Bukkit.getPlayer(a[0]);
			int amount = Integer.parseInt(a[2]);
			switch (a[1].toLowerCase())
			{

			case "coin":
			{
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
		if (label.equalsIgnoreCase("sheeplol") && ((Player) sender).isOp())
		{
			try
			{
				((Player) sender).getPassenger().eject();
				((Player) sender).eject();
				// ((Player) sender).spigot().
			} catch (Exception ex)
			{
			}
			for (Entity e : ((Player) sender).getNearbyEntities(5, 5, 5))
			{
				if (e instanceof Sheep)
				{
					((Player) sender).setPassenger(e);
					return false;
				}
			}
		}
		if (label.equalsIgnoreCase("rank"))
		{
			if (sender instanceof Player)
			{
				if (!((Player) sender).isOp())
					return false;
			}
			Player p = Bukkit.getPlayer(a[0]);
			ServerRank r = RankManager.getRankFromString(a[1]);
			RankManager.setRank(p, r);

		}
		if (label.equalsIgnoreCase("test"))
		{
			//new ParametricFlower(((Player) sender).getLocation(), new ParticleLib18(ParticleType.VILLAGER_HAPPY, 1, 1, 0));
			Menu m = new Menu("Wardrobe: " + a[0], getClosestTo(16, 9));
			int end = 0;
			for(int i = 0; i < 16; i++){
				byte dyeColour = (byte)i;
				int slot = (DyeColor.values().length-1) - i;
				String colour = WordUtils.capitalize(DyeColor.values()[slot].toString().toLowerCase().replace("_", " "));
				ItemStack dye = ItemTools.setName(new ItemStack(Material.INK_SACK, 1, dyeColour),  ChatColor.RESET + "" + colour);
				m.addItem(dye, i);
				end++;
			}
			end = end + 2;
			m.addItem(ItemTools.setName(new ItemStack(Material.ARROW), "�a<�a�m-- �f�aGo Back"), end);
			m.displayMenu((Player)sender);
		}
		return false;
	}
	public static Material getType(String s){
		s = s.toLowerCase();
		for(Material m : Material.values()){
			if(m.toString().toLowerCase().contains("LEATHER") && m.toString().toLowerCase().contains(s)){
				return m;
			}
		}
		return Material.LEATHER_CHESTPLATE;
	}
	@EventHandler
	public void onInteract(InventoryClickEvent e){
		if(!e.getInventory().getTitle().contains("Wardrobe:")){
			return;
		}
		String type = e.getInventory().getTitle().split("Wardrobe: ")[1];
		Material t = getType(type);
		if(!(e.getCurrentItem().getData() instanceof Dye)) return;
		Dye dye =  (Dye) e.getCurrentItem().getData();
		ItemStack lhelmet = new ItemStack(t, 1);
		LeatherArmorMeta lam = (LeatherArmorMeta)lhelmet.getItemMeta();
		lam.setColor(dye.getColor().getColor());
		lhelmet.setItemMeta(lam);
		switch(t){
		case LEATHER_HELMET: e.getWhoClicked().getInventory().setHelmet(lhelmet); break;
		case LEATHER_CHESTPLATE: e.getWhoClicked().getInventory().setChestplate(lhelmet);break;
		case LEATHER_LEGGINGS: e.getWhoClicked().getInventory().setLeggings(lhelmet);break;
		case LEATHER_BOOTS: e.getWhoClicked().getInventory().setBoots(lhelmet);break;
		default: return;
		}
	}
	public static int getClosestTo(int j, int rejex){
		int working = rejex; for(int i = 0; i < 9*7; i++) if(j > working) working = working + rejex; return working;
	}
	static boolean isOnline(Player p)
	{
		for (Player p2 : Bukkit.getOnlinePlayers())
		{
			if (p2.getName().equalsIgnoreCase(p.getName()))
			{
				return true;
			}
		}
		return false;
	}

	private static void registerEntities()
	{
		for (CustomEntityType entity : CustomEntityType.values())
		{
			try
			{
				Method a = EntityTypes.class.getDeclaredMethod("a", new Class<?>[] { Class.class, String.class, int.class });
				a.setAccessible(true);
				a.invoke(null, entity.getCustomClass(), entity.getName(), entity.getID());
			} catch (Exception e)
			{
				e.printStackTrace();
				Bukkit.broadcastMessage(e.getCause() + "");
			}
		}
	}
}
