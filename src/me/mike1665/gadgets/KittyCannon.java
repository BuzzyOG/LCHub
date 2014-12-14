package me.mike1665.gadgets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import me.mike1665.Main.Main;
import me.mike1665.ammo.FireWorksAmmoManager;
import me.mike1665.ammo.KittyCannonAmmoManager;
import me.mike1665.utils.CustomEntityFirework;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.FireworkEffect.Builder;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class KittyCannon implements Listener{

	  private final Random random = new Random();
		private ArrayList<UUID> _coolDown = new ArrayList<UUID>();
	  HashMap<Player, BukkitRunnable> _cdRunnable = new HashMap<Player, BukkitRunnable>();

  
	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerUse(PlayerInteractEvent event) {
	  final Player p = event.getPlayer(); 
	  
	  if (p.getItemInHand() == null) {
			return;
		}
	    if (p.getItemInHand().getType() != Material.STICK) {
	        return;
	    }
		if (this._coolDown.contains(p.getUniqueId())) {
			return;
		}
	  if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
		  if ((disName(p.getItemInHand()) != null) && (disName(p.getItemInHand()).equalsIgnoreCase(ChatColor.GOLD + "Kitty Cannon " + ChatColor.DARK_RED + KittyCannonAmmoManager.balaceCatAmmo(p)))){
			  this._coolDown.add(p.getUniqueId());
				Bukkit.getScheduler().runTaskLater(Main.schedule, new Runnable() {
			
					@Override
					public void run() {
						KittyCannon.this._coolDown.remove(p.getUniqueId());
				
					}
				}, 8L);
				    
				KittyCannonAmmoManager.takeCatAmmo(p, 1);
				if (KittyCannonAmmoManager.balaceCatAmmo(p) < 1){
					p.getInventory().setItemInHand(null);
					p.sendMessage("testdsdas");
					return;
				}
		    	ItemStack snow = new ItemStack(Material.STICK, 1);
		    	ItemMeta sno = snow.getItemMeta();
		    	sno.setDisplayName(ChatColor.GOLD + "Kitty Cannon " + ChatColor.DARK_RED + KittyCannonAmmoManager.balaceCatAmmo(p));
		    	snow.setItemMeta(sno);
		    	p.getInventory().setItemInHand(snow);    
	    		Entity ent = (org.bukkit.entity.Ocelot) p.getWorld().spawnEntity(p.getLocation(), EntityType.OCELOT);
	    		final Ocelot ocelot = (Ocelot) ent;
				if (ocelot == null)
				{
					return;
				}
				final int i = random.nextInt(Ocelot.Type.values().length);
				ocelot.setCatType(Ocelot.Type.values()[i]);
				ocelot.setTamed(true);
				ocelot.setBaby();
				ocelot.setVelocity(p.getEyeLocation().getDirection().multiply(2));
				new BukkitRunnable() {
					
					int count = 1;
					@Override
					public void run() {
						final Location loc = ocelot.getLocation();
						ocelot.remove();
						final org.bukkit.entity.Firework fw = (org.bukkit.entity.Firework) ocelot.getWorld().spawnEntity(loc.add(/*0.5, 1.2, 0.5*/ 0.5, 1.2, 0.5), EntityType.FIREWORK);
						Builder builder = FireworkEffect.builder();
						FireworkMeta m = fw.getFireworkMeta();
						builder.trail(random.nextBoolean()).flicker(random.nextBoolean());
						builder.withColor(Color.fromRGB(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
						builder.with(Type.values()[random.nextInt(Type.values().length)]);
						builder.withFade(Color.fromRGB(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
						m.addEffect(builder.build());
						m.setPower(/*random.nextInt(3) + 1*/ 0 );
						fw.detonate();
						fw.setFireworkMeta(m);
						count --;
						if (count == 0) cancel();
					}
				}.runTaskTimer(Main.schedule, 12, 1);
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
	
	  public static double arrondi(double A, int B) {
		    return (int)(A * Math.pow(10.0D, B) + 0.5D) / Math.pow(10.0D, B);
		  }
	
}
