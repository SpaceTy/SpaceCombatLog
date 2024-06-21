package me.spacety.spacecombatlog.util;

import java.util.HashMap;
import java.util.UUID;

import me.spacety.spacecombatlog.SpaceCombatLog;

public class TimingsHandler {

    private static HashMap<UUID, Long> combatTimings = new HashMap<>();

    public static void addCombatTiming(UUID uuid) {
        combatTimings.put(uuid, System.currentTimeMillis());
    }

    public static void removeCombatTiming(UUID uuid) {
        combatTimings.remove(uuid);
    }

    public static boolean isInCombat(UUID uuid) {
        if (combatTimings.containsKey(uuid)) {
            if (System.currentTimeMillis() - combatTimings.get(uuid) < SpaceCombatLog.getInt("combat-time") * 1000) {
                return true;
            } else {
                combatTimings.remove(uuid);
                return false;
            }
        } else {
            return false;
        }
    }

    public static long getTimeLeftInCombat(UUID uuid) {
        if (combatTimings.containsKey(uuid)) {
            long elapsedTime = System.currentTimeMillis() - combatTimings.get(uuid);
            long combatDuration = SpaceCombatLog.getInt("combat-time") * 1000;
            long timeLeft = combatDuration - elapsedTime;
            return timeLeft > 0 ? timeLeft : 0;
        } else {
            return 0;
        }
    }
}
