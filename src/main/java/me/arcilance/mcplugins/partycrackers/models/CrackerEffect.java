package me.arcilance.mcplugins.partycrackers.models;

import org.bukkit.Color;
import org.bukkit.Particle;

public class CrackerEffect {

    private Color color;
    private int count;
    private int offsetX;
    private int offsetY;
    private int offsetZ;
    private int speed;

    public CrackerEffect(Color color, int count, int offsetX, int offsetY, int offsetZ, int speed) {

        this.color = color;
        this.count = count;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
        this.speed = speed;
    }

    public Particle getParticle() {

        return Particle.REDSTONE;
    }

    public Particle.DustOptions getDust() {

        return new Particle.DustOptions(color, 1);
    }

    public int getCount() {

        return count;
    }

    public int getOffsetX() {

        return offsetX;
    }

    public int getOffsetY() {

        return offsetY;
    }

    public int getOffsetZ() {

        return offsetZ;
    }

    public int getSpeed() {

        return speed;
    }
}
