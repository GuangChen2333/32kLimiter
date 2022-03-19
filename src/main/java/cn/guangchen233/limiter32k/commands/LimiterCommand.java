package cn.guangchen233.limiter32k.commands;

import cn.guangchen233.limiter32k.LimiterMain;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class LimiterCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(
                    ChatColor.GOLD + "/32klimiter reload | " + ChatColor.BOLD + "Reload configuration files."
            );
            sender.sendMessage(
                    ChatColor.GOLD + "/32klimiter enable | " + ChatColor.BOLD + "Temporarily enable the plugin."
            );
            sender.sendMessage(
                    ChatColor.GOLD + "/32klimiter disable | " + ChatColor.BOLD + "Temporarily disable the plugin."
            );
            sender.sendMessage(
                    ChatColor.GOLD + "/32klimiter status | " + ChatColor.BOLD + "Get the current status of the plugin."
            );
            return true;
        }
        switch (args[0]) {
            case "reload" :
                sender.sendMessage(ChatColor.GREEN + "Reloading...");
                LimiterMain.getInstance().reload();
                sender.sendMessage(ChatColor.GREEN + "Loading completed");
                break;
            case "enable":
                LimiterMain.isEnabled = true;
                sender.sendMessage(ChatColor.GREEN + "Plugin is enabled.");
                break;
            case "disable":
                LimiterMain.isEnabled = false;
                sender.sendMessage(ChatColor.GREEN + "Plugin is disabled.");
                break;
            case "status":
                String status;
                if (LimiterMain.isEnabled) {
                    status = ChatColor.GREEN + "Enabled";
                } else {
                    status = ChatColor.RED + "Disabled";
                }
                sender.sendMessage(ChatColor.GOLD + "The current status of the plugin is: " + status);
                break;
            default:
                sender.sendMessage(ChatColor.RED + "Unknown method");
                return false;
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> commands = Arrays.asList("reload", "enable", "disable", "status");
        if (args.length == 1) {
            List<String> matchedCommands = new ArrayList<>();
            for (String matchCommand: commands) {
                String pattern = "^" + args[0] + ".*";
                if (Pattern.matches(pattern, matchCommand)) {
                    matchedCommands.add(matchCommand);
                }
            }
            if (matchedCommands.isEmpty()) {
                matchedCommands = commands;
            }
            return matchedCommands;
        } else {
            return null;
        }
    }
}
