package me.refriz.minigames.pvparena;

import me.refriz.Inferris;
import me.refriz.lobby.LobbyHandler;
import me.refriz.minigames.teampvp.TeamPVP;
import me.refriz.server.Locations;
import me.refriz.utils.PlayerModifiers;
import me.refriz.utils.ServerType;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.List;

public class PVPArena extends Kits implements Listener {

    private static List<String> ingame = new ArrayList<>();
    private static String prefix;

    public static List<String> getIngame() {
        return ingame;
    }

    static String getPrefix() {
        prefix = ChatColor.GREEN + "[Inferris] ";
        return prefix;
    }

    public static void enterArena(Player player) {
        if (!getIngame().contains(player.getName())) {
            new PlayerModifiers().setLobby(player, false);
            player.teleport(Coordinates.PVP.getPVPLocation());
            player.spigot().respawn();

            Inferris.lobby.remove(player);
            getIngame().add(player.getName());

            //new PVPArena().applyItems(player);

            for (Player all : Bukkit.getServer().getOnlinePlayers()) {
                if (getIngame().contains(all.getName())) {
                    all.sendMessage(getPrefix() + ChatColor.AQUA + player.getName() + " joined");
                }
            }
        }
    }

    public static void exitArena(Player player) {
        if (!Inferris.lobby.contains(player)) {
            new PlayerModifiers().setLobby(player, true);

            LobbyHandler.giveItems(player);

            PlayerModifiers.all(player);
            player.setAllowFlight(false);
            player.setGameMode(GameMode.ADVENTURE);
            player.setLevel(0);

            for (Player all : Bukkit.getServer().getOnlinePlayers()) {
                if (getIngame().contains(all.getName())) {
                    all.sendMessage(getPrefix() + ChatColor.RED + player.getName() + " left");
                }
            }

            ingame.remove(player.getName());

            if (ServerType.getType.isServer(true, false, false)) {
                player.teleport(new Location(Bukkit.getWorld("world"), 156.446, 22.06250, 220.528)); //Christmas
            } else {
                player.teleport(Locations.SPAWN.getLocation());
            }
        }
    }

    enum Coordinates {
        PVP(616, 4, 90);

        private final double x;
        private final double y;
        private final double z;

        Coordinates(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public Location getPVPLocation() {
            return new Location(Bukkit.getWorld("world"), x, y, z);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Player killer = event.getEntity().getKiller();
        if (ingame.contains(player.getName())) {

            player.setBedSpawnLocation(killer.getLocation());

            event.setKeepInventory(true);
            player.setGameMode(GameMode.SPECTATOR);
            player.getInventory().clear();

            player.teleport(killer.getLocation());
            player.spigot().respawn();
            player.teleport(killer.getLocation());

            player.getWorld().strikeLightningEffect(player.getLocation().add(0, 2, 0));

            for(Player all : Bukkit.getServer().getOnlinePlayers()){
                if(ingame.contains(all.getName())){
                    all.sendMessage(ChatColor.AQUA + killer.getName() + ChatColor.YELLOW + " killed " + player.getName());
                }
            }

            BukkitScheduler scheduler = Bukkit.getScheduler();
            scheduler.scheduleSyncDelayedTask(Inferris.getInstance(), new Runnable() {
                @Override
                public void run() {
                    player.setGameMode(GameMode.ADVENTURE);
                    player.teleport(Coordinates.PVP.getPVPLocation());
                    //applyItems(player);
                }
            }, 60L);
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event){
        Player player = event.getPlayer();

        if(ingame.contains(player.getName())){

        }
    }
}
