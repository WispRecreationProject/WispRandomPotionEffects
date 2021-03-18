package dev.yhdiamond.wisprandompotioneffect;

import dev.yhdiamond.wisprandompotioneffect.bstats.Metrics;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class WispRandomPotionEffect extends JavaPlugin {
    public static boolean gamestarted = false;
    public static WispRandomPotionEffect plugin;
    public static BukkitTask br;
    public static Map<UUID, PotionEffectType> uuidPotionEffectTypeMap = new HashMap<>();
    @Override
    public void onEnable() {
        getCommand("wisprandompotioneffects").setExecutor(new StartCommand());
        getCommand("wisprandompotioneffects").setTabCompleter(new CommandComplete());
        new Metrics(this, 10711);
        plugin = this;
    }
}
