package pl.KarwszPL.Avatarserv.ElementalDuels.ArenaStorage;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import pl.KarwszPL.Avatarserv.ElementalDuels.Objects.Arena;

public class ArenaUtils {


    public static String arenaToString(Arena arena) {

        return arena.name + "->" + locationToString(arena.position) + "->" + locationToString(arena.position1) + "->" + arena.element;
    }

    public static Arena stringToArena(String string) {

        if (!string.contains("->")) return null;

        String[] s = string.split("->");

        System.out.println(string);


        return new Arena(s[0], stringToLocation(s[1]), stringToLocation(s[2]), s[3]);
    }


    private static String locationToString(Location location){
        return location.getWorld().getName() + ";" + location.getX() + ";" + location.getY() + ";" + location.getZ() + ";" + location.getYaw() + ";" + location.getPitch();
    }

    private static Location stringToLocation(String string) {

        String[] s = string.split(";");
        return new Location(Bukkit.getWorld(s[0]), Double.parseDouble(s[1]), Double.parseDouble(s[2]), Double.parseDouble(s[3]), Float.parseFloat(s[4]), Float.parseFloat(s[5]));
    }


}
