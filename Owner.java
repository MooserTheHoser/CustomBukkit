package moosercoding.hungrycraftcustom.admin;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Owner implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        String isPlayerOwner = "%luckperms_in_group_owner%"; //  (%luckperms_in_group_<groupname>% will return 'yes' or 'no' in string text format
        isPlayerOwner = PlaceholderAPI.setPlaceholders(p, isPlayerOwner); //convert placeholder to result text

        if (p.hasPermission("hungrycraftcustom.owner")) {
            p.sendMessage(isPlayerOwner); //debugging
            if (isPlayerOwner.equals("yes")) {
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " parent remove owner");
                p.sendMessage(ChatColor.GOLD + "Removing owner privledges");
            } else {
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " parent add owner");
                p.sendMessage(ChatColor.GREEN + "Giving owner privledges");
            }
        } else {
            p.sendMessage(ChatColor.RED + "You do not have permission!");
        }

        return true;
    }
} 

//NOTES:
//Don't forget to implement PlaceholderAPI repositories and dependencies into your project pom.xml, add [PlaceholderAPI] as a soft depend in your plugin.yml & then add the following code to your onEnable method (uncommented):

//        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
//            getLogger().info("PlaceholderAPI found! Registering all related commands...");
//            getCommand("owner").setExecutor(new Owner());
//        } else {
//            getLogger().severe("Could not find PlaceholderAPI! Some features will not work...");
//        }
