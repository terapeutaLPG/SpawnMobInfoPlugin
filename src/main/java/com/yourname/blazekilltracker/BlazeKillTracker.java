package com.yourname.blazekilltracker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class BlazeKillTracker extends JavaPlugin implements Listener, TabCompleter {

    private File dataFolder;
    private File blazeKillsFile;
    private File alertsConfigFile;
    private File spawnHistoryFile;
    private DateTimeFormatter dateFormat;
    private final Map<UUID, Boolean> playerAlerts = new HashMap<>();
    private final Map<UUID, SpawnInfo> mobSpawnInfo = new HashMap<>();

    @Override
    public void onEnable() {
        // Initialize plugin
        this.dataFolder = getDataFolder();
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }

        this.blazeKillsFile = new File(dataFolder, "blaze_kills.txt");
        if (!blazeKillsFile.exists()) {
            try {
                blazeKillsFile.createNewFile();
            } catch (IOException e) {
                getLogger().severe("Could not create blaze kills file!");
                e.printStackTrace();
            }
        }

        this.alertsConfigFile = new File(dataFolder, "alerts_config.txt");
        if (!alertsConfigFile.exists()) {
            try {
                alertsConfigFile.createNewFile();
            } catch (IOException e) {
                getLogger().severe("Could not create alerts config file!");
                e.printStackTrace();
            }
        }

        this.spawnHistoryFile = new File(dataFolder, "spawn_history.txt");
        if (!spawnHistoryFile.exists()) {
            try {
                spawnHistoryFile.createNewFile();
            } catch (IOException e) {
                getLogger().severe("Could not create spawn history file!");
                e.printStackTrace();
            }
        }

        // Load alerts configuration
        loadAlertsConfig();

        this.dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        // Register events
        getServer().getPluginManager().registerEvents(this, this);

        // Register tab completers
        getCommand("blazekill").setTabCompleter(this);
        getCommand("blazekills").setTabCompleter(this);

        getLogger().info("BlazeKillTracker has been enabled!");
    }

    @Override
    public void onDisable() {
        saveAlertsConfig();
        getLogger().info("BlazeKillTracker has been disabled!");
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        // Check if the killed entity is a Blaze
        if (event.getEntityType() != EntityType.BLAZE) {
            return;
        }

        // Check if the killer is a player
        if (!(event.getEntity().getKiller() instanceof Player)) {
            return;
        }

        Player killer = event.getEntity().getKiller();
        Location location = event.getEntity().getLocation();
        LocalDateTime now = LocalDateTime.now();

        // Create kill record
        BlazeKillRecord record = new BlazeKillRecord(
                killer.getName(),
                killer.getUniqueId().toString(),
                location.getWorld().getName(),
                location.getBlockX(),
                location.getBlockY(),
                location.getBlockZ(),
                now.format(dateFormat)
        );

        // Save to file
        saveKillRecord(record);

        // Notify player
        killer.sendMessage(ChatColor.GREEN + "Blaze kill recorded! Location: "
                + location.getBlockX() + ", " + location.getBlockY() + ", " + location.getBlockZ());
    }

    private void saveKillRecord(BlazeKillRecord record) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(blazeKillsFile, true))) {
            writer.println(record.toString());
        } catch (IOException e) {
            getLogger().severe("Could not save Blaze kill record!");
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("blazekills")) {
            return handleBlazeKillsCommand(sender, args);
        } else if (command.getName().equalsIgnoreCase("blazekillsreload")) {
            return handleReloadCommand(sender);
        } else if (command.getName().equalsIgnoreCase("blazekill")) {
            return handleBlazeKillCommand(sender, args);
        }
        return false;
    }

    private boolean handleBlazeKillsCommand(CommandSender sender, String[] args) {
        if (!sender.hasPermission("blazekilltracker.view")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return true;
        }

        try {
            List<BlazeKillRecord> records = loadKillRecords();

            if (args.length > 0) {
                // Show kills for specific player
                String targetPlayer = args[0];
                List<BlazeKillRecord> playerRecords = records.stream()
                        .filter(record -> record.getPlayerName().equalsIgnoreCase(targetPlayer))
                        .collect(Collectors.toList());

                if (playerRecords.isEmpty()) {
                    sender.sendMessage(ChatColor.YELLOW + "No Blaze kills found for player: " + targetPlayer);
                    return true;
                }

                sender.sendMessage(ChatColor.GOLD + "=== Blaze Kills for " + targetPlayer + " ===");
                for (BlazeKillRecord record : playerRecords) {
                    sender.sendMessage(ChatColor.WHITE + "Time: " + ChatColor.YELLOW + record.getTimestamp());
                    sender.sendMessage(ChatColor.WHITE + "World: " + ChatColor.AQUA + record.getWorld());
                    sender.sendMessage(ChatColor.WHITE + "Location: " + ChatColor.GREEN
                            + record.getX() + ", " + record.getY() + ", " + record.getZ());
                    sender.sendMessage(ChatColor.GRAY + "---");
                }
                sender.sendMessage(ChatColor.GOLD + "Total kills: " + playerRecords.size());

            } else {
                // Show general statistics
                Map<String, Long> playerCounts = records.stream()
                        .collect(Collectors.groupingBy(BlazeKillRecord::getPlayerName, Collectors.counting()));

                sender.sendMessage(ChatColor.GOLD + "=== Blaze Kill Statistics ===");
                sender.sendMessage(ChatColor.WHITE + "Total Blaze kills: " + ChatColor.YELLOW + records.size());
                sender.sendMessage(ChatColor.WHITE + "Top killers:");

                playerCounts.entrySet().stream()
                        .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                        .limit(10)
                        .forEach(entry -> {
                            sender.sendMessage(ChatColor.AQUA + entry.getKey() + ": "
                                    + ChatColor.YELLOW + entry.getValue() + " kills");
                        });

                sender.sendMessage(ChatColor.GRAY + "Use /blazekills <player> to see detailed kills for a player");
            }

        } catch (IOException e) {
            sender.sendMessage(ChatColor.RED + "Error reading kill records!");
            getLogger().severe("Error reading kill records: " + e.getMessage());
        }

        return true;
    }

    private boolean handleReloadCommand(CommandSender sender) {
        if (!sender.hasPermission("blazekilltracker.reload")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return true;
        }

        // Reload configuration if needed
        reloadConfig();
        sender.sendMessage(ChatColor.GREEN + "BlazeKillTracker configuration reloaded!");
        return true;
    }

    private boolean handleBlazeKillCommand(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Ta komenda może być używana tylko przez graczy!");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("blazekilltracker.alerts")) {
            player.sendMessage(ChatColor.RED + "Nie masz uprawnień do używania tej komendy!");
            return true;
        }

        if (args.length == 0) {
            showBlazeKillHelp(player);
            return true;
        }

        String action = args[0].toLowerCase();

        switch (action) {
            case "active":
                playerAlerts.put(player.getUniqueId(), true);
                player.sendMessage(ChatColor.GREEN + "Alerty o spawn eggs zostały WŁĄCZONE!");
                saveAlertsConfig();
                return true;
            case "deactive":
                playerAlerts.put(player.getUniqueId(), false);
                player.sendMessage(ChatColor.RED + "Alerty o spawn eggs zostały WYŁĄCZONE!");
                saveAlertsConfig();
                return true;
            case "hist":
                return handleSpawnHistoryCommand(player, args);
            case "logitem":
                return handleLogItemCommand(player);
            case "help":
                showBlazeKillHelp(player);
                return true;
            default:
                showBlazeKillHelp(player);
                return true;
        }
    }

    private void showBlazeKillHelp(Player player) {
        player.sendMessage(ChatColor.GOLD + "=== BlazeKill Help ===");
        player.sendMessage(ChatColor.AQUA + "Plugin by jaruso99");
        player.sendMessage(ChatColor.YELLOW + "/blazekill active" + ChatColor.WHITE + " - Włącza alerty o spawn eggs (Blaze + Ghast)");
        player.sendMessage(ChatColor.YELLOW + "/blazekill deactive" + ChatColor.WHITE + " - Wyłącza alerty o spawn eggs");
        player.sendMessage(ChatColor.YELLOW + "/blazekill hist <gracz>" + ChatColor.WHITE + " - Historia respawnów gracza");
        player.sendMessage(ChatColor.YELLOW + "/blazekill logitem" + ChatColor.WHITE + " - Daje łopatę MobLog do sprawdzania spawnu");
        player.sendMessage(ChatColor.YELLOW + "/blazekill help" + ChatColor.WHITE + " - Wyświetla tę pomoc");
        player.sendMessage(ChatColor.GRAY + "Status: "
                + (playerAlerts.getOrDefault(player.getUniqueId(), false)
                ? ChatColor.GREEN + "WŁĄCZONE" : ChatColor.RED + "WYŁĄCZONE"));
        player.sendMessage(ChatColor.GRAY + "Monitorowane moby: " + ChatColor.AQUA + "Blaze, Ghast");
    }

    private List<BlazeKillRecord> loadKillRecords() throws IOException {
        List<BlazeKillRecord> records = new ArrayList<>();

        if (!blazeKillsFile.exists()) {
            return records;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(blazeKillsFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    BlazeKillRecord record = BlazeKillRecord.fromString(line);
                    if (record != null) {
                        records.add(record);
                    }
                } catch (Exception e) {
                    getLogger().warning("Could not parse kill record: " + line);
                }
            }
        }

        return records;
    }

    // Event handlers for mob spawning
    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        // Check if it's spawned by spawn egg (any mob type)
        if (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SPAWNER_EGG) {

            Location location = event.getLocation();
            LocalDateTime now = LocalDateTime.now();

            // Find nearby players (within 5 blocks) to determine who spawned it
            Player spawner = null;
            for (Player player : location.getWorld().getPlayers()) {
                if (player.getLocation().distance(location) <= 5.0) {
                    spawner = player;
                    break;
                }
            }

            if (spawner != null) {
                // Save spawn info for MobLog
                SpawnInfo spawnInfo = new SpawnInfo(
                        spawner.getName(),
                        spawner.getUniqueId().toString(),
                        now.format(dateFormat),
                        location.getBlockX(),
                        location.getBlockY(),
                        location.getBlockZ()
                );
                mobSpawnInfo.put(event.getEntity().getUniqueId(), spawnInfo);

                // Only send alerts for Blaze and Ghast
                if (event.getEntityType() == EntityType.BLAZE || event.getEntityType() == EntityType.GHAST) {
                    // Save spawn record
                    saveSpawnRecord(spawner, event.getEntityType().name(), location, now);

                    // Notify operators
                    notifyOperators(spawner, event.getEntityType().name(), location, now);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        // Load player's alert preference (default: true for ops)
        if (!playerAlerts.containsKey(player.getUniqueId())) {
            playerAlerts.put(player.getUniqueId(), player.isOp());
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        // Save alerts configuration when player leaves
        saveAlertsConfig();
    }

    // Tab completion
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (command.getName().equalsIgnoreCase("blazekill")) {
            if (args.length == 1) {
                return Arrays.asList("active", "deactive", "hist", "logitem", "help")
                        .stream()
                        .filter(s -> s.toLowerCase().startsWith(args[0].toLowerCase()))
                        .collect(Collectors.toList());
            } else if (args.length == 2 && args[0].equalsIgnoreCase("hist")) {
                // Return list of online players for hist command
                return getServer().getOnlinePlayers().stream()
                        .map(Player::getName)
                        .filter(name -> name.toLowerCase().startsWith(args[1].toLowerCase()))
                        .collect(Collectors.toList());
            }
        } else if (command.getName().equalsIgnoreCase("blazekills")) {
            if (args.length == 1) {
                // Return list of online players
                return getServer().getOnlinePlayers().stream()
                        .map(Player::getName)
                        .filter(name -> name.toLowerCase().startsWith(args[0].toLowerCase()))
                        .collect(Collectors.toList());
            }
        }
        return new ArrayList<>();
    }

    // Utility methods
    private void notifyOperators(Player spawner, String mobType, Location location, LocalDateTime time) {
        // Convert mob type to readable mob name
        String mobName = "";
        if (mobType.equals("BLAZE")) {
            mobName = "Blaze";
        } else if (mobType.equals("GHAST")) {
            mobName = "Ghast";
        }

        String message = ChatColor.RED + "[SPAWN ALERT] " + ChatColor.YELLOW + spawner.getName()
                + ChatColor.WHITE + " zespawnował " + ChatColor.AQUA + mobName
                + ChatColor.WHITE + " w " + ChatColor.GREEN + location.getWorld().getName()
                + " (" + location.getBlockX() + ", " + location.getBlockY() + ", " + location.getBlockZ() + ")"
                + ChatColor.GRAY + " - Kliknij aby się tp!";

        // Create clickable message for teleport
        TextComponent clickableMessage = new TextComponent(message);
        clickableMessage.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,
                "/tp " + location.getBlockX() + " " + location.getBlockY() + " " + location.getBlockZ()));

        // Send to all operators with alerts enabled
        for (Player operator : getServer().getOnlinePlayers()) {
            if (playerAlerts.getOrDefault(operator.getUniqueId(), false)) {
                operator.spigot().sendMessage(clickableMessage);
            }
        }
    }

    private void loadAlertsConfig() {
        if (!alertsConfigFile.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(alertsConfigFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    UUID playerUuid = UUID.fromString(parts[0]);
                    boolean alertsEnabled = Boolean.parseBoolean(parts[1]);
                    playerAlerts.put(playerUuid, alertsEnabled);
                }
            }
        } catch (IOException e) {
            getLogger().warning("Could not load alerts configuration!");
        }
    }

    private void saveAlertsConfig() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(alertsConfigFile))) {
            for (Map.Entry<UUID, Boolean> entry : playerAlerts.entrySet()) {
                writer.println(entry.getKey().toString() + ":" + entry.getValue());
            }
        } catch (IOException e) {
            getLogger().severe("Could not save alerts configuration!");
        }
    }

    // Spawn history methods
    private boolean handleSpawnHistoryCommand(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage(ChatColor.RED + "Użycie: /blazekill hist <gracz>");
            return true;
        }

        String targetPlayer = args[1];

        try {
            List<SpawnRecord> records = loadSpawnRecords();
            List<SpawnRecord> playerRecords = records.stream()
                    .filter(record -> record.getPlayerName().equalsIgnoreCase(targetPlayer))
                    .collect(Collectors.toList());

            if (playerRecords.isEmpty()) {
                player.sendMessage(ChatColor.YELLOW + "Brak historii respawnów dla gracza: " + targetPlayer);
                return true;
            }

            player.sendMessage(ChatColor.GOLD + "=== Historia respawnów dla " + targetPlayer + " ===");
            for (SpawnRecord record : playerRecords) {
                String mobName = record.getMobType().equals("BLAZE") ? "Blaze" : "Ghast";
                player.sendMessage(ChatColor.WHITE + "Mob: " + ChatColor.AQUA + mobName);
                player.sendMessage(ChatColor.WHITE + "Czas: " + ChatColor.YELLOW + record.getTimestamp());
                player.sendMessage(ChatColor.WHITE + "Świat: " + ChatColor.AQUA + record.getWorld());
                player.sendMessage(ChatColor.WHITE + "Lokalizacja: " + ChatColor.GREEN
                        + record.getX() + ", " + record.getY() + ", " + record.getZ());
                player.sendMessage(ChatColor.GRAY + "---");
            }
            player.sendMessage(ChatColor.GOLD + "Łącznie respawnów: " + playerRecords.size());

        } catch (IOException e) {
            player.sendMessage(ChatColor.RED + "Błąd podczas odczytu historii!");
            getLogger().severe("Error reading spawn history: " + e.getMessage());
        }

        return true;
    }

    private void saveSpawnRecord(Player spawner, String mobType, Location location, LocalDateTime time) {
        SpawnRecord record = new SpawnRecord(
                spawner.getName(),
                spawner.getUniqueId().toString(),
                mobType,
                location.getWorld().getName(),
                location.getBlockX(),
                location.getBlockY(),
                location.getBlockZ(),
                time.format(dateFormat)
        );

        try (PrintWriter writer = new PrintWriter(new FileWriter(spawnHistoryFile, true))) {
            writer.println(record.toString());
        } catch (IOException e) {
            getLogger().severe("Could not save spawn record!");
            e.printStackTrace();
        }
    }

    private List<SpawnRecord> loadSpawnRecords() throws IOException {
        List<SpawnRecord> records = new ArrayList<>();

        if (!spawnHistoryFile.exists()) {
            return records;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(spawnHistoryFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    SpawnRecord record = SpawnRecord.fromString(line);
                    if (record != null) {
                        records.add(record);
                    }
                } catch (Exception e) {
                    getLogger().warning("Could not parse spawn record: " + line);
                }
            }
        }

        return records;
    }

    // Inner class to represent a Blaze kill record
    public static class BlazeKillRecord {

        private String playerName;
        private String playerUuid;
        private String world;
        private int x, y, z;
        private String timestamp;

        public BlazeKillRecord(String playerName, String playerUuid, String world,
                int x, int y, int z, String timestamp) {
            this.playerName = playerName;
            this.playerUuid = playerUuid;
            this.world = world;
            this.x = x;
            this.y = y;
            this.z = z;
            this.timestamp = timestamp;
        }

        public String getPlayerName() {
            return playerName;
        }

        public String getPlayerUuid() {
            return playerUuid;
        }

        public String getWorld() {
            return world;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getZ() {
            return z;
        }

        public String getTimestamp() {
            return timestamp;
        }

        @Override
        public String toString() {
            return String.join(";", playerName, playerUuid, world,
                    String.valueOf(x), String.valueOf(y), String.valueOf(z), timestamp);
        }

        public static BlazeKillRecord fromString(String line) {
            String[] parts = line.split(";");
            if (parts.length != 7) {
                return null;
            }

            try {
                return new BlazeKillRecord(
                        parts[0], // playerName
                        parts[1], // playerUuid
                        parts[2], // world
                        Integer.parseInt(parts[3]), // x
                        Integer.parseInt(parts[4]), // y
                        Integer.parseInt(parts[5]), // z
                        parts[6] // timestamp
                );
            } catch (NumberFormatException e) {
                return null;
            }
        }
    }

    // Inner class to represent a spawn record
    public static class SpawnRecord {

        private final String playerName;
        private final String playerUuid;
        private final String mobType;
        private final String world;
        private final int x, y, z;
        private final String timestamp;

        public SpawnRecord(String playerName, String playerUuid, String mobType, String world,
                int x, int y, int z, String timestamp) {
            this.playerName = playerName;
            this.playerUuid = playerUuid;
            this.mobType = mobType;
            this.world = world;
            this.x = x;
            this.y = y;
            this.z = z;
            this.timestamp = timestamp;
        }

        public String getPlayerName() {
            return playerName;
        }

        public String getPlayerUuid() {
            return playerUuid;
        }

        public String getMobType() {
            return mobType;
        }

        public String getWorld() {
            return world;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getZ() {
            return z;
        }

        public String getTimestamp() {
            return timestamp;
        }

        @Override
        public String toString() {
            return String.join(";", playerName, playerUuid, mobType, world,
                    String.valueOf(x), String.valueOf(y), String.valueOf(z), timestamp);
        }

        public static SpawnRecord fromString(String line) {
            String[] parts = line.split(";");
            if (parts.length != 8) {
                return null;
            }

            try {
                return new SpawnRecord(
                        parts[0], // playerName
                        parts[1], // playerUuid
                        parts[2], // mobType
                        parts[3], // world
                        Integer.parseInt(parts[4]), // x
                        Integer.parseInt(parts[5]), // y
                        Integer.parseInt(parts[6]), // z
                        parts[7] // timestamp
                );
            } catch (NumberFormatException e) {
                return null;
            }
        }
    }

    // MobLog item methods
    private boolean handleLogItemCommand(Player player) {
        ItemStack logItem = createMobLogItem();
        player.getInventory().addItem(logItem);
        player.sendMessage(ChatColor.GREEN + "Otrzymałeś łopatę MobLog!");
        player.sendMessage(ChatColor.YELLOW + "Kliknij nią na moba aby sprawdzić kto go zespawnował!");
        return true;
    }

    private ItemStack createMobLogItem() {
        ItemStack item = new ItemStack(Material.DIAMOND_SHOVEL);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.GOLD + "MobLog");
            meta.addEnchant(Enchantment.LUCK, 1, true);
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY + "Kliknij na moba aby sprawdzić");
            lore.add(ChatColor.GRAY + "kto go zespawnował i kiedy");
            lore.add(ChatColor.BLUE + "Plugin by jaruso99");
            meta.setLore(lore);
            item.setItemMeta(meta);
        }
        return item;
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();
        ItemStack item = player.getInventory().getItemInMainHand();

        // Check if player is holding MobLog item
        if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()
                && item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "MobLog")) {

            event.setCancelled(true);

            // Check if entity has spawn info
            UUID entityUUID = entity.getUniqueId();
            if (mobSpawnInfo.containsKey(entityUUID)) {
                SpawnInfo spawnInfo = mobSpawnInfo.get(entityUUID);

                player.sendMessage(ChatColor.GOLD + "=== MobLog Info ===");
                player.sendMessage(ChatColor.AQUA + "Typ moba: " + ChatColor.WHITE + entity.getType().name());
                player.sendMessage(ChatColor.AQUA + "Zespawnowany przez: " + ChatColor.YELLOW + spawnInfo.getSpawnerName());
                player.sendMessage(ChatColor.AQUA + "Czas spawnu: " + ChatColor.WHITE + spawnInfo.getSpawnTime());
                player.sendMessage(ChatColor.AQUA + "Lokalizacja spawnu: " + ChatColor.GREEN
                        + spawnInfo.getX() + ", " + spawnInfo.getY() + ", " + spawnInfo.getZ());

            } else {
                player.sendMessage(ChatColor.YELLOW + "Brak informacji o spawnie tego moba");
                player.sendMessage(ChatColor.GRAY + "Mob mógł zostać zespawnowany naturalnie lub przed uruchomieniem pluginu");
            }
        }
    }

    // Inner class to represent spawn information
    public static class SpawnInfo {

        private final String spawnerName;
        private final String spawnerUuid;
        private final String spawnTime;
        private final int x, y, z;

        public SpawnInfo(String spawnerName, String spawnerUuid, String spawnTime, int x, int y, int z) {
            this.spawnerName = spawnerName;
            this.spawnerUuid = spawnerUuid;
            this.spawnTime = spawnTime;
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public String getSpawnerName() {
            return spawnerName;
        }

        public String getSpawnerUuid() {
            return spawnerUuid;
        }

        public String getSpawnTime() {
            return spawnTime;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getZ() {
            return z;
        }
    }

}
