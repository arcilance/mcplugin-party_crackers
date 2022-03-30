package me.arcilance.mcplugins.partycrackers.models;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import javax.naming.Name;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Cracker {

    private String id;
    private Material type;
    private String name;
    private List<String> lore;
    private boolean enchanted;
    private int warmup;
    private Map<CrackerReward, Integer> rewards;
    private Map<CrackerSound, Integer> sounds;
    private Map<CrackerEffect, Integer> effects;
    private Random random;

    public Cracker(String id, Material type, String name, List<String> lore, boolean enchanted, int warmup, Map<CrackerReward, Integer> rewards, Map<CrackerSound, Integer> sounds, Map<CrackerEffect, Integer> effects) {

        this.id = id;
        this.type = type;
        this.name = name;
        this.lore = lore;
        this.enchanted = enchanted;
        this.warmup = warmup;
        this.rewards = rewards;
        this.sounds = sounds;
        this.effects = effects;

        random = new Random();
    }

    public int getWarmup() {

        return warmup;
    }

    public CrackerReward getRandomReward() {

        int val = random.nextInt(100);

        for (Map.Entry<CrackerReward, Integer> entry : rewards.entrySet()) {

            val -= entry.getValue();

            if (val < 0) {

                return entry.getKey();
            }
        }
        return null;
    }

    public CrackerSound getRandomSound() {

        int val = random.nextInt(100);

        for (Map.Entry<CrackerSound, Integer> entry : sounds.entrySet()) {

            val -= entry.getValue();

            if (val < 0) {

                return entry.getKey();
            }
        }
        return null;
    }

    public CrackerEffect getRandomEffect() {

        int val = random.nextInt(100);

        for (Map.Entry<CrackerEffect, Integer> entry : effects.entrySet()) {

            val -= entry.getValue();

            if (val < 0) {

                return entry.getKey();
            }
        }
        return null;
    }

    public ItemStack getItemStack(int amount, NamespacedKey key) {

        ItemStack item = new ItemStack(type, amount);

        if (enchanted) {

            item.addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.LOOT_BONUS_BLOCKS, 1);
        }

        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        meta.setUnbreakable(true);
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, id);

        item.setItemMeta(meta);

        return item;
    }
}
