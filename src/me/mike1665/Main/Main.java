package me.mike1665.Main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import me.mike1665.click.AdminGadgetsClick;
import me.mike1665.click.BuyGadgetsClick;
import me.mike1665.click.CosMenuClick;
import me.mike1665.click.GadjetsMenuClick;
import me.mike1665.click.MountMenuClick;
import me.mike1665.click.PlayerGadjetsClick;
import me.mike1665.click.VipGadjetsClick;
import me.mike1665.coinapi.ApiEvent;
import me.mike1665.coinapi.LcCoinsAPI;
import me.mike1665.coinapi.LcTokensAPI;
import me.mike1665.eventhandlers.BatBlaster;
import me.mike1665.eventhandlers.BuyEnderDoge;
import me.mike1665.eventhandlers.BuyMeowBalls;
import me.mike1665.eventhandlers.CatWorks;
import me.mike1665.eventhandlers.CoinBomb;
import me.mike1665.eventhandlers.EnderDoge;
import me.mike1665.eventhandlers.EnderRide;
import me.mike1665.eventhandlers.EntityHook;
import me.mike1665.eventhandlers.FireworkLauncher;
import me.mike1665.eventhandlers.MelonBlock;
import me.mike1665.eventhandlers.MeowBall;
import me.mike1665.eventhandlers.PaintballGun;
import me.mike1665.eventhandlers.PixlBomb;
import me.mike1665.eventhandlers.RespawnEvent;
import me.mike1665.eventhandlers.TNTFun;
import me.mike1665.hubstuff.DoubleJump;
import me.mike1665.hubstuff.LaunchPad;
import me.mike1665.hubstuff.NoHunger;
import me.mike1665.menu.AdminGadgets;
import me.mike1665.menu.BuyGadgets;
import me.mike1665.menu.CosmeticsMenu;
import me.mike1665.menu.GadjetsMenu;
import me.mike1665.menu.MountMenu;
import me.mike1665.menu.PlayerGadjets;
import me.mike1665.menu.VipGadjets;
import me.mike1665.mount.types.Frust;
import me.mike1665.mount.types.Mule;
import me.mike1665.mount.types.Undead;
import me.mike1665.mounts.MountManager;
import me.mike1665.parkour.CourseOne;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

import code.husky.mysql.MySQL;

import com.arrayprolc.command.ArrayCommandHandler;
import com.arrayprolc.event.ArrayEventSetup;
import com.arrayprolc.sql.SQLTools;
import com.arrayprolc.strings.MessageType;
import com.arrayprolc.strings.StringManager;
import com.mysql.jdbc.Connection;

import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.entity.EntityManager;
import de.slikey.effectlib.listener.ItemListener;

public class Main extends JavaPlugin implements Listener{
	
	public Scoreboard board;
	public HashMap<String, String> colors = new HashMap<String, String>();
	public static List<String> activate = new ArrayList<String>();
	public static HashMap<String, String> rainbowarmor = new HashMap<String, String>();
	public Integer armortask = null;
	public Integer updateboard = null;
	public static Location _spawn;
    @SuppressWarnings("unused")
	private ArrayList<String> usingarmor;
	String tag = ChatColor.RED + "" + ChatColor.BOLD + "Parkour " + ChatColor.RESET + "" + ChatColor.DARK_GRAY + "> ";
	public static Main instance;
	public boolean logdb = true;
	public Mule m = new Mule();
	public Undead und = new Undead();
	public Frust fro = new Frust();
	private EntityManager entityManager;
	public static MySQL MySQL;
	public static java.sql.Connection c = null;
	
