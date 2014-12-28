package com.lightcraftmc.menu;

import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import com.lightcraftmc.fusebox.menu.Menu;
import com.lightcraftmc.fusebox.menu.PlayerMenuEvent;
import com.lightcraftmc.fusebox.util.item.ItemTools;
import com.lightcraftmc.hub.main.Main;
import com.lightcraftmc.particle.CircleParticle;
import com.lightcraftmc.particle.ParticleManager.ParticleType;

public class ParticleMenu implements Listener {

	public static Menu m;
	Main plugin;
	Material[] display = { Material.WHEAT, Material.NOTE_BLOCK, Material.FLINT_AND_STEEL, Material.WATER_BUCKET, Material.LAVA_BUCKET, Material.IRON_INGOT, Material.INK_SACK,
			Material.ENCHANTMENT_TABLE, Material.POTION, Material.FIREBALL, Material.OBSIDIAN, Material.SNOW_BALL, Material.SLIME_BALL, Material.FERMENTED_SPIDER_EYE,
			Material.SNOW_BLOCK, Material.CLAY};
	public ParticleMenu(){
		plugin = Main.getInstance();
		m = new Menu("�a�lParticles", 9*6);

		int i = 0;
		for(ParticleType e : ParticleType.values()){
			int spot = i+(9);
			m.addItem(ItemTools.setName(new ItemStack(display[i]), "�b" + getName(i, display[i])), spot);
			i++;
		}
	}
	
	public String getName(int slot, Material type){
		return WordUtils.capitalize(ParticleType.values()[slot].toString().toLowerCase().replace("_", ""));
	}
	
	@EventHandler
	public void click(PlayerMenuEvent e){
		if(!e.getInventory().getName().equals(m.getName())){
			return;
		}
		ItemStack i = e.getInventory().getItem(e.getSlot());
		if(fromMaterial(i.getType()) != null){
			ParticleType type = fromMaterial(i.getType());
			CircleParticle.Activate(e.getPlayer(), type);
		}
	}
	
	public ParticleType fromMaterial(Material m){
		int i = 0;
		for(ParticleType t : ParticleType.values()){
			if(display[i] == m) return t;
			i++;
		}
		return null;
	}
}
