package osu.koume.stopwaters;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.plugin.java.JavaPlugin;
import osu.koume.stopwaters.command.CommandSW;
import osu.koume.stopwaters.temp.CustomConfig;

public class Main extends JavaPlugin implements Listener {
	public static CustomConfig config;
	public static boolean sw;
	public static boolean sl;
	public static boolean sv;

	public static void sw(boolean b) {
		sw = b;
		config.getConfig().set("sw", b);
		config.saveConfig();
	}

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		getCommand("sw").setExecutor(new CommandSW());
		getCommand("sw").setTabCompleter(new CommandSW());
		getConfigs();
	}

	private void getConfigs() {
		config = new CustomConfig(this);
		config.reloadConfig();

		sw = getConfig().getBoolean("sw");
		sl = getConfig().getBoolean("sl");
		sv = getConfig().getBoolean("sv");
	}

	@Override
	public void onDisable() {
	}

	@EventHandler
	public void onBlockFromTo(BlockFromToEvent e) {
		e.setCancelled(sw);
	}

}
