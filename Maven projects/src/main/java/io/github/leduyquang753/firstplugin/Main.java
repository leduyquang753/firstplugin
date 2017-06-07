package io.github.leduyquang753.firstplugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
	private Plugin plugin;
	@Override
	public void onEnable() {
		getLogger().info("quang_play's test plugin has been enabled!!! :)");		
	}
	@Override
	public void onDisable() {
		getLogger().info("quang_play's test plugin has been disabled!!! :)");
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player sder = (Player)sender;
		if (cmd.getName().equalsIgnoreCase("play")) {
			boolean typed = false;
			String conf = "EMPTY";
			String str = "FALSE";
			try {
				if (!getDataFolder().exists()) {
					getDataFolder().mkdirs();
				}
				File config = new File(getDataFolder(),"config.yml");
				if (!config.exists()) {
					getLogger().info("Didn't see the config.yml. Creating it.");
					saveDefaultConfig();
				} else {
					getLogger().info("See the config.yml. Using it.");
					conf = plugin.getConfig().getString("Command-typed");
					if (conf=="FALSE") typed=false;
					if (conf=="TRUE") typed=true;
					if (!(conf=="TRUE" || conf=="FALSE")) plugin.getConfig().set("Command-typed", str);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (!typed) {
				sender.sendMessage(ChatColor.YELLOW + "LOL You have typed the command that no one seen!!!");
				String theReward = "The Ultimate Sworrrrrrrrd!";
				PlayerInventory inven = sder.getInventory();
				List<String> lores = new ArrayList<String>();
				lores.add("This is your ULTIMAAAAAATE sword!");
				lores.add("Just keep it! LOL");
				ItemStack itemReward = new ItemStack(Material.DIAMOND_SWORD);
				itemReward.addEnchantment(Enchantment.DAMAGE_ALL, 3);
				itemReward.addUnsafeEnchantment(Enchantment.KNOCKBACK, 5);
				itemReward.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
				itemReward.addEnchantment(Enchantment.LOOT_BONUS_MOBS, 3);
				ItemMeta im = itemReward.getItemMeta();
				im.setDisplayName(theReward);
				im.setLore(lores);
				itemReward.setItemMeta(im);
				inven.addItem(itemReward);
				typed = true;
				return true;
			} else sender.sendMessage(ChatColor.YELLOW + "Someone has known this command. LOL");
		}
		return false;
	}
}
