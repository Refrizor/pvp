package me.refriz.utils;

import me.refriz.Inferris;
import me.refriz.minigames.pvparena.PVPArena;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class EventLoader {

    PluginManager pm = Bukkit.getPluginManager();

    public void init(){
        pm.registerEvents(new PVPArena(), Inferris.getInstance());
    }
}