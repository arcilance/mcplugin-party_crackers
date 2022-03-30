package me.arcilance.mcplugins.partycrackers.models;

import org.bukkit.Sound;

public class CrackerSound {

    private Sound sound;
    private float volume;
    private float pitch;

    public CrackerSound(Sound sound, float volume, float pitch) {

        this.sound = sound;
        this.volume = volume;
        this.pitch = pitch;
    }

    public Sound getSound() {

        return sound;
    }

    public float getPitch() {

        return pitch;
    }

    public float getVolume() {

        return volume;
    }
}
