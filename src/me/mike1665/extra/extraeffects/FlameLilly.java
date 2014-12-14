package me.mike1665.extra.extraeffects;

import me.mike1665.effects.EffectManager;
import me.mike1665.extra.ExtraManager;
import me.mike1665.particles18.ParticleLib18;
import me.mike1665.update.UpdateType;
import me.mike1665.update.event.UpdateEvent;
import me.mike1665.utils.MathUtils;
import me.mike1665.utils.UtilVector;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.util.Vector;

public class FlameLilly
  implements Listener
{
  private int particles = 150;
  private int particlesPerIteration = 12;
  private float size = 1.0F;
  private float xFactor = 1.0F;
  private float yFactor = 0.6F;
  private float zFactor = 1.0F;
  private float yOffset = 0.6F;
  private double xRotation;
  private double yRotation;
  private double zRotation = 0.0D;
  private int step;

  @EventHandler
  public void LocationUpdater(UpdateEvent event)
  {
    if (event.getType() == UpdateType.TICK)
    {
      for (Player p : EffectManager.effect3.keySet())
      {
        if (EffectManager.getEffect(p) == EffectManager.EffectType.FlameLilly)
        {
          if (p.isValid())
          {
            if (ExtraManager.isMoving(p))
            {
              Location location = p.getLocation();
              Vector vector = new Vector();

              for (int i = 0; i < this.particlesPerIteration; i++) {
                this.step += 1;

                float t = 3.141593F / this.particles * this.step;
                float r = MathUtils.sin(t * 2.718282F * this.particlesPerIteration / this.particles) * this.size;
                float s = r * 3.141593F * t;

                vector.setX(this.xFactor * r * -MathUtils.cos(s));
                vector.setZ(this.zFactor * r * -MathUtils.sin(s));
                vector.setY(this.yFactor + this.yOffset - 1.0F);

                UtilVector.rotateVector(vector, this.xRotation, this.yRotation, this.zRotation);
     		   	ParticleLib18 water = new ParticleLib18(me.mike1665.particles18.ParticleLib18.ParticleType.FLAME, 0.0D, 1, 0.0D);
     		   	water.sendToLocation(location.add(vector));
                location.subtract(vector);
              }

            }
            else if (!p.isInsideVehicle()) {
              ParticleLib18 water = new ParticleLib18(me.mike1665.particles18.ParticleLib18.ParticleType.FLAME, 0.0500000007450581D, 3, 0.2000000029802322D);
              water.sendToLocation(p.getLocation().add(0.0D, 1.0D, 0.0D));
            }
          }
        }
      }
    }
  }
}