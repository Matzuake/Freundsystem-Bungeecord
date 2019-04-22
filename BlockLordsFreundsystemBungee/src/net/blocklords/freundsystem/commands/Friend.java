package net.blocklords.freundsystem.commands;

import java.util.List;

import net.blocklords.freundsystem.Main;
import net.blocklords.freundsystem.database.FriendManager;
import net.blocklords.freundsystem.database.InviteManager;
import net.blocklords.freundsystem.database.MySQL;
import net.blocklords.freundsystem.database.SettingManager;
import net.blocklords.freundsystem.lang.MessageHandler;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;

import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.plugin.Command;

public class Friend  extends Command {

    public Friend(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            return;
        }
        ProxiedPlayer pp = (ProxiedPlayer)sender;
        if ((Main.disabledServer.contains(pp.getServer().getInfo().getName())) &&
                (args.length == 0))
        {
            pp.chat("/friend");
            return;
        }
        if ((args.length == 1) &&
                (args[0].equalsIgnoreCase("list")))
        {
            pp.sendMessage(MessageHandler.getMessage("MESSAGE_LIST_TOP"));
            List<String> friends = FriendManager.getAllFriends(pp.getName());
            for (String friend : friends)
            {
                BaseComponent[] hover1 = new ComponentBuilder(MessageHandler.getMessage("HOVER_REMOVE")).create();
                BaseComponent[] hover2 = new ComponentBuilder(MessageHandler.getMessage("HOVER_JUMP")).create();
                BaseComponent[] hover3 = new ComponentBuilder(MessageHandler.getMessage("HOVER_MSG")).create();
                ComponentBuilder cb = new ComponentBuilder("�e" + friend + " ").append(MessageHandler.getMessage("CODE_REMOVE")).event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/friend remove " + friend)).event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, hover1)).append(MessageHandler.getMessage("CODE_JUMP")).event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/friend jump " + friend)).event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, hover2)).append(MessageHandler.getMessage("CODE_MSG")).event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/msg " + friend + " ")).event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, hover3));
                pp.sendMessage(cb.create());
            }
            pp.sendMessage(MessageHandler.getMessage("MESSAGE_LIST_BOTTOM"));
            return;
        }
        if (args.length == 2)
        {
            if (args[0].equalsIgnoreCase("add"))
            {
                String name = args[1];
                if (name.equalsIgnoreCase(pp.getName()))
                {
                    pp.sendMessage(MessageHandler.getMessage("MESSAGE_SELF_INTERACT"));
                    return;
                }
                if (!MySQL.isInDatabase(name))
                {
                    pp.sendMessage(MessageHandler.getMessage("MESSAGE_NOT_IN_MYSQL"));
                    return;
                }
                if (FriendManager.isFriend(pp.getName(), name))
                {
                    pp.sendMessage(MessageHandler.getMessage("MESSAGE_ALREADY_FRIEND"));
                    return;
                }
                if (!SettingManager.canGetRequests(name))
                {
                    pp.sendMessage(MessageHandler.getMessage("MESSAGE_NOT_ACCEPTING_INVITES"));
                    return;
                }
                if (FriendManager.getFriends(pp.getName()) >= 54)
                {
                    pp.sendMessage(MessageHandler.getMessage("MESSAGE_FULL_FRIENDLIST"));
                    return;
                }
                if (FriendManager.getFriends(name) >= 54)
                {
                    pp.sendMessage(MessageHandler.getMessage("MESSAGE_FRIEND_FULL_FRIENDLIST"));
                    return;
                }
                if (InviteManager.sendedRequest(pp.getName(), name)) {
                    pp.sendMessage(MessageHandler.getMessage("MESSAGE_ALREADY_INVITED"));
                }
                InviteManager.addRequest(pp.getName(), name);
                String message = MessageHandler.getMessage("MESSAGE_SENDED_INVITE");
                message = message.replaceAll("%NAME%", name);
                pp.sendMessage(message);
                ProxiedPlayer pp2 = ProxyServer.getInstance().getPlayer(name);
                if (pp2 != null)
                {
                    String message2 = MessageHandler.getMessage("MESSAGE_GET_INVITE");
                    message2 = message2.replaceAll("%NAME%", pp.getName());
                    String accept = MessageHandler.getMessage("CODE_ACCEPT");
                    String deny = MessageHandler.getMessage("CODE_DENY");
                    ComponentBuilder cb = new ComponentBuilder(message2 + " ").append(accept).event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/friend accept " + pp.getName())).append(deny).event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/friend deny " + pp.getName()));
                    pp2.sendMessage(cb.create());
                }
                return;
            }
            if (args[0].equalsIgnoreCase("remove"))
            {
                String name = args[1];
                if (!MySQL.isInDatabase(name))
                {
                    pp.sendMessage(MessageHandler.getMessage("MESSAGE_NOT_IN_MYSQL"));
                    return;
                }
                if (!FriendManager.isFriend(pp.getName(), name))
                {
                    String message = MessageHandler.getMessage("MESSAGE_NO_FRIEND");
                    message = message.replace("%PLAYER%", name);
                    pp.sendMessage(message);
                    return;
                }
                FriendManager.removeFriend(pp.getName(), name);
                String message = MessageHandler.getMessage("MESSAGE_REMOVE_FRIEND");
                message = message.replaceAll("%NAME%", name);
                pp.sendMessage(message);
                ProxiedPlayer pp2 = ProxyServer.getInstance().getPlayer(name);
                if (pp2 != null)
                {
                    String message2 = MessageHandler.getMessage("MESSAGE_FRIEND_REMOVE_PLAYER");
                    message2 = message2.replaceAll("%NAME%", pp.getName());
                    pp2.sendMessage(message2);
                }
                return;
            }
            if (args[0].equalsIgnoreCase("accept"))
            {
                String name = args[1];
                if (!MySQL.isInDatabase(name))
                {
                    pp.sendMessage(MessageHandler.getMessage("MESSAGE_NOT_IN_MYSQL"));
                    return;
                }
                if (!InviteManager.sendedRequest(name, pp.getName()))
                {
                    pp.sendMessage(MessageHandler.getMessage("MESSAGE_NOT_INVITED"));
                    return;
                }
                InviteManager.removeRequest(name, pp.getName());
                FriendManager.addFriend(name, pp.getName());
                String msg = MessageHandler.getMessage("MESSAGE_FRIEND_ACCEPT");
                msg = msg.replaceAll("%NAME%", pp.getName());
                String msg2 = MessageHandler.getMessage("MESSAGE_FRIEND_ACCEPT");
                msg2 = msg2.replaceAll("%NAME%", name);
                pp.sendMessage(msg2);
                ProxiedPlayer pp2 = ProxyServer.getInstance().getPlayer(name);
                if (pp2 != null) {
                    pp2.sendMessage(msg);
                }
                return;
            }
            if (args[0].equalsIgnoreCase("deny"))
            {
                String name = args[1];
                if (!MySQL.isInDatabase(name))
                {
                    pp.sendMessage(MessageHandler.getMessage("MESSAGE_NOT_IN_MYSQL"));
                    return;
                }
                if (!InviteManager.sendedRequest(name, pp.getName()))
                {
                    pp.sendMessage(MessageHandler.getMessage("MESSAGE_NOT_INVITED"));
                    return;
                }
                InviteManager.removeRequest(name, pp.getName());
                String msg = MessageHandler.getMessage("MESSAGE_NOT_ACCEPTED");
                String msg2 = MessageHandler.getMessage("MESSAGE_NOT_ACCEPTING");
                msg = msg.replaceAll("%NAME%", pp.getName());
                msg2 = msg2.replaceAll("%NAME%", name);
                pp.sendMessage(msg2);
                ProxiedPlayer pp2 = ProxyServer.getInstance().getPlayer(name);
                if (pp2 != null) {
                    pp2.sendMessage(msg);
                }
                return;
            }
            if (args[0].equalsIgnoreCase("jump"))
            {
                String name = args[1];
                if (pp.getName().equalsIgnoreCase(name))
                {
                    pp.sendMessage("MESSAGE_SELF_INTERACT");
                    return;
                }
                if (!MySQL.isInDatabase(name))
                {
                    pp.sendMessage(MessageHandler.getMessage("MESSAGE_NOT_IN_MYSQL"));
                    return;
                }
                if (!FriendManager.isFriend(pp.getName(), name))
                {
                    String message = MessageHandler.getMessage("MESSAGE_NO_FRIEND");
                    message = message.replace("%PLAYER%", name);
                    pp.sendMessage(message);
                    return;
                }
                ProxiedPlayer pp2 = ProxyServer.getInstance().getPlayer(name);
                if (pp2 == null)
                {
                    pp.sendMessage(MessageHandler.getMessage("MESSAGE_FRIEND_NOT_ONLINE"));
                    return;
                }
                pp.connect(pp2.getServer().getInfo());
                String msg = MessageHandler.getMessage("MESSAGE_FRIEND_JUMPING");
                msg = msg.replaceAll("%SERVER%", pp2.getServer().getInfo().getName());
                msg = msg.replaceAll("%NAME%", pp2.getName());
                pp.sendMessage(msg);
                return;
            }
            if (args[0].equalsIgnoreCase("toggle"))
            {
                if (args[1].equalsIgnoreCase("Invites"))
                {
                    if (SettingManager.canGetRequests(pp.getName()))
                    {
                        SettingManager.setcanSendRequests(pp.getName(), false);
                        pp.sendMessage(MessageHandler.getMessage("MESSAGE_INVITES_OFF"));
                    }
                    else
                    {
                        SettingManager.setcanSendRequests(pp.getName(), true);
                        pp.sendMessage(MessageHandler.getMessage("MESSAGE_INVITES_ON"));
                    }
                    return;
                }
                if (args[1].equalsIgnoreCase("Joinmsg"))
                {
                    if (SettingManager.canSendMessages(pp.getName()))
                    {
                        SettingManager.setcanSendMessage(pp.getName(), false);
                        pp.sendMessage(MessageHandler.getMessage("MESSAGE_JOINMESSAGE_OFF"));
                    }
                    else
                    {
                        SettingManager.setcanSendMessage(pp.getName(), true);
                        pp.sendMessage(MessageHandler.getMessage("MESSAGE_JOINMESSAGE_ON"));
                    }
                    return;
                }
                if (args[1].equalsIgnoreCase("Msg"))
                {
                    if (SettingManager.canSendJoinMessages(pp.getName()))
                    {
                        SettingManager.setcanSendJoinMessage(pp.getName(), false);
                        pp.sendMessage(MessageHandler.getMessage("MESSAGE_MESSAGE_OFF"));
                    }
                    else
                    {
                        SettingManager.setcanSendJoinMessage(pp.getName(), true);
                        pp.sendMessage(MessageHandler.getMessage("MESSAGE_MESSAGE_ON"));
                    }
                    return;
                }
            }
        }
        sendHelp(pp);
    }

    public void sendHelp(ProxiedPlayer pp)
    {
        pp.sendMessage(MessageHandler.getMessage("HELP_FRIEND_LIST"));
        pp.sendMessage(MessageHandler.getMessage("HELP_FRIEND_ADD"));
        pp.sendMessage(MessageHandler.getMessage("HELP_FRIEND_REMOVE"));
        pp.sendMessage(MessageHandler.getMessage("HELP_FRIEND_JUMP"));
        pp.sendMessage(MessageHandler.getMessage("HELP_FRIEND_TOGGLE"));
        pp.sendMessage(MessageHandler.getMessage("HELP_FRIEND_MSG"));
    }
}
