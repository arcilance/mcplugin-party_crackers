package me.arcilance.mcplugins.partycrackers.commands;

import com.google.common.primitives.Ints;
import me.arcilance.mcplugins.partycrackers.PartyCrackers;
import me.arcilance.mcplugins.partycrackers.models.Cracker;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CrackerCommand implements CommandExecutor {

    private PartyCrackers plugin;

    public CrackerCommand(PartyCrackers plugin) {

        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player) && args.length <= 2) {

            return true;
        }

        if (args.length < 2) {

            sender.sendMessage("Usage: /cracker [ID] [Amount] [Player]");
            return true;
        }

        Cracker cracker = plugin.getManager().getCrackerFromID(args[0]);

        if (cracker == null) {

            sender.sendMessage("Unable to find cracker");
            return true;
        }

        Integer amount = Ints.tryParse(args[1]);

        if (amount == null){

            sender.sendMessage("Invalid amount of crackers");
            return true;
        }

        if (args.length == 2){

            ((Player) sender).getInventory().addItem(cracker.getItemStack(amount, plugin.getManager().getKey()));
        }
        else if (args.length == 3) {

            Player receivingPlayer = Bukkit.getPlayerExact(args[2]);

            if (receivingPlayer == null) {

                sender.sendMessage("Player not found");
                return true;
            }

            receivingPlayer.getInventory().addItem(cracker.getItemStack(amount, plugin.getManager().getKey()));
        }
        return true;
    }
}
