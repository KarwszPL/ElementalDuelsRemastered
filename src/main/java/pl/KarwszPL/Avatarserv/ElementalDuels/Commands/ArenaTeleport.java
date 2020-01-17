package pl.KarwszPL.Avatarserv.ElementalDuels.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.KarwszPL.Avatarserv.ElementalDuels.Objects.Arena;

public class ArenaTeleport implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (!(sender instanceof Player)) {
            System.out.println("Musisz byc graczem aby uzywac tej komendy!");
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("ElementalDuels.ArenaTeleport")) {
            player.sendMessage(ChatColor.RED + "Niewystarczajace uprawnienia!");
            return false;
        }

        switch (args.length) {
            case (0):
                player.sendMessage(ChatColor.AQUA + "Podaj nazwe areny na ktora chcesz sie przeniesc!");
                return false;
            case (1):
                player.sendMessage(ChatColor.AQUA + "Podaj pozycje na ktora chcesz sie przeniesc: 1/2");
                return false;
        }

        if (!Arena.getArenaExist(args[0])) {
            player.sendMessage(ChatColor.AQUA + "Ta arena nie istnieje!");
            return false;
        }

        if (!args[1].equals("1") && !args[1].equals("2")) {
            player.teleport(Arena.getArenaPosition(args[0], 1));
            player.sendMessage(ChatColor.AQUA + "Przeniesiono na pozycje 1 areny " + args[0] + "!");
            return false;
        }
        else {
            player.teleport(Arena.getArenaPosition(args[0], Integer.parseInt(args[1])));
            player.sendMessage(ChatColor.AQUA + "Przeniesiono na pozycje " + args[1] + " areny " + args[0] + "!");
        }



        return false;
    }
}
