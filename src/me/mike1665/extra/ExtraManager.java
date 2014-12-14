package me.mike1665.extra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import me.mike1665.Main.Main;
import me.mike1665.effects.EffectManager;
import me.mike1665.extra.extraeffects.Cloud;
import me.mike1665.extra.extraeffects.CloudLight;
import me.mike1665.extra.extraeffects.CloudSnow;
import me.mike1665.extra.extraeffects.FlameLilly;
import me.mike1665.extra.extraeffects.FlameRing;
import me.mike1665.extra.extraeffects.Fountain;
import me.mike1665.extra.extraeffects.GreenRing;
import me.mike1665.extra.extraeffects.GreenSpiral;
import me.mike1665.extra.extraeffects.Helix;
import me.mike1665.extra.extraeffects.HourGlass;
import me.mike1665.extra.extraeffects.Shield;
import me.mike1665.extra.extraeffects.Tornado;
import me.mike1665.extra.extraeffects.Vortex;
import me.mike1665.particle.ParticleManager;
import me.mike1665.utils.UpdateEvent;
import me.mike1665.utils.UpdateType;
import me.mike1665.utils.UtilLocation;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

public class ExtraManager
  implements Listener
{
  public static List<String> Moving = new ArrayList();
  final HashMap<UUID, Location> lastBlockLocation = new HashMap();

  @EventHandler
  public void ParticleAura(UpdateEvent event) { if (event.getType() == UpdateType.FASTEST)
    {
      for (Player player : EffectManager.effect.keySet())
      {
        Location Current = player.getLocation();
        Location Last = (Location)this.lastBlockLocation.get(player.getUniqueId());
        if (this.lastBlockLocation.get(player.getUniqueId()) == null)
        {
          this.lastBlockLocation.put(player.getUniqueId(), Current);
          Last = (Location)this.lastBlockLocation.get(player.getUniqueId());
        }
        this.lastBlockLocation.put(player.getUniqueId(), player.getLocation());
        if ((Last.getX() != Current.getX()) || (Last.getY() != Current.getY()) || (Last.getZ() != Current.getZ()))
        {
          if (!Moving.contains(player.getName())) {
            Moving.add(player.getName());
          }
        }
        else if (Moving.contains(player.getName()))
          Moving.remove(player.getName());
      }
    }
  }

  public static boolean isMoving(Player p)
  {
    if (Moving.contains(p.getName())) {
      return false;
    }
    return true;
  }

  public static boolean hasExtraEffect(Player p)
  {
    if ((EffectManager.getEffect(p) == EffectManager.EffectType.Cloud) || 
      (EffectManager.getEffect(p) == EffectManager.EffectType.CloudSnow) || 
      (EffectManager.getEffect(p) == EffectManager.EffectType.CloudLight) || 
      (EffectManager.getEffect(p) == EffectManager.EffectType.Helix) || 
      (EffectManager.getEffect(p) == EffectManager.EffectType.Vortex) || 
      (EffectManager.getEffect(p) == EffectManager.EffectType.FlameLilly) || 
      (EffectManager.getEffect(p) == EffectManager.EffectType.HourGlass) || 
      (EffectManager.getEffect(p) == EffectManager.EffectType.Shield) || 
      (EffectManager.getEffect(p) == EffectManager.EffectType.Fountain) || 
      (EffectManager.getEffect(p) == EffectManager.EffectType.GreenRing) || 
      (EffectManager.getEffect(p) == EffectManager.EffectType.FlameRing) || 
      (EffectManager.getEffect(p) == EffectManager.EffectType.GreenSpiral) || 
      (EffectManager.getEffect(p) == EffectManager.EffectType.Tornado))
    {
      return true;
    }

    return false;
  }

  public static void removeExtraEffect(Player p)
  {
    EffectManager.effect.remove(p);
    UtilLocation.locationEvery2Second.remove(p);
  }

  public static void Activate(Player p, EffectManager.EffectType type)
  {
    if (!EffectManager.hasEffect(p))
    {
      EffectManager.effect.put(p, type);

      if (!hasExtraEffect(p))
      {
        UtilLocation.locationEvery2Second.put(p, p.getLocation());
      }
      else
      {
        removeExtraEffect(p);
        EffectManager.effect.put(p, type);
        UtilLocation.locationEverySecond.put(p, p.getLocation());
      }
    } else {
      if (hasExtraEffect(p)) {
        removeExtraEffect(p);
        EffectManager.effect.put(p, type);
        UtilLocation.locationEverySecond.put(p, p.getLocation());
      }
      if (ParticleManager.hasCircleEffect(p))
        p.sendMessage("Too Many effects");
    }
  }

  public static void registerEvents(Main plugin)
  {
    PluginManager pm = plugin.getServer().getPluginManager();
    pm.registerEvents(new Cloud(), plugin);
    pm.registerEvents(new CloudSnow(), plugin);
    pm.registerEvents(new CloudLight(), plugin);
    pm.registerEvents(new Helix(), plugin);
    pm.registerEvents(new Vortex(), plugin);
    pm.registerEvents(new Tornado(), plugin);
    pm.registerEvents(new HourGlass(), plugin);
    pm.registerEvents(new Shield(), plugin);
    pm.registerEvents(new Fountain(), plugin);
    pm.registerEvents(new FlameLilly(), plugin);
    pm.registerEvents(new GreenRing(), plugin);
    pm.registerEvents(new FlameRing(), plugin);
    pm.registerEvents(new GreenSpiral(), plugin);
    pm.registerEvents(new ExtraManager(), plugin);
  }
}