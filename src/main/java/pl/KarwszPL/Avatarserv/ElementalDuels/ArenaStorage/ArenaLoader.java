package pl.KarwszPL.Avatarserv.ElementalDuels.ArenaStorage;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginEnableEvent;
import pl.KarwszPL.Avatarserv.ElementalDuels.Objects.Arena;

public class ArenaLoader implements Listener {


    @EventHandler
    public void onEnable(PluginEnableEvent event) {
        for (String string : ArenaStorage.getArenaStorage().getKeys(true)) {

                if (!Arena.getArenaExist(string)) {
                    Arena.addArena(ArenaUtils.stringToArena(ArenaStorage.getArenaStorage().getString(string)));
                }

            }

        }
    }



