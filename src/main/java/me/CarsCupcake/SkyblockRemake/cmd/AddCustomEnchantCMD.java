package me.CarsCupcake.SkyblockRemake.cmd;


import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

import me.CarsCupcake.SkyblockRemake.Items.Enchantments.SkyblockEnchants;
import org.jetbrains.annotations.NotNull;


public class AddCustomEnchantCMD implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender arg0, @NotNull Command arg1, String arg2, String[] arg3) {


        if (!arg2.equalsIgnoreCase("ench"))
            return false;
        SkyblockPlayer player = SkyblockPlayer.getSkyblockPlayer((Player) arg0);

        if (arg3.length != 2) {
            return false;
        }
        if (player.getItemInHand() == null)
            return false;

        if (SkyblockEnchants.skyblockEnchantIds.contains(arg3[0])) {
            int level = Integer.parseInt(arg3[1]);
            ItemMeta meta = player.getItemInHand().getItemMeta();
            if (level > 0)
                meta.addEnchant(SkyblockEnchants.enchantments.get(SkyblockEnchants.skyblockEnchantIds.indexOf(arg3[0])), Integer.parseInt(arg3[1]), true);
            else
                meta.removeEnchant(SkyblockEnchants.enchantments.get(SkyblockEnchants.skyblockEnchantIds.indexOf(arg3[0])));
            player.getItemInHand().setItemMeta(meta);
            Main.itemUpdater(player.getItemInHand(), player);

        } else
            player.sendMessage(arg3[0] + " dosent exist");

        return false;
    }

}
