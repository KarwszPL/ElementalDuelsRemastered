package pl.KarwszPL.Avatarserv.ElementalDuels.ArenaStorage;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import pl.KarwszPL.Avatarserv.ElementalDuels.ElementalDuels;

import java.io.File;
import java.io.IOException;

public class ArenaStorage {

    private static YamlConfiguration arenaStorage = new YamlConfiguration();
    private static File file;

    public static void createConfig() {
        file = new File(ElementalDuels.getPlugin().getDataFolder() + "/" + "ArenaStorage.yml");
        try {
            arenaStorage.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static YamlConfiguration getArenaStorage(){
        return arenaStorage;
    }

    public static void save() {
        try {
            getArenaStorage().save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
