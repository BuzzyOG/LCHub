package fr.coco_gigpn.prodigygadget.mount.mounts;

import me.mike1665.Main.Main;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.Horse.Variant;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import fr.coco_gigpn.prodigygadget.gadget.gadgets.Rocket;
import fr.coco_gigpn.prodigygadget.mount.MountManager;
import fr.coco_gigpn.prodigygadget.util.Configs;
import fr.coco_gigpn.prodigygadget.util.Messages;
import fr.coco_gigpn.prodigygadget.util.UtilBlock;
import fr.coco_gigpn.prodigygadget.util.UtilBlock.RiderType;
import fr.coco_gigpn.prodigygadget.util.UtilLag;
import fr.coco_gigpn.prodigygadget.util.UtilPet;

public class AngelRider implements Listener {

	
	

	public static void playAngelRider(Player p) {
		if(!Rocket.stationnaire.containsKey(p) || !Rocket.effect.containsKey(p)) {
		
		if (!MountManager.HasPet(p)) {
		
		final Horse horse = p.getWorld().spawn(p.getLocation(), Horse.class);
		Entity entity = horse;
		Horse entityHorse = (Horse) entity;
		entityHorse.getInventory().setSaddle(
				new ItemStack(Material.SADDLE));
		horse.setCustomName(Messages.petName.replace("%ridername", Messages.angelriderPrefix.replace("&", "�")).replace("%player", p.getName().replace("&", "�")).replace("&", "�"));

		horse.setCustomNameVisible(true);
		horse.setOwner(p);
		horse.setVariant(Variant.HORSE);
		horse.setColor(Color.WHITE);
		horse.setAdult();
		horse.setPassenger(p);

		horse.setMetadata("angelrider", new FixedMetadataValue(Main.schedule, "angelrider"));
		MountManager.pet.put(p.getUniqueId(), horse);
		UtilPet.makePet(horse, p.getUniqueId());	
		
		
		} else {
			p.sendMessage(Messages.alreadypet.replace("&", "�"));
		}
		} else {
			p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1, 1);
		}
	}
	
	
	@EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
		if (event.getFrom().getBlockX() != event.getTo().getBlockX() || event.getFrom().getBlockZ() != event.getTo().getBlockZ())
        {
			Player p = event.getPlayer();
			Entity e = p.getVehicle();
			
			if (e instanceof Horse) {
	
		    
		
				
		if (e.hasMetadata("angelrider")) {
			if (UtilLag.ServerisLag() && Configs.stopParticleOnLag == true) {
				 return;
			 } else {
				 
				 if(Configs.MountPlaceBlock == true) {
		
					
					for (Block block : UtilBlock.getInRadius(MountManager.pet.get(p.getUniqueId()).getLocation(), 2.5D).keySet())
				    {
				      if (block.getType() == Material.PORTAL) {
				        return;
				      }
				      if (block.getType() == Material.CACTUS) {
				        return;
				      }
				      if (block.getType() == Material.IRON_PLATE) {
					        return;
					  }
				      if (block.getType() == Material.CHEST) {
					        return;
					  }
				      if (block.getType() == Material.LONG_GRASS) {
					        return;
					  }
				      if (block.getType() == Material.REDSTONE_LAMP_ON) {
					        return;
					  }
				      if (block.getType() == Material.REDSTONE_LAMP_OFF) {
					        return;
					  }
				      if (block.getType() == Material.ITEM_FRAME) {
					        return;
					  }
				      if (block.getType() == Material.RED_ROSE) {
					        return;
					  }
				      if (block.getType() == Material.RED_MUSHROOM) {
					        return;
					  }
				      if (block.getType() == Material.RAILS) {
					        return;
					  }
				      if (block.getType() == Material.ACTIVATOR_RAIL) {
					        return;
					  }
				      if (block.getType() == Material.POWERED_RAIL) {
					        return;
					  }
				      if (block.getType() == Material.VINE) {
					        return;
					  }
				      if (block.getType() == Material.CROPS) {
					        return;
					  }
				      if (block.getType() == Material.WHEAT) {
					        return;
					  }
				      if (block.getType() == Material.DOUBLE_PLANT) {
					        return;
					  }
				      if (block.getType() == Material.CARROT) {
					        return;
					  }
				      if (block.getType() == Material.IRON_PLATE) {
					        return;
					  }
				      if (block.getType() == Material.GOLD_PLATE) {
					        return;
					  }
				      if (block.getType() == Material.WOOD_PLATE) {
					        return;
					  }
				      if (block.getType() == Material.SKULL) {
							return;
						}
				      
				    }
					
					 for (Block block : UtilBlock.getInRadius(MountManager.pet.get(p.getUniqueId()).getLocation(), 2.5D).keySet())
					    {
				    	  if (UtilBlock.solid(block)){

						        if(block.getType() != Material.SNOW_BLOCK) {
						        	
						        	if (!UtilBlock.blockToRestore.contains(block)) {
						      
						        	UtilBlock.setBlockToRestore(block, 80 , (byte) 0, 1L, RiderType.AngelEffect);
						        	
						        	}
						    
						        }
						      }
					    }
				 }
				
				}
			
			
		}
			}
        }
	}

}
