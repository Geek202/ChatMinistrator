package me.geek.tom.chatministrator.util;

import me.geek.tom.chatministrator.ChatMinistratorMain;

import org.bukkit.OfflinePlayer;


import java.util.Iterator;
import java.util.Set;

public class Util {

    public static int capsScore(String data) {
        int capsCount = 0;
        int lowerCount = 0;
        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);
            if (Character.isUpperCase(c)) {
                capsCount++;
            } else if (Character.isLowerCase(c)) {
                lowerCount++;
            }
        }

        int score;

        if (lowerCount == 0) {
            score = 10000;
        } else {
            try {
                score = capsCount / lowerCount;
            } catch (ArithmeticException e) {
                score = 10000;
            }
        }
        return score;
    }

    public static void alertOps(String issue) {
        Set<OfflinePlayer> ops = ChatMinistratorMain.plugin.getServer().getOperators();
        Iterator<OfflinePlayer> opsIter = ops.iterator();
        while (opsIter.hasNext()) {
            OfflinePlayer player = opsIter.next();
            if (player.getPlayer() != null) {
                player.getPlayer().sendMessage(issue);
            }
        }
    }
}
