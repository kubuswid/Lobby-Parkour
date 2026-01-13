package net.crumb.lobbyParkour.listeners;

import net.crumb.lobbyParkour.systems.ParkourSessionManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.List;

public class PlayerDamageListener implements Listener {
    private final List<EntityDamageEvent.DamageCause> ignoredCauses= List.of(
            EntityDamageEvent.DamageCause.KILL,
            EntityDamageEvent.DamageCause.VOID,
            EntityDamageEvent.DamageCause.CUSTOM,
            EntityDamageEvent.DamageCause.WORLD_BORDER
    );

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (ParkourSessionManager.isInSession(player.getUniqueId())) {
                if (isIgnoredCause(event.getCause())) return;
                event.setCancelled(true);
            }
        }
    }

    private boolean isIgnoredCause(EntityDamageEvent.DamageCause cause) {
        for (EntityDamageEvent.DamageCause ignoredCause : ignoredCauses) {
            if (cause == ignoredCause) {
                return true;
            }
        }
        return false;
    }
}
