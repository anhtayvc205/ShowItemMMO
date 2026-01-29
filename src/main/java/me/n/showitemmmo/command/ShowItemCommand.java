package me.n.showitemmmo.command;

import me.n.showitemmmo.util.HoverMMO;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ShowItemCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player p)) return true;

        ItemStack item = p.getInventory().getItemInMainHand();
        if (item.getType().isAir()) {
            p.sendMessage(Component.text("§cBạn không cầm item nào!"));
            return true;
        }

        Component msg = Component.text("§e" + p.getName() + " §fđã show item: ")
                .append(Component.text("§b[ITEM]")
                        .hoverEvent(HoverEvent.showText(HoverMMO.create(item))));

        p.getServer().broadcast(msg);
        return true;
    }
}
