package me.arcilance.mcplugins.partycrackers;

import me.arcilance.mcplugins.partycrackers.models.Cracker;
import me.arcilance.mcplugins.partycrackers.models.CrackerEffect;
import me.arcilance.mcplugins.partycrackers.models.CrackerReward;
import me.arcilance.mcplugins.partycrackers.models.CrackerSound;
import org.bukkit.NamespacedKey;

import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.Map;

public class CrackerManager {

    private PartyCrackers plugin;

    private Map<String, Cracker> crackers;
    private Map<String, CrackerReward> rewards;
    private Map<String, CrackerSound> sounds;
    private Map<String, CrackerEffect> effects;

    private NamespacedKey crackerKey;

    public CrackerManager(PartyCrackers plugin) {

        this.plugin = plugin;

        crackers = new HashMap<>();
        rewards = new HashMap<>();
        sounds = new HashMap<>();
        effects = new HashMap<>();

        crackerKey = new NamespacedKey(this.plugin, "cracker");
    }

    public boolean isCracker(ItemStack item) {

        if (item == null || !item.hasItemMeta()) {

            return false;
        }

        return item.getItemMeta().getPersistentDataContainer().has(crackerKey, PersistentDataType.STRING);

    }

    public Cracker getCrackerFromItem(ItemStack item) {

        return isCracker(item) ? crackers.get(item.getItemMeta().getPersistentDataContainer().get(crackerKey, PersistentDataType.STRING)) : null;
    }

    public Cracker getCrackerFromID(String id) {

        return crackers.get(id);
    }

    public void addCracker(String id, Cracker cracker) {

        crackers.put(id, cracker);
    }

    public void addCrackerReward(String id, CrackerReward crackerReward) {

        rewards.put(id, crackerReward);
    }

    public Map<String, CrackerReward> getAllCrackerRewards() {

        return rewards;
    }

    public NamespacedKey getKey() {

        return crackerKey;
    }

    public Map<String, Cracker> getAllCrackers() {

        return crackers;
    }

    public void addCrackerSound(String key, CrackerSound crackerSound) {

        sounds.put(key, crackerSound);
    }

    public void addCrackerEffect(String key, CrackerEffect crackerEffect) {

        effects.put(key, crackerEffect);
    }

    public Map<String, CrackerSound> getAllCrackerSounds() {

        return sounds;
    }

    public Map<String, CrackerEffect> getAllCrackerEffects() {

        return effects;
    }
}
