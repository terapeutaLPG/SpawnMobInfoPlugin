package com.yourname.blazekilltracker;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.entity.EntityType;
import org.bukkit.Location;
import org.bukkit.ChatColor;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class BlazeKillTracker extends JavaPlugin implements Listener {
    
    private File dataFolder;
    private File blazeKillsFile;
    private DateTimeFormatter dateFormat;
    
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
        
        this.dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        
        // Register events
        getServer().getPluginManager().registerEvents(this, this);
        
        getLogger().info("BlazeKillTracker has been enabled!");
    }
    
    @Override
    public void onDisable() {
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
        killer.sendMessage(ChatColor.GREEN + "Blaze kill recorded! Location: " + 
                          location.getBlockX() + ", " + location.getBlockY() + ", " + location.getBlockZ());
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
                    sender.sendMessage(ChatColor.WHITE + "Location: " + ChatColor.GREEN + 
                                     record.getX() + ", " + record.getY() + ", " + record.getZ());
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
                        sender.sendMessage(ChatColor.AQUA + entry.getKey() + ": " + 
                                         ChatColor.YELLOW + entry.getValue() + " kills");
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
        
        public String getPlayerName() { return playerName; }
        public String getPlayerUuid() { return playerUuid; }
        public String getWorld() { return world; }
        public int getX() { return x; }
        public int getY() { return y; }
        public int getZ() { return z; }
        public String getTimestamp() { return timestamp; }
        
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
                    parts[6]  // timestamp
                );
            } catch (NumberFormatException e) {
                return null;
            }
        }
    }
}
