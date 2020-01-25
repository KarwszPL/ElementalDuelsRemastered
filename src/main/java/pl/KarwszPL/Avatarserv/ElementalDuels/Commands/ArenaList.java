package pl.KarwszPL.Avatarserv.ElementalDuels.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.KarwszPL.Avatarserv.ElementalDuels.Objects.Arena;

public class ArenaList implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            System.out.println("Musisz byc graczem aby uzyc tej komendy!");
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("ElementalDuels.ArenaList")) {
            player.sendMessage(ChatColor.RED + "Niewystarczajace uprawnienia!");
            return false;
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (Arena arena : Arena.arenaArrayList) {
            if (arena != null) {
                stringBuilder.append(arena.name).append(", ");
            }
        }

        if (stringBuilder.toString().length() == 0) {
            player.sendMessage(ChatColor.AQUA + "Brak dostepnych aren!");
            return false;
        }
        player.sendMessage(ChatColor.AQUA + stringBuilder.toString().substring(0, stringBuilder.toString().length() - 2));

        return false;
    }
}
