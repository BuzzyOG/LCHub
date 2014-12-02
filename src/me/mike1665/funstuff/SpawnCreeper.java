package me.mike1665.funstuff;

import me.mike1665.Main.Main;
import me.mike1665.coinapi.LcCoinsAPI;
import me.mike1665.menu.BuyGadgets;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SpawnCreeper implements Listener {

	private Main plugin;

	public SpawnCreeper(Main main) {
		this.plugin = main;
	}

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		try{
		Player p = (Player) event.getWhoClicked();
		if (event.getInventory().getName()
				.equalsIgnoreCase(BuyGadgets.buygadmenu.getName())) {
			event.setCancelled(true);

			if (event.getCurrentItem() == null) {
				return;
			}

			if (!(event.getCurrentItem().hasItemMeta())) {
				return;
			}

			if (!event.getCurrentItem().hasItemMeta()) {
				return;
			}
		

		}
		if (event.getCurrentItem().getItemMeta().getDisplayName()
				.equals("�aFun Creeper")) {
			if(LcCoinsAPI.hasEnough(p, 30)) {
				LcCoinsAPI.takePoints(p, 30);
					ItemStack enderpearl = new ItemStack(Material.EGG, 2);
					ItemMeta ender = enderpearl.getItemMeta();
					ender.setDisplayName("�4F�cu�6n �eC�2r�ae�be�3p�1e�9r");
					enderpearl.setItemMeta(ender);
					p.getInventory().addItem(enderpearl);
					p.closeInventory();
					return;
				} else {
					p.sendMessage("Youdont have enough money!");
				}
			}
		}catch(Exception ex){}
	}

	public void spawnCreeper(Player player, Location l) {
		final Creeper creeper = (Creeper) player.getWorld().spawn(l, Creeper.class);
		Bukkit.getServer().getScheduler()
				.scheduleSyncRepeatingTask(plugin, new Runnable() {
					int num = 1;

					@Override
					public void run() {
						if (num == 1) {
							creeper.setCustomName(ChatColor.DARK_RED
									+ "Fun Creeper");
							creeper.setCustomNameVisible(true);
							num++;
						} else if (num == 2) {
							creeper.setCustomName(ChatColor.RED 
									+ "Fun Creeper");
							creeper.setCustomNameVisible(true);
							num++;
						} else if (num == 3) {
							creeper.setCustomName(ChatColor.GOLD
									+ "Fun Creeper");
							creeper.setCustomNameVisible(true);
							num++;
						} else if (num == 4) {
							creeper.setCustomName(ChatColor.YELLOW
									+ "Fun Creeper");
							creeper.setCustomNameVisible(true);
							num++;
						} else if (num == 5) {
							creeper.setCustomName(ChatColor.DARK_GREEN
									+ "Fun Creeper");
							creeper.setCustomNameVisible(true);
							num++;
						} else if (num == 6) {
							creeper.setCustomName(ChatColor.GREEN
									+ "Fun Creeper");
							creeper.setCustomNameVisible(true);
							num++;
						} else if (num == 7) {
							creeper.setCustomName(ChatColor.BLUE
									+ "Fun Creeper");
							creeper.setCustomNameVisible(true);
							num = 1;
						} else if (num == 8) {
							creeper.setCustomName(ChatColor.DARK_AQUA
									+ "Fun Creeper");
							creeper.setCustomNameVisible(true);
							num = 1;
						} else if (num == 9) {
							creeper.setCustomName(ChatColor.DARK_BLUE
									+ "Fun Creeper");
							creeper.setCustomNameVisible(true);
							num = 1;
						} else if (num == 10) {
							creeper.setCustomName(ChatColor.BLUE
									+ "Fun Creeper");
							creeper.setCustomNameVisible(true);
							num = 1;
						} else if (num == 11) {
							creeper.setCustomName(ChatColor.LIGHT_PURPLE
									+ "Fun Creeper");
							creeper.setCustomNameVisible(true);
							num = 1;
						} else if (num == 12) {
							creeper.setCustomName(ChatColor.DARK_PURPLE
									+ "Fun Creeper");
							creeper.setCustomNameVisible(true);
							num = 1;
						} else if (!creeper.isValid()) {
							
						}
					}
				}, 0, 1 * 2);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler (priority = EventPriority.HIGHEST)
	public void arrowtp(ProjectileHitEvent e) {
		Projectile proj = e.getEntity();
		if ((proj instanceof Egg)) {
			Egg egg = (Egg) proj;
			if ((egg.getShooter() instanceof Player)) {
				Player p = (Player) egg.getShooter();
				if ((disName(p.getItemInHand()) != null)
						&& (disName(p.getItemInHand())
								.equalsIgnoreCase("�4F�cu�6n �eC�2r�ae�be�3p�1e�9r"))) {
					spawnCreeper(p, new Location(egg.getWorld(), egg.getLocation().getX(), egg.getLocation().getY(), egg.getLocation().getZ(), p.getLocation().getYaw(), p.getLocation().getPitch()));
					p.setItemInHand(null);
				}
			}
		} 
	}

	public String disName(ItemStack i) {
		if (i == null) {
			return null;
		}
		if (!i.hasItemMeta()) {
			return null;
		}
		if (!i.getItemMeta().hasDisplayName()) {
			return null;
		}
		return i.getItemMeta().getDisplayName();
	}
}