package pl.KarwszPL.Avatarserv.ElementalDuels.Listeners;

import com.projectkorra.projectkorra.BendingPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import pl.KarwszPL.Avatarserv.ElementalDuels.Commands.DuelQueue;
import pl.KarwszPL.Avatarserv.ElementalDuels.Objects.Arena;

import java.util.ArrayList;

public class IllegalSpellListener implements Listener {

    public static ArrayList<Player> players = new ArrayList<>();

    @EventHandler
    public void onMove(PlayerMoveEvent event) {

        if (players.contains(event.getPlayer())) event.setCancelled(true);

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (Arena.getPlayerArena(player) == null) return;
        if (DuelQueue.queue.containsKey(player) || Arena.getPlayerArena(player) != null) {
            if (event.getClickedBlock() != null) {
                if (event.getClickedBlock().getType().equals(Material.SIGN_POST) || event.getClickedBlock().getType().equals(Material.WALL_SIGN)) {
                    player.sendMessage(ChatColor.RED + "Musisz wyjsc z kolejki pojedynkow aby korzystac z tabliczek!");
                    event.setCancelled(true);
                }
            }
        }


        BendingPlayer bendingPlayer = BendingPlayer.getBendingPlayer(player);
        if (bendingPlayer.getBoundAbility() == null) return;
        if (Arena.getPlayerArenaInUse(player) && !Arena.getPlayerArenaElements(player).contains(bendingPlayer.getBoundAbility().getElement().getName())) {
            player.setHealth(0);
        }

    }

    @EventHandler
    public void onInteract(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        BendingPlayer bendingPlayer = BendingPlayer.getBendingPlayer(player);
        if (bendingPlayer.getBoundAbility() == null) return;
        if (Arena.getPlayerArena(player) == null) return;
        if (Arena.getPlayerArenaInUse(player) && !Arena.getPlayerArenaElements(player).contains(bendingPlayer.getBoundAbility().getElement().getName())) {
            player.setHealth(0);
        }

    }

}
