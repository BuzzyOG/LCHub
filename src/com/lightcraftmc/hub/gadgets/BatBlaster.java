package com.lightcraftmc.hub.gadgets;

import java.util.ArrayList;
import java.util.HashMap;

import me.mike1665.ammo.BatBlasterAmmoManager;
import me.mike1665.ammo.EnderDogeAmmoManager;
import me.mike1665.particlelib.ParticleEffect;
import me.mike1665.particles18.ParticleLib18;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.lightcraftmc.fusebox.util.UtilAction;
import com.lightcraftmc.fusebox.util.UtilAlg;
import com.lightcraftmc.fusebox.util.UtilEnt;
import com.lightcraftmc.fusebox.util.UtilEntity;
import com.lightcraftmc.fusebox.util.strings.MessageType;
import com.lightcraftmc.fusebox.util.strings.StringManager;
import com.lightcraftmc.hub.main.Main;

public class BatBlaster implements Listener {

	private HashMap<Player, Long> _active = new HashMap<Player, Long>();
	private HashMap<Player, Location> _velocity = new HashMap<Player, Location>();
	private HashMap<Player, ArrayList<Bat>> _bats = new HashMap<Player, ArrayList<Bat>>();
	private HashMap<Player, Double> _time = new HashMap<Player, Double>();
	HashMap<Player, BukkitRunnable> _cdRunnable = new HashMap<Player, BukkitRunnable>();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@EventHandler
	public void Activate(final PlayerInteractEvent event) {
		final Player player = event.getPlayer();
		if (player.getItemInHand() == null) {
			return;
		}
	    if (player.getItemInHand().getType() != Material.IRON_BARDING) {
	        return;
	    }
		if (event.getAction() == Action.RIGHT_CLICK_AIR
				|| event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if ((disName(player.getItemInHand()) != null)&& (disName(player.getItemInHand())
							.equalsIgnoreCase(ChatColor.DARK_GRAY + "Bat Blaster " + ChatColor.DARK_RED + BatBlasterAmmoManager.balaceBatAmmo(player)))) {
				if (this._time.containsKey(player)) {
					return;
				}
				this._time.put(player, Double.valueOf(3.1D));
				this._cdRunnable.put(player, new BukkitRunnable() {
					public void run() {
						BatBlaster.this._time.put(player, Double
								.valueOf(((Double) BatBlaster.this._time
										.get(player)).doubleValue() - 0.1D));
						if (((Double) BatBlaster.this._time.get(player))
								.doubleValue() < 0.01D) {
							BatBlaster.this._time.remove(player);
							BatBlaster.this._cdRunnable.remove(player);
							cancel();
						}
					}
				});
				((BukkitRunnable) this._cdRunnable.get(player)).runTaskTimer(
						Main.getInstance(), 2L, 2L);
				
				BatBlasterAmmoManager.takeBatAmmo(player, 1);
				if (BatBlasterAmmoManager.balaceBatAmmo(player) < 1){
					player.getInventory().setItemInHand(null);
					player.sendMessage(StringManager.getPrefix(MessageType.ERROR) + ChatColor.AQUA+ "You ran out of ammo!");
					return;
				}
	    		ItemStack snow = new ItemStack(Material.IRON_BARDING, 1);
	    		ItemMeta sno = snow.getItemMeta();
	    		sno.setDisplayName(ChatColor.DARK_GRAY + "Bat Blaster " + ChatColor.DARK_RED + BatBlasterAmmoManager.balaceBatAmmo(player));
	    		snow.setItemMeta(sno);
	    		player.getInventory().setItemInHand(snow);

				this._velocity.put(player, player.getEyeLocation());
				this._active.put(player,
						Long.valueOf(System.currentTimeMillis()));

				this._bats.put(player, new ArrayList());

				final Integer task = Integer.valueOf(Bukkit.getScheduler()
						.runTaskTimer(Main.getInstance(), new Runnable() {
							@SuppressWarnings("deprecation")
							public void run() {
								for (Player cur : Bukkit.getOnlinePlayers()) {
									if (BatBlaster.this._active
											.containsKey(cur)) {
										Location loc = (Location) BatBlaster.this._velocity
												.get(cur);
										for (Bat bat : BatBlaster.this._bats
												.get(cur)) {
											if (bat.isValid()) {
												Vector rand = new Vector(
														(Math.random() - 0.5D) / 3.0D,
														(Math.random() - 0.5D) / 3.0D,
														(Math.random() - 0.5D) / 3.0D);
												bat.setVelocity(loc
														.getDirection().clone()
														.multiply(0.5D)
														.add(rand));

												for (Player other : Bukkit
														.getServer()
														.getOnlinePlayers()) {
													if (!other.equals(cur)) {
														if (UtilEntity.hitBox(bat
																.getLocation(),
																other, 2.0D,
																null)) {
															UtilAction
																	.velocity(
																			other,
																			UtilAlg.getTrajectory(
																					cur,
																					other),
																			0.4D,
																			false,
																			0.0D,
																			0.2D,
																			10.0D,
																			true);

															bat.getWorld()
																	.playSound(
																			bat.getLocation(),
																			Sound.BAT_HURT,
																			1.0F,
																			1.0F);

															bat.remove();
															for (int k = 0; k < 3; k++)
																bat.getWorld()
																		.playEffect(
																				bat.getLocation(),
																				Effect.SMOKE,
																				1);
														}
													}
												}
											}
										}
									}
								}
							}
						}, 1L, 1L).getTaskId());

				Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
					public void run() {
						ArrayList<Bat> batList = BatBlaster.this._bats
								.get(event.getPlayer());
						for (Bat bat : batList) {
							bat.getWorld().playEffect(bat.getLocation(),
									Effect.SMOKE, 1);
							bat.remove();
							Bukkit.getScheduler().cancelTask(task.intValue());
						}

						BatBlaster.this._bats.remove(event.getPlayer());
					}
				}, 40L);

				for (int i = 0; i < 16; i++)
					((ArrayList) this._bats.get(player)).add((Bat) player
							.getWorld().spawn(player.getEyeLocation(),
									Bat.class));
			}
		}
	}

	public void Clear(Player player) {

		this._active.remove(player);
		this._velocity.remove(player);
		if (this._bats.containsKey(player)) {
			for (Bat bat : this._bats.get(player)) {
				if (bat.isValid()) {
				    ParticleLib18 angry = new ParticleLib18(me.mike1665.particles18.ParticleLib18.ParticleType.VILLAGER_ANGRY, 0.0F, 3, 0);
					angry.sendToLocation(bat.getLocation());
				}
				bat.remove();
			}

			this._bats.remove(player);
		}
	}

	public static double arrondi(double A, int B) {
		return (int) (A * Math.pow(10.0D, B) + 0.5D) / Math.pow(10.0D, B);
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