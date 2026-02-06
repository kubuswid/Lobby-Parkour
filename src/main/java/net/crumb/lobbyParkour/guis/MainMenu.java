package net.crumb.lobbyParkour.guis;

import net.crumb.lobbyParkour.utils.ConfigManager;
import net.crumb.lobbyParkour.utils.ItemMaker;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainMenu {
    private static final LegacyComponentSerializer lcs = LegacyComponentSerializer.legacyAmpersand();


    public static void openMenu(Player player) {
        if (!player.hasPermission("lpk.admin")) return;
        Inventory gui = Bukkit.createInventory(null, 9 * 3, lcs.deserialize("         &d&lʟᴏʙʙʏ ᴘᴀʀᴋᴏᴜʀ"));
        List<String> emptyLore = new ArrayList<>();

        ItemStack backgroundDark = ItemMaker.createItem("minecraft:purple_stained_glass_pane", 1, "", emptyLore);
        ItemStack backgroundNormal = ItemMaker.createItem("minecraft:magenta_stained_glass_pane", 1, "", emptyLore);
        ItemStack backgroundLight = ItemMaker.createItem("minecraft:pink_stained_glass_pane", 1, "", emptyLore);

        ItemStack parkourList = ItemMaker.createItem("minecraft:paper", 1, "&a⚑ &aParkour List", Arrays.asList("&7Manage your parkour courses", "&eClick to view!"));
        ItemStack newParkour = ItemMaker.createItem("minecraft:light_weighted_pressure_plate", 1, "&a&l+ &aCreate a new parkour", Arrays.asList("&7Setup a new parkour course", "&eClick to setup!"));
        ItemStack parkourLeaderboard = ItemMaker.createItem("minecraft:name_tag", 1, "&a✯ Parkour Leaderboards", Arrays.asList("&7Setup a parkour leaderboard", "&eClick to manage!"));
        ItemStack reloadParkours = ItemMaker.createItem("minecraft:clock", 1, "&a🔁 Reload Parkours", Arrays.asList("&7Reload all parkours on the server", "&eClick to reload!"));

        gui.setItem(0, backgroundDark);
        gui.setItem(1, backgroundDark);
        gui.setItem(2, backgroundDark);

        gui.setItem(3, backgroundNormal);
        gui.setItem(4, backgroundNormal);
        gui.setItem(5, backgroundNormal);
        gui.setItem(6, backgroundNormal);

        gui.setItem(7, backgroundLight);
        gui.setItem(8, backgroundLight);

        gui.setItem(9, backgroundDark);
        gui.setItem(17, backgroundLight);
        gui.setItem(18, backgroundDark);
        gui.setItem(19, backgroundDark);

        gui.setItem(20, backgroundNormal);
        gui.setItem(21, backgroundNormal);
        gui.setItem(22, backgroundNormal);
        gui.setItem(23, backgroundNormal);

        gui.setItem(24, backgroundLight);
        gui.setItem(25, backgroundLight);
        gui.setItem(26, backgroundLight);

        gui.setItem(10, newParkour);
        gui.setItem(11, parkourList);
        gui.setItem(12, parkourLeaderboard);
        gui.setItem(16, reloadParkours);

        player.openInventory(gui);
        try {
            Sound sound = Sound.valueOf(ConfigManager.getSounds().getChestOpen());
            player.playSound(player.getLocation(), sound, 0.8f, 1.0f);
        } catch (IllegalArgumentException ignored) {
            player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 0.8f, 1.0f);
        }
    }
}
