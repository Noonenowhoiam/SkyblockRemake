package me.CarsCupcake.SkyblockRemake.Items.reforges.ReforgeStones;

import java.util.ArrayList;

import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import me.CarsCupcake.SkyblockRemake.Items.reforges.Reforge;
import me.CarsCupcake.SkyblockRemake.Items.reforges.ReforgeStatPackage;

public class Precise implements Reforge{

    @Override
    public ReforgeStatPackage CommonStats() {
        ReforgeStatPackage statPackage = new ReforgeStatPackage();
        statPackage.addStats(Stats.Strength, 3);
        statPackage.addStats(Stats.CritChance, 5);
        statPackage.addStats(Stats.CritDamage, 8);
        return statPackage;
    }

    @Override
    public ReforgeStatPackage UncommonStats() {
        ReforgeStatPackage statPackage = new ReforgeStatPackage();
        statPackage.addStats(Stats.Strength, 7);
        statPackage.addStats(Stats.CritChance, 10);
        statPackage.addStats(Stats.CritDamage, 9);
        return statPackage;
    }

    @Override
    public ReforgeStatPackage RareStats() {
        ReforgeStatPackage statPackage = new ReforgeStatPackage();
        statPackage.addStats(Stats.Strength, 12);
        statPackage.addStats(Stats.CritChance, 18);
        statPackage.addStats(Stats.CritDamage, 10);
        return statPackage;
    }

    @Override
    public ReforgeStatPackage EpicStats() {
        ReforgeStatPackage statPackage = new ReforgeStatPackage();
        statPackage.addStats(Stats.Strength, 18);
        statPackage.addStats(Stats.CritChance, 32);
        statPackage.addStats(Stats.CritDamage, 11);
        return statPackage;
    }

    @Override
    public ReforgeStatPackage LegendaryStats() {
        ReforgeStatPackage statPackage = new ReforgeStatPackage();
        statPackage.addStats(Stats.Strength, 25);
        statPackage.addStats(Stats.CritChance, 50);
        statPackage.addStats(Stats.CritDamage, 10);
        return statPackage;
    }

    @Override
    public ReforgeStatPackage MythicStats() {
        ReforgeStatPackage statPackage = new ReforgeStatPackage();
        statPackage.addStats(Stats.Strength, 34);
        statPackage.addStats(Stats.CritChance, 70);
        statPackage.addStats(Stats.CritDamage, 15);
        return statPackage;
    }

    @Override
    public ReforgeStatPackage DivineStats() {
        // TODO Auto-generated method stub
        return MythicStats();
    }

    @Override
    public ItemType[] Reforgable() {
        ItemType[] types = {ItemType.Bow};
        return types;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "Precise";
    }

    @Override
    public ArrayList<String> getLore() {
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§9Precise Bonus");
        lore.add("§7Deal §a+10% §7extra damage when");
        lore.add("§7arrows hit the head of a mob");
        return lore;
    }

}
