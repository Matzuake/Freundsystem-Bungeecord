package net.blocklords.freundsystem.listener;

import java.util.List;

import net.blocklords.freundsystem.database.*;
import net.blocklords.freundsystem.lang.MessageHandler;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerConnectListener  implements Listener
{
    @EventHandler
    public void on(PostLoginEvent e)
    {
        if (!MySQL.isInDatabase(e.getPlayer())) {
            MySQL.addPlayerToDatabase(e.getPlayer());
        }
        PlayerInfoManager.setOnline(e.getPlayer().getName(), true);

        List<String> friends = FriendManager.getAllFriends(e.getPlayer().getName());
        String msg;
        for (String friend : friends)
        {
            ProxiedPlayer pp2 = ProxyServer.getInstance().getPlayer(friend);
            if ((pp2 != null) &&
                    (SettingManager.canSendJoinMessages(friend)))
            {
                msg = MessageHandler.getMessage("MESSAGE_FRIEND_JOIN");
                msg = msg.replaceAll("%NAME%", e.getPlayer().getName());
                pp2.sendMessage(msg);
            }
        }
        if (InviteManager.getRequests(e.getPlayer().getName()) == 0) {
            return;
        }
        List<String> getRequest = InviteManager.getAllRequests(e.getPlayer().getName());
        String message = MessageHandler.getMessage("MESSAGE_REQUESTS_JOIN");
        message = message.replaceAll("%REQUEST%", String.valueOf(InviteManager.getRequests(e.getPlayer().getName())));
        e.getPlayer().sendMessage(message);
        for (String request : getRequest)
        {
            String accept = MessageHandler.getMessage("CODE_ACCEPT");
            String deny = MessageHandler.getMessage("CODE_DENY");
            ComponentBuilder cb = new ComponentBuilder("§8- §e" + request + " ").append(accept).event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/friend accept " + request)).append(deny).event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/friend deny " + request));
            e.getPlayer().sendMessage(cb.create());
        }
    }
}
