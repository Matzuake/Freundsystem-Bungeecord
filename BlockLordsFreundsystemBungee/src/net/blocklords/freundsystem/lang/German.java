package net.blocklords.freundsystem.lang;

public class German {
    public static String getMessage(String code)
    {
        String msg = "No Message! Please send this to the Developer: " + code;
        if (code.equalsIgnoreCase("HELP_FRIEND_LIST")) {
            msg = "§e/friend list §8- §aZeigt dir alle Freunde in deiner Freundesliste";
        }
        if (code.equalsIgnoreCase("HELP_FRIEND_ADD")) {
            msg = "§e/friend add <Spieler> §8- §aFrage <Spieler> an, ob er dein Freund sein m§chte.";
        }
        if (code.equalsIgnoreCase("HELP_FRIEND_REMOVE")) {
            msg = "§e/friend remove <Spieler> §8- §aEntferne <Spieler> von deiner Freundesliste";
        }
        if (code.equalsIgnoreCase("HELP_FRIEND_JUMP")) {
            msg = "§e/friend jump <Spieler> §8- §aSpringe zu deinem Freund";
        }
        if (code.equalsIgnoreCase("HELP_FRIEND_MSG")) {
            msg = "§e/msg <Spieler> <Nachricht> §8- §aSende <Spieler> eine private Nachricht";
        }
        if (code.equalsIgnoreCase("HELP_FRIEND_TOGGLE")) {
            msg = "§e/friend toggle <Invites/Joinmsg/Msg> §8- §a§ndere deine Freundeseinstellungen";
        }
        if (code.equalsIgnoreCase("MESSAGE_LIST_TOP")) {
            msg = "§m               §r§8| §aDeine Freunde §8|§m               ";
        }
        if (code.equalsIgnoreCase("MESSAGE_LIST_BOTTOM")) {
            msg = "§m                                               ";
        }
        if (code.equalsIgnoreCase("MESSAGE_NOT_IN_MYSQL")) {
            msg = "§cWir konnten diesen Spieler nicht in unserer Datenbank finden!";
        }
        if (code.equalsIgnoreCase("MESSAGE_NOT_ACCEPTING_INVITES")) {
            msg = "§cDieser Spieler nimmt keine Anfragen an!";
        }
        if (code.equalsIgnoreCase("MESSAGE_FULL_FRIENDLIST")) {
            msg = "§cDeine Freundesliste ist voll!";
        }
        if (code.equalsIgnoreCase("MESSAGE_FRIEND_FULL_FRIENDLIST")) {
            msg = "§cVon dem Spieler die Freundesliste ist voll!";
        }
        if (code.equalsIgnoreCase("MESSAGE_SENDED_INVITE")) {
            msg = "§aDu hast erfolgreich eine Anfrage an §e%NAME% §agesendet";
        }
        if (code.equalsIgnoreCase("MESSAGE_GET_INVITE")) {
            msg = "§aDu hast eine Freundesanfrage von §e%NAME% §abekommen.";
        }
        if (code.equalsIgnoreCase("MESSAGE_NO_FRIEND")) {
            msg = "§e%NAME% §cist nicht in deiner Freundesliste!";
        }
        if (code.equalsIgnoreCase("MESSAGE_REMOVE_FRIEND")) {
            msg = "§cDu hast §e%NAME% §cerfolgreich von deiner Freundesliste entfernt!";
        }
        if (code.equalsIgnoreCase("MESSAGE_FRIEND_REMOVE_PLAYER")) {
            msg = "§e%NAME% §chat dich von seiner Freundeslistte entfernt!";
        }
        if (code.equalsIgnoreCase("MESSAGE_NOT_INVITED")) {
            msg = "§cDu hast keine Anfrage von diesem Spieler bekommen!";
        }
        if (code.equalsIgnoreCase("MESSAGE_FRIEND_ACCEPT")) {
            msg = "§aDu bist nun mit §e%NAME%§a befreundet!";
        }
        if (code.equalsIgnoreCase("MESSAGE_NOT_ACCEPTED")) {
            msg = "§cDeine Anfrage an §e%NAME% §cwurde nicht angenommen!";
        }
        if (code.equalsIgnoreCase("MESSAGE_NOT_ACCEPTING")) {
            msg = "§cDu hast die Anfrage von §e%NAME% §cnicht angenommen!";
        }
        if (code.equalsIgnoreCase("MESSAGE_ALREADY_FRIEND")) {
            msg = "§cDu hast diesen Spieler schon in deiner Freundesliste!";
        }
        if (code.equalsIgnoreCase("MESSAGE_SELF_INTERACT")) {
            msg = "§cDu kannst nicht mit dir selbst interagieren!";
        }
        if (code.equalsIgnoreCase("MESSAGE_FRIEND_NOT_ONLINE")) {
            msg = "§cDieser Spieler ist nicht online!";
        }
        if (code.equalsIgnoreCase("MESSAGE_FRIEND_JUMPING")) {
            msg = "§aSpringe §e%NAME% §ahinterher. Springe auf §e%SERVER%";
        }
        if (code.equalsIgnoreCase("MESSAGE_CANT_RECIVE_MESSAGE")) {
            msg = "§cDu kannst diesem Freund nicht anschreiben!";
        }
        if (code.equalsIgnoreCase("MESSAGE_MSG_START")) {
            msg = "§e%SENDER% §a=> §e%GETTER% §a: §3";
        }
        if (code.equalsIgnoreCase("MESSAGE_FRIEND_JOIN")) {
            msg = "§e%NAME%§a ist nun online";
        }
        if (code.equalsIgnoreCase("MESSAGE_FRIEND_LEAVE")) {
            msg = "§e%NAME%§c ist nun offline";
        }
        if (code.equalsIgnoreCase("MESSAGE_REQUESTS_JOIN")) {
            msg = "§aDu hast noch §e%REQUEST% §aAnfragen offen!";
        }
        if (code.equalsIgnoreCase("MESSAGE_JOINMESSAGE_ON")) {
            msg = "§aDu hast Join/Leave Nachrichten angemacht";
        }
        if (code.equalsIgnoreCase("MESSAGE_JOINMESSAGE_OFF")) {
            msg = "§aDu hast Join/Leave Nachrichten ausgemacht";
        }
        if (code.equalsIgnoreCase("MESSAGE_INVITES_ON")) {
            msg = "§aDu hast Anfrage angemacht";
        }
        if (code.equalsIgnoreCase("MESSAGE_INVITES_OFF")) {
            msg = "§aDu hast Anfragen ausgemacht";
        }
        if (code.equalsIgnoreCase("MESSAGE_MESSAGE_ON")) {
            msg = "§aDu hast Private Nachrichten angemacht";
        }
        if (code.equalsIgnoreCase("MESSAGE_MESSAGE_OFF")) {
            msg = "§aDu hast Private Nachrichten ausgemacht";
        }
        if (code.equalsIgnoreCase("MESSAGE_ALREADY_INVITED")) {
            msg = "§cDu hast schon eine Anfrage an diesen Spieler gesendet!";
        }
        if (code.equalsIgnoreCase("HOVER_REMOVE")) {
            msg = "§cKlick hier, um diesen Freund zu entfernen";
        }
        if (code.equalsIgnoreCase("HOVER_JUMP")) {
            msg = "§aKlicke hier, um zu diesem Freund zu springen";
        }
        if (code.equalsIgnoreCase("HOVER_MSG")) {
            msg = "§aKlicke hier, um diesem Freund eine Nachricht zu senden";
        }
        if (code.equalsIgnoreCase("CODE_REMOVE")) {
            msg = "§4§l[ENTFERNEN] ";
        }
        if (code.equalsIgnoreCase("CODE_JUMP")) {
            msg = "§5§l[SPRINGEN] ";
        }
        if (code.equalsIgnoreCase("CODE_MSG")) {
            msg = "§a§l[NACHRICHT] ";
        }
        if (code.equalsIgnoreCase("CODE_ACCEPT")) {
            msg = "§a§l[ANNEHMEN] ";
        }
        if (code.equalsIgnoreCase("CODE_DENY")) {
            msg = "§c§l[ABLEHNEN]";
        }
        return msg;
    }
}
