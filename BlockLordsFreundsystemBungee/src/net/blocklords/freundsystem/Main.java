package net.blocklords.freundsystem;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.blocklords.freundsystem.commands.Friend;
import net.blocklords.freundsystem.commands.Msg;
import net.blocklords.freundsystem.database.MySQL;
import net.blocklords.freundsystem.listener.PlayerConnectListener;
import net.blocklords.freundsystem.listener.PlayerDisconnectListener;
import net.blocklords.freundsystem.listener.PlayerSwitchServer;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class Main extends Plugin {

    public static String lang = "";
    public static List<String> disabledServer = new ArrayList();

    @Override
    public void onEnable() {
        ProxyServer.getInstance().getPluginManager().registerListener(this, new PlayerConnectListener());
        ProxyServer.getInstance().getPluginManager().registerListener(this, new PlayerDisconnectListener());
        ProxyServer.getInstance().getPluginManager().registerListener(this, new PlayerSwitchServer());
        //ProxyServer.getInstance().getPluginManager().registerCommand(this, new Msg("msg"));
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new Friend("friend"));
        try
        {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdir();
            }
            File file = new File(getDataFolder().getPath(), "config.yml");
            if (!file.exists())
            {
                file.createNewFile();
                Configuration cfg = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
                cfg.set("MySQL.Host", "HOST");
                cfg.set("MySQL.User", "USERNAME");
                cfg.set("MySQL.Password", "Password");
                cfg.set("MySQL.Database", "DATABASE");
                cfg.set("MySQL.Port", "3306");
                cfg.set("lang", "English");
                cfg.set("disableCommand", new ArrayList());
                ConfigurationProvider.getProvider(YamlConfiguration.class).save(cfg, file);
            }
            Configuration cfg = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);

            MySQL.host = cfg.getString("MySQL.Host");
            MySQL.username = cfg.getString("MySQL.User");
            MySQL.database = cfg.getString("MySQL.Database");
            MySQL.password = cfg.getString("MySQL.Password");

            MySQL.connect();
            MySQL.createTable();
            lang = cfg.getString("lang");
            disabledServer = cfg.getStringList("disableCommand");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }


}
