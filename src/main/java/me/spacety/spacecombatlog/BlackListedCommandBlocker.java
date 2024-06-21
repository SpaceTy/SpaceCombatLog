package me.spacety.spacecombatlog;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class BlackListedCommandBlocker implements Listener {

    @EventHandler
    public void onCommandExecute(PlayerCommandPreprocessEvent event) {
        String command = event.getMessage();
        System.out.println(command);
    }
    
}
