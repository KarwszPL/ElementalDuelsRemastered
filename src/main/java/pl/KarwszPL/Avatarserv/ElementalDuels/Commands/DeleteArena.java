package pl.KarwszPL.Avatarserv.ElementalDuels.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.KarwszPL.Avatarserv.ElementalDuels.Objects.Arena;

public class DeleteArena implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            System.out.println("Musisz byc graczem aby uzyc tej komendy!");
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("ElementalDuels.DeleteArena")) {
            player.sendMessage(ChatColor.RED + "Niewystarczajace uprawnienia!");
            return false;
        }

        if (args.length == 0) {
            player.sendMessage(ChatColor.AQUA + "Podaj nazwe areny ktora chcesz usunac!");
            return false;
        }

        if (!Arena.getArenaExist(args[0])) {
            player.sendMessage(ChatColor.AQUA + "Arena " + args[0] + " nie istnieje!");
            return false;
        }
        Arena.deleteArena(args[0]);
        player.sendMessage(ChatColor.AQUA + "Pomyslnie usunieto arene " + args[0] + "!");

        return false;
    }
}
