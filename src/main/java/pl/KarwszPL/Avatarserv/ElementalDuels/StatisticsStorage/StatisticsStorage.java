package pl.KarwszPL.Avatarserv.ElementalDuels.StatisticsStorage;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import pl.KarwszPL.Avatarserv.ElementalDuels.ElementalDuels;

import java.io.File;
import java.io.IOException;

public class StatisticsStorage {

    private static YamlConfiguration arenaStorage = new YamlConfiguration();
    private static File file;

    public static void createConfig() {
        file = new File(ElementalDuels.getPlugin().getDataFolder() + "/" + "StatisticsStorage.yml");
        try {
            file.createNewFile();
            arenaStorage.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static YamlConfiguration getStatisticsStorage(){
        return arenaStorage;
    }

    public static void save() {
        try {
            getStatisticsStorage().save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
