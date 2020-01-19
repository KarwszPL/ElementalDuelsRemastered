package pl.KarwszPL.Avatarserv.ElementalDuels;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import pl.KarwszPL.Avatarserv.ElementalDuels.ArenaStorage.ArenaLoader;
import pl.KarwszPL.Avatarserv.ElementalDuels.ArenaStorage.ArenaStorage;
import pl.KarwszPL.Avatarserv.ElementalDuels.ArenaStorage.ArenaUtils;
import pl.KarwszPL.Avatarserv.ElementalDuels.Commands.*;
import pl.KarwszPL.Avatarserv.ElementalDuels.Listeners.IllegalSpellListener;
import pl.KarwszPL.Avatarserv.ElementalDuels.Listeners.PlayerDeathQuitListener;
import pl.KarwszPL.Avatarserv.ElementalDuels.Objects.Arena;

public final class ElementalDuels extends JavaPlugin {


    @Override
    public void onEnable() {

        ArenaStorage.createConfig();

        Bukkit.getPluginCommand("CreateArena").setExecutor(new CreateArena());
        Bukkit.getPluginCommand("ArenaList").setExecutor(new ArenaList());
        Bukkit.getPluginCommand("ArenaTeleport").setExecutor(new ArenaTeleport());
        Bukkit.getPluginCommand("DeleteArena").setExecutor(new DeleteArena());
        Bukkit.getPluginCommand("DuelQueue").setExecutor(new DuelQueue());
        Bukkit.getPluginManager().registerEvents(new ArenaLoader(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDeathQuitListener(), this);
        Bukkit.getPluginManager().registerEvents(new IllegalSpellListener(), this);
        DuelQueue.startQueue();

    }

    @Override
    public void onDisable() {
            for (String string : ArenaStorage.getArenaStorage().getKeys(false)) {
                ArenaStorage.getArenaStorage().set(string, null);
            }
            for (Arena arena : Arena.arenaArrayList) {
                ArenaStorage.getArenaStorage().set(arena.name, ArenaUtils.arenaToString(arena));
            }
            ArenaStorage.save();
        }




    public static Plugin getPlugin(){
        return Bukkit.getPluginManager().getPlugin("ElementalDuels");
    }



}
