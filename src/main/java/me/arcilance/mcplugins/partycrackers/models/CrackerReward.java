package me.arcilance.mcplugins.partycrackers.models;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class CrackerReward {

    private Material type;
    private int minAmount;
    private int maxAmount;

    private Random random;

    public CrackerReward(Material type, int minAmount, int maxAmount) {

        this.type = type;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;

        random = new Random();
    }

    public ItemStack getRandomAmountItem() {

        return new ItemStack(type, random.nextInt((maxAmount - minAmount) + 1) + minAmount);
    }

}
