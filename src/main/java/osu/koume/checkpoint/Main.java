package osu.koume.checkpoint;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import osu.koume.checkpoint.temp.CustomConfig;

public class Main extends JavaPlugin implements Listener {
	static Main plugin;
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new EventManager(), this);
		plugin = this;
	}


	@Override
	public void onDisable() {
	}

	public static CustomConfig getPlayerConfig(Player p){
		return new CustomConfig(plugin, "User\\" + p.getUniqueId() + ".yml");
	}
}
