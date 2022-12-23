package me.CarsCupcake.SkyblockRemake.Enchantments.NormalEnchants;

import me.CarsCupcake.SkyblockRemake.API.ItemEvents.GetStatFromItemEvent;
import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.DamagePrepairEvent;
import me.CarsCupcake.SkyblockRemake.API.SkyblockDamageEvent;
import me.CarsCupcake.SkyblockRemake.Enchantments.SkyblockEnchants;
import me.CarsCupcake.SkyblockRemake.Items.ItemHandler;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Stats;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class Overload extends Enchantment implements Listener {
    public Overload() {
        super(new NamespacedKey(Main.getMain(), "Overload"));
    }

    @NotNull
    @Override
    public String getName() {
        return "Overload";
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @NotNull
    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.BOW;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(@NotNull Enchantment enchantment) {
        return false;
    }

    @Override
    public boolean canEnchantItem(@NotNull ItemStack itemStack) {
        return true;
    }

    @EventHandler
    public void onHit(DamagePrepairEvent event) {
        if(event.getCalculator().getType() == SkyblockDamageEvent.DamageType.PlayerToEntity && event.getCalculator().getProjectile() != null) {
            if (ItemHandler.hasEnchantment(SkyblockEnchants.OVERLOAD, event.getCalculator().getProjectile())){
                if(Main.getplayerStat(event.getPlayer(), Stats.CritChance) > 100){
                    Random r = new Random();
                    if(r.nextBoolean()){
                        event.getCalculator().setOverload(true);
                        event.addPreMultiplier(ItemHandler.getEnchantmentLevel(SkyblockEnchants.OVERLOAD, event.getCalculator().getProjectile()) * 10);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onStatsGain(GetStatFromItemEvent event){
        if(event.getStat() == Stats.CritDamage || event.getStat() == Stats.CritChance){
            if(ItemHandler.hasEnchantment(SkyblockEnchants.OVERLOAD, event.getItem()))
                event.setValue(event.getValue() + event.getItem().getItemMeta().getEnchants().get(SkyblockEnchants.OVERLOAD));
        }
    }

}
