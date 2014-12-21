package me.jrl1004.lightcraft.utils;

import java.util.Random;

import me.jrl1004.lightcraft.gadgets.seekers.SeekerBeam;
import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class JacobCommandHandler implements Listener {

	private static Random random;
	static Main plugin;
	public static JacobCommandHandler instance2;

	public static void setup() {
		plugin = Main.getInstance();
		instance2 = new JacobCommandHandler();
		random = new Random();
		Bukkit.getPluginManager().registerEvents(instance2, Main.getInstance());
	}

	public static boolean command(CommandSender sender, Command cmd,
			String label, String[] a) {
		String name = cmd.getName();
		if (name.equalsIgnoreCase("Seeker")) {
			if (sender instanceof Player == false)
				return false;
			Player s = (Player) sender;
			if (a.length == 0) {
				s.sendMessage("/Seeker <target>");
				return true;
			}
			Player t = Bukkit.getPlayer(a[0]);
			if (t == null) {
				s.sendMessage("Player is offline");
				return true;
			}
			SeekerBeam
					.launchBeam(
							s.getEyeLocation(),
							t.getEyeLocation(),
							(int) (s.getEyeLocation().distance(
									t.getEyeLocation()) * 2),
							Math.sin(random.nextInt(4)),
							Math.sin(random.nextInt(4)),
							Math.sin(random.nextInt(4)));
			return true;
		}

		return false;
	}
}
