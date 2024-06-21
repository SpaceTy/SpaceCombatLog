package me.spacety.spacecombatlog.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import me.spacety.spacecombatlog.SpaceCombatLog;
import me.spacety.spacecombatlog.util.PlayerMessage;
import me.spacety.spacecombatlog.util.TimingsHandler;

public class BlackListedCommandBlocker implements Listener {

    @EventHandler
    public void onCommandExecute(PlayerCommandPreprocessEvent event) {
        String command = event.getMessage();
        System.out.println(command);

        if (event.getPlayer().hasPermission("spacecombatlog.bypass")) {

        }

        if (!TimingsHandler.isInCombat(event.getPlayer().getUniqueId())) {
            return;
        }

        if (containsIgnoreCase(SpaceCombatLog.getStringList("blacklisted-commands"), command)){
            event.setCancelled(true);
            PlayerMessage.send(event.getPlayer(), SpaceCombatLog.getString("messages.command-blocked"));
        }
    }

    public static boolean containsIgnoreCase(String[] array, String target) {
        if (array == null || target == null) {
            return false;
        }
        
        for (String s : array) {
            if (target.equalsIgnoreCase(s)) {
                return true;
            }
        }
        
        return false;
    }
    
}
