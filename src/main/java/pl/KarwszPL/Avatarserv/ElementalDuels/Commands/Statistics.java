package pl.KarwszPL.Avatarserv.ElementalDuels.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.KarwszPL.Avatarserv.ElementalDuels.Objects.PlayerStatistics;

import java.util.Random;

public class Statistics implements CommandExecutor {

    Random random = new Random();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            System.out.println("Musisz byc graczem aby uzyc tej komendy!");
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("ElementalDuels.Statistics")) {
            player.sendMessage(ChatColor.RED + "Niewystarczajace uprawnienia!");
            return false;
        }

        if (args.length < 1) {
            player.sendMessage(ChatColor.RED + "Podaj nazwe gracza ktorego statystyki chcesz sprawdzic!");
            return false;
        }

        if (Bukkit.getPlayer(args[0]) == null) {
            player.sendMessage(ChatColor.RED + "Ten gracz nie jest online!");
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);
        PlayerStatistics playerStatistics = PlayerStatistics.getStatistics(target);
        if (playerStatistics == null) {
            player.sendMessage(ChatColor.RED + "Wystapil niespodziewany blad. Skontaktuj sie z administratorem.");
            return false;
        }
        int all = playerStatistics.f + playerStatistics.a + playerStatistics.w + playerStatistics.e + playerStatistics.av + playerStatistics.c + playerStatistics.s;

        player.sendMessage(ChatColor.AQUA + "--------------------------------------");
        player.sendMessage(ChatColor.GOLD + "Statystyki gracza "+ target.getName());
        player.sendMessage(ChatColor.RED + "O: " + playerStatistics.f + ChatColor.AQUA +  " | " + ChatColor.DARK_GRAY + "P: " + playerStatistics.a +  " | " + ChatColor.BLUE + "W: " + playerStatistics.w +  " | " + ChatColor.GREEN + "Z: " + playerStatistics.e +  " | " + ChatColor.DARK_PURPLE + "A: " + playerStatistics.av +  " | " + ChatColor.YELLOW + "C: " + playerStatistics.c +  " | " + ChatColor.DARK_AQUA + "D: " + playerStatistics.s);
        player.sendMessage(ChatColor.AQUA + "Lacznie: " + all);
        player.sendMessage(ChatColor.AQUA + "--------------------------------------");
        if (random.nextInt(10) == 1) player.sendMessage(ChatColor.LIGHT_PURPLE + "ElementalDuelsRemastered 1.0 By KarwszPL");

        return false;
    }
}
