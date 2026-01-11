package net.crumb.lobbyParkour.utils;

import net.crumb.lobbyParkour.LobbyParkour;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundUtils {
    private static final LobbyParkour plugin = LobbyParkour.getInstance();

    public static void playSoundSequence(Player player, Sound sound, float volume, float pitch, long delayTicks) {
        if (delayTicks <= 0) {
            SchedulerUtils.runTask(plugin, player, () -> {
                player.playSound(player.getLocation(), sound, volume, pitch);
            });
            return;
        }

        SchedulerUtils.runTaskLater(plugin, player, () -> {
            player.playSound(player.getLocation(), sound, volume, pitch);
        }, delayTicks);
    }
}
