package pl.KarwszPL.Avatarserv.ElementalDuels.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import pl.KarwszPL.Avatarserv.ElementalDuels.Objects.PlayerStatistics;
import pl.KarwszPL.Avatarserv.ElementalDuels.StatisticsStorage.StatisticsStorage;

public class JoinQuitStatistics implements Listener {


    public static String playerStatisticsToString(PlayerStatistics playerStatistics) {
        return playerStatistics.player.getName() + "," + playerStatistics.f + "," + playerStatistics.a + "," + playerStatistics.w + "," + playerStatistics.e + "," + playerStatistics.av + "," + playerStatistics.c + "," + playerStatistics.s;
    }
    
    public static PlayerStatistics stringToPlayerStatistics(String string) {
     String[] split = string.split(",");
     return new PlayerStatistics(Bukkit.getPlayer(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3]), Integer.parseInt(split[4]), Integer.parseInt(split[5]), Integer.parseInt(split[6]), Integer.parseInt(split[7]));
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (StatisticsStorage.getStatisticsStorage().get(player.getName()) == null) {
            PlayerStatistics.addStatistics(new PlayerStatistics(player, 0 ,0 ,0 ,0 ,0 ,0,0));
        }
        else PlayerStatistics.addStatistics(stringToPlayerStatistics(StatisticsStorage.getStatisticsStorage().getString(player.getName())));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (PlayerStatistics.getStatistics(player) == null) {
            StatisticsStorage.getStatisticsStorage().set(player.getName(), playerStatisticsToString(new PlayerStatistics(player, 0, 0, 0, 0, 0, 0, 0)));
            PlayerStatistics.removeStatistics(player);
        }
        else {
            StatisticsStorage.getStatisticsStorage().set(player.getName(), playerStatisticsToString(PlayerStatistics.getStatistics(player)));
            PlayerStatistics.removeStatistics(player);
        }
        StatisticsStorage.save();

    }


    @EventHandler
    public void onEnable(PluginEnableEvent event) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (StatisticsStorage.getStatisticsStorage().get(player.getName()) == null) {
                PlayerStatistics.addStatistics(new PlayerStatistics(player, 0, 0, 0, 0, 0, 0, 0));
            } else
                PlayerStatistics.addStatistics(stringToPlayerStatistics(StatisticsStorage.getStatisticsStorage().getString(player.getName())));
        }
        StatisticsStorage.save();
    }


    @EventHandler
    public void onDisable(PluginDisableEvent event) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (PlayerStatistics.getStatistics(player) == null) {
                StatisticsStorage.getStatisticsStorage().set(player.getName(), playerStatisticsToString(new PlayerStatistics(player, 0, 0, 0, 0, 0, 0, 0)));
                PlayerStatistics.removeStatistics(player);
            } else {
                StatisticsStorage.getStatisticsStorage().set(player.getName(), playerStatisticsToString(PlayerStatistics.getStatistics(player)));
                PlayerStatistics.removeStatistics(player);
            }
        }
        StatisticsStorage.save();
    }


}
