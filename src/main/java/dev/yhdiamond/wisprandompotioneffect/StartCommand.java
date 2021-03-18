package dev.yhdiamond.wisprandompotioneffect;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class StartCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;
            if (sender.hasPermission("wisprandompotioneffects.toggle")){
                if (args.length == 1){
                    if (args[0].equals("start")){
                        if (!WispRandomPotionEffect.gamestarted) {
                            WispRandomPotionEffect.gamestarted = true;
                            for (Player player : Bukkit.getOnlinePlayers()) {
                                Random random = new Random();
                                PotionEffectType type = PotionEffectRunnable.potioneffects.get(random.nextInt(PotionEffectRunnable.potioneffects.size()));
                                PotionEffect randomeffect = new PotionEffect(type, 200, 1);
                                player.addPotionEffect(randomeffect);
                                WispRandomPotionEffect.uuidPotionEffectTypeMap.put(player.getUniqueId(), type);
                            }
                            WispRandomPotionEffect.br = new PotionEffectRunnable().runTaskTimer(WispRandomPotionEffect.plugin, 200, 200);
                            Bukkit.broadcastMessage(ChatColor.GREEN + "Wisp Random Potion Effects challenge has started!");
                        } else {
                            Bukkit.broadcastMessage(ChatColor.RED + "Wisp Random Potion Effects challenge is already started!");
                        }
                    }
                    else if (args[0].equals("stop")){
                        if (WispRandomPotionEffect.gamestarted) {
                            WispRandomPotionEffect.gamestarted = false;
                            Bukkit.broadcastMessage(ChatColor.GREEN + "Wisp Random Potion Effects challenge has ended!");
                            for (Player player : Bukkit.getOnlinePlayers()) {
                                PotionEffectType givenEffect = WispRandomPotionEffect.uuidPotionEffectTypeMap.get(p.getUniqueId());
                                if (givenEffect != null) {
                                    player.removePotionEffect(givenEffect);
                                }
                            }
                            WispRandomPotionEffect.br.cancel();
                        } else {
                            Bukkit.broadcastMessage(ChatColor.RED + "Wisp Random Potion Effects challenge isn't started!");
                        }
                    }
                    else {
                        p.sendMessage(ChatColor.RED+"/wisprandompotioneffects <start/stop>");
                    }
                }
                else {
                    p.sendMessage(ChatColor.RED+"/wisprandompotioneffects <start/stop>");
                }
            }
            else {
                p.sendMessage(ChatColor.RED+"You do not have the required permission to run this command!");
            }
        }
        else{
            sender.sendMessage(ChatColor.RED+"This command is for players only!");
        }

        return true;
    }
}