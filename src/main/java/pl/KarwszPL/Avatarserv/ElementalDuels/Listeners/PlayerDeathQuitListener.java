package pl.KarwszPL.Avatarserv.ElementalDuels.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.KarwszPL.Avatarserv.ElementalDuels.Commands.DuelQueue;
import pl.KarwszPL.Avatarserv.ElementalDuels.Objects.Arena;

public class PlayerDeathQuitListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (Arena.getPlayerArena(player) != null) {
            if (Arena.getPlayerArenaInUse(player)) {
                player.setHealth(0);
            }
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (Arena.getPlayerArena(player) != null) {
            if (Arena.getPlayerArenaInUse(player)) {
                for (Player p : player.getWorld().getPlayers()) {
                    p.sendMessage(ChatColor.AQUA + "Gracz " + Arena.getPlayerEnemy(player).getName() + " wygral pojedynek na arenie " + Arena.getPlayerArena(player) + " przeciwko graczowi " + player.getName() + "!");
                }

                Arena.setInUse(Arena.getPlayerArena(player), false);
                Arena.removePlayerFromArenas(Arena.getPlayerEnemy(player));
                Arena.removePlayerFromArenas(player);
                DuelQueue.queue.remove(player);
                DuelQueue.queue.remove(Arena.getPlayerEnemy(player));

            }
        }
    }


}
