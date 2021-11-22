package cn.guangchen233.limiter32k;

import cn.guangchen233.limiter32k.commands.LimiterCommand;
import cn.guangchen233.limiter32k.events.EventListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class LimiterMain extends JavaPlugin {
    private final EventListener eventListener = new EventListener();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        FileConfiguration config = getConfig();
        boolean isEnabled = config.getBoolean("enabled");
        this.getLogger().info("Thanks for using this plugin");
        this.getLogger().info("Current version: " + this.getDescription().getVersion());
        this.getLogger().info("Plugin enabled: " + isEnabled);
        if (isEnabled) {
            Bukkit.getPluginManager().registerEvents(eventListener, this);
        }
        Bukkit.getPluginCommand("32klimiter").setExecutor(new LimiterCommand(eventListener));
    }
}
