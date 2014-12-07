package me.mike1665.click;

import me.mike1665.ammo.BatBlasterAmmoManager;
import me.mike1665.ammo.EnderDogeAmmoManager;
import me.mike1665.ammo.FireWorksAmmoManager;
import me.mike1665.ammo.KittyCannonAmmoManager;
import me.mike1665.ammo.MeowAmmoManager;
import me.mike1665.coinapi.ApiEvent;
import me.mike1665.coinapi.LcCoinsAPI;
import me.mike1665.coinapi.LcTokensAPI;
import me.mike1665.menu.BuyGadgets;
import me.mike1665.menu.CosmeticsMenu;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BuyGadgetsClick implements Listener{
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		try{
		Player p = (Player) event.getWhoClicked();
		if (event.getInventory().getName().equalsIgnoreCase(BuyGadgets.name)) {
			event.setCancelled(true);

			if (event.getCurrentItem() == null) {
				return;
			}

			if (!(event.getCurrentItem().hasItemMeta())) {
				return;
			}
			if (!(event.getCurrentItem().getItemMeta().hasDisplayName())) {
				return;
			}

			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("�5Meow Balls")) {
				if(LcCoinsAPI.hasEnough(p, 60) && !(MeowAmmoManager.balaceMeowAmmo(p) > 1)) {
					LcCoinsAPI.takePoints(p, 60);
					MeowAmmoManager.giveMeowAmmo(p, 50);
					ItemStack snow = new ItemStack(Material.BLAZE_ROD, 1);
		    		ItemMeta sno = snow.getItemMeta();
		    		sno.setDisplayName(ChatColor.GREEN + "MeowBall " + ChatColor.DARK_RED + MeowAmmoManager.balaceMeowAmmo(p));
		    		snow.setItemMeta(sno);
		    		p.getInventory().setItem(2, snow);
		    		ApiEvent.updatescore(p);
                	p.closeInventory();
				} else if (MeowAmmoManager.balaceMeowAmmo(p) > 1) {
					ItemStack snow = new ItemStack(Material.BLAZE_ROD, 1);
		    		ItemMeta sno = snow.getItemMeta();
		    		sno.setDisplayName(ChatColor.GREEN + "MeowBall " + ChatColor.DARK_RED + MeowAmmoManager.balaceMeowAmmo(p));
		    		snow.setItemMeta(sno);
		    		p.getInventory().setItem(2, snow);
                	p.closeInventory();
				} else {
					p.sendMessage(ChatColor.RED + "You dont have enough money!");
				}
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("�9Ender Doge")) {
				if(LcCoinsAPI.hasEnough(p, 50) && !(EnderDogeAmmoManager.balaceEnderDogeAmmo(p) > 1)) {
					LcCoinsAPI.takePoints(p, 50);
					EnderDogeAmmoManager.giveEnderDogeAmmo(p, 50);
					ItemStack snow = new ItemStack(Material.FIREWORK_CHARGE, 1);
		    		ItemMeta sno = snow.getItemMeta();
		    		sno.setDisplayName(ChatColor.DARK_AQUA + "EnderDoge " + ChatColor.DARK_RED + EnderDogeAmmoManager.balaceEnderDogeAmmo(p));
		    		snow.setItemMeta(sno);
		    		p.getInventory().setItem(2, snow);
		    		ApiEvent.updatescore(p);
                	p.closeInventory();
                	
				}else if(EnderDogeAmmoManager.balaceEnderDogeAmmo(p) > 1) {
					ItemStack snow = new ItemStack(Material.FIREWORK_CHARGE, 1);
		    		ItemMeta sno = snow.getItemMeta();
		    		sno.setDisplayName(ChatColor.DARK_AQUA + "EnderDoge " + ChatColor.DARK_RED + EnderDogeAmmoManager.balaceEnderDogeAmmo(p));
		    		snow.setItemMeta(sno);
		    		p.getInventory().setItem(2, snow);
                	p.closeInventory();
                	
				} else {
					p.sendMessage(ChatColor.RED + "You dont have enough money!");
				}
		}
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("�5Fireworks")) {
				if(LcCoinsAPI.hasEnough(p, 100) && !(FireWorksAmmoManager.balaceFireWorkAmmo(p) > 1)) {
					LcCoinsAPI.takePoints(p, 100);
					FireWorksAmmoManager.giveFireWorkAmmo(p, 50);
					ItemStack snow = new ItemStack(Material.FIREWORK, 1);
		    		ItemMeta sno = snow.getItemMeta();
		    		sno.setDisplayName(ChatColor.RED + "Fireworks " + ChatColor.DARK_RED + FireWorksAmmoManager.balaceFireWorkAmmo(p));
		    		snow.setItemMeta(sno);
		    		p.getInventory().setItem(2, snow);
		    		ApiEvent.updatescore(p);
					p.closeInventory();
				}else if (FireWorksAmmoManager.balaceFireWorkAmmo(p) > 1) {
					ItemStack snow = new ItemStack(Material.FIREWORK, 1);
		    		ItemMeta sno = snow.getItemMeta();
		    		sno.setDisplayName(ChatColor.RED + "Fireworks " + ChatColor.DARK_RED + FireWorksAmmoManager.balaceFireWorkAmmo(p));
		    		snow.setItemMeta(sno);
		    		p.getInventory().setItem(2, snow);
                	p.closeInventory();
				} else {
					p.sendMessage(ChatColor.RED + "You dont have enough money!");
				}
			} 
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("�aFun Creepers")) {
				if(LcTokensAPI.hasEnough(p, 50)) {
					LcTokensAPI.takePoints(p, 50);
            		p.closeInventory();
				}else {
					p.sendMessage(ChatColor.RED + "You dont have enough money!");
				}
			} 
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("�5Bat Blaster")) {
				if(LcCoinsAPI.hasEnough(p, 30) && !(BatBlasterAmmoManager.balaceBatAmmo(p) > 1)) {
					LcCoinsAPI.takePoints(p, 30);
					BatBlasterAmmoManager.giveBatAmmo(p, 50);
					ItemStack snow = new ItemStack(Material.IRON_BARDING, 1);
		    		ItemMeta sno = snow.getItemMeta();
		    		sno.setDisplayName(ChatColor.DARK_GRAY + "Bat Blaster " + ChatColor.DARK_RED + BatBlasterAmmoManager.balaceBatAmmo(p));
		    		snow.setItemMeta(sno);
		    		p.getInventory().setItem(2, snow);
		    		ApiEvent.updatescore(p);
            		p.closeInventory();
				}else if (BatBlasterAmmoManager.balaceBatAmmo(p) > 1) {
					ItemStack snow = new ItemStack(Material.IRON_BARDING, 1);
		    		ItemMeta sno = snow.getItemMeta();
		    		sno.setDisplayName(ChatColor.DARK_GRAY + "Bat Blaster " + ChatColor.DARK_RED + BatBlasterAmmoManager.balaceBatAmmo(p));
		    		snow.setItemMeta(sno);
		    		p.getInventory().setItem(2, snow);
                	p.closeInventory();
				}
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("�dPaintball Gun")) {
			if(LcTokensAPI.hasEnough(p, 60)) {
				LcTokensAPI.takePoints(p, 60);
				p.sendMessage("In dev.");
        		p.closeInventory();
			}else {
				p.sendMessage(ChatColor.RED + "You dont have enough money!");
			}
		} 
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("�bKitty Cannon")) {
				if(LcTokensAPI.hasEnough(p, 60) && !(KittyCannonAmmoManager.balaceCatAmmo(p) > 1)) {
					LcTokensAPI.takePoints(p, 60);
					KittyCannonAmmoManager.giveCatAmmo(p, 50);
					ItemStack snow = new ItemStack(Material.STICK, 1);
		    		ItemMeta sno = snow.getItemMeta();
		    		sno.setDisplayName(ChatColor.GOLD + "Kitty Cannon " + ChatColor.DARK_RED + KittyCannonAmmoManager.balaceCatAmmo(p));
		    		snow.setItemMeta(sno);
		    		p.getInventory().setItem(2, snow);
		    		ApiEvent.updatescore(p);
	        		p.closeInventory();
				}else if(KittyCannonAmmoManager.balaceCatAmmo(p) > 1) {
					ItemStack snow = new ItemStack(Material.STICK, 1);
		    		ItemMeta sno = snow.getItemMeta();
		    		sno.setDisplayName(ChatColor.GOLD + "Kitty Cannon " + ChatColor.DARK_RED + KittyCannonAmmoManager.balaceCatAmmo(p));
		    		snow.setItemMeta(sno);
		    		p.getInventory().setItem(2, snow);
                	p.closeInventory();
				}
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("�2Coin Bomb")) {
				if(LcCoinsAPI.hasEnough(p, 5000)) {
					LcCoinsAPI.takePoints(p, 5000);
					ItemStack ender = new ItemStack(Material.DOUBLE_PLANT, 1);
					ItemMeta e = ender.getItemMeta();
					e.setDisplayName(ChatColor.DARK_GREEN + "Coin Bomb");
            		ender.setItemMeta(e);
            		p.getInventory().addItem(ender);
            		p.closeInventory();
				}else {
					p.sendMessage(ChatColor.RED + "You dont have enough money!");	
				}
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("�dToken Bomb")) {
				if(LcTokensAPI.hasEnough(p, 100)) {
					LcTokensAPI.takePoints(p, 100);
					ItemStack ender = new ItemStack(Material.NETHER_STAR, 1);
					ItemMeta e = ender.getItemMeta();
					e.setDisplayName(ChatColor.LIGHT_PURPLE + "Token Bomb");
					ender.setItemMeta(e);
					p.getInventory().addItem(ender);
            		p.closeInventory();
				} else {
					p.sendMessage(ChatColor.RED + "You dont have enough money!");
				}
		}if (event.getCurrentItem().getItemMeta().getDisplayName().equals("�d�lTeasure chest")) {
			if(LcTokensAPI.hasEnough(p, 80)) {
				LcTokensAPI.takePoints(p, 80);
				ItemStack ender = new ItemStack(Material.DIAMOND_BLOCK, 1);
				ItemMeta e = ender.getItemMeta();
				e.setDisplayName(ChatColor.DARK_BLUE + "Disco Ball");
        		ender.setItemMeta(e);
        		p.getInventory().addItem(ender);
        		p.closeInventory();
			}else {
				p.sendMessage(ChatColor.RED + "You dont have enough money!");	
			}
		}
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("�cGo Back")) {
				p.openInventory(CosmeticsMenu.cosmenu(p));
				p.playSound(p.getLocation(), Sound.DOOR_CLOSE, 10, 10);
				return;
		}
		}
		}catch(Exception e){}
	}
}
