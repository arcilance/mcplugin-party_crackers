package me.arcilance.mcplugins.partycrackers;

import me.arcilance.mcplugins.partycrackers.commands.CrackerCommand;
import me.arcilance.mcplugins.partycrackers.commands.CrackerCommandTabCompletion;
import me.arcilance.mcplugins.partycrackers.events.CrackerDropListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class PartyCrackers extends JavaPlugin {

    private CrackerManager manager;
    private Config config;

    @Override
    public void onEnable() {

        saveDefaultConfig();

        config = new Config(this);
        manager = new CrackerManager(this);

        config.loadConfig();

        getCommand("cracker").setExecutor(new CrackerCommand(this));
        getCommand("cracker").setTabCompleter(new CrackerCommandTabCompletion(this));
        getServer().getPluginManager().registerEvents(new CrackerDropListener(this), this);

    }

    @Override
    public void onDisable() {

    }

    public CrackerManager getManager() {

        return manager;
    }

}
