package net.blocklords.freundsystem.commands;

import net.blocklords.freundsystem.database.FriendManager;
import net.blocklords.freundsystem.database.MySQL;
import net.blocklords.freundsystem.database.SettingManager;
import net.blocklords.freundsystem.lang.MessageHandler;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Msg  extends Command
{
    public Msg(String name)
    {
        super(name);
    }

    public void execute(CommandSender sender, String[] args)
    {
        if (!(sender instanceof ProxiedPlayer)) {
            return;
        }
        ProxiedPlayer pp = (ProxiedPlayer)sender;
        if (args.length >= 2)
        {
            String name = args[0];
            if (!MySQL.isInDatabase(name))
            {
                pp.sendMessage(MessageHandler.getMessage("MESSAGE_NOT_IN_MYSQL"));
                return;
            }
            ProxiedPlayer pp2 = ProxyServer.getInstance().getPlayer(name);
            if (pp2 == null)
            {
                pp.sendMessage(MessageHandler.getMessage("MESSAGE_FRIEND_NOT_ONLINE"));
                return;
            }
            if (!FriendManager.isFriend(pp.getName(), name))
            {
                String msg = MessageHandler.getMessage("MESSAGE_NO_FRIEND");
                msg = msg.replaceAll("%PLAYER%", name);
                pp.sendMessage(msg);
                return;
            }
            if (!SettingManager.canSendMessages(name))
            {
                pp.sendMessage(MessageHandler.getMessage("MESSAGE_CANT_RECIVE_MESSAGE"));
                return;
            }
            String msg = "";
            for (int i = 1; i < args.length; i++) {
                msg = msg + args[i] + " ";
            }
            String message = MessageHandler.getMessage("MESSAGE_MSG_START");
            message = message.replaceAll("%SENDER%", pp.getName());
            message = message.replaceAll("%GETTER%", pp2.getName());
            message = message + msg;
            pp.sendMessage(message);
            pp2.sendMessage(message);
            return;
        }
        pp.sendMessage(MessageHandler.getMessage("HELP_FRIEND_MSG"));
    }
}
