package me.refriz;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import me.refriz.achievements.AchievementClickEvents;
import me.refriz.coins.CoinHandler;
import me.refriz.commands.*;
import me.refriz.events.AdminColors;
import me.refriz.events.JoinEvent;
import me.refriz.events.QuitEvent;
import me.refriz.lobby.LobbyMetadata;
import me.refriz.lobby.activities.Booster;
import me.refriz.lobby.activities.eastereggs.Eastereggs;
import me.refriz.lobby.activities.Trampoline;
import me.refriz.lobby.menu.game.GameClickEvents;
import me.refriz.lobby.LobbyHandler;
import me.refriz.lobby.menu.profile.ProfileClickEvents;
import me.refriz.lobby.menu.selector.SelectorClickEvents;
import me.refriz.lobby.menu.shop.ShopClickEvents;
import me.refriz.lobby.menu.shop.gadgets.PaintballGun;
import me.refriz.lobby.menu.shop.gadgets.SugarRush;
import me.refriz.minigames.king.KingGraceperiod;
import me.refriz.minigames.king.KingPregame;
import me.refriz.minigames.midstforth.*;
import me.refriz.minigames.midstforth.menu.PanelEvents;
import me.refriz.minigames.midstforth.npcmanager.NPCHandler;
import me.refriz.minigames.pvparena.PVPArena;
import me.refriz.minigames.teampvp.Pregame;
import me.refriz.minigames.teampvp.TeamPVP;
import me.refriz.ranks.*;
import me.refriz.server.ColorSigns;
import me.refriz.utils.*;
import me.refriz.warps.WarpLoginEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;

public class Inferris extends JavaPlugin {

    public static Inferris instance;

    public void onEnable() {

        Initializer.init();

        getCommand("leave").setExecutor(new CommandLeave());
    }

    public void onDisable() {
        this.getLogger().info("Disabled");
        Initializer.shutdown();
    }

    public static Inferris getInstance() {
        return instance;
    }
}