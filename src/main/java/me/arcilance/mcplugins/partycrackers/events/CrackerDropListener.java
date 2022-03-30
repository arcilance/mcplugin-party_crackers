package me.arcilance.mcplugins.partycrackers.events;

import me.arcilance.mcplugins.partycrackers.PartyCrackers;
import me.arcilance.mcplugins.partycrackers.models.Cracker;
import me.arcilance.mcplugins.partycrackers.models.CrackerEffect;
import me.arcilance.mcplugins.partycrackers.models.CrackerReward;
import me.arcilance.mcplugins.partycrackers.models.CrackerSound;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import java.awt.*;

public class CrackerDropListener implements Listener {

    private PartyCrackers plugin;

    public CrackerDropListener(PartyCrackers plugin) {

        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDropItemEvent(PlayerDropItemEvent e) {

        Cracker cracker = plugin.getManager().getCrackerFromItem(e.getItemDrop().getItemStack());

        if (cracker == null) {

            return;
        }

        e.getItemDrop().setGlowing(true);
        e.getItemDrop().setPickupDelay(Integer.MAX_VALUE);

        for (int i = 1; i <= e.getItemDrop().getItemStack().getAmount(); i++) {

            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {

                CrackerReward reward = cracker.getRandomReward();
                CrackerSound sound = cracker.getRandomSound();
                CrackerEffect effect = cracker.getRandomEffect();

                if (reward != null && sound != null && effect != null) {

                    World world = e.getItemDrop().getWorld();

                    world.playSound(e.getItemDrop().getLocation(), sound.getSound(), sound.getVolume(), sound.getPitch());
                    world.spawnParticle(effect.getParticle(), e.getItemDrop().getLocation(), effect.getCount(), effect.getOffsetX(), effect.getOffsetY(), effect.getOffsetZ(), effect.getSpeed(), effect.getDust());
                    world.dropItemNaturally(e.getItemDrop().getLocation(), reward.getRandomAmountItem());
                    e.getItemDrop().remove();
                }
            }, cracker.getWarmup() * 20L);
        }
    }
}
