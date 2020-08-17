package me.Spoochiee.customlinks;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.fusesource.jansi.Ansi;

import me.Spoochiee.customlinks.Files.DataManager;
import me.Spoochiee.customlinks.Metrics.MetricsLite;

public class Main extends JavaPlugin implements CommandExecutor, Listener {
	
	String enableMessage = "Thank you for using, ";
	String plugin = "CustomLinksGUI";
	String versionMessage = "You are running, ";
	String version = "Version: 1.1.2";
	String creator = "Spoochiee!";
	
	public Main() {}
	
	public Inventory inv;
	
	public DataManager data;
	
	@Override
	public void onEnable() {
		this.getLogger().log(Level.INFO, Ansi.ansi().fg(Ansi.Color.GREEN).boldOff().a(enableMessage).fg(Ansi.Color.MAGENTA).a(plugin).fg(Ansi.Color.WHITE).toString());
		this.getLogger().log(Level.INFO, Ansi.ansi().fg(Ansi.Color.RED).boldOff().a(versionMessage).fg(Ansi.Color.CYAN).a(version).fg(Ansi.Color.WHITE).toString());
		this.getLogger().log(Level.INFO, Ansi.ansi().fg(Ansi.Color.RED).boldOff().a("Plugin created by: ").fg(Ansi.Color.CYAN).a(creator).fg(Ansi.Color.WHITE).toString());
		
		this.data = new DataManager(this);
		
		this.getCommand("linksgui").setExecutor(new ReloadCommand(this, data));
		
		this.getServer().getPluginManager().registerEvents(this, this);
		createInv();
		
		//Enables bStats
		int pluginId = 8496;
		@SuppressWarnings("unused")
		MetricsLite metrics = new MetricsLite(this, pluginId);
	}
	
