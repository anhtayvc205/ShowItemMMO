package me.n.showitemmmo.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Map;

public class HoverMMO {

    public static Component create(ItemStack item) {

        ItemMeta meta = item.getItemMeta();
        Component c = Component.empty();

        // TÊN ITEM
        Component name = meta.hasDisplayName()
                ? Component.text(meta.getDisplayName(), NamedTextColor.AQUA, TextDecoration.BOLD)
                : Component.text(item.getType().name().replace("_", " "),
                NamedTextColor.AQUA, TextDecoration.BOLD);

        c = c.append(name).append(Component.newline());

        // LORE
        if (meta.hasLore()) {
            for (String lore : meta.getLore()) {
                c = c.append(Component.text(lore, NamedTextColor.GRAY))
                        .append(Component.newline());
            }
        }

        // LINE
        c = c.append(Component.text("────────────", NamedTextColor.DARK_GRAY))
                .append(Component.newline());

        // ENCHANT
        for (Map.Entry<Enchantment, Integer> ench : item.getEnchantments().entrySet()) {
            c = c.append(
                    Component.text("✦ ", NamedTextColor.GREEN)
                            .append(Component.text(
                                    ench.getKey().getKey().getKey().replace("_", " ")
                                            + " " + toRoman(ench.getValue()),
                                    NamedTextColor.GREEN
                            ))
            ).append(Component.newline());
        }

        return c;
    }

    private static String toRoman(int n) {
        return switch (n) {
            case 1 -> "I";
            case 2 -> "II";
            case 3 -> "III";
            case 4 -> "IV";
            case 5 -> "V";
            default -> String.valueOf(n);
        };
    }
}
