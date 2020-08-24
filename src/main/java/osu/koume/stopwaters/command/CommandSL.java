package osu.koume.stopwaters.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import osu.koume.stopwaters.Main;
import osu.koume.stopwaters.temp.MESSAGE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CommandSL implements CommandExecutor, TabCompleter {
	private String[] Commands = {"on", "off"};

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player){
			if(!sender.isOp()){
				sender.sendMessage(MESSAGE.NO_OP);
				return true;
			}
		}

		if(args.length == 0) {
			if (Main.sl) {
				Main.sl(false);
				Bukkit.broadcastMessage(MESSAGE.SL_OFF);
			} else {
				Main.sl(true);
				Bukkit.broadcastMessage(MESSAGE.SL_ON);
			}
		} else {
			if(args[0].equalsIgnoreCase("on")){
				Main.sl(true);
				Bukkit.broadcastMessage(MESSAGE.SL_ON);
			} else if(args[0].equalsIgnoreCase("off")){
				Main.sl(false);
				Bukkit.broadcastMessage(MESSAGE.SL_OFF);
			}
		}
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args){
		List<String> ret = new ArrayList<String>();
		if(args.length == 1){
			StringUtil.copyPartialMatches(args[0], Arrays.asList(Commands), ret);
		}

		Collections.sort(ret);
		return ret;
	}
}
