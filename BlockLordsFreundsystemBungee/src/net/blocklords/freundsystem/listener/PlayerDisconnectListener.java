package net.blocklords.freundsystem.listener;

import java.util.List;

import net.blocklords.freundsystem.database.FriendManager;
import net.blocklords.freundsystem.database.PlayerInfoManager;
import net.blocklords.freundsystem.database.SettingManager;
import net.blocklords.freundsystem.lang.MessageHandler;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerDisconnectListener  implements Listener {
    @EventHandler
    public void on(PlayerDisconnectEvent e) {
        PlayerInfoManager.setOnline(e.getPlayer().getName(), false);
        List<String> friends = FriendManager.getAllFriends(e.getPlayer().getName());
        for (String friend : friends) {
            ProxiedPlayer pp2 = ProxyServer.getInstance().getPlayer(friend);
            if ((pp2 != null) &&
                    (SettingManager.canSendJoinMessages(friend))) {
                String msg = MessageHandler.getMessage("MESSAGE_FRIEND_LEAVE");
                msg = msg.replaceAll("%NAME%", e.getPlayer().getName());
                pp2.sendMessage(msg);
            }
        }
    }
}
