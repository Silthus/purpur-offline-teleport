package net.silthus.offlinetp;

import co.aikar.commands.PaperCommandManager;
import kr.entree.spigradle.annotations.PluginMain;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.io.File;

@PluginMain
public class OfflineTeleporter extends JavaPlugin {

    @Getter
    @Accessors(fluent = true)
    private static OfflineTeleporter instance;
    private PaperCommandManager commandManager;

    public OfflineTeleporter() {
        instance = this;
    }

    public OfflineTeleporter(
            JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file) {
        super(loader, description, dataFolder, file);
        instance = this;
    }

    @Override
    public void onEnable() {

        commandManager = new PaperCommandManager(this);
        commandManager.registerCommand(new TeleportCommands(this));
    }
}
