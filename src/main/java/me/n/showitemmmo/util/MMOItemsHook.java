package me.n.showitemmmo.util;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Method;

public class MMOItemsHook {

    public static boolean isPresent() {
        return Bukkit.getPluginManager().getPlugin("MMOItems") != null;
    }

    public static Component create(ItemStack item) {
        try {
            Class<?> mmoItems = Class.forName("net.Indyuce.mmoitems.MMOItems");
            Object plugin = mmoItems.getField("plugin").get(null);

            Method getMMOItem = plugin.getClass()
                    .getMethod("getMMOItem", ItemStack.class);

            Object mmoItem = getMMOItem.invoke(plugin, item);
            if (mmoItem == null) return null;

            Method getName = mmoItem.getClass().getMethod("getName");
            String name = (String) getName.invoke(mmoItem);

            Method getLore = mmoItem.getClass().getMethod("getLore");
            Iterable<String> lore = (Iterable<String>) getLore.invoke(mmoItem);

            Component c = Component.text(name).append(Component.newline());
            for (String l : lore) {
                c = c.append(Component.text(l)).append(Component.newline());
            }

            return c;
        } catch (Exception e) {
            return null;
        }
    }
}
