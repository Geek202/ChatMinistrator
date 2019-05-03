package me.geek.tom.chatministrator;

import com.google.common.collect.Lists;
import me.geek.tom.chatministrator.commands.CommandAdd;
import me.geek.tom.chatministrator.commands.CommandRemove;
import me.geek.tom.chatministrator.events.EventListener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.logging.Logger;

public class ChatMinistratorMain extends JavaPlugin {

    public Logger getLOGGER() {
        return LOGGER;
    }

    private Logger LOGGER;
    public FileConfiguration config;
    public static ChatMinistratorMain plugin;

    public void onEnable() {
        LOGGER = getLogger();
        LOGGER.info("ChatMinistrator ready!");
        config = this.getConfig();

        List<String> defaultBlacklist = Lists.newArrayList();

        plugin = this;

        config.addDefault("checkCaps", true);
        config.addDefault("capsScoreMax", 1);
        config.addDefault("blacklistEnable", false);
        config.addDefault("blacklist", defaultBlacklist);
        config.addDefault("alertOpsToCaps", true);
        config.addDefault("alertOpsToBlacklist", true);

        config.options().copyDefaults(true);
        saveConfig();

        getServer().getPluginManager().registerEvents(new EventListener(), this);
        this.getCommand("cmblacklistadd").setExecutor(new CommandAdd());
        this.getCommand("cmblacklistremove").setExecutor(new CommandRemove());

    }

    public void onDisable() {
        LOGGER.info("So long, and thanks for all the fish! :D");
    }

}
