package me.arcilance.mcplugins.partycrackers.commands;

import me.arcilance.mcplugins.partycrackers.PartyCrackers;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;
import sun.jvm.hotspot.utilities.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CrackerCommandTabCompletion implements TabCompleter {

    PartyCrackers plugin;

    public CrackerCommandTabCompletion(PartyCrackers plugin) {

        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        List<String> completions = new ArrayList<>();
        List<String> commands = new ArrayList<>();

        if (args.length == 1) {

            plugin.getManager().getAllCrackers().forEach((crackerKey, crackerValue) -> commands.add(crackerKey));
            StringUtil.copyPartialMatches(args[0], commands, completions);
        }

        if (args.length == 2) {

            Stream.iterate(1, n -> n + 1).limit(64).forEach(n -> commands.add(String.valueOf(n)));
            StringUtil.copyPartialMatches(args[1], commands, completions);
        }

        if (args.length == 3) {

            return null;
        }

        Collections.sort(completions);
        return completions;
    }
}
