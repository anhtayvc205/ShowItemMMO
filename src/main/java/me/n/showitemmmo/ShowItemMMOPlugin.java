package me.n.showitemmmo;

import me.n.showitemmmo.command.ShowItemCommand;
import me.n.showitemmmo.listener.ChatListener;
import org.bukkit.plugin.java.JavaPlugin;

public class ShowItemMMOPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("showitem").setExecutor(new ShowItemCommand());
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getLogger().info("ShowItemMMO enabled (reflection mode)");
    }
}
