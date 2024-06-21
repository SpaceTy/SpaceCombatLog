package me.spacety.spacecombatlog;

import org.bukkit.plugin.java.JavaPlugin;

public final class SpaceCombatLog extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new BlackListedCommandBlocker(), this);
    }

    @Override
    public void onDisable() {

    }
}
