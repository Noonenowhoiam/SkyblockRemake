package me.CarsCupcake.SkyblockRemake.Skyblock;

import com.google.common.annotations.Beta;
import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.API.CalculatorException;
import me.CarsCupcake.SkyblockRemake.API.HealthChangeReason;
import me.CarsCupcake.SkyblockRemake.API.SkyblockDamageEvent;
import me.CarsCupcake.SkyblockRemake.Items.SpawnEggEntitys;
import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.SkyblockRemakeEvents;
import me.CarsCupcake.SkyblockRemake.Tools;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class Calculator {
    public boolean isCrit = false;
    public int cccalc = 0;
    public double damage = 0;
    private SkyblockDamageEvent.DamageType type;
    private LivingEntity e;
    private Projectile projectile;
    private SkyblockDamageEvent result;
    private boolean isMagic = false;
    private String abilityName;
    private double abilityScaling = 1;
    private boolean isFerocity = false;
    public Calculator(){}
    public Calculator(boolean setFerocity){
        isFerocity = setFerocity;
    }
    public Calculator(Projectile p){
        projectile = p;
    }
    public boolean isFerocity(){
        return isFerocity;
    }
    public void setFerocity(boolean b){
        isFerocity = b;
    }
    public  double playerToEntityDamage(LivingEntity e, SkyblockPlayer player){
        this.e = e;
        if(e.getScoreboardTags().contains("npc"))
            return 0d;
        type = SkyblockDamageEvent.DamageType.PlayerToEntity;
        double stre = Main.playerstrengthcalc(player);
        double cd = Main.playercdcalc(player);
        int weapondmg =(int) Main.weapondamage(player.getItemInHand());
        weapondmg *= player.getRawDamageMult();
        System.out.println(player.getAdititveMultiplier());

        double cc = Main.playercccalc(player);
         cccalc = (int )(Math.random() * 100 + 1);

        double preMultiplier = (float) SkyblockRemakeEvents.additiveMultiplier(player);
        preMultiplier += SkyblockPlayer.getSkyblockPlayer(player).getAdititveMultiplier();

        double damage;
            if(cccalc <= cc) {
                isCrit = true;
                damage = (5 + (float)weapondmg) * (1+((float)stre/100)) * (1+((float)cd/100)) * (1+(preMultiplier)) ;

            }else {
                damage = (5 + (float)weapondmg) * (1+((float)stre/100))* (1+(preMultiplier));
            }
            if(SkyblockEntity.livingEntity.containsKey(e)){
                SkyblockEntity entity = SkyblockEntity.livingEntity.get(e);
                if(entity instanceof Defensive ed){
                    double defense = ed.getDefense();
                    double ehp = entity.getMaxHealth()*(1+(defense/100));
                    double effectivedmg = entity.getMaxHealth()/ehp ;
                    damage*=effectivedmg;
                }
            }
        this.damage =  damage;
        return  damage;
    }



    //bundle has Damage - True Damage
    public void entityToPlayerDamage(SkyblockEntity entity, SkyblockPlayer player) {
        entityToPlayerDamage(entity, player, new Bundle<>(entity.getDamage(), entity.getTrueDamage()));
    }
    public void entityToPlayerDamage(SkyblockEntity entity, SkyblockPlayer player, Bundle<Integer, Integer> stats) {
        e = entity.getEntity();
        type = SkyblockDamageEvent.DamageType.EntityToPlayer;
       double damage = stats.getLast();

        float ehp =  (float)Main.playerhealthcalc(player)*(1+((float)Main.playerdefcalc(player)/100));
        float effectivedmg = (float)Main.playerhealthcalc(player)/ehp ;
        int totaldmg = (int) ((int) damage*effectivedmg);


            SkyblockEntity se = entity;
            int truedamage = stats.getLast();
            if(truedamage != 0) {
                float trueehp =  (float)Main.playerhealthcalc(player)*(1+((float)Main.playertruedefense(player)/100));
                float effectivetruedmg = (float)Main.playerhealthcalc(player)/trueehp;
                totaldmg += (int) ( truedamage*effectivetruedmg);

        }
           this.damage = totaldmg;
    }

    public void damagePlayer(SkyblockPlayer player){
        damagePlayer(player, EntityDamageEvent.DamageCause.CUSTOM);
    }
    public void damagePlayer(SkyblockPlayer player, EntityDamageEvent.DamageCause cause) {

        if(projectile == null)
            result = new SkyblockDamageEvent(player, e, this, type, cause);
        else
            result = new SkyblockDamageEvent(player, e, this, type, cause, projectile);
        Bukkit.getPluginManager().callEvent(result);

        if(result.isCancelled())
            return;
        if(Main.absorbtion.get(player) - damage  < 0) {
            float restdamage =   (float)damage - (float) Main.absorbtion.get(player);
            Main.absorbtion.replace(player, 0);
            player.setHealth( player.currhealth  - (int)restdamage, HealthChangeReason.Damage);
        }else {
            Main.absorbtion.replace(player, Main.absorbtion.get(player) - (int)damage);
        }





            Main.updatebar(player);
    }
    public void damageEntity(LivingEntity e, SkyblockPlayer player){
        damageEntity(e,player, EntityDamageEvent.DamageCause.CUSTOM);
    }
    public void damageEntity(LivingEntity e, SkyblockPlayer player, EntityDamageEvent.DamageCause cause) {
        if(Main.entitydead.containsKey(e))
            return;
        if(e.getScoreboardTags().contains("npc"))
            return;
        if(projectile == null)
            result = new SkyblockDamageEvent(player, e, this, type, cause);
        else
            result = new SkyblockDamageEvent(player, e, this, type, cause, projectile);
        Bukkit.getPluginManager().callEvent(result);

        if(result.isCancelled())
            return;



        if(e.getScoreboardTags().contains("invinc")) {
            return;
        }
        int newHealth;
        if(SkyblockEntity.livingEntity.containsKey(e)) {
            SkyblockEntity se = SkyblockEntity.livingEntity.get(e);
            se.damage((int)damage,SkyblockPlayer.getSkyblockPlayer(player));
            newHealth = se.getHealth();


        }else {
            int live = Main.currentityhealth.get(e) - (int) damage;
            Main.currentityhealth.replace(e, live);
            newHealth = Main.currentityhealth.get(e);
        }

        if(newHealth <= 0)
            e.addScoreboardTag("killer:" + player.getName());
        else
            Main.updateentitystats(e);
        if((SkyblockEntity.livingEntity.containsKey(e) && SkyblockEntity.livingEntity.get(e).getHealth() <= 0) || (!SkyblockEntity.livingEntity.containsKey(e)&&Main.currentityhealth.get(e) <= 0) ) {
            e.addScoreboardTag("killer:" + player.getName());
            Main.EntityDeath(e);
            e.damage(9999999,player);
            if(e instanceof EnderDragon)
                e.setHealth(0);

            SkyblockEntity.livingEntity.remove(e);

            if(e.getScoreboardTags() != null) {
                Set<String> scores = e.getScoreboardTags();
                ArrayList<Player> owners = new ArrayList<>();
                scores.forEach(tag ->{

                    if(tag.startsWith("combatxp:")) {



                        if(Main.SlayerCurrXp.containsKey(player) == true && Main.SlayerName.containsKey(player) == true && Main.SlayerName.get(player).equals("Revenant Horror") && e.getType() == EntityType.ZOMBIE) {
                            Main.SlayerCurrXp.replace(player, Main.SlayerCurrXp.get(player) + Integer.parseInt(tag.split(":")[1]));
                            SkyblockScoreboard.updateScoreboard(player);
                            Random r = new Random();
                            int low = 0;//includes 1
                            int high = 100;// includes 100
                            int result = r.nextInt(high-low) + low;
                            if(result <= 15) {
                                if(Main.SlayerLevel.get(player) == 4) {
                                    low = 1;
                                    high = 5;
                                    result = r.nextInt(high-low) + low;
                                    if(result == 5) {
                                        SpawnEggEntitys.SummonRevT4MiniBoss2(e.getLocation());
                                    }else {
                                        SpawnEggEntitys.SummonRevT4MiniBoss1(e.getLocation());
                                    }
                                }else {
                                    SpawnEggEntitys.SummonRevT3MiniBoss1(e.getLocation());
                                }
                            }

                            if(Main.SlayerCurrXp.get(player) >= Main.SlayerRequireXp.get(player)) {
                                Main.SlayerCurrXp.remove(player);
                                BukkitRunnable runnable =new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        System.out.println("runnn");
                                        if(Main.SlayerLevel.get(player) == 1)
                                            SpawnEggEntitys.SummonT1Rev(e.getLocation(), player.getName());
                                        if(Main.SlayerLevel.get(player) == 2)
                                            SpawnEggEntitys.SummonT2Rev(e.getLocation(), player.getName());
                                        if(Main.SlayerLevel.get(player) == 3)
                                            SpawnEggEntitys.SummonT3Rev(e.getLocation(), player.getName());
                                        if(Main.SlayerLevel.get(player) == 4)
                                            SpawnEggEntitys.SummonT4Rev(e.getLocation(), player.getName());

                                    }
                                };runnable.runTaskLater(Main.getMain(), 2*20);


                            }

                        }
                    }
                    if(tag.startsWith("revslayer")) {

                        scores.forEach(tags ->{
                            if(tags.startsWith("owner")) {
                                Player owner = Bukkit.getServer().getPlayer(tags.split(":")[1]);
                                owner.sendMessage("Your Rev slayer has ben killed");
                                owners.add(owner);
                                if(Main.SlayerName.containsKey(owner)) {
                                    Main.SlayerName.remove(owner);
                                    Main.SlayerLevel.remove(owner);
                                    Main.SlayerRequireXp.remove(owner);}
                                SkyblockScoreboard.updateScoreboard(player);
                            }
                        });
                    }
                    if(tag.startsWith("voidgloomt2")) {
                        if( Main.beaconPicketUp.containsKey(e) && Main.beaconPicketUp.get(e) == false) {
                            if(Main.beaconBeforeBlock.get(Main.beaconLocation.get(e)) != null)
                                Main.beaconLocation.get(e).getBlock().setType(Main.beaconBeforeBlock.get(Main.beaconLocation.get(e)).getType());
                            else
                                Main.beaconLocation.get(e).getBlock().setType(Material.AIR);
                        }
                        if(Main.beaconThrown.containsKey(e) && Main.beaconThrown.get(e) == true)
                            SkyblockRemakeEvents.kill_voidgloom_beacon(e);
                        Main.beaconBeforeBlock.remove(Main.beaconLocation.get(e));
                        Main.beaconLocation.remove(e);
                        Main.beaconOnGround.remove(e);
                        Main.beaconOwner.remove(player);
                        Main.beaconPicketUp.remove(e);
                        Main.beaconThrown.remove(e);


                    }
                });

                if(owners != null) {
                    owners.forEach(owner-> e.addScoreboardTag("killer:" + owner.getName()));
                }
            }

            if(!e.getScoreboardTags().contains("killer")) {
                e.addScoreboardTag("killer:" + player.getName());
            }
            if(projectile != null){
                    e.addScoreboardTag("arrowkill:" + player.getName());





            }
            if(isMagic)
                e.addScoreboardTag("abilitykill");
            Main.updateentitystats(e);
        }


    }
    public void showDamageTag(Entity e){
        Location loc = new Location(e.getWorld(), e.getLocation().getX() ,e.getLocation().getY() + 0.5 , e.getLocation().getZ());
        showDamageTag(loc);

    }
    public void showDamageTag(Location loc){
        if(result != null && result.isCancelled())
            return;

        final String str = String.format("%.0f", (Tools.round(damage, 0)));



        ArmorStand stand = loc.getWorld().spawn(loc, ArmorStand.class, armorstand ->{
            armorstand.setVisible(false);

            armorstand.setGravity(false);
            armorstand.setMarker(true);


            armorstand.setCustomNameVisible(true);

            armorstand.setInvulnerable(true);
            if(isCrit) {
                String name = "§f✧";
                String num = "" + str;
                int col =1;
                int coltype = 1;
                String colstr = "§f";

                for (char x : num.toCharArray()) {
                    name = name + colstr + x;
                    ++col;
                    if(col ==2) {
                        col = 0;
                        ++coltype;
                        switch(coltype) {
                            case 1:
                                colstr = "§f";
                                break;
                            case 2:
                                colstr = "§e";
                                break;
                            case 3:
                                colstr = "§6";
                                coltype = 0;
                                break;

                        }

                    }
                }
                String x = "✧";
                name = name + colstr + x;
                armorstand.setCustomName(name);
            }else
                armorstand.setCustomName("§7" + str);

            armorstand.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 999999));
            armorstand.addScoreboardTag("damage_tag");
            armorstand.setArms(false);

            armorstand.setBasePlate(false);});

        Main.getMain().killarmorstand(stand);

    }

    public void setProjectile(Projectile p){
        projectile = p;
    }
    public void playerToEntityMagicDamage(SkyblockPlayer player, @Nullable LivingEntity e, double magicDamage){
        this.e = e;
        if(e != null &&e.getScoreboardTags().contains("npc"))
            return;

        if(e != null && e.getScoreboardTags().contains("abilityimun"))
            return;

        double abilityDamage = Main.playerabilitydamagecalc(player);
        double inteligens = Main.playermanacalc(player);

        double baseMult = 0;
        double postMult = abilityDamage;
        damage = magicDamage * (1 + (inteligens/100)* abilityScaling) * (1+(baseMult/100)) * (1+(postMult/100));

    }
    public void setMagic(String str){
        isMagic = true;
        abilityName = str;
    }
    public void setMagic(String str, double abilityScaling){
        isMagic = true;
        abilityName = str;
        this.abilityScaling = abilityScaling;
    }
    public void sendMagicMessage(int entityAmount, SkyblockPlayer player){
        if (!isMagic)
            throw new CalculatorException("There is no magic");
        player.sendMessage("§7Your "+ abilityName + " hit §c"+ entityAmount +"§7 enemy"+ ((entityAmount > 1) ? "s" : "" ) + " for §c" + String.format("%.1f", damage) + " §7damage.");
    }

    public boolean isMagic(){
        return isMagic;
    }
    private int getSkyblockEntityHealth(LivingEntity e){
        return SkyblockEntity.livingEntity.get(e).getHealth();
    }
    private int getBaseEntity(LivingEntity e){
        return Main.currentityhealth.get(e);
    }
    public SkyblockDamageEvent getResult() {
        return result;
    }
    public Projectile getProjectile(){
        return projectile;
    }

}
