package me.refriz.utils;

import me.refriz.Inferris;
import me.refriz.minigames.midstforth.GatewaySpawns;
import me.refriz.minigames.midstforth.Particles;
import me.refriz.minigames.midstforth.npcmanager.NPCHandler;
import me.refriz.ranks.RankType;
import me.refriz.server.Announcements;
import me.refriz.server.HologramSpawns;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import static me.refriz.Inferris.*;

public class Initializer {

    public static void init() {

        new EventLoader().init();
        new EventLoader().cosmetics();
        new EventLoader().mistforth();

        mainScoreboard = Inferris.instance.getServer().getScoreboardManager().getMainScoreboard();

        ownerTeam = mainScoreboard.registerNewTeam("1-Owner");
        adminTeam = mainScoreboard.registerNewTeam("2-Admin");
        modTeam = mainScoreboard.registerNewTeam("3-Mod");
        coolTeam = mainScoreboard.registerNewTeam("4-Cool");
        donor2Team = mainScoreboard.registerNewTeam("5-Donor2");
        donorTeam = mainScoreboard.registerNewTeam("6-Donor");
        testerTeam = mainScoreboard.registerNewTeam("7-Tester");
        noneTeam = mainScoreboard.registerNewTeam("8-None");

        red = mainScoreboard.registerNewTeam("Red");
        blue = mainScoreboard.registerNewTeam("Blue");

        ownerTeam.setPrefix(RankType.OWNER.getPrefix() + ChatUtils.SPACER.getMessage());
        adminTeam.setPrefix(RankType.ADMIN.getPrefix() + ChatUtils.SPACER.getMessage());
        modTeam.setPrefix(RankType.MOD.getPrefix() + ChatUtils.SPACER.getMessage());
        coolTeam.setPrefix(RankType.COOL.getPrefix() + ChatUtils.SPACER.getMessage());
        donor2Team.setPrefix(RankType.DONOR2.getShortPrefix() + ChatUtils.SPACER.getMessage());
        donorTeam.setPrefix(RankType.DONOR.getShortPrefix() + ChatUtils.SPACER.getMessage());
        testerTeam.setPrefix(RankType.TESTER.getShortPrefix() + ChatUtils.SPACER.getMessage());
        noneTeam.setPrefix(ChatColor.GRAY + "");

        ownerTeam.setColor(ChatColor.YELLOW);
        adminTeam.setColor(ChatColor.YELLOW);
        modTeam.setColor(ChatColor.YELLOW);
        coolTeam.setColor(ChatColor.WHITE);
        donor2Team.setColor(ChatColor.WHITE);
        donorTeam.setColor(ChatColor.WHITE);
        testerTeam.setColor(ChatColor.WHITE);
        noneTeam.setColor(ChatColor.GRAY);

        red.setPrefix(ChatColor.RED + "");
        blue.setPrefix(ChatColor.BLUE + "");
        red.setColor(ChatColor.RED);
        blue.setColor(ChatColor.BLUE);

        new Announcements().init();
        new HologramSpawns().init();

        if(ServerID.get(ServerType.MIDSTFORTH.getPort())) {
            new GatewaySpawns().init();
            new Particles().waterDrops();
            new NPCHandler().init();
        }

        for (Team team : mainScoreboard.getTeams()) {
            team.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
        }
    }

    public static void shutdown() {
        ownerTeam.unregister();
        adminTeam.unregister();
        modTeam.unregister();
        coolTeam.unregister();
        donor2Team.unregister();
        donorTeam.unregister();
        testerTeam.unregister();
        noneTeam.unregister();
        red.unregister();
        blue.unregister();

        if (ServerType.getType.isServer(false, false, true)) {
            new NPCHandler().prune();
        }
    }

    public static void initializeType(Player player){

        if(ServerType.getType.isServer(true, false, false)) {
            Title.sendTitle(player, ChatColor.AQUA + "Inferris", ChatColor.RED + "In development", 10, 70, 20);
        }
        if(ServerType.getType.isServer(false, true, false)){
            Title.sendTitle(player, ChatColor.AQUA + "Minigames", ChatColor.RED + "In development", 10, 70, 20);
        }
    }
}