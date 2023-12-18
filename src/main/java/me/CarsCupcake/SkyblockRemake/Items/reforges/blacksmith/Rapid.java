package me.CarsCupcake.SkyblockRemake.Items.reforges.blacksmith;

import java.util.ArrayList;

import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import me.CarsCupcake.SkyblockRemake.Items.reforges.Reforge;
import me.CarsCupcake.SkyblockRemake.Items.reforges.ReforgeStatPackage;

public class Rapid implements Reforge{

    @Override
    public ReforgeStatPackage CommonStats() {
        ReforgeStatPackage statPackage = new ReforgeStatPackage();
        statPackage.addStats(Stats.Strength, 2);
        statPackage.addStats(Stats.CritDamage, 35);
        return statPackage;
    }

    @Override
    public ReforgeStatPackage UncommonStats() {
        ReforgeStatPackage statPackage = new ReforgeStatPackage();
        statPackage.addStats(Stats.Strength, 3);
        statPackage.addStats(Stats.CritDamage, 45);
        return statPackage;
    }

    @Override
    public ReforgeStatPackage RareStats() {
        ReforgeStatPackage statPackage = new ReforgeStatPackage();
        statPackage.addStats(Stats.Strength, 4);
        statPackage.addStats(Stats.CritDamage, 55);
        return statPackage;
    }

    @Override
    public ReforgeStatPackage EpicStats() {
        ReforgeStatPackage statPackage = new ReforgeStatPackage();
        statPackage.addStats(Stats.Strength, 7);
        statPackage.addStats(Stats.CritDamage, 65);
        return statPackage;
    }

    @Override
    public ReforgeStatPackage LegendaryStats() {
        ReforgeStatPackage statPackage = new ReforgeStatPackage();
        statPackage.addStats(Stats.Strength, 10);
        statPackage.addStats(Stats.CritDamage, 75);
        return statPackage;
    }

    @Override
    public ReforgeStatPackage MythicStats() {
        ReforgeStatPackage statPackage = new ReforgeStatPackage();
        statPackage.addStats(Stats.Strength, 15);
        statPackage.addStats(Stats.CritDamage, 90);
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
        return "Rapid";
    }

    @Override
    public ArrayList<String> getLore() {
        // TODO Auto-generated method stub
        return null;
    }

}
