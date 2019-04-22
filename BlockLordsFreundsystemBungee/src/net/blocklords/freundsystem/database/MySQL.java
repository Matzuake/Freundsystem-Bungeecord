package net.blocklords.freundsystem.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MySQL {
    public static String username = "";
    public static String password = "";
    public static String database = "";
    public static String host = "";
    public static String port = "3306";
    public static Connection con;

    public static void connect()
    {
        if (!isConnected()) {
            try
            {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" +
                        port + "/" + database, username, password);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void close()
    {
        if (isConnected()) {
            try
            {
                con.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static boolean isConnected()
    {
        return con != null;
    }

    public static void createTable()
    {
        if (isConnected()) {
            try
            {
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS Friends (Playername TEXT,PlayerUUID TEXT,Friendname TEXT,FriendUUID TEXT,RID int(11) NOT NULL auto_increment,primary KEY (RID));");
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS Invite (PlayerUUID TEXT,FriendUUID TEXT,RID int(11) NOT NULL auto_increment,primary KEY (RID));");
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS PlayerInfo (Playername TEXT,PlayerUUID TEXT,isOnline INT(12),Server TEXT, RID int(11) NOT NULL auto_increment,primary KEY (RID));");
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS Settings (Playername TEXT,PlayerUUID TEXT,getMsg TEXT,getJoinMsg TEXT,getInvites TEXT,RID int(11) NOT NULL auto_increment,primary KEY (RID));");
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void update(String qry)
    {
        try
        {
            con.createStatement().executeUpdate(qry);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static ResultSet getResult(String qry)
    {
        try
        {
            return con.createStatement().executeQuery(qry);
        }
        catch (SQLException e)
        {
            connect();
        }
        return getResult(qry);
    }

    public static boolean isInDatabase(ProxiedPlayer player)
    {
        try
        {
            ResultSet rs = getResult("SELECT * FROM PlayerInfo WHERE PlayerUUID = '" + player.getUniqueId().toString() + "'");
            if (rs.next())
            {
                update("UPDATE PlayerInfo SET Playername = '" + player.getName() + "' WHERE PlayerUUID = '" + player.getUniqueId().toString() + "'");
                update("UPDATE Friends SET Playername = '" + player.getName() + "' WHERE PlayerUUID = '" + player.getUniqueId().toString() + "'");
                update("Update Friends SET Friendname = '" + player.getName() + "' WHERE FriendUUID = '" + player.getUniqueId().toString() + "'");
                update("UPDATE Settings SET Playername = '" + player.getName() + "' WHERE PlayerUUID = '" + player.getName().toString() + "'");
                return true;
            }
            return false;
        }
        catch (Exception ex)
        {
            connect();
        }
        return isInDatabase(player);
    }

    public static boolean isInDatabase(String player)
    {
        try
        {
            ResultSet rs = getResult("SELECT * FROM PlayerInfo WHERE Playername = '" + player + "'");
            if (rs.next()) {
                return true;
            }
            return false;
        }
        catch (Exception ex)
        {
            connect();
        }
        return isInDatabase(player);
    }

    public static void addPlayerToDatabase(ProxiedPlayer player)
    {
        try
        {
            update("INSERT INTO PlayerInfo (Playername, PlayerUUID, isOnline, Server) VALUES ('" + player.getName() + "', '" + player.getUniqueId().toString() + "', '1', 'here')");
            update("INSERT INTO Settings (Playername, PlayerUUID, getMsg, getJoinMsg, getInvites) VALUES ('" + player.getName() + "', '" + player.getUniqueId().toString() + "', 'true', 'true', 'true')");
        }
        catch (Exception ex)
        {
            connect();
            addPlayerToDatabase(player);
        }
    }
}
