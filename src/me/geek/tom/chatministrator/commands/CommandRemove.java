package me.geek.tom.chatministrator.commands;

import me.geek.tom.chatministrator.ChatMinistratorMain;
import me.geek.tom.chatministrator.util.Util;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandRemove implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length < 1) {
                return false;
            }

            ChatMinistratorMain.plugin.config.set("blacklist", Util.removeAllFromList(ChatMinistratorMain.plugin.config.getStringList("blacklist"), args[0]));
            ChatMinistratorMain.plugin.saveConfig();

            sender.sendMessage(ChatColor.YELLOW + "Removed " + ChatColor.WHITE + "\"" + args[0] + "\"" + ChatColor.YELLOW + " from the ChatMinistrator blacklist!");
        } else {
            sender.sendMessage(ChatColor.RED + "At this time, this command cannot be run from the server console");
        }
        return true;
    }
}
