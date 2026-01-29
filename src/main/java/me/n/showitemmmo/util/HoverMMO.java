package me.n.showitemmmo.util;

import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.item.learn.MMOItem;
import net.Indyuce.mmoitems.api.Type;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.inventory.ItemStack;

public class HoverMMO {

    public static Component create(ItemStack item) {

        // Nếu là MMOItem
        MMOItem mmo = MMOItems.plugin.getMMOItem(item);
        if (mmo != null) {
            return fromMMOItem(mmo);
        }

        // Item thường
        return fromVanilla(item);
    }

    private static Component fromMMOItem(MMOItem item) {
        Component c = Component.empty();

        c = c.append(Component.text(item.getName(),
                NamedTextColor.AQUA, TextDecoration.BOLD))
                .append(Component.newline());

        item.getLore().forEach(lore ->
                c = c.append(Component.text(lore, NamedTextColor.GRAY))
                        .append(Component.newline())
        );

        return c;
    }

    private static Component fromVanilla(ItemStack item) {
        return Component.text(item.getType().name(), NamedTextColor.GRAY);
    }
}
