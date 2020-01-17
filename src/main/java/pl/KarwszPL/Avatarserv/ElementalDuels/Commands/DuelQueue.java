package pl.KarwszPL.Avatarserv.ElementalDuels.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import pl.KarwszPL.Avatarserv.ElementalDuels.Objects.Arena;

import java.util.HashMap;

public class DuelQueue implements CommandExecutor {

    private static HashMap<Player, String> queue = new HashMap<>();

    public static void startQueue() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : queue.keySet()) {

                    if (Arena.getPlayerArena(player) != null) return;

                    if (Arena.getAvailableArena(queue.get(player)) == null) return;
                    String arena = Arena.getAvailableArena(queue.get(player));
                    Arena.addPlayer(arena, player);


                }
            }
        }.runTaskTimer(Bukkit.getPluginManager().getPlugin("ElementalDuels"), 1, 40);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            System.out.println("Musisz byc graczem aby uzyc tej komendy!");
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "Podaj pierwsza litere z nazwy zywiolu do ktorego kolejki chcesz dolaczyc!");
            return false;
        }

        if (args[0].equalsIgnoreCase("O")) {
            player.sendMessage(ChatColor.RED + "Dolaczono do kolejki ognia!");
        }
        else if (args[0].equalsIgnoreCase("P")) {
            player.sendMessage(ChatColor.DARK_GRAY + "Dolaczono do kolejki powietrza!");
        }
        else if (args[0].equalsIgnoreCase("W")) {
            player.sendMessage(ChatColor.AQUA + "Dolaczono do kolejki wody!");
        }
        else if (args[0].equalsIgnoreCase("Z")) {
            player.sendMessage(ChatColor.GREEN + "Dolaczono do kolejki ziemi!");
        }
        else if (args[0].equalsIgnoreCase("A")) {
            player.sendMessage(ChatColor.DARK_PURPLE + "Dolaczono do kolejki avatara!");
        }
        else if (args[0].equalsIgnoreCase("C")) {
            player.sendMessage(ChatColor.YELLOW + "Dolaczono do kolejki chi!");
        }
        else if (args[0].equalsIgnoreCase("S")) {
            player.sendMessage(ChatColor.DARK_AQUA + "Dolaczono do kolejki duchow!");
        }
        else {
            player.sendMessage(ChatColor.RED + "Niepoprawny element!");
            return false;
        }

        queue.put(player, commandElementToElementName(args[0]));
        return false;
    }
    
    
    private String commandElementToElementName(String string){
        
        if (string.equalsIgnoreCase("O")) {
            return "Fire";
        }
        else if (string.equalsIgnoreCase("P")) {
            return "Air";
        }
        else if (string.equalsIgnoreCase("W")) {
            return "Water";
        }
        else if (string.equalsIgnoreCase("Z")) {
            return "Earth";
        }
        else if (string.equalsIgnoreCase("A")) {
            return "Avatar";
        }
        else if (string.equalsIgnoreCase("C")) {
            return "Chi";
        }
        else if (string.equalsIgnoreCase("S")) {
            return "Spirit";
        }
        
        return "Unkown";
    }
    
    
}
