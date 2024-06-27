package me.spacety.spacecombatlog;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.spacety.spacecombatlog.commands.SCLCommandHandler;
import me.spacety.spacecombatlog.listeners.BlackListedCommandBlocker;
import me.spacety.spacecombatlog.listeners.PlayerTakeDamage;

public final class SpaceCombatLog extends JavaPlugin {
    private static FileConfiguration config;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new BlackListedCommandBlocker(), this);
        getServer().getPluginManager().registerEvents(new PlayerTakeDamage(), this);
        getCommand("scl").setExecutor(new SCLCommandHandler());
        getCommand("scl").setTabCompleter(new SCLCommandHandler());
        loadCfg();

        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new SCLPlaceholder(this).register();
        }
    }

    @Override
    public void onDisable() {

    }

    public static String getString(String key) {
        return config.getString(key);
    }

    public static String[] getStringList(String key) {
        return config.getStringList(key).toArray(new String[0]);
    }

    public static int getInt(String key) {
        return config.getInt(key);
    }

    public static boolean getBoolean(String key) {
        return config.getBoolean(key);
    }

    public static void reloadCfg() {
        SpaceCombatLog plugin = SpaceCombatLog.getPlugin(SpaceCombatLog.class);
        plugin.reloadConfig();
        config = plugin.getConfig();
    }

    public void loadCfg() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        saveDefaultConfig();
        config = getConfig();
    }
}
