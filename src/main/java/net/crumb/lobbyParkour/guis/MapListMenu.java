package net.crumb.lobbyParkour.guis;

import net.crumb.lobbyParkour.LobbyParkour;
import net.crumb.lobbyParkour.database.ParkoursDatabase;
import net.crumb.lobbyParkour.database.Query;
import net.crumb.lobbyParkour.utils.ItemMaker;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MapListMenu {
    private static final LegacyComponentSerializer lcs = LegacyComponentSerializer.legacyAmpersand();
    private static final LobbyParkour plugin = LobbyParkour.getInstance();

    public static void openMenu(Player player) {
        if (!player.hasPermission("lpk.admin")) return;
        Inventory gui = Bukkit.createInventory(null, 9 * 6, lcs.deserialize("&a&lParkour List"));
        List<String> emptyLore = new ArrayList<>();

        ItemStack background = ItemMaker.createItem("minecraft:lime_stained_glass_pane", 1, "", emptyLore);
        ItemStack backArrow = ItemMaker.createItem("minecraft:arrow", 1, "&aBack", List.of("&7Previous page"));
        ItemStack closeButton = ItemMaker.createItem("minecraft:barrier", 1, "&cClose", emptyLore);

        int size = gui.getSize();

        for (int slot = 0; slot < size; slot++) {
            int row = slot / 9;
            int col = slot % 9;

            boolean isTopOrBottom = row == 0 || row == 5;
            boolean isLeftOrRight = col == 0 || col == 8;

            if (isTopOrBottom || isLeftOrRight) {
                gui.setItem(slot, background);
            }
        }

        gui.setItem(48, backArrow);
        gui.setItem(49, closeButton);

        try {
            Query query = new Query(plugin.getParkoursDatabase().getConnection());
            List<String> maps = query.parkourMaps();


            if (maps.isEmpty()) {
                player.openInventory(gui);
                return;
            }

            int[] contentSlots = {
                    10, 11, 12, 13, 14, 15, 16,
                    19, 20, 21, 22, 23, 24, 25,
                    28, 29, 30, 31, 32, 33, 34,
                    37, 38, 39, 40, 41, 42, 43
            };

            int index = 0;
            for (String map : maps) {
                if (index >= contentSlots.length) break;

                ItemStack mapItem = ItemMaker.createItem("minecraft:grass_block", 1, "&a" + map, List.of("&eClick to manage!"));
                gui.setItem(contentSlots[index], mapItem);
                index++;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        player.openInventory(gui);
    }
}
