package pl.KarwszPL.Avatarserv.ElementalDuels.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import pl.KarwszPL.Avatarserv.ElementalDuels.Commands.DuelQueue;
import pl.KarwszPL.Avatarserv.ElementalDuels.ElementalDuels;
import pl.KarwszPL.Avatarserv.ElementalDuels.Objects.Arena;
import pl.KarwszPL.Avatarserv.ElementalDuels.Objects.PlayerStatistics;

public class PlayerDeathQuitListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (Arena.getPlayerArena(player) != null) {
            if (Arena.getPlayerArenaInUse(player)) {
                player.setHealth(0);
            }
        }
        DuelQueue.queue.remove(player);
        Arena.removePlayerFromArenas(player);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (Arena.getPlayerArena(player) != null) {
            if (Arena.getPlayerArenaInUse(player)) {
                for (Player p : player.getWorld().getPlayers()) {
                    p.sendMessage(ChatColor.AQUA + "Gracz " + Arena.getPlayerEnemy(player).getName() + " wygral pojedynek na arenie " + Arena.getPlayerArena(player) + " przeciwko graczowi " + player.getName() + "!");
                }

                Player enemy = Arena.getPlayerEnemy(player);
                if (Arena.getArenaExist("Spawn")) {
                    new BukkitRunnable() {
                        public void run() {
                            enemy.teleport(Arena.getArenaPosition("Spawn", 1));
                        }
                    }.runTaskLater(Bukkit.getPluginManager().getPlugin("ElementalDuels"), 80);
                }

                switch (Arena.getPlayerArenaElement(player)) {
                    case "Fire":
                        PlayerStatistics.addFireWins(enemy, 1);
                        break;
                    case "Air":
                        PlayerStatistics.addAirWins(enemy, 1);
                        break;
                    case "Water":
                        PlayerStatistics.addWaterWins(enemy, 1);
                        break;
                    case "Earth":
                        PlayerStatistics.addEarthWins(enemy, 1);
                        break;
                    case "Avatar":
                        PlayerStatistics.addAvatarWins(enemy, 1);
                        break;
                    case "Chi":
                        PlayerStatistics.addChiWins(enemy, 1);
                        break;
                    case "Spirit":
                        PlayerStatistics.addSpiritWins(enemy, 1);
                        break;
                }

                DuelQueue.queue.remove(player);
                DuelQueue.queue.remove(Arena.getPlayerEnemy(player));
                Arena.restartArena(Arena.getPlayerArena(player));


            }
        }
    }


}
