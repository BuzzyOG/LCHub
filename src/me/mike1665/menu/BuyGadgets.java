package me.mike1665.menu;

import net.lightcraftmc.fusebox.util.item.ItemTools;

import java.util.ArrayList;

import me.mike1665.ammo.BatBlasterAmmoManager;
import me.mike1665.ammo.EnderDogeAmmoManager;
import me.mike1665.ammo.FireWorksAmmoManager;
import me.mike1665.ammo.FunCreeperAmmoManager;
import me.mike1665.ammo.GadgetAmmo;
import me.mike1665.ammo.KittyCannonAmmoManager;
import me.mike1665.ammo.MeowAmmoManager;
import me.mike1665.ammo.PaintballAmmoManager;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class BuyGadgets
  implements Listener
{
  public static String name = "�a�lGadgets";
  
  public static ItemStack createItem(Material material, int amount, short shrt, String displayname, String lore)
  {
    ItemStack item = new ItemStack(material, amount, shrt);
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName(displayname);
    ArrayList<String> Lore = new ArrayList();
    Lore.add(lore);
    meta.setLore(Lore);
    
    item.setItemMeta(meta);
    return item;
  }
  
  public static Inventory buygadmenu(Player p)
  {
    Inventory buygadmenu = Bukkit.createInventory(null, 54, name);
    
    buygadmenu.setItem(
      4, 
      createItem(Material.BED, 1, (short)0, "�cGo Back", 
      ""));
    
    buygadmenu.setItem(19, ItemTools.setName(new ItemStack(Material.SNOW_BALL), "�5Meow Balls", 
      new String[] {
      "", 
      "�rWant a little bit of fun on", 
      "�rthe hub? Throw some of these", 
      "�rand everyone will be amazed!", 
      "", 
      "�aClick to purchase", 
      "�r50 for �a500 Coins", 
      "", 
      "�rYour ammo: �b" + MeowAmmoManager.balaceMeowAmmo(p) }));
    

    buygadmenu.setItem(20, ItemTools.setName(new ItemStack(Material.ENDER_PEARL), "�9Ender Doge", 
      new String[] {
      "", 
      "�rNeed a little WOOF WOOF on the", 
      "�rserver?", 
      "", 
      "�aClick to purchase", 
      "�r50 for �a500 Coins", 
      "", 
      "�rYour ammo: �b" + EnderDogeAmmoManager.balaceEnderDogeAmmo(p) }));
    
    buygadmenu.setItem(21, ItemTools.setName(new ItemStack(Material.FIREWORK), "�5Fireworks", 
      new String[] {
      "", 
      "�rWant to style to the hub with", 
      "�rKABOOM? This is the right thing.", 
      "", 
      "�aClick to purchase", 
      "�r50 for �a800 Coins", 
      "", 
      "�rYour ammo: �b" + FireWorksAmmoManager.balaceFireWorkAmmo(p) }));
    
    buygadmenu.setItem(22, ItemTools.setName(new ItemStack(Material.EGG), "�aFun Creepers", 
      new String[] {
      "", 
      "�rWant to prank the players with", 
      "�rsome creepers?", 
      "", 
      "�aClick to purchase", 
      "�r10 for �a300 Coins", 
      "", 
      "�rYour ammo: �b" + FunCreeperAmmoManager.balaceCreeperAmmo(p) }));
    
    buygadmenu.setItem(23, ItemTools.setName(new ItemStack(Material.IRON_BARDING), "�5Bat Blaster", 
      new String[] {
      "", 
      "�rWant to move players around with", 
      "�rstyle? This is the right thing!", 
      "", 
      "�aClick to purchase", 
      "�r50 for �a800 Coins", 
      "", 
      "�rYour ammo: �b" + BatBlasterAmmoManager.balaceBatAmmo(p) }));
    
    buygadmenu.setItem(24, ItemTools.setName(new ItemStack(Material.DIAMOND_BARDING), "�dPaintball Gun", 
      new String[] {
      "�4�lTHIS FEATURE IS IN DEVELOPMENT!", 
      "", 
      "�rWant to color the hub with", 
      "�rcool looking style?", 
      "", 
      "�aClick to purchase", 
      "�r50 for �a800 Coins", 
      "", 
      "�rYour ammo: �b" + PaintballAmmoManager.balacePBAmmo(p) }));
    
    buygadmenu.setItem(25, ItemTools.setName(new ItemStack(Material.STICK), "�bKitty Cannon", 
      new String[] {
      "", 
      "�rDoes it shoot cats", 
      "�ror something else?", 
      "", 
      "�aClick to purchase", 
      "�r50 for �a100 �dTokens", 
      "", 
      "�rYour ammo: �b" + KittyCannonAmmoManager.balaceCatAmmo(p) }));
    
    buygadmenu.setItem(28, ItemTools.setName(new ItemStack(Material.DIAMOND_HOE), "�aPig Quake Gun", 
       new String[] {
       "", 
       "�rShoots pigs ridng", 
       "�rfireworks!?!?", 
       "", 
       "�aClick to purchase", 
       "�r50 for �a200 �dTokens", 
       "", 
       "�rYour ammo: �b" + GadgetAmmo.balanceGadgetAmo(p, "QuakeGunAmmo") }));
    
    buygadmenu.setItem(29, ItemTools.setName(new ItemStack(Material.DOUBLE_PLANT), "�2Coin Bomb", 
      new String[] {
      "", 
      "�rWant to throw an awesome", 
      "�rparty with coins?", 
      "", 
      "�aClick to purchase", 
      "�r1 for �a300 Coins", 
      "", 
      "�rYour ammo: �bNot Needed." }));
    
    buygadmenu.setItem(30, ItemTools.setName(new ItemStack(Material.NETHER_STAR), "�dToken Bomb", 
      new String[] {
      "", 
      "�rWant to throw an awesome", 
      "�rparty with RARE tokens?", 
      "", 
      "�aClick to purchase", 
      "�r1 for �d200 Tokens", 
      "", 
      "�rYour ammo: �bNot Needed." }));
    
    buygadmenu.setItem(31, ItemTools.setName(new ItemStack(Material.DIAMOND_SWORD), "�dPvP Sword", 
      new String[] {
      "", 
      "�rWant to PVP with others?", 
      "", 
      "�aClick to get!", 
      "�aCompletely free!", 
      "", 
      "�rYour ammo: �bNot Needed." }));
    
    buygadmenu.setItem(32, ItemTools.setName(new ItemStack(Material.BOW), "�bTeleport Bow", 
      new String[] {
      "", 
      "�rWant to teleport around?", 
      "", 
      "�aClick to get!", 
      "�aCompletely free!", 
      "", 
      "�rYour ammo: �bNot Needed." }));
    
    buygadmenu.setItem(49, ItemTools.setName(new ItemStack(Material.CHEST), "�d�lTreasure chest", 
      new String[] {
      "", 
      "�rWant to earn something completely", 
      "�rRARE and \"free\"!", 
      "", 
      "�aClick to purchase", 
      "�r1 for �d500 Tokens", 
      "", 
      "�rYour chests: �bCOMING SOON!" }));
    
    buygadmenu.setItem(8, ItemTools.setName(new ItemStack(Material.FIREBALL), "�4�lRemove Current Gadget", 
      new String[] {
      "", 
      "�rRemove your current gadget!", 
      "�rDont worry! Your ammo is saved!", 
      "" }));
    
    return buygadmenu;
  }
  
  @EventHandler(priority=EventPriority.HIGHEST)
  public void onInventoryClickEvent111(InventoryClickEvent event)
  {
    if (event.getCurrentItem() == null) {
      return;
    }
    event.getWhoClicked();
    if (event.getSlot() == 2) {
      event.setCancelled(true);
    }
  }
  
  @EventHandler(priority=EventPriority.HIGHEST)
  public void onPlayerDropItem111(PlayerDropItemEvent event)
  {
    int current_slot = event.getPlayer().getInventory().getHeldItemSlot();
    if (current_slot == 2) {
      event.setCancelled(true);
    }
  }
}


/* Location:           A:\LC\Lobby\plugins\HubPlugin.jar
 * Qualified Name:     me.mike1665.menu.BuyGadgets
 * JD-Core Version:    0.7.0.1
 */