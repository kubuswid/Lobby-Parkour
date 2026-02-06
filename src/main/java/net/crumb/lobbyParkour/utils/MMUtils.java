package net.crumb.lobbyParkour.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;

public class MMUtils {
    private static final LegacyComponentSerializer lcs = LegacyComponentSerializer.legacyAmpersand();

    // Sends a player a message with the legacy color format
    public static void sendMessage(Player player, String message) {
        Component parsed = lcs.deserialize(message);
        player.sendMessage(parsed);
    }

    public static void sendMessage(Player player, String message, MessageType messageType) {
        Component prefix;
        Component parsed;

        ConfigManager.Messages messages = ConfigManager.getMessages();

        switch (messageType) {
            case INFO:
                prefix = lcs.deserialize(messages.getPrefixInfo());
                parsed = lcs.deserialize(messages.getColorInfo() + message);
                break;
            case WARNING:
                prefix = lcs.deserialize(messages.getPrefixWarning());
                parsed = lcs.deserialize(messages.getColorWarning() + message);
                break;
            case ERROR:
                prefix = lcs.deserialize(messages.getPrefixError());
                parsed = lcs.deserialize(messages.getColorError() + message);
                break;
            case DEBUG:
                prefix = lcs.deserialize(messages.getPrefixDebug());
                parsed = lcs.deserialize(messages.getColorDebug() + message);
                break;
            default:
                player.sendMessage(lcs.deserialize(message));
                return;
        }

        player.sendMessage(prefix.append(parsed));
    }
}
