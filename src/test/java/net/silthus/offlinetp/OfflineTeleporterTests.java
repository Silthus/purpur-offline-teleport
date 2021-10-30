package net.silthus.offlinetp;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import org.bukkit.event.player.PlayerJoinEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OfflineTeleporterTests {

    private ServerMock server;

    @BeforeEach
    public void setUp() {
        server = MockBukkit.mock();
        MockBukkit.load(OfflineTeleporter.class);
    }

    @AfterEach
    public void tearDown() {
        MockBukkit.unmock();
    }
}
