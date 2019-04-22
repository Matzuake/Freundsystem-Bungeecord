package net.blocklords.freundsystem.lang;

public class English {
    public static String getMessage(String code)
    {
        String msg = "No Message! Please send this to the Developer: " + code;
        if (code.equalsIgnoreCase("HELP_FRIEND_LIST")) {
            msg = "§e/friend list §8- §aShow you your friends in your Friendlist";
        }
        if (code.equalsIgnoreCase("HELP_FRIEND_ADD")) {
            msg = "§e/friend add <Player> §8- §aInvite <Player> to be in your Friendlist";
        }
        if (code.equalsIgnoreCase("HELP_FRIEND_REMOVE")) {
            msg = "§e/friend remove <Player> §8- §aRemove <Player> from your Friendlist";
        }
        if (code.equalsIgnoreCase("HELP_FRIEND_JUMP")) {
            msg = "§e/friend jump <Player> §8- §aJump to your friend";
        }
        if (code.equalsIgnoreCase("HELP_FRIEND_MSG")) {
            msg = "§e/msg <Player> <Message> §8- §aSend your Friend a message";
        }
        if (code.equalsIgnoreCase("HELP_FRIEND_TOGGLE")) {
            msg = "§e/friend toggle <Invites/JoinMsg/Msg> §8- §aToggle your Friend settings";
        }
        if (code.equalsIgnoreCase("MESSAGE_LIST_TOP")) {
            msg = "§m               §r§8| §aYour Friends §8|§r§m               ";
        }
        if (code.equalsIgnoreCase("MESSAGE_LIST_BOTTOM")) {
            msg = "§m                                              ";
        }
        if (code.equalsIgnoreCase("MESSAGE_NOT_IN_MYSQL")) {
            msg = "§cThis Player can not be found in our Database!";
        }
        if (code.equalsIgnoreCase("MESSAGE_NOT_ACCEPTING_INVITES")) {
            msg = "§cThis Player do not want Friend Requests!";
        }
        if (code.equalsIgnoreCase("MESSAGE_FULL_FRIENDLIST")) {
            msg = "§cYour Friendlist is full!";
        }
        if (code.equalsIgnoreCase("MESSAGE_FRIEND_FULL_FRIENDLIST")) {
            msg = "§cThis Player's Friendlist is full!";
        }
        if (code.equalsIgnoreCase("MESSAGE_SENDED_INVITE")) {
            msg = "§aYou have sucsessfully send a Request to §e%NAME%";
        }
        if (code.equalsIgnoreCase("MESSAGE_GET_INVITE")) {
            msg = "§aYou got an Friendrequest from §e%NAME%§a.";
        }
        if (code.equalsIgnoreCase("MESSAGE_NO_FRIEND")) {
            msg = "§cThis Player is not on your Friendlist!";
        }
        if (code.equalsIgnoreCase("MESSAGE_REMOVE_FRIEND")) {
            msg = "§cYou sucsessfully removed §e%NAME%§c from your Friendlist!";
        }
        if (code.equalsIgnoreCase("MESSAGE_FRIEND_REMOVE_PLAYER")) {
            msg = "§e%NAME% §chas removed you from his Friendlist!";
        }
        if (code.equalsIgnoreCase("MESSAGE_NOT_INVITED")) {
            msg = "§cYou did not recive a Request from this Player!";
        }
        if (code.equalsIgnoreCase("MESSAGE_FRIEND_ACCEPT")) {
            msg = "§aYou are now friends with §e%NAME%§a!";
        }
        if (code.equalsIgnoreCase("MESSAGE_NOT_ACCEPTED")) {
            msg = "§cYour Request to §e%NAME% §cwas not accepted!!";
        }
        if (code.equalsIgnoreCase("MESSAGE_NOT_ACCEPTING")) {
            msg = "§cYou did not Accept the Request from §e%NAME%§c!";
        }
        if (code.equalsIgnoreCase("MESSAGE_ALREADY_FRIEND")) {
            msg = "§cThis Player is already your Friend!";
        }
        if (code.equalsIgnoreCase("MESSAGE_SELF_INTERACT")) {
            msg = "§cYou can not interact with yourself!";
        }
        if (code.equalsIgnoreCase("MESSAGE_FRIEND_NOT_ONLINE")) {
            msg = "§cThis Player is not online!";
        }
        if (code.equalsIgnoreCase("MESSAGE_FRIEND_JUMPING")) {
            msg = "§aJumping to §e%NAME%§a. Joining Server §e%SERVER%";
        }
        if (code.equalsIgnoreCase("MESSAGE_CANT_RECIVE_MESSAGE")) {
            msg = "§cYou can not message this Friend";
        }
        if (code.equalsIgnoreCase("MESSAGE_MSG_START")) {
            msg = "§e%SENDER% §a=> §e%GETTER% §a: §3";
        }
        if (code.equalsIgnoreCase("MESSAGE_FRIEND_JOIN")) {
            msg = "§e%NAME%§a is now online";
        }
        if (code.equalsIgnoreCase("MESSAGE_FRIEND_LEAVE")) {
            msg = "§e%NAME%§c is now offline";
        }
        if (code.equalsIgnoreCase("MESSAGE_REQUESTS_JOIN")) {
            msg = "§aYou have %REQUEST% Requests open!";
        }
        if (code.equalsIgnoreCase("MESSAGE_JOINMESSAGE_ON")) {
            msg = "§aYou turned Join/Leave Messages on";
        }
        if (code.equalsIgnoreCase("MESSAGE_JOINMESSAGE_OFF")) {
            msg = "§aYou turned Join/Leave Messages off";
        }
        if (code.equalsIgnoreCase("MESSAGE_INVITES_ON")) {
            msg = "§aYou turned Friend Requests on";
        }
        if (code.equalsIgnoreCase("MESSAGE_INVITES_OFF")) {
            msg = "§aYou turned Friend Requests off";
        }
        if (code.equalsIgnoreCase("MESSAGE_MESSAGE_ON")) {
            msg = "§aYou turned Private Messages on";
        }
        if (code.equalsIgnoreCase("MESSAGE_MESSAGE_OFF")) {
            msg = "§aYou turned Private Messages on";
        }
        if (code.equalsIgnoreCase("MESSAGE_ALREADY_INVITED")) {
            msg = "§cYou are already requesting this Player to be your  friend!";
        }
        if (code.equalsIgnoreCase("HOVER_REMOVE")) {
            msg = "§cClick here to remove this Friend";
        }
        if (code.equalsIgnoreCase("HOVER_JUMP")) {
            msg = "§aClick here to jump to this Friend";
        }
        if (code.equalsIgnoreCase("HOVER_MSG")) {
            msg = "§aClick here to message your Friend";
        }
        if (code.equalsIgnoreCase("CODE_REMOVE")) {
            msg = "§4§l[REMOVE] ";
        }
        if (code.equalsIgnoreCase("CODE_JUMP")) {
            msg = "§5§l[JUMP] ";
        }
        if (code.equalsIgnoreCase("CODE_MSG")) {
            msg = "§a§l[MESSAGE] ";
        }
        if (code.equalsIgnoreCase("CODE_ACCEPT")) {
            msg = "§a§l[ACCEPT] ";
        }
        if (code.equalsIgnoreCase("CODE_DENY")) {
            msg = "§c§l[DENY]";
        }
        return msg;
    }
}
