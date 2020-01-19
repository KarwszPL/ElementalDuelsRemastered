package pl.KarwszPL.Avatarserv.ElementalDuels.Objects;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import pl.KarwszPL.Avatarserv.ElementalDuels.Commands.DuelQueue;
import pl.KarwszPL.Avatarserv.ElementalDuels.Listeners.IllegalSpellListener;

import java.util.ArrayList;

public class Arena {

    public String name;
    public Location position;
    public Location position1;
    public String element;
    private boolean inUse;
    private Player player;
    private Player player1;

    public static ArrayList<Arena> arenaArrayList = new ArrayList<>();

    public Arena(String name, Location position, Location position1, String element){
        this.name = name;
        this.position = position;
        this.position1 = position1;
        this.element = element;
        this.inUse = false;
        this.player = null;
        this.player1 = null;
    }

    public static void addArena(Arena arena){
        Arena.arenaArrayList.add(arena);
    }

    public static Boolean getArenaExist(String string) {
        for (Arena arena : arenaArrayList) {
            if (arena == null) return true;
            if (arena.name.equalsIgnoreCase(string)) return true;
        }

        return false;
    }

    public static Location getArenaPosition(String name, int i) {
        for (Arena arena : arenaArrayList) {
            if (arena.name.equalsIgnoreCase(name)) {
                switch (i) {
                    case(1):
                        return arena.position;
                    case (2):
                        return arena.position1;
                    default:
                        return null;
                }
            }
        }
        return null;
    }


    public static void deleteArena(String string) {
        for (Arena arena : arenaArrayList) {
            if (arena.name.equalsIgnoreCase(string)) {
                arenaArrayList.remove(arena);
                return;
            }
        }
    }

    public static void setInUse(String name, Boolean inUse) {

        for (Arena arena : arenaArrayList) {
            if (arena.name.equalsIgnoreCase(name)) {
                arenaArrayList.remove(arena);
                arena.inUse = inUse;
                arenaArrayList.add(arena);
            }
        }


    }



    private static String getAllowedElements(String name) {

        for (Arena arena : arenaArrayList) {
            if (arena.name.equalsIgnoreCase(name)) {
                switch (arena.element) {
                    case ("Fire"):
                        return "Fire, Lightning, Combustion";
                    case ("Air"):
                        return "Air, Spiritual";
                    case ("Water"):
                        return "Water, Ice, Healing, Plant, Blood";
                    case ("Earth"):
                        return "Earth,Lava,Metal";
                    case ("Avatar"):
                        return "Avatar";
                    case ("Chi"):
                        return "Chi";
                    case ("Spirit"):
                        return "Spirit";

                }
            }
        }
        return "";
    }


    public static String getAvailableArena(String element){
        for (Arena arena : arenaArrayList) {
            if (arena.element.equalsIgnoreCase(element) && !arena.inUse) {
                return arena.name;
            }
        }
        return null;
    }


    public static void addPlayer(String name, Player player) {

        for (Arena arena : arenaArrayList) {
            new BukkitRunnable() {
                public void run() {
                    if (arena.name.equalsIgnoreCase(name)) {
                        arenaArrayList.remove(arena);
                        if (arena.player == null) arena.player = player;
                        else {
                            arena.player1 = player;
                            DuelQueue.queue.remove(player);
                            arena.inUse = true;
                            startDuel(arena.player, arena.player1, arena);
                        }
                        arenaArrayList.add(arena);
                    }
                }
            }.runTaskLater(Bukkit.getPluginManager().getPlugin("ElementalDuels"), 1);
        }

    }

    public static void removePlayerFromArenas(Player player) {
        for (Arena arena : arenaArrayList) {
            if (arena.player == player) {
                arenaArrayList.remove(arena);
                arena.player = arena.player1;
                arena.player1 = null;
                arenaArrayList.add(arena);
            }
        }
    }


    public static String getPlayerArena(Player player) {
        for (Arena arena : arenaArrayList) {
            if (arena.player == player || arena.player1 == player) return arena.name;
        }
        return null;
    }

    public static Player getPlayerEnemy(Player player) {
        for (Arena arena : arenaArrayList) {
            if (arena.player == player || arena.player1 == player) {
                if (arena.player != null && arena.player1 != null) {
                    if (arena.player == player) return arena.player1;
                    else return arena.player;
                }
            }
        }
        return null;
    }

    public static Boolean getPlayerArenaInUse(Player player) {
        for (Arena arena : arenaArrayList) {
            if ((arena.player == player || arena.player1 == player) && arena.inUse) return true;
        }
        return false;
    }

    public static String getPlayerArenaElements(Player player) {
        for (Arena arena : arenaArrayList) {
            if (arena.player == player || arena.player1 == player) return getAllowedElements(arena.name);
        }
        return null;
    }

    private static void startDuel(Player player, Player player1, Arena arena) {
        player.teleport(arena.position);
        player1.teleport(arena.position1);
        startCounting(player, player1);
    }

    private static void startCounting(Player player, Player player1) {
        IllegalSpellListener.players.add(player);
        IllegalSpellListener.players.add(player1);
        new BukkitRunnable() {
            public void run(){
                player.sendTitle(ChatColor.GREEN + "3", "");
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_HARP, 1 ,1);
                player1.sendTitle(ChatColor.GREEN + "3", "");
                player1.playSound(player.getLocation(), Sound.BLOCK_NOTE_HARP, 1 ,1);
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("ElementalDuels"), 20);

        new BukkitRunnable() {
            public void run(){
                player.sendTitle(ChatColor.GREEN + "2", "");
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_HARP, 1 ,1);
                player1.sendTitle(ChatColor.GREEN + "2", "");
                player1.playSound(player.getLocation(), Sound.BLOCK_NOTE_HARP, 1 ,1);
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("ElementalDuels"), 40);

        new BukkitRunnable() {
            public void run(){
                player.sendTitle(ChatColor.GREEN + "1", "");
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_HARP, 1 ,1);
                player1.sendTitle(ChatColor.GREEN + "1", "");
                player1.playSound(player.getLocation(), Sound.BLOCK_NOTE_HARP, 1 ,1);
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("ElementalDuels"), 60);

        new BukkitRunnable() {
            public void run(){
                player.sendTitle(ChatColor.GREEN + "START!", "");
                player.playSound(player.getLocation(), Sound.ENTITY_WITHER_DEATH, 1 ,1);
                player1.sendTitle(ChatColor.GREEN + "START!", "");
                player1.playSound(player.getLocation(), Sound.ENTITY_WITHER_DEATH, 1 ,1);
                IllegalSpellListener.players.remove(player);
                IllegalSpellListener.players.remove(player1);
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("ElementalDuels"), 80);

    }




}
