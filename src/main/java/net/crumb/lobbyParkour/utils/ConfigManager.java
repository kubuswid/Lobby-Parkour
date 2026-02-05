package net.crumb.lobbyParkour.utils;

import java.util.List;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {
    private static FileConfiguration config;

    public static void loadConfig(FileConfiguration config) {
        ConfigManager.config = config;
    }

    public static Format getFormat() {
        return new Format();
    }

    public static Settings getSettings() {
        return new Settings();
    }

    public static Messages getMessages() {
        return new Messages();
    }

    public static Sounds getSounds() {
        return new Sounds();
    }

    public static class Format {
        private final String path = "formatting.";

        public String getStartPlate() {
            return config.getString("formatting.start-plate", "&a⚑ &f%parkour_name%");
        }

        public String getEndPlate() {
            return config.getString("formatting.end-plate", "&c⚑ &f%parkour_name%");
        }

        public String getCheckpointPlate() {
            return config.getString("formatting.checkpoint-plate");
        }

        public String getTimer() {
            return config.getString("formatting.timer", "%m%:%s%:%ms%");
        }

        public String getStartMessage() {
            return config.getString("formatting.start-message");
        }

        public String getCancelMessage() {
            return config.getString("formatting.cancel-message");
        }

        public String getEndMessage() {
            return config.getString("formatting.end-message");
        }

        public String getResetMessage() {
            return config.getString("formatting.reset-message");
        }

        public String getTpMessage() {
            return config.getString("formatting.tp-message");
        }

        public String getCheckpointMessage() {
            return config.getString("formatting.checkpoint-message");
        }

        public String getActionBar() {
            return config.getString("formatting.action-bar", "&b%timer% &3⌚   &8|   &a&a%checkpoint%&7/%checkpoint_total% &a⚑");
        }

        public String getCheckpointSkipMessage() {
            return config.getString(path + "checkpoint-skip-message");
        }

        public Leaderboard getLeaderboard() {
            return new Leaderboard("formatting.leaderboard.");
        }

        public static class Leaderboard {
            private final String path;

            public Leaderboard(String basePath) {
                this.path = basePath;
            }

            public String getTitle() {
                return config.getString(this.path + "title");
            }

            public int getMaximumDisplayed() {
                return config.getInt(this.path + "maximum-displayed", 10);
            }

            public boolean isPersonalBestEnabled() {
                return config.getBoolean(this.path + "personal-best-enabled", false);
            }

            public String getDefaultLineStyle() {
                return config.getString(this.path + "default-line-style");
            }

            public String getPersonalBestStyle() {
                return config.getString(this.path + "personal-best-style");
            }

            public String getEmptyLineStyle() {
                return config.getString(this.path + "empty-line-style");
            }

            public List<String> getLines() {
                return config.getStringList(this.path + "lines");
            }

            public DisplayItem getDisplayItem() {
                return new DisplayItem(this.path + "display-item.");
            }

            public static class DisplayItem {
                private final String path;

                public DisplayItem(String basePath) {
                    this.path = basePath;
                }

                public boolean isEnabled() {
                    return config.getBoolean(this.path + "enabled", true);
                }

                public Material getItem() {
                    String itemName = config.getString(this.path + "item", "minecraft:stone")
                            .replace("minecraft:", "")
                            .toUpperCase();

                    Material material = Material.matchMaterial(itemName);

                    // Fallback if the material doesn't exist
                    return (material != null) ? material : Material.STONE;
                }

                public boolean hasEnchantGlint() {
                    return config.getBoolean(this.path + "enchant-glint", true);
                }

                public boolean isSpinEnabled() {
                    return config.getBoolean(this.path + "spin-enabled", false);
                }
            }
        }
    }

    public static class Settings {
        public int getLeaderboardUpdateRate() {
            return config.getInt("settings.leaderboard-update", 1);
        }

        public int getLeaderboardQueryRate() {
            return config.getInt("settings.leaderboard-query-update", 1);
        }
    }

    public static class Messages {
        public String getPrefixInfo() {
            return config.getString("messages.prefix-info", "&9ⓘ ");
        }

        public String getPrefixWarning() {
            return config.getString("messages.prefix-warning", "&e⚠ ");
        }

        public String getPrefixError() {
            return config.getString("messages.prefix-error", "&c☒ ");
        }

        public String getPrefixDebug() {
            return config.getString("messages.prefix-debug", "&d? ");
        }

        public String getColorInfo() {
            return config.getString("messages.color-info", "&a");
        }

        public String getColorWarning() {
            return config.getString("messages.color-warning", "&e");
        }

        public String getColorError() {
            return config.getString("messages.color-error", "&c");
        }

        public String getColorDebug() {
            return config.getString("messages.color-debug", "&f");
        }
    }

    public static class Sounds {
        public String getParkourStart() {
            return config.getString("sounds.parkour-start", "BLOCK_NOTE_BLOCK_PLING");
        }

        public String getParkourEnd() {
            return config.getString("sounds.parkour-end", "BLOCK_NOTE_BLOCK_PLING");
        }

        public String getCheckpoint() {
            return config.getString("sounds.checkpoint", "BLOCK_NOTE_BLOCK_PLING");
        }

        public String getError() {
            return config.getString("sounds.error", "BLOCK_ANVIL_LAND");
        }

        public String getClick() {
            return config.getString("sounds.click", "BLOCK_NOTE_BLOCK_PLING");
        }

        public String getFinish1() {
            return config.getString("sounds.finish-1", "BLOCK_NOTE_BLOCK_PLING");
        }

        public String getFinish2() {
            return config.getString("sounds.finish-2", "BLOCK_NOTE_BLOCK_PLING");
        }

        public String getFinish3() {
            return config.getString("sounds.finish-3", "BLOCK_NOTE_BLOCK_PLING");
        }

        public String getReset() {
            return config.getString("sounds.reset", "BLOCK_NOTE_BLOCK_PLING");
        }

        public String getCancel() {
            return config.getString("sounds.cancel", "ENTITY_ENDERMAN_TELEPORT");
        }

        public String getChestOpen() {
            return config.getString("sounds.chest-open", "BLOCK_CHEST_OPEN");
        }

        public String getLevelUp() {
            return config.getString("sounds.level-up", "ENTITY_PLAYER_LEVELUP");
        }
    }
}
