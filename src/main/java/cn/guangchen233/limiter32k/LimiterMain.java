package cn.guangchen233.limiter32k;

import cn.guangchen233.limiter32k.commands.LimiterCommand;
import cn.guangchen233.limiter32k.events.EventListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class LimiterMain extends JavaPlugin {
    private static LimiterMain INSTANCE;
    public static boolean isEnabled;

    public static LimiterMain getInstance() {
        return INSTANCE;
    }

    public void reload() {
        reloadConfig();
        isEnabled = getConfig().getBoolean("enabled");
    }

    @Override
    public void onEnable() {
        INSTANCE = this;
        saveDefaultConfig();
        isEnabled = getConfig().getBoolean("enabled");
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
        Bukkit.getPluginCommand("32klimiter").setExecutor(new LimiterCommand());
        Bukkit.getPluginCommand("32klimiter").setTabCompleter(new LimiterCommand());
    }
}
