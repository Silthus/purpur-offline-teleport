package net.silthus.offlinetp;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.InvalidCommandArgument;
import co.aikar.commands.annotation.*;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@CommandAlias("offlinetp")
public class TeleportCommands extends BaseCommand {

    private final OfflineTeleporter plugin;

    public TeleportCommands(OfflineTeleporter plugin) {
        this.plugin = plugin;
    }

    @Subcommand("player")
    @CommandCompletion("@offlineplayers")
    public void teleport(@Flags("self") Player player, String name) {

        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayerIfCached(name);
        if (offlinePlayer == null) {
            throw new InvalidCommandArgument("Unable to find a cached offline player for the name: " + name);
        }

        teleportOfflinePlayer(offlinePlayer, player.getLocation())
                .thenAccept(teleported -> {
                    if (teleported) {
                        player.sendMessage(ChatColor.GREEN + "Teleported " + offlinePlayer.getName() + " to " + player.getLocation() + ".");
                    } else {
                        player.sendMessage(ChatColor.RED + "Failed to teleport " + offlinePlayer.getName());
                    }
                });
    }

    @Subcommand("all")
    @CommandCompletion("@worlds")
    public void teleportAll(@Flags("self") Player player, @Optional String world) {

        World bukkitWorld = null;
        if (world != null) {
            bukkitWorld = Bukkit.getWorld(world);
            if (bukkitWorld == null) {
                throw new InvalidCommandArgument("There is no world with the name " + world);
            }
        }

        final UUID worldId = bukkitWorld != null ? bukkitWorld.getUID() : null;
        final Location location = player.getLocation();
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            OfflinePlayer[] offlinePlayers = Bukkit.getServer().getOfflinePlayers();
            if (worldId != null) {
                Arrays.stream(offlinePlayers)
                        .filter(offlinePlayer -> offlinePlayer.getLocation() != null)
                        .filter(offlinePlayer -> offlinePlayer.getLocation().getWorld().getUID().equals(worldId))
                        .forEach(offlinePlayer -> teleportOfflinePlayer(offlinePlayer, location));
            } else {
                Arrays.stream(offlinePlayers)
                        .forEach(offlinePlayer -> teleportOfflinePlayer(offlinePlayer, location));
            }
            player.sendMessage(ChatColor.GREEN + "Teleporting " + offlinePlayers.length + " players to " + player.getLocation() + ". This may take some time...");
        });
    }

    private CompletableFuture<Boolean> teleportOfflinePlayer(OfflinePlayer offlinePlayer, Location location) {
        return offlinePlayer.teleportOfflineAsync(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
    }
}
