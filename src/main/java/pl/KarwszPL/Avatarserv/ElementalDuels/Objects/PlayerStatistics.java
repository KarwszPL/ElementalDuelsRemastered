package pl.KarwszPL.Avatarserv.ElementalDuels.Objects;

import org.bukkit.entity.Player;
import pl.KarwszPL.Avatarserv.ElementalDuels.StatisticsStorage.StatisticsStorage;

import java.util.ArrayList;

public class PlayerStatistics {

    public Player player;
    public int f;
    public int a;
    public int w;
    public int e;
    public int av;
    public int c;
    public int s;
    private static ArrayList<PlayerStatistics> playerStatistics = new ArrayList<>();

    public PlayerStatistics(Player player, int f, int a, int w, int e, int av, int c, int s) {
        this.player = player;
        this.f = f;
        this.a = a;
        this.w = w;
        this.e = e;
        this.av = av;
        this.c = c;
        this.s = s;
    }

    public static void removeStatistics(Player player) {
        for (PlayerStatistics playerStatistics : playerStatistics) {
            if (playerStatistics.player == player) PlayerStatistics.playerStatistics.remove(playerStatistics);
            return;
        }
    }

    public static PlayerStatistics getStatistics(Player player) {
        for (PlayerStatistics playerStatistics : playerStatistics) {
            if (playerStatistics.player == player) {
                return  playerStatistics;
            }
        }
        return null;
    }

    public static void addFireWins(Player player, int i) {
        for (PlayerStatistics playerStatistics : playerStatistics) {
            if (playerStatistics.player == player) {
                PlayerStatistics.playerStatistics.remove(playerStatistics);
                playerStatistics.f = playerStatistics.f + i;
                PlayerStatistics.playerStatistics.add(playerStatistics);
                break;
            }
        }
    }

    public static void addAirWins(Player player, int i) {
        for (PlayerStatistics playerStatistics : playerStatistics) {
            if (playerStatistics.player == player) {
                PlayerStatistics.playerStatistics.remove(playerStatistics);
                playerStatistics.a = playerStatistics.a + i;
                PlayerStatistics.playerStatistics.add(playerStatistics);
                break;
            }
        }
    }

    public static void addWaterWins(Player player, int i) {
        for (PlayerStatistics playerStatistics : playerStatistics) {
            if (playerStatistics.player == player) {
                PlayerStatistics.playerStatistics.remove(playerStatistics);
                playerStatistics.w = playerStatistics.w + i;
                PlayerStatistics.playerStatistics.add(playerStatistics);
                break;
            }
        }
    }

    public static void addEarthWins(Player player, int i) {
        for (PlayerStatistics playerStatistics : playerStatistics) {
            if (playerStatistics.player == player) {
                PlayerStatistics.playerStatistics.remove(playerStatistics);
                playerStatistics.e = playerStatistics.e + i;
                PlayerStatistics.playerStatistics.add(playerStatistics);
                break;
            }
        }
    }

    public static void addAvatarWins(Player player, int i) {
        for (PlayerStatistics playerStatistics : playerStatistics) {
            if (playerStatistics.player == player) {
                PlayerStatistics.playerStatistics.remove(playerStatistics);
                playerStatistics.av = playerStatistics.av + i;
                PlayerStatistics.playerStatistics.add(playerStatistics);
                break;
            }
        }
    }

    public static void addChiWins(Player player, int i) {
        for (PlayerStatistics playerStatistics : playerStatistics) {
            if (playerStatistics.player == player) {
                PlayerStatistics.playerStatistics.remove(playerStatistics);
                playerStatistics.c = playerStatistics.c + i;
                PlayerStatistics.playerStatistics.add(playerStatistics);
                break;
            }
        }
    }

    public static void addSpiritWins(Player player, int i) {
        for (PlayerStatistics playerStatistics : playerStatistics) {
            if (playerStatistics.player == player) {
                PlayerStatistics.playerStatistics.remove(playerStatistics);
                playerStatistics.s = playerStatistics.s + i;
                PlayerStatistics.playerStatistics.add(playerStatistics);
                break;
            }
        }
    }

    public static void addStatistics(PlayerStatistics playerStatistics) {
        PlayerStatistics.playerStatistics.add(playerStatistics);
    }




}
