package com.lightcraftmc.hubstuff;

import org.bukkit.event.Listener;

import com.lightcraftmc.coinapi.ApiEvent;

public class UpdateScore
  implements Listener
{
  public ApiEvent ae = new ApiEvent();
  
  /*@EventHandler
  public void updateScore(UpdateEvent e)
  {
    if (e.getType() == UpdateType.SEC) {
      for (Player p : Bukkit.getOnlinePlayers()) {
        ApiEvent.updatescore(p);
      }
    }
  } */
}


/* Location:           A:\LC\Lobby\plugins\HubPlugin.jar
 * Qualified Name:     com.lightcraftmc.hubstuff.UpdateScore
 * JD-Core Version:    0.7.0.1
 */