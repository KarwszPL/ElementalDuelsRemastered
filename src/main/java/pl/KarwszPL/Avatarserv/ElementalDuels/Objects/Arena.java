package pl.KarwszPL.Avatarserv.ElementalDuels.Objects;

import org.bukkit.Location;
import org.bukkit.entity.Player;

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



    public static String getAllowedElements(String name) {

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
            if (arena.name.equalsIgnoreCase(name)) {
                arenaArrayList.remove(arena);

                if (arena.player == null) arena.player = player;
                else {
                    arena.player1 = player;
                    arena.inUse = true;
                }



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





}
