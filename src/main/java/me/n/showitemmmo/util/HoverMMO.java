package me.n.showitemmmo.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.inventory.ItemStack;

public class HoverMMO {

    public static Component create(ItemStack item) {
        if (MMOItemsHook.isPresent()) {
            Component c = MMOItemsHook.create(item);
            if (c != null) return c;
        }

        return Component.text(item.getType().name(), NamedTextColor.GRAY);
    }
}
