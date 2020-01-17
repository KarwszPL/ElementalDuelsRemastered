package pl.KarwszPL.Avatarserv.ElementalDuels.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.KarwszPL.Avatarserv.ElementalDuels.Objects.Arena;

import java.util.HashMap;

public class CreateArena implements CommandExecutor {

    private HashMap<Player, Integer> commandStage = new HashMap<>();
    private HashMap<Player, Arena> playerArenaHashMap = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (!(sender instanceof Player)) {
            System.out.println("Tylko gracz moze wprowadzic to polecenie!");
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("ElementalDuels.CreateArena")) {
            player.sendMessage(ChatColor.RED + "Nie posiadasz wystarczajacych uprawnien!");
            return false;
        }

        commandStage.putIfAbsent(player, 0);

        if (commandStage.get(player) == 0) {
            if (args.length == 0) {
                player.sendMessage(ChatColor.AQUA + "Musisz podac nazwe areny ktora chcesz utworzyc!");
                return false;
            }

            else playerArenaHashMap.put(player, new Arena(args[0], null, null, null));
            commandStage.put(player, 1);
            player.sendMessage(ChatColor.AQUA + "Teraz stan na pierwszej pozycji areny, po czym ponownie wprowadz komende.");
            return false;

        }

        if (commandStage.get(player) == 1) {

            playerArenaHashMap.put(player, new Arena(playerArenaHashMap.get(player).name, player.getLocation(), null, null));
            commandStage.put(player, 2);

            player.sendMessage( ChatColor.AQUA + "Ponow proces na drugiej pozycji areny.");
            return false;

        }

        if (commandStage.get(player) == 2) {

            playerArenaHashMap.put(player, new Arena(playerArenaHashMap.get(player).name, playerArenaHashMap.get(player).position, player.getLocation(), null));
            commandStage.put(player, 3);

            player.sendMessage(ChatColor.AQUA + "Teraz ponow komende, uzywajac angielskiej nazwy elementu jako argument.");
            return false;

        }

        if (commandStage.get(player) == 3) {
            if (args.length == 0) {
                player.sendMessage(ChatColor.AQUA + "Musisz wprowadzic angielska nazwe elementu areny!");
                return false;
            }

            else Arena.addArena(new Arena(playerArenaHashMap.get(player).name, playerArenaHashMap.get(player).position, playerArenaHashMap.get(player).position1, args[0]));

            commandStage.put(player, 0);
            player.sendMessage(ChatColor.AQUA + "Pomyslnie utworzono arene " + playerArenaHashMap.get(player).name);
            playerArenaHashMap.put(player, null);

        }


        return false;
    }
}