	@Override
	public void onDisable() {
		this.getLogger().log(Level.INFO, Ansi.ansi().fg(Ansi.Color.GREEN).boldOff().a(enableMessage).fg(Ansi.Color.MAGENTA).a(plugin).fg(Ansi.Color.WHITE).toString());
		this.getLogger().log(Level.INFO, Ansi.ansi().fg(Ansi.Color.RED).boldOff().a(versionMessage).fg(Ansi.Color.CYAN).a(version).fg(Ansi.Color.WHITE).toString());
		this.getLogger().log(Level.INFO, Ansi.ansi().fg(Ansi.Color.RED).boldOff().a("Plugin created by: ").fg(Ansi.Color.CYAN).a(creator).fg(Ansi.Color.WHITE).toString());
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("links")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage("You cannot perform that command in a console!");
				return true;
			}
			Player player = (Player) sender;
			//opens the GUI
			if(player.hasPermission("customlinksgui.links")) {
				player.openInventory(inv);
				return true;
			} 
			else {
				player.sendMessage("You do not have permission to use this!");
				return false;
			}
		}
		return false;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		try {
			if(event.getInventory().equals(inv)) {
				if(!event.getClickedInventory().equals(inv))
					event.setCancelled(true);
				if(!event.getView().getTitle().contains(ChatColor.translateAlternateColorCodes('&', this.data.getConfig().getString("GUI.name"))))
					event.setCancelled(true);
			    if(event.getCurrentItem() == null)
			    	event.setCancelled(true);
			    if(event.getCurrentItem().getItemMeta() == null)
			    	event.setCancelled(true);
			    if(event.getRawSlot() > 9)
			    	event.setCancelled(true);
				
				Player player = (Player) event.getWhoClicked();
				
				if(event.getRawSlot() == 0) {
					//Link One
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.data.getConfig().getString("name.link1") + ": " + this.data.getConfig().getString("links.link1")));
					player.closeInventory();
				}
				
				if(event.getRawSlot() == 1) {
					//Link Two
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.data.getConfig().getString("name.link2") + ": " + this.data.getConfig().getString("links.link2")));
					player.closeInventory();
				}
				
				if(event.getRawSlot() == 2) {
					//Link Three
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.data.getConfig().getString("name.link3") + ": " + this.data.getConfig().getString("links.link3")));
					player.closeInventory();
				}
				
				if(event.getRawSlot() == 3) {
					//Link Four
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.data.getConfig().getString("name.link4") + ": " + this.data.getConfig().getString("links.link4")));
					player.closeInventory();
				}
				
				if(event.getRawSlot() == 4) {
					//Link Five
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.data.getConfig().getString("name.link5") + ": " + this.data.getConfig().getString("links.link5")));
					player.closeInventory();
				}
				
				if(event.getRawSlot() == 5) {
					//Link Six
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.data.getConfig().getString("name.link6") + ": " + this.data.getConfig().getString("links.link6")));
					player.closeInventory();
				}
				
				if(event.getRawSlot() == 6) {
					//Link Seven
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.data.getConfig().getString("name.link7") + ": " + this.data.getConfig().getString("links.link7")));
					player.closeInventory();
				}
				
				if(event.getRawSlot() == 7) {
					event.setCancelled(true);
				}
				
				if(event.getRawSlot() == 8) {
					//Exit Button
					player.closeInventory();
				}
				event.setCancelled(true);
			}
		}
		catch(Exception e) {
			
		}
	}
	
	public void createInv() {
		inv = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', this.data.getConfig().getString("GUI.name")));
		
		ItemStack item = new ItemStack(Material.EMERALD_BLOCK);
		ItemMeta meta = item.getItemMeta();
		
		if(this.data.getConfig().getBoolean("number.1") == true || this.data.getConfig().getBoolean("number.2") == true || this.data.getConfig().getBoolean("number.3") == true ||
				this.data.getConfig().getBoolean("number.4") == true || this.data.getConfig().getBoolean("number.5") == true || this.data.getConfig().getBoolean("number.6") == true || 
					this.data.getConfig().getBoolean("number.7") == true) {
			//First Item/Link
			meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.data.getConfig().getString("name.link1")));
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.translateAlternateColorCodes('&', this.data.getConfig().getString("description.link1")));
			meta.setLore(lore);
			item.setItemMeta(meta);
			inv.setItem(0, item);
			
			
			
			//Exit Button
			item.setType(Material.BARRIER);
			meta.setDisplayName(ChatColor.RED + "Exit!");
			lore.clear();
			lore.add(ChatColor.GRAY + "Click here to Exit LinksGUI!");
			meta.setLore(lore);
			item.setItemMeta(meta);
			inv.setItem(8, item);
		}
		
		item.setType(Material.EMERALD_BLOCK);
		
		if(this.data.getConfig().getBoolean("number.2") == true || this.data.getConfig().getBoolean("number.3") == true || this.data.getConfig().getBoolean("number.4") == true 
				| this.data.getConfig().getBoolean("number.5") == true || this.data.getConfig().getBoolean("number.6") == true || this.data.getConfig().getBoolean("number.7") == true) {
			//Second Item/Link
			meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.data.getConfig().getString("name.link2")));
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.translateAlternateColorCodes('&', this.data.getConfig().getString("description.link2")));
			meta.setLore(lore);
			item.setItemMeta(meta);
			inv.setItem(1, item);
		}
		
		if(this.data.getConfig().getBoolean("number.3") == true || this.data.getConfig().getBoolean("number.4") == true 
				| this.data.getConfig().getBoolean("number.5") == true || this.data.getConfig().getBoolean("number.6") == true || this.data.getConfig().getBoolean("number.7") == true) {
			//Third Item/Link
			meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.data.getConfig().getString("name.link3")));
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.translateAlternateColorCodes('&', this.data.getConfig().getString("description.link3")));
			meta.setLore(lore);
			item.setItemMeta(meta);
			inv.setItem(2, item);
		}
		
		if(this.data.getConfig().getBoolean("number.4") == true || this.data.getConfig().getBoolean("number.5") == true || this.data.getConfig().getBoolean("number.6") == true 
				|| this.data.getConfig().getBoolean("number.7") == true) {
			//Fourth Item/Link
			meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.data.getConfig().getString("name.link4")));
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.translateAlternateColorCodes('&', this.data.getConfig().getString("description.link4")));
			meta.setLore(lore);
			item.setItemMeta(meta);
			inv.setItem(3, item);
		}
		
		if(this.data.getConfig().getBoolean("number.5") == true || this.data.getConfig().getBoolean("number.6") == true || this.data.getConfig().getBoolean("number.7") == true) {
			//Fifth Item/Link
			meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.data.getConfig().getString("name.link5")));
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.translateAlternateColorCodes('&', this.data.getConfig().getString("description.link5")));
			meta.setLore(lore);
			item.setItemMeta(meta);
			inv.setItem(4, item);
		}
		
		if(this.data.getConfig().getBoolean("number.6") == true || this.data.getConfig().getBoolean("number.7") == true) {
			//Sixth Item/Link
			meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.data.getConfig().getString("name.link6")));
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.translateAlternateColorCodes('&', this.data.getConfig().getString("description.link6")));
			meta.setLore(lore);
			item.setItemMeta(meta);
			inv.setItem(5, item);
		}
		
		if(this.data.getConfig().getBoolean("number.7") == true) {
			//Seventh Item/Link
			meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.data.getConfig().getString("name.link7")));
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.translateAlternateColorCodes('&', this.data.getConfig().getString("description.link7")));
			meta.setLore(lore);
			item.setItemMeta(meta);
			inv.setItem(6, item);
		}
	
	}
	
}
