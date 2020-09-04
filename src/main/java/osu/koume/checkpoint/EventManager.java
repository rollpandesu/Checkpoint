package osu.koume.checkpoint;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import osu.koume.checkpoint.temp.CustomConfig;
import osu.koume.checkpoint.temp.MESSAGE;

public class EventManager implements Listener {

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			Block b = e.getClickedBlock();
			if (b.getType().equals(Material.WALL_SIGN) || b.getType().equals(Material.SIGN_POST)) {
				Sign s = (Sign) b.getState();
				if (s.getLine(0).equalsIgnoreCase("cp")) {
					setCheckpoint(e.getPlayer());
					e.setCancelled(true);
					return;
				}
			}

			e.setCancelled(checkItem(e.getPlayer()));

		} else if (e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
			e.setCancelled(checkItem(e.getPlayer()));
		}
	}

	private boolean checkItem(Player p) {
		ItemStack i = p.getInventory().getItemInMainHand();
		if (i == null) {
			return false;
		}

		if (i.getType().equals(Material.FEATHER)) {
			Location loc = getCheckpoint(p);
			if (loc != null) {
				p.teleport(loc);
			} else {
				p.sendMessage(MESSAGE.NO_CP);
			}
			return true;
		} else if (i.getType().equals(Material.SULPHUR)) {
			setCheckpoint(p);
			return true;
		}
		return false;
	}

	private Location getCheckpoint(Player p) {
		CustomConfig c = Main.getPlayerConfig(p);
		return (Location) c.getConfig().get("cp");
	}

	private void setCheckpoint(Player p) {
		CustomConfig c = Main.getPlayerConfig(p);
		c.getConfig().set("cp", p.getLocation());
		c.saveConfig();
		p.sendMessage(MESSAGE.SET_CP);
		p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
	}

	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			e.setCancelled(e.getCause().equals(EntityDamageEvent.DamageCause.FALL));
		}

	}

}
