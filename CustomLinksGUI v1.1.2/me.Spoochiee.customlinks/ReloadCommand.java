package me.Spoochiee.customlinks;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Spoochiee.customlinks.Files.DataManager;

public class ReloadCommand implements CommandExecutor {
	
	private Main plugin;
	private DataManager data;
	
	public ReloadCommand(Main plugin, DataManager data) {
		this.plugin = plugin;
		this.data = data;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		if(label.equalsIgnoreCase("linksgui")) {
			if(!(sender instanceof Player)) {
				if(args.length == 0) {
					// Console has issued /customlinksGUI
					sender.sendMessage("Correct Usage: linksgui reload");
					return true;
				}
				if(args.length > 0 ) {
					if(args[0].equalsIgnoreCase("reload")) {
							// Console has issued /linksgui reload
							sender.sendMessage(this.data.getConfig().getString("reload.message"));
							this.data.reloadConfig();
							this.plugin.createInv();
							return true;
					}
					else {
						// Console issued linksgui random
						sender.sendMessage("Correct Usage: linksgui reload");
						return true;
					}
				}
			}
			Player player = (Player) sender;
			
			if(player.hasPermission("customlinksgui.reload")) {
				if(args.length == 0) {
					// Player has issued /customlinksGUI
					player.sendMessage("Correct Usage: /linksgui reload");
					return true;
				}
				if(args.length > 0 ) {
					if(args[0].equalsIgnoreCase("reload")) {
						// Player has issued /linkgui reload
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.data.getConfig().getString("reload.message")));
						this.data.reloadConfig();
						this.plugin.createInv();
						return true;
					}
					else {
						// Player issued command /linksgui random
						player.sendMessage("Correct Usage: /linksgui reload");
						return true;
						}
					}
				}
			else {
				player.sendMessage("You do not have permission to use this!");
				return false;
				}
			}
		return false;
	}
}
