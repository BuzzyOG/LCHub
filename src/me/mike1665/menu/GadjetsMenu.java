package me.mike1665.menu;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GadjetsMenu implements Listener{
	
	public ItemStack createItem(Material material, int amount, short shrt,
			String displayname, String lore) {
		ItemStack item = new ItemStack(material, amount, (short) shrt);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayname);
		ArrayList<String> Lore = new ArrayList<String>();
		Lore.add(lore);
		meta.setLore(Lore);

		item.setItemMeta(meta);
		return item;
	}

	public static Inventory gadmenu;
	{
		gadmenu = Bukkit.createInventory(null, 27, "�7�nGadgets Menu");

		gadmenu.setItem(
				4,
				createItem(Material.DIAMOND, 1, (short) 0, "�6Vip Gadgets",
						"�bFun VIP Gadgets!"));
		gadmenu.setItem(
				3, 
				createItem(Material.IRON_INGOT, 1, (short) 0, "�aRegular Gadgets",
						"�bFun Player Gadgets!"));
		gadmenu.setItem(
				5,
				createItem(Material.ENDER_PEARL, 1, (short) 0, "�4Admin Gadgets",
						"�bAdmin's Super Gadgets!"));
		gadmenu.setItem(
				27,
				createItem(Material.NETHER_STAR, 1, (short) 0, "�bBack to Hub Menu",
						"�6 "));
	}

}
