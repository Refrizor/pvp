package me.refriz.commands;

import me.refriz.minigames.pvparena.PVPArena;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandLeave implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        Player player = (Player) sender;

        if(PVPArena.getIngame().contains(player.getName())){
            PVPArena.exitArena(player);
        }
        return true;
    }
}