	public void onEnable() {
		instance = this;


		LcTokensAPI.initialize(this);
		LcCoinsAPI.initialize(this);
		ApiEvent.initialize(this);
		Frust.initialize(this);
		MountManager.initialize(this);
		Undead.initialize(this);
		MountMenu.initialize(this);
		Mule.initialize(this);
		CourseOne.initialize(this);
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this, this);
		pm.registerEvents(new EnderDoge(), this);
		pm.registerEvents(new CatWorks(this), this);
		pm.registerEvents(new MeowBall(), this);
		pm.registerEvents(new VipGadjetsClick(), this);
		pm.registerEvents(new VipGadjets(), this);
		pm.registerEvents(new GadjetsMenuClick(), this);
		pm.registerEvents(new GadjetsMenu(), this);
		pm.registerEvents(new PlayerGadjetsClick(), this);
		pm.registerEvents(new PlayerGadjets(), this);
		pm.registerEvents(new AdminGadgetsClick(this), this);
		pm.registerEvents(new AdminGadgets(), this);
		pm.registerEvents(new EnderRide(), this);
		pm.registerEvents(new MelonBlock(), this);
		pm.registerEvents(new EntityHook(), this);
		pm.registerEvents(new RespawnEvent(), this);
		pm.registerEvents(new TNTFun(this), this);
		pm.registerEvents(new FireworkLauncher(), this);
		pm.registerEvents(new PaintballGun(this), this);
		pm.registerEvents(new BatBlaster(this), this);
		pm.registerEvents(new ApiEvent(), this);
		pm.registerEvents(new LcTokensAPI(), this);
		pm.registerEvents(new CoinBomb(this), this);
		pm.registerEvents(new LcCoinsAPI(), this);
		pm.registerEvents(new CosmeticsMenu(), this);
		pm.registerEvents(new CosMenuClick(), this);
		pm.registerEvents(new MountManager(), this);
		pm.registerEvents(new CourseOne(), this);
		pm.registerEvents(new MountMenuClick(), this);
		pm.registerEvents(new DoubleJump(), this);
		pm.registerEvents(new NoHunger(), this);
		pm.registerEvents(new LaunchPad(), this);
		pm.registerEvents(new BuyMeowBalls(), this);
		pm.registerEvents(new BuyGadgetsClick(), this);
		pm.registerEvents(new BuyGadgets(), this);
		pm.registerEvents(new BuyEnderDoge(), this);
		pm.registerEvents(new PixlBomb(this), this);
		ArrayEventSetup.setupEvents(this);
		ArrayCommandHandler.setup(this);
		colors.put("red", "255,0,0");
    	colors.put("orange", "255,127,0");
    	colors.put("yellow", "255,255,0");
    	colors.put("green", "0,255,0");
    	colors.put("blue", "0,0,255");
    	colors.put("indigo", "75,0,130");
    	colors.put("violet", "143,0,255");
    	armorrun();
        this.usingarmor = new ArrayList<String>();
		entityManager = new EntityManager(this);
        EffectManager.initialize();

