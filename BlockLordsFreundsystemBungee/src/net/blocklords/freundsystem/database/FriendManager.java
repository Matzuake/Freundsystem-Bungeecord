package net.blocklords.freundsystem.database;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FriendManager {
    public static List<String> getAllFriends(String player)
    {
        List<String> friends = new ArrayList();
        try
        {
            ResultSet rs = MySQL.getResult("SELECT * FROM Friends WHERE Playername = '" + player + "'");
            while (rs.next()) {
                friends.add(rs.getString("Friendname"));
            }
        }
        catch (Exception ex)
        {
            MySQL.connect();
            return getAllFriends(player);
        }
        return friends;
    }

    public static void addFriend(String player, String friend)
    {
        try
        {
            String puuid = PlayerInfoManager.getUUID(player);
            String fuuid = PlayerInfoManager.getUUID(friend);

            MySQL.update("INSERT INTO Friends (Playername, PlayerUUID, Friendname, FriendUUID) VALUES ('" + player + "', '" + puuid + "', '" + friend + "', '" + fuuid + "')");
            MySQL.update("INSERT INTO Friends (Playername, PlayerUUID, Friendname, FriendUUID) VALUES ('" + friend + "', '" + fuuid + "', '" + player + "', '" + puuid + "')");
        }
        catch (Exception ex)
        {
            MySQL.connect();
            addFriend(player, friend);
        }
    }

    public static void removeFriend(String player, String friend)
    {
        try
        {
            MySQL.update("DELETE FROM Friends WHERE Playername = '" + player + "' AND Friendname = '" + friend + "'");
            MySQL.update("DELETE FROM Friends WHERE Friendname = '" + player + "' AND Playername = '" + friend + "'");
        }
        catch (Exception ex)
        {
            MySQL.connect();
            removeFriend(player, friend);
        }
    }

    public static boolean isFriend(String player, String player2)
    {
        try
        {
            ResultSet rs = MySQL.getResult("SELECT * FROM Friends WHERE Playername = '" + player + "'");
            while (rs.next())
            {
                String friendname = rs.getString("Friendname");
                if (player2.equalsIgnoreCase(friendname)) {
                    return true;
                }
            }
        }
        catch (Exception ex)
        {
            MySQL.connect();
            return isFriend(player, player2);
        }
        return false;
    }

    public static int getFriends(String player)
    {
        return getAllFriends(player).size();
    }
}
