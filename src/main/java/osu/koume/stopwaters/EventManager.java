package osu.koume.stopwaters;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPhysicsEvent;

public class EventManager implements Listener {

	@EventHandler
	public void onBlockFromTo(BlockFromToEvent e) {
		e.setCancelled(Main.sw);
	}


	@EventHandler
	public void onBlockPhysics(BlockPhysicsEvent e) {
		if(e.getBlock().getType().equals(Material.LADDER)){
			e.setCancelled(Main.sl);
		}
	}
}
