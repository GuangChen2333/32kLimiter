package cn.guangchen233.limiter32k.commands;

import cn.guangchen233.limiter32k.events.EventListener;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Objects;

public class LimiterCommand implements CommandExecutor {
    private final EventListener eventListener;

    public LimiterCommand(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Too few parameters");
            return false;
        }
        if (Objects.equals(args[0], "enable")) {
            if (eventListener.isEnabled()) {
                sender.sendMessage(ChatColor.RED + "The plugin is already enabled");
            } else {
                eventListener.setEnabled(true);
                sender.sendMessage(ChatColor.GREEN + "The plugin has been enabled");
            }
        } else if (Objects.equals(args[0], "disable")) {
            if (!eventListener.isEnabled()) {
                sender.sendMessage(ChatColor.RED + "The plugin is already disabled");
            } else {
                eventListener.setEnabled(false);
                sender.sendMessage(ChatColor.GREEN + "The plugin has been disabled");
            }
        }
        return true;
    }
}
