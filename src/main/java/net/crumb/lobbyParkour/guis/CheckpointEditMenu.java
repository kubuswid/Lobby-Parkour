package net.crumb.lobbyParkour.guis;

import net.crumb.lobbyParkour.LobbyParkour;
import net.crumb.lobbyParkour.database.ParkoursDatabase;
import net.crumb.lobbyParkour.database.Query;
import net.crumb.lobbyParkour.utils.ItemMaker;
import net.crumb.lobbyParkour.utils.LocationHelper;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CheckpointEditMenu {
    private static final LegacyComponentSerializer lcs = LegacyComponentSerializer.legacyAmpersand();
    private static final LobbyParkour plugin = LobbyParkour.getInstance();

    public static void openMenu(Player player, String parkourName, Location location) {
        if (!player.hasPermission("lpk.admin")) return;
        Inventory gui = Bukkit.createInventory(null, 9 * 3, lcs.deserialize("&9&lManage Checkpoint"));
        List<String> emptyLore = new ArrayList<>();

        ItemStack background = ItemMaker.createItem("minecraft:blue_stained_glass_pane", 1, "", emptyLore);
        ItemStack backArrow = ItemMaker.createItem("minecraft:arrow", 1, "&aBack", List.of("&7Previous page"));
        ItemStack closeButton = ItemMaker.createItem("minecraft:barrier", 1, "&cClose", emptyLore);
        ItemStack deleteButton = ItemMaker.createItem("minecraft:tnt", 1, "&cDelete Checkpoint", List.of("&e&lWARNING! &eAction can not be undone!", "&eClick to delete!"));
        ItemStack changeCheckpointType = ItemMaker.createItem("minecraft:light_weighted_pressure_plate", 1, "&aChange Type", List.of("&eClick to change!"));
        ItemStack relocateCheckpoint = ItemMaker.createItem("minecraft:compass", 1, "&aRelocate Checkpoint", List.of("&eClick to relocate!"));
        ItemStack secretItem = ItemMaker.createItem("minecraft:blue_stained_glass_pane", 1, "", emptyLore);

        ItemMeta meta = secretItem.getItemMeta();
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(parkourName));
        lore.add(Component.text(LocationHelper.locationToString(location)));
        meta.lore(lore);
        secretItem.setItemMeta(meta);


        int size = gui.getSize();

        for (int slot = 0; slot < size; slot++) {
            int row = slot / 9;
            int col = slot % 9;

            boolean isTopOrBottom = row == 0 || row == 2;
            boolean isLeftOrRight = col == 0 || col == 8;

            if (isTopOrBottom || isLeftOrRight) {
                gui.setItem(slot, background);
            }
        }

        gui.setItem(0, secretItem);
        gui.setItem(10, changeCheckpointType);
        gui.setItem(11, relocateCheckpoint);

        gui.setItem(21, backArrow);
        gui.setItem(22, closeButton);
        gui.setItem(26, deleteButton);

        player.openInventory(gui);
    }
}
