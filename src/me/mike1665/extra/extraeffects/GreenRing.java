package me.mike1665.extra.extraeffects;

import me.mike1665.effects.EffectManager;
import me.mike1665.extra.ExtraManager;
import me.mike1665.particles18.ParticleLib18;
import me.mike1665.utils.UpdateEvent;
import me.mike1665.utils.UpdateType;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GreenRing
  implements Listener
{
  private double i = 0.0D;

  @EventHandler
  public void LocationUpdater(UpdateEvent event) { if (event.getType() == UpdateType.TICK)
    {
      for (Player p : EffectManager.effect.keySet())
      {
        if (EffectManager.getEffect(p) == EffectManager.EffectType.GreenRing)
        {
          if (p.isValid())
          {
            if (ExtraManager.isMoving(p))
            {
              double slice = 0.1570796326794897D;
              double radius = 1.0D;

              Location ploca = p.getLocation();
              double angle = slice * this.i;
              double angle2 = slice * this.i;
              angle2 -= angle2 * 2.0D;

              double newX = ploca.getX() - radius * Math.cos(angle);
              double newZ = ploca.getZ() - radius * Math.sin(angle);
              double newY2 = ploca.getY() + 1.0D + radius * Math.cos(angle);
              double newX2 = ploca.getX() + radius * Math.cos(angle2);
              double newZ2 = ploca.getZ() + radius * Math.sin(angle2);
              Location p1 = new Location(p.getWorld(), newX, newY2, newZ);
              Location p2 = new Location(p.getWorld(), newX2, newY2, newZ2);

   		   	  ParticleLib18 happy = new ParticleLib18(me.mike1665.particles18.ParticleLib18.ParticleType.VILLAGER_HAPPY, 0.0D, 1, 0.0D);
   		   	  ParticleLib18 happy2 = new ParticleLib18(me.mike1665.particles18.ParticleLib18.ParticleType.VILLAGER_HAPPY, 0.0D, 1, 0.0D);
   		   	  happy.sendToLocation(p1);
   		   	  happy2.sendToLocation(p2);
            }
            else if (!p.isInsideVehicle()) {
   		   	  ParticleLib18 happy = new ParticleLib18(me.mike1665.particles18.ParticleLib18.ParticleType.VILLAGER_HAPPY, 0.1000000014901161D, 4, 0.300000011920929D);
   		   	  happy.sendToLocation(p.getLocation().add(0.0D, 1.0D, 0.0D));
            }

          }

          this.i += 1.0D;
        }
      }
    }
  }
}