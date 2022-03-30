package me.arcilance.mcplugins.partycrackers;

import me.arcilance.mcplugins.partycrackers.models.Cracker;
import me.arcilance.mcplugins.partycrackers.models.CrackerEffect;
import me.arcilance.mcplugins.partycrackers.models.CrackerReward;
import me.arcilance.mcplugins.partycrackers.models.CrackerSound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Config {

    private PartyCrackers plugin;

    public Config(PartyCrackers plugin) {

        this.plugin = plugin;
    }

    public void loadConfig() {

        loadRewards();
        loadSounds();
        loadEffects();
        loadCrackers();
    }

    private void loadSounds() {

        Set<String> keys = plugin.getConfig().getConfigurationSection("sounds").getKeys(false);

        for (String key : keys) {

            Sound sound = Sound.valueOf(plugin.getConfig().getString("sounds." + key + ".sound").toUpperCase());
            float volume = (float) plugin.getConfig().getDouble("sounds." + key + ".volume");
            float pitch = (float) plugin.getConfig().getDouble("sounds." + key + ".pitch");

            plugin.getManager().addCrackerSound(key, new CrackerSound(sound, volume, pitch));
        }
    }

    private void loadEffects() {

        Set<String> keys = plugin.getConfig().getConfigurationSection("effects").getKeys(false);

        for (String key : keys) {

            Color awtColor = Color.decode(plugin.getConfig().getString("effects." + key + ".color"));
            org.bukkit.Color color = org.bukkit.Color.fromRGB(awtColor.getRed(), awtColor.getGreen(), awtColor.getBlue());
            int count = plugin.getConfig().getInt("effects." + key + ".count");
            int offsetX = plugin.getConfig().getInt("effects." + key + ".offset_x");
            int offsetY = plugin.getConfig().getInt("effects." + key + ".offset_y");
            int offsetZ = plugin.getConfig().getInt("effects." + key + ".offset_z");
            int speed = plugin.getConfig().getInt("effects." + key + ".speed");

            plugin.getManager().addCrackerEffect(key, new CrackerEffect(color, count, offsetX, offsetY, offsetZ, speed));
        }
    }

    private void loadRewards() {


        Set<String> keys = plugin.getConfig().getConfigurationSection("rewards").getKeys(false);

        for (String key : keys) {

            Material type = Material.matchMaterial(plugin.getConfig().getString("rewards." + key + ".type"));
            int minAmount = plugin.getConfig().getInt("rewards." + key + ".min_amount");
            int maxAmount = plugin.getConfig().getInt("rewards." + key + ".max_amount");

            plugin.getManager().addCrackerReward(key, new CrackerReward(type, minAmount, maxAmount));
        }
    }

    private void loadCrackers() {

        Set<String> keys = plugin.getConfig().getConfigurationSection("crackers").getKeys(false);

        for (String key : keys) {

            Material type = Material.matchMaterial(plugin.getConfig().getString("crackers." + key + ".type"));
            String name = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("crackers." + key + ".name"));

            List<String> lore = Arrays.asList(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("crackers." + key + ".lore")).split("\n"));
            boolean enchanted = plugin.getConfig().getBoolean("crackers." + key + ".enchanted");
            int warmup = plugin.getConfig().getInt("crackers." + key + ".warmup");

            List<Map<String, Integer>> crackerRewardsRaw = (List) plugin.getConfig().getMapList("crackers." + key + ".rewards");
            Map<CrackerReward, Integer> crackerRewards = new HashMap<>();
            crackerRewardsRaw.forEach(reward -> reward.entrySet().forEach(rewardEntry -> crackerRewards.put(plugin.getManager().getAllCrackerRewards().get(rewardEntry.getKey()), rewardEntry.getValue())));

            List<Map<String, Integer>> crackerSoundsRaw = (List) plugin.getConfig().getMapList("crackers." + key + ".sounds");
            Map<CrackerSound, Integer> crackerSounds = new HashMap<>();
            crackerSoundsRaw.forEach(reward -> reward.entrySet().forEach(rewardEntry -> crackerSounds.put(plugin.getManager().getAllCrackerSounds().get(rewardEntry.getKey()), rewardEntry.getValue())));

            List<Map<String, Integer>> crackerEffectsRaw = (List) plugin.getConfig().getMapList("crackers." + key + ".effects");
            Map<CrackerEffect, Integer> crackerEffects = new HashMap<>();
            crackerEffectsRaw.forEach(reward -> reward.entrySet().forEach(rewardEntry -> crackerEffects.put(plugin.getManager().getAllCrackerEffects().get(rewardEntry.getKey()), rewardEntry.getValue())));

            plugin.getManager().addCracker(key, new Cracker(key, type, name, lore, enchanted, warmup, crackerRewards, crackerSounds, crackerEffects));
        }
    }
}
