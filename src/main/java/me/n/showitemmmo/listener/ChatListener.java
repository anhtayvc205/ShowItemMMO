package me.n.showitemmmo.listener;

import io.papermc.paper.event.player.AsyncChatEvent;
import me.n.showitemmmo.util.HoverMMO;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncChatEvent e) {

        Player p = e.getPlayer();
        String msg = PlainTextComponentSerializer.plainText().serialize(e.message());

        if (!msg.contains("[i]") && !msg.contains("[item]")) return;

        ItemStack item = p.getInventory().getItemInMainHand();
        if (item.getType().isAir()) return;

        e.setCancelled(true);

        String name = item.hasItemMeta() && item.getItemMeta().hasDisplayName()
                ? item.getItemMeta().getDisplayName()
                : item.getType().name().replace("_", " ");

        Component itemComp = Component.text(name, NamedTextColor.AQUA)
                .hoverEvent(HoverEvent.showText(HoverMMO.create(item)));

        Component finalMsg = Component.text("<" + p.getName() + "> ", NamedTextColor.GRAY);

        String[] parts = msg.split("\\[i\\]|\\[item\\]", -1);
        for (int i = 0; i < parts.length; i++) {
            finalMsg = finalMsg.append(Component.text(parts[i], NamedTextColor.WHITE));
            if (i < parts.length - 1) {
                finalMsg = finalMsg.append(itemComp);
            }
        }

        p.getServer().broadcast(finalMsg);
    }
}
