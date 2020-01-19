package pl.KarwszPL.Avatarserv.ElementalDuels.Listeners;

import com.projectkorra.projectkorra.BendingPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
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
