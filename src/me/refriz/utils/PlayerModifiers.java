package me.refriz.utils;

import me.refriz.Inferris;
import me.refriz.lobby.LobbyMetadata;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class PlayerModifiers {

    public static void all(Player player) {
        player.setHealth(20.0);
        player.setFoodLevel(20);
        player.setWalkSpeed(0.2F);
    }

    public void setLobby(Player player, boolean set) {
        //PlayerModifiers.all(player);

        if (set) {
            if (!Inferris.lobby.contains(player)) {
                Inferris.lobby.add(player);


                Inferris.antiFly.remove(player.getName());

                player.getInventory().clear();
                player.setAllowFlight(false);
                player.setGameMode(GameMode.ADVENTURE);
                player.setLevel(0);

                if (ServerType.getType.isServer(true, true, false)) {

                    PlayerModifiers.all(player);
                    player.getInventory().clear();
                    player.setAllowFlight(false);
                    player.setGameMode(GameMode.ADVENTURE);
                    player.setLevel(0);

                    player.getInventory().setItem(0, LobbyMetadata.getProfile());
                    player.getInventory().setItem(4, LobbyMetadata.getSelector());
                    player.getInventory().setItem(8, LobbyMetadata.getShop());
                }
                if (ServerType.getType.isServer(false, true, false)) {
                    player.getInventory().setItem(2, LobbyMetadata.getGames());
                }
            }
        } else {
            if (Inferris.lobby.contains(player)) {
                Inferris.lobby.remove(player);
                Inferris.antiFly.remove(player.getName());
                player.getInventory().clear();
            }
        }
    }

    public void removePotionEffects(Player player) {
        for (PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }
    }
}