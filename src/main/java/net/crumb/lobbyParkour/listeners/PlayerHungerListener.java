package net.crumb.lobbyParkour.listeners;

import net.crumb.lobbyParkour.systems.ParkourSessionManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class PlayerHungerListener implements Listener {
    @EventHandler
    public void onPlayerHungerChange(FoodLevelChangeEvent event) {
        if (ParkourSessionManager.isInSession(event.getEntity().getUniqueId())) {
            event.setCancelled(true);
        }
    }
}
