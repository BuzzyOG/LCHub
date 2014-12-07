package me.mike1665.gadgets;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import me.mike1665.Main.Main;
import me.mike1665.ammo.EnderDogeAmmoManager;
import me.mike1665.ammo.FireWorksAmmoManager;
import me.mike1665.ammo.MeowAmmoManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Sound;
import org.bukkit.FireworkEffect.Builder;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Material;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class FireWorks implements Listener{

	public final Random random = new Random();
	private ArrayList<UUID> _coolDown = new ArrayList<UUID>();

	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerPlaceBlock(PlayerInteractEvent event) {
		final Player player = event.getPlayer();
		
		if (player.getItemInHand() == null) {
			return;
		}
	    if (player.getItemInHand().getType() != Material.NETHER_STAR) {
	        return;
	    }
		if (this._coolDown.contains(player.getUniqueId())) {
			return;
		}
		  if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
		 if ((disName(player.getItemInHand()) != null) && (disName(player.getItemInHand()).equalsIgnoreCase(ChatColor.RED + "Firework " + "�7��b " + FireWorksAmmoManager.balaceFireWorkAmmo(player) + " �7��b"))){		
				this._coolDown.add(player.getUniqueId());
				Bukkit.getScheduler().runTaskLater(Main.schedule, new Runnable() {
			
					@Override
					public void run() {
						FireWorks.this._coolDown.remove(player.getUniqueId());
				
					}
				}, 5L);
		
				final org.bukkit.entity.Firework fw = (org.bukkit.entity.Firework) player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);
				Builder builder = FireworkEffect.builder();
				FireworkMeta m = fw.getFireworkMeta();
				builder.trail(random.nextBoolean()).flicker(random.nextBoolean());
				builder.withColor(Color.fromRGB(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
				builder.with(Type.values()[random.nextInt(Type.values().length)]);
				builder.withFade(Color.fromRGB(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
				m.addEffect(builder.build());
				m.setPower(random.nextInt(3)+1);
				fw.setFireworkMeta(m);
				event.setCancelled(true);
				
				FireWorksAmmoManager.takeFireWorkAmmo(player, 1);
				if (FireWorksAmmoManager.balaceFireWorkAmmo(player) < 1){
					player.getInventory().setItemInHand(null);
					player.sendMessage("testdsdas");
					return;
				}
	    		ItemStack snow = new ItemStack(Material.FIREWORK, 1);
	    		ItemMeta sno = snow.getItemMeta();
	    		sno.setDisplayName(ChatColor.RED + "Firework " + "�7��b " + FireWorksAmmoManager.balaceFireWorkAmmo(player) + " �7��b");
	    		snow.setItemMeta(sno);
	    		player.getInventory().setItemInHand(snow);	
		 		}
			}
		}
	
	public String disName(ItemStack i)
	{
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
