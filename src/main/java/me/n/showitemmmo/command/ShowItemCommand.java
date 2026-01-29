package me.n.showitemmmo.command;

import me.n.showitemmmo.util.HoverMMO;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
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
            p.sendMessage(Component.text("Bạn không cầm item nào!", NamedTextColor.RED));
            return true;
        }

        String name = item.hasItemMeta() && item.getItemMeta().hasDisplayName()
                ? item.getItemMeta().getDisplayName()
                : item.getType().name().replace("_", " ");

        Component itemComponent = Component.text(name, NamedTextColor.AQUA)
                .hoverEvent(HoverEvent.showText(HoverMMO.create(item)));

        Component msg = Component.text(p.getName() + " đã show item: ", NamedTextColor.YELLOW)
                .append(itemComponent);

        p.getServer().broadcast(msg);
        return true;
    }
}
