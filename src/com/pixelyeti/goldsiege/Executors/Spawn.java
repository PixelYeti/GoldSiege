package com.pixelyeti.goldsiege.Executors;

import com.pixelyeti.goldsiege.Main;
import com.pixelyeti.goldsiege.Util.ServerUtils;
import com.pixelyeti.goldsiege.Util.StringUtilities;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

/**
 * Created by Callum on 09/01/2016.
 */
public class Spawn implements CommandExecutor {
    public Spawn(Main plugin) {
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(StringUtilities.errorMessage);
            return false;
        }
        Player p = (Player) commandSender;
        if (!p.hasPermission("goldsiege.player.spawn") || !p.hasPermission("goldsiege.player.*")) {
            p.sendMessage(StringUtilities.noPermission);
            return false;
        }
        if(args.length > 0) {
            p.sendMessage(StringUtilities.invalidArguments);
            return false;
        }

        ConfigurationSection serverSection = Main.getInstance().getConfig().getConfigurationSection("Server");
        if (!serverSection.contains("Spawn") || serverSection != null) {
            p.teleport(ServerUtils.getServerSpawn());
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("Teleported to spawn"));
            p.sendMessage(StringUtilities.prefix + ChatColor.GREEN + "Teleported to spawn!");
        } else {
            p.sendMessage(StringUtilities.errorMessage);
        }

        return false;
    }
}
