package fr.euphyllia.skyfolia.commands;

import fr.euphyllia.skyfolia.Main;
import fr.euphyllia.skyfolia.commands.subcommands.CreateSubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SkyFoliaCommand implements CommandExecutor, TabCompleter {

    private Main plugin;

    public SkyFoliaCommand(Main main) {
        this.plugin = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length != 0) {
            String subCommand = args[0].trim().toLowerCase();
            String[] listArgs = Arrays.copyOfRange(args, 1, args.length);
            return switch (subCommand) {
                case "create" -> new CreateSubCommand(this.plugin).onCommand(sender, command, label, listArgs);
                default -> false;
            };
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> tab = new ArrayList<>();
        if (args.length == 1) {
            SubCommands[] subCommand = SubCommands.values();
            for (SubCommands sub : subCommand) {
                tab.add(sub.getSubCommandName());
            }
        } else {
            String subCommand = args[0].trim().toLowerCase();
            String[] listArgs = Arrays.copyOfRange(args, 1, args.length);
            return switch (subCommand) {
                default -> tab;
            };
        }
        return null;
    }

}