package osu.koume.stopwaters;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.plugin.java.JavaPlugin;
import osu.koume.stopwaters.command.CommandSL;
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

	public static void sl(boolean b) {
		sl = b;
		config.getConfig().set("sl", b);
		config.saveConfig();
	}
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new EventManager(), this);
		getCommand("sw").setExecutor(new CommandSW());
		getCommand("sw").setTabCompleter(new CommandSW());
		getCommand("sl").setExecutor(new CommandSL());
		getCommand("sl").setTabCompleter(new CommandSL());
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


}
