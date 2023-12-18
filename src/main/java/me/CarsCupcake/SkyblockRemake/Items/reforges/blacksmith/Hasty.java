package me.CarsCupcake.SkyblockRemake.Items.reforges.blacksmith;

import java.util.ArrayList;

import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import me.CarsCupcake.SkyblockRemake.Items.reforges.Reforge;
import me.CarsCupcake.SkyblockRemake.Items.reforges.ReforgeStatPackage;

public class Hasty implements Reforge{

    @Override
    public ReforgeStatPackage CommonStats() {
        ReforgeStatPackage statPackage = new ReforgeStatPackage();
        statPackage.addStats(Stats.Strength, 3);
        statPackage.addStats(Stats.CritChance, 20);
        return statPackage;
    }

    @Override
    public ReforgeStatPackage UncommonStats() {
        ReforgeStatPackage statPackage = new ReforgeStatPackage();
        statPackage.addStats(Stats.Strength, 5);
        statPackage.addStats(Stats.CritChance, 25);
        return statPackage;
    }

    @Override
    public ReforgeStatPackage RareStats() {
        ReforgeStatPackage statPackage = new ReforgeStatPackage();
        statPackage.addStats(Stats.Strength, 7);
        statPackage.addStats(Stats.CritChance, 30);
        return statPackage;
    }

    @Override
    public ReforgeStatPackage EpicStats() {
        ReforgeStatPackage statPackage = new ReforgeStatPackage();
        statPackage.addStats(Stats.Strength, 10);
        statPackage.addStats(Stats.CritChance, 40);
        return statPackage;
    }

    @Override
    public ReforgeStatPackage LegendaryStats() {
        ReforgeStatPackage statPackage = new ReforgeStatPackage();
        statPackage.addStats(Stats.Strength, 15);
        statPackage.addStats(Stats.CritChance, 50);
        return statPackage;
    }

    @Override
    public ReforgeStatPackage MythicStats() {
        ReforgeStatPackage statPackage = new ReforgeStatPackage();
        statPackage.addStats(Stats.Strength, 20);
        statPackage.addStats(Stats.CritChance, 60);
        return statPackage;
    }

    @Override
    public ReforgeStatPackage DivineStats() {
        // TODO Auto-generated method stub
        return MythicStats();
    }

    @Override
    public ItemType[] Reforgable() {
        return new ItemType[]{ItemType.Sword, ItemType.Wand, ItemType.Bow};
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "Hasty";
    }

    @Override
    public ArrayList<String> getLore() {
        // TODO Auto-generated method stub
        return null;
    }

}
