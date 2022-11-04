package me.CarsCupcake.SkyblockRemake.Enchantments.UltEnchants;

import me.CarsCupcake.SkyblockRemake.Enchantments.UltimateEnchant;
import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class FatalTempo extends UltimateEnchant {
    public FatalTempo() {
        super(new NamespacedKey(Main.getMain(), "FATAL_TEMPO"));
    }

    @NotNull
    @Override
    public String getName() {
        return "Fatal Tempo";
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
        return false;
    }
}
