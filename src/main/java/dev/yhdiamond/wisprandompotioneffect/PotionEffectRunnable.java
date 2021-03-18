package dev.yhdiamond.wisprandompotioneffect;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PotionEffectRunnable extends BukkitRunnable {
    public static List<PotionEffectType> potioneffects = Arrays.asList(PotionEffectType.values());

    @Override
    public void run() {
        if (WispRandomPotionEffect.gamestarted) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                Random random = new Random();
                PotionEffectType type = potioneffects.get(random.nextInt(potioneffects.size()));
                PotionEffect randomeffect = new PotionEffect(type, 200, 1);
                player.addPotionEffect(randomeffect);
                WispRandomPotionEffect.uuidPotionEffectTypeMap.put(player.getUniqueId(), type);
            }
        }

    }
}
