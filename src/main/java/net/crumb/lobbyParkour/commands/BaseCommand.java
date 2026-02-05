package net.crumb.lobbyParkour.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.crumb.lobbyParkour.LobbyParkour;
import net.crumb.lobbyParkour.guis.LeaderboardMenu;
import net.crumb.lobbyParkour.guis.MainMenu;
import net.crumb.lobbyParkour.systems.LeaderboardManager;
import net.crumb.lobbyParkour.systems.LeaderboardUpdater;
import net.crumb.lobbyParkour.utils.MMUtils;
import net.crumb.lobbyParkour.utils.MessageType;
import net.crumb.lobbyParkour.utils.ReloadParkour;
import net.crumb.lobbyParkour.utils.ConfigManager;
import net.crumb.lobbyParkour.utils.SoundUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class BaseCommand {
    private final LobbyParkour plugin = LobbyParkour.getInstance();
    private static final LegacyComponentSerializer lcs = LegacyComponentSerializer.legacyAmpersand();
    private static final LeaderboardUpdater updater = LeaderboardUpdater.getInstance();
    private static final int CENTER_PX = 130;

    LiteralArgumentBuilder<CommandSourceStack> baseCommand = Commands.literal("lpk")
            .requires(source -> source.getSender().hasPermission("ptz.admin"))
            .executes(ctx -> {
                CommandSender sender = ctx.getSource().getSender();
                if (sender instanceof Player player) {
                    MainMenu.openMenu(player);
                }
                return Command.SINGLE_SUCCESS;
            })
            .then(Commands.literal("help")
                    .executes(ctx -> {
                        CommandSender sender = ctx.getSource().getSender();
                        sender.sendMessage(lcs.deserialize("&b/lpk &7- Opens the main menu"));
                        sender.sendMessage(lcs.deserialize("&b/lpk credits &7- Shows a credits message"));
                        sender.sendMessage(lcs.deserialize("&b/lpk help &7- Shows a list of available commands"));
                        return Command.SINGLE_SUCCESS;
                    })
            )
            .then(Commands.literal("credits")
                    .executes(ctx -> {
                        CommandSender sender = ctx.getSource().getSender();
                        if (sender instanceof Player player) {
                            sendCredits(player);
                        }
                        return Command.SINGLE_SUCCESS;
                    })
            )
            .then(Commands.literal("leaderboards")
                    .executes(ctx -> {
                        CommandSender sender = ctx.getSource().getSender();
                        if (sender instanceof Player player) {
                            MMUtils.sendMessage(player, "This feature is currently unavailable", MessageType.ERROR);
                            try {
                                Sound sound = Sound.valueOf(ConfigManager.getSounds().getError());
                                SoundUtils.playSoundSequence(player, sound, 1.0f, 1.1f, 0);
                            } catch (IllegalArgumentException ignored) {
                                SoundUtils.playSoundSequence(player, Sound.BLOCK_ANVIL_LAND, 1.0f, 1.1f, 0);
                            }
                            // LeaderboardMenu.openMenu(player);
                        }
                        return Command.SINGLE_SUCCESS;
                    })
            )
            .then(Commands.literal("cache")
                    .executes(ctx -> {
                        ctx.getSource().getSender().sendMessage(updater.getCache().toString());
                        return 1;
                    })
            )
            .then(Commands.literal("test")
                    .then(Commands.argument("message", StringArgumentType.greedyString())
                            .executes(ctx -> {
                                String msg = StringArgumentType.getString(ctx, "message");
                                CommandSender sender = ctx.getSource().getSender();
                                if (sender instanceof Player player) {
                                    MMUtils.sendMessage(player, msg, MessageType.NONE);
                                }
                                return 1;
                            })
                    )
            )
            .then(Commands.literal("reload")
                    .executes(ctx -> {
                        CommandSender sender = ctx.getSource().getSender();
                        if (sender instanceof Player player) {
                            ReloadParkour.reload(player);
                        }
                        return 1;
                    })
            );



    LiteralCommandNode<CommandSourceStack> buildCommand = baseCommand.build();

    public LiteralCommandNode<CommandSourceStack> getBuildCommand() {
        return buildCommand;
    }
        
    private void sendCredits(Player player) {
        List<String> lines = List.of(
                "&d&m                                                        ",
                "    &d⭐ &l&dʟᴏʙʙʏ ᴘᴀʀᴋᴏᴜʀ &r&d⭐",
                "",
                "    &7Brought to you by:  &6crumb",
                "    &7Developed by: Kalbskinder &7& ZetMine",
                "",
                "    &8» &7Join us at &8«",
                "        &8→ &bhttps://discord.gg/8xQXBbCa8R",
                "&d&m                                                        "
        );


        lines.forEach(line -> {
            MMUtils.sendMessage(player, line);
        });

        try {
            Sound sound = Sound.valueOf(ConfigManager.getSounds().getLevelUp());
            SoundUtils.playSoundSequence(player, sound, 0.7f, 1f, 0);
            SoundUtils.playSoundSequence(player, sound, 0.7f, 1.1f, 3);
            SoundUtils.playSoundSequence(player, sound, 0.7f, 1.2f, 6);
            SoundUtils.playSoundSequence(player, sound, 0.7f, 1.3f, 9);
            SoundUtils.playSoundSequence(player, sound, 0.7f, 1.4f, 12);
        } catch (IllegalArgumentException ignored) {
            SoundUtils.playSoundSequence(player, Sound.ENTITY_PLAYER_LEVELUP, 0.7f, 1f, 0);
            SoundUtils.playSoundSequence(player, Sound.ENTITY_PLAYER_LEVELUP, 0.7f, 1.1f, 3);
            SoundUtils.playSoundSequence(player, Sound.ENTITY_PLAYER_LEVELUP, 0.7f, 1.2f, 6);
            SoundUtils.playSoundSequence(player, Sound.ENTITY_PLAYER_LEVELUP, 0.7f, 1.3f, 9);
            SoundUtils.playSoundSequence(player, Sound.ENTITY_PLAYER_LEVELUP, 0.7f, 1.4f, 12);
        }
    }
}
