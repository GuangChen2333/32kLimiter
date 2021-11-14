package cn.guangchen233.limiter32k;

import cn.guangchen233.limiter32k.events.EventListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class LimiterMain extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
        this.getLogger().info("Thanks for using this plugin");
        this.getLogger().info("Current version: " + this.getDescription().getVersion());
    }
}
