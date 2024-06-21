package me.spacety.spacecombatlog.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import me.spacety.spacecombatlog.SpaceCombatLog;
import me.spacety.spacecombatlog.util.PlayerMessage;

public class SCLCommandHandler implements CommandExecutor, TabCompleter {

    private static final List<String> SUBCOMMANDS = new ArrayList<>();

    static {
        SUBCOMMANDS.add("reload");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command arg1, @NotNull String arg2,
            @NotNull String[] args) {
        if (args.length == 0) {
            PlayerMessage.send((Player) sender, SpaceCombatLog.getString("messages.help"));
            return true;
        }

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("spacecombatlog.reload")) {
                    SpaceCombatLog.reloadCfg();
                    PlayerMessage.send((Player) sender, SpaceCombatLog.getString("messages.config-reloaded"));
                } else {
                    PlayerMessage.send((Player) sender, SpaceCombatLog.getString("messages.no-permission"));
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command,
            @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1) {
            List<String> suggestions = new ArrayList<>();
            String currentArg = args[0].toLowerCase();
            for (String subcommand : SUBCOMMANDS) {
                if (subcommand.toLowerCase().startsWith(currentArg)) {
                    suggestions.add(subcommand);
                }
            }
            return suggestions;
        }
        return null;
    }
}
