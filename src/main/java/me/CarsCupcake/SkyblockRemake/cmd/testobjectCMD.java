package me.CarsCupcake.SkyblockRemake.cmd;


import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import me.CarsCupcake.SkyblockRemake.Dungeon.Boss.F7.F7Phase3;
import me.CarsCupcake.SkyblockRemake.Dungeon.MiniBoss.ShadowAssasin;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Tools;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;


public class testobjectCMD implements CommandExecutor{
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("testobject")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("Du kannst das net");
				return true;
			}
			F7Phase3.arrowPoint.getPattern().paint();


			return true;
			
		}
		
		return false;
	}

}
