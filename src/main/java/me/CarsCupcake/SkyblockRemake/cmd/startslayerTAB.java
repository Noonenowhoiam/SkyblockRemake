package me.CarsCupcake.SkyblockRemake.cmd;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class startslayerTAB implements TabCompleter{
	List<String> arguments = new ArrayList<String>();
	List<String> arguments1 = new ArrayList<String>();
	List<String> arguments2 = new ArrayList<String>();
	List<String> slayer = new ArrayList<String>();
public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		
		List<String> result = new ArrayList<String>();
		if(slayer.isEmpty()) {
			slayer.add("zombie");
			slayer.add("blaze");
			slayer.add("enderman");
			
			}
	if(arguments.isEmpty()) {
		arguments.add("1");
		arguments.add("2");
		arguments.add("3");
		arguments.add("4");
	}
		if(arguments1.isEmpty()) {
			arguments1.add("1");
			arguments1.add("quazii");
			arguments1.add("typhoeus");


		}
	if(arguments2.isEmpty()) {
		arguments2.add("1");
	}
		if (args.length == 1) {
			for (String a : slayer) {
				if (a.toLowerCase().startsWith(args[0].toLowerCase()))
					result.add(a);
			}
			return result;
		}
		if (args.length == 2 ) {
			if(args[0].equals("zombie"))
				for (String a : arguments) {
					if (a.toLowerCase().startsWith(args[1].toLowerCase()))
						result.add(a);
				}else
			if(args[0].equals("blaze"))
				for (String a : arguments1) {
					if (a.toLowerCase().startsWith(args[1].toLowerCase()))
						result.add(a);
				}
			if(args[0].equals("enderman"))
				for (String a : arguments2) {
					if (a.toLowerCase().startsWith(args[1].toLowerCase()))
						result.add(a);
				}
			else result.clear();
			return result;
		}
		if (args.length > 2 ) {
			
			result.add("");
			return result;
		}
			
		
		
		return null;
	}
}
