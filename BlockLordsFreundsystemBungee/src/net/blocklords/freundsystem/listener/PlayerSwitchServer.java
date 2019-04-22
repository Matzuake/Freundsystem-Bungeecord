package net.blocklords.freundsystem.listener;

import net.blocklords.freundsystem.database.MySQL;
import net.blocklords.freundsystem.database.PlayerInfoManager;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerSwitchServer  implements Listener
{
    @EventHandler
    public void on(ServerSwitchEvent e)
    {
        if (MySQL.isInDatabase(e.getPlayer())) {
            PlayerInfoManager.setServer(e.getPlayer().getName(), e.getPlayer().getServer().getInfo().getName());
        }
    }
}
