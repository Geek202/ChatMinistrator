package me.geek.tom.chatministrator.events;

import com.google.common.collect.Lists;
import me.geek.tom.chatministrator.ChatMinistratorMain;
import me.geek.tom.chatministrator.util.Util;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.*;

public class EventListener implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (Util.capsScore(event.getMessage()) > ChatMinistratorMain.plugin.config.getInt("capsScoreMax")) {
            this.cancelPlayerChatMessage(event, "No caps in chat please.");
            Util.alertOps(ChatColor.WHITE + event.getPlayer().getDisplayName() + ChatColor.RED + " tried to post a chat message with too many caps!");
        }

        if (ChatMinistratorMain.plugin.config.getBoolean("blacklistEnable")) {
            List<String> blacklist = ChatMinistratorMain.plugin.config.getStringList("blacklist");
            for (int i = 0; i < blacklist.size(); i++) {
                ChatMinistratorMain.plugin.getLOGGER().info(blacklist.get(i));
                if (event.getMessage().contains(blacklist.get(i))) {
                    this.cancelPlayerChatMessage(event, "Your message contains blacklisted words.");
                    if (ChatMinistratorMain.plugin.config.getBoolean("alertOpsToBlacklist")) {
                        Util.alertOps(ChatColor.WHITE + event.getPlayer().getDisplayName() + ChatColor.RED + " tried to post a blacklisted word to chat!");
                    }
                }

            }
        }
    }

    public static void cancelPlayerChatMessage(AsyncPlayerChatEvent event, String reason) {
        event.setCancelled(true);
        event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(reason).color(ChatColor.RED).create());
        event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 0.7f);
    }
}
