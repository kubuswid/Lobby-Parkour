package net.crumb.lobbyParkour.listeners;

import net.crumb.lobbyParkour.systems.ParkourSession;
import net.crumb.lobbyParkour.systems.ParkourSessionManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveListener implements Listener {
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        // Remove player from pk session if he was playing
        if (ParkourSessionManager.isInSession(player.getUniqueId())) {
            ParkourSession oldSession = ParkourSessionManager.getSession(player.getUniqueId());
            PlayerInteractListener.restoreInventory(player, oldSession);
            ParkourSessionManager.endSession(player.getUniqueId());
        }
    }
}