		loadListeners();
		MySQL = new MySQL(Bukkit.getServer().getPluginManager().getPlugin("HubPlugin"), "db4free.net", "3306", "lcnetwork", "lcnetwork", getConfig().getString("sqlpassword"));
		try {
			c = MySQL.openConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SQLTools.statementTest();
	}
	@Override
	public void onDisable() {
		entityManager.dispose();
        EffectManager.disposeAll();
		HandlerList.unregisterAll((Plugin) this);
	}
	private void loadListeners() {
		getServer().getPluginManager().registerEvents(new ItemListener(), this);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public List<EffectManager> getEffectManagers() {
		return EffectManager.getManagers();
	}
	public void saveFile(){
		this.saveConfig();
	}
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] a) {
		
		if(ArrayCommandHandler.command(sender, cmd, label, a)) return true;
		Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("gadgets")) {
			player.openInventory(me.mike1665.menu.GadjetsMenu.gadmenu);
		}
		if (cmd.getName().equalsIgnoreCase("cosmenu")) {
			player.openInventory(me.mike1665.menu.CosmeticsMenu.cosmenu);
		}
		if (cmd.getName().equalsIgnoreCase("mountmenu")) {
			player.openInventory(MountMenu.getMountShop(player));
		}
		if (cmd.getName().equalsIgnoreCase("parkour")) {
			player.sendMessage(ChatColor.RED + "          :" +  ChatColor.RED + " Parkour Version 1" + ChatColor.RED + " :");
			player.sendMessage(ChatColor.RED + "          :" +  ChatColor.GREEN + " Developed by @SwaggyYolo " + ChatColor.RED + ":");
			player.sendMessage(tag + ChatColor.YELLOW + " /setparkour1 - Sets the spawn for Parkour 1. ");
			player.sendMessage(tag + ChatColor.YELLOW + " /parkour1 - Teleport to the set point. ");
			player.sendMessage(tag + ChatColor.YELLOW + " /cp1 - Sets Checkpoint for Course 1. ");

		}
		 if (cmd.getName().equalsIgnoreCase("setparkour1") && player.isOp()) {
             getConfig().set("parkour1.world", player.getLocation().getWorld().getName());
             getConfig().set("parkour1.x", player.getLocation().getX());
             getConfig().set("parkour1.y", player.getLocation().getY());
             getConfig().set("parkour1.z", player.getLocation().getZ());
             getConfig().set("parkour1.direction", player.getLocation().getDirection());
             getConfig().set("parkour1.yaw", player.getLocation().getYaw());
             getConfig().set("parkour1.pitch", player.getLocation().getPitch());

             saveConfig();
             player.sendMessage(tag + ChatColor.GREEN + "Parkour 1 spawn set!");
             return true;
		 }
		 if (cmd.getName().equalsIgnoreCase("cp1") && player.isOp()) {
             getConfig().set("cp1.world", player.getLocation().getWorld().getName());
             getConfig().set("cp1.x", player.getLocation().getX());
             getConfig().set("cp1.y", player.getLocation().getY());
             getConfig().set("cp1.z", player.getLocation().getZ());
             getConfig().set("cp1.direction", player.getLocation().getDirection());
             getConfig().set("cp1.yaw", player.getLocation().getYaw());
             getConfig().set("cp1.pitch", player.getLocation().getPitch());

             saveConfig();
             player.sendMessage(StringManager.getMessage("Checkpoint set for parkour 1!", MessageType.PARKOUR));
             return true;
		 }
		 if (cmd.getName().equalsIgnoreCase("parkour1")) {
			if (getConfig().getConfigurationSection("parkour1") == null) {
				player.sendMessage(StringManager.getPrefix(MessageType.ERROR) + "Parkour spawn not yet set!");
				return true;
			}
			org.bukkit.World w = Bukkit.getServer().getWorld(getConfig().getString("parkour1.world"));
			double yaw = getConfig().getDouble("parkour1.yaw");
			double pitch = getConfig().getDouble("parkour1.pitch");
            double x = getConfig().getDouble("parkour1.x");
            double y = getConfig().getDouble("parkour1.y");
            double z = getConfig().getDouble("parkour1.z");
            Location parkour1 = new Location(w, x, y,z);
            parkour1.setPitch((float) pitch);
            parkour1.setYaw((float) yaw);
            player.teleport(parkour1);
            player.sendMessage(StringManager.getPrefix(MessageType.PARKOUR) + "Teleported to " + ChatColor.AQUA +"Parkour1");
		}
		 
		 
		if (cmd.getName().equalsIgnoreCase("caoff")) {
			armorCancel(player, "off");
			player.sendMessage(StringManager.getPrefix(MessageType.GADGETS) + "Armor deactivated.");

		}
		if (cmd.getName().equalsIgnoreCase("stats")) {
			int a1 = LcTokensAPI.balancePoints(player);
			int b1 = LcCoinsAPI.balancePoints(player);
			player.sendMessage(StringManager.getPrefix(MessageType.INFO) + " Tokens - " + a1);
			player.sendMessage(StringManager.getPrefix(MessageType.INFO) + " Coins - " + b1);

		}
		if (cmd.getName().equalsIgnoreCase("addstaff") && player.isOp()) {
			if(player.isOp()) {
				this.getConfig().set(player.getUniqueId() + ".Administrator", true);
				this.saveFile();
				player.sendMessage(StringManager.getPrefix(MessageType.SUCCESS) + ChatColor.BLUE + "Added " + ChatColor.YELLOW + player.getUniqueId().toString() + ChatColor.BLUE +" to socreboard staff!");
				}	
			}
		
		if (cmd.getName().equalsIgnoreCase("givetokens") && player.isOp()) {
			if (a.length >= 1) {
				if(a.length == 1) {
					int tempValue;
					try
					{
						tempValue = Integer.parseInt(a[0]);
					}
					catch(NumberFormatException e)
					{
						player.sendMessage(StringManager.getPrefix(MessageType.ERROR) + "Value must be a number!");
						return true;
					}
					LcTokensAPI.givePoints(player, tempValue);
					ApiEvent.updatescore(player);
					player.sendMessage(StringManager.getPrefix(MessageType.TRANSACTION) + "" + a[0] + ChatColor.AQUA +" Tokens recieved!");

				}else if (a.length == 2){
					LcTokensAPI.givePoints(Bukkit.getOfflinePlayer(a[0]), Integer.parseInt(a[1]));
					Player tempPlayer = this.getServer().getPlayer(a[0]);
					if(tempPlayer != null){
						ApiEvent.updatescore(tempPlayer);
						tempPlayer.sendMessage(StringManager.getPrefix(MessageType.TRANSACTION) + "" + a[1] + ChatColor.AQUA +" Tokens recieved!");
					}
					
				}
				
			} else {
				player.sendMessage(ChatColor.RED + "Somehting Failed!");
			}
		}
		if (cmd.getName().equalsIgnoreCase("givecoins") && player.isOp()) {
			if (a.length >= 1) {
				if(a.length == 1) {
					int tempValue;
					try
					{
						tempValue = Integer.parseInt(a[0]);
					}
					catch(NumberFormatException e)
					{
						player.sendMessage(StringManager.getPrefix(MessageType.ERROR) + "Value must be a number!");
						return true;
					}
					LcCoinsAPI.givePoints(player, tempValue);
					ApiEvent.updatescore(player);
					player.sendMessage(StringManager.getPrefix(MessageType.TRANSACTION) + a[0] + ChatColor.AQUA +" Coins recieved!");

				}else if (a.length == 2){
					LcCoinsAPI.givePoints(Bukkit.getOfflinePlayer(a[0]), Integer.parseInt(a[1]));
					Player tempPlayer = this.getServer().getPlayer(a[0]);
					if(tempPlayer != null){
						ApiEvent.updatescore(tempPlayer);
						tempPlayer.sendMessage(StringManager.getPrefix(MessageType.TRANSACTION) + "" + a[1] + ChatColor.AQUA + " Coins recieved!");
					}
					
				}
				
			} else{
				player.sendMessage(StringManager.getPrefix(MessageType.ERROR) + "Something Failed");
			}
		}
		
		return false;
	}
	
    public void armorCancel(Player player, String reason) {
    	UUID playerName = player.getUniqueId();
    	activate.remove(playerName);
		rainbowarmor.remove(playerName);
		player.getInventory().setArmorContents(null);
    }
    
    public void armorrun() {
    	armortask = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
		    public void run() {
		    	cyclearmor();
		    }
		}, 0, 4);
    }
    
	public void cyclearmor() {
    	for(String playerName : rainbowarmor.keySet()) {
    		Player player = Bukkit.getPlayer(playerName);
    		if(player != null) {
    			String color = rainbowarmor.get(playerName);
    			color = nextColor(color);
    			setArmorColor(player,
    				getColor(color),
    				getColor(color),
    				getColor(color),
    				getColor(color)
    			);
    			rainbowarmor.put(playerName, color);
    		}
    	}
    }
	
    public void setArmorColor(Player player, Color helmetColor, Color chestplateColor, Color leggingsColor, Color bootsColor) {
    	PlayerInventory playerInventory = player.getInventory();
    	
    	List<String> lore = new ArrayList<String>();
    	lore.add("§aR§ba§ci§dn§eb§1o§2w §7Armor");
    	
    	ItemStack lhelmet = new ItemStack(Material.LEATHER_HELMET, 1);
    	LeatherArmorMeta lam = (LeatherArmorMeta)lhelmet.getItemMeta();
    	lam.setColor(helmetColor);
    	lam.setLore(lore);
    	lhelmet.setItemMeta(lam);
    	playerInventory.setHelmet(lhelmet);
    	
    	ItemStack lchestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
    	lam.setColor(chestplateColor);
    	lchestplate.setItemMeta(lam);
    	playerInventory.setChestplate(lchestplate);
    	
    	ItemStack lleggings = new ItemStack(Material.LEATHER_LEGGINGS, 1);
    	lam.setColor(leggingsColor);
    	lleggings.setItemMeta(lam);
    	playerInventory.setLeggings(lleggings);
    	
    	ItemStack lboots = new ItemStack(Material.LEATHER_BOOTS, 1);
    	lam.setColor(bootsColor);
    	lboots.setItemMeta(lam);
    	playerInventory.setBoots(lboots);
    }
    
    public Color getColor(String color) {
    	if(colors.containsKey(color)) {
    		String RGB = colors.get(color);
    		String[] RGBArray = RGB.split(",");
    		return Color.fromRGB(Integer.parseInt(RGBArray[0]), Integer.parseInt(RGBArray[1]), Integer.parseInt(RGBArray[2]));
    	}
    	return null;
    }
    
    public String nextColor(String color) {
    	if(color.equalsIgnoreCase("red")) return "orange";
    	else if(color.equalsIgnoreCase("orange")) return "yellow";
    	else if(color.equalsIgnoreCase("yellow")) return "green";
    	else if(color.equalsIgnoreCase("green")) return "blue";
    	else if(color.equalsIgnoreCase("blue")) return "indigo";
    	else if(color.equalsIgnoreCase("indigo")) return "violet";
    	else if(color.equalsIgnoreCase("violet")) return "red";
    	else return "red";
    }
    
    public static Location GetSpawn() {
        return _spawn.clone();
      }
}