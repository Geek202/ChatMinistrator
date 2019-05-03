package me.geek.tom.chatministrator.commands;

import me.geek.tom.chatministrator.ChatMinistratorMain;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandAdd implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length < 1) {
                return false;
            }

            List<String> bl = ChatMinistratorMain.plugin.config.getStringList("blacklist");
            bl.add(args[0]);

            ChatMinistratorMain.plugin.config.set("blacklist", bl);
            ChatMinistratorMain.plugin.saveConfig();

            sender.sendMessage(ChatColor.YELLOW + "Added " + ChatColor.WHITE + "\"" + args[0] + "\"" + ChatColor.YELLOW + " to the ChatMinistrator blacklist!");
        } else {
            sender.sendMessage(ChatColor.RED + "At this time, this command cannot be run from the server console");
        }

        // If the player (or console) uses our command correct, we can return true
        return true;
    }
}
