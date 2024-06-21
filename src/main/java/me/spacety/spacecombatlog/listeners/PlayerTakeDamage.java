package me.spacety.spacecombatlog.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import me.spacety.spacecombatlog.SpaceCombatLog;
import me.spacety.spacecombatlog.util.TimingsHandler;

public class PlayerTakeDamage implements Listener {

    @EventHandler
    public void onPlayerTakeDamage(EntityDamageEvent event) {

        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if (SpaceCombatLog.getBoolean("strict-mode")) {
                System.out.println(event.getCause());
                System.out.println(event.getDamageSource().getCausingEntity());
                if (event.getDamageSource().getCausingEntity() instanceof Player) {
                    TimingsHandler.addCombatTiming(player.getUniqueId());
                }
            } else {
                TimingsHandler.addCombatTiming(player.getUniqueId());
            }
        }

    }
    
}
