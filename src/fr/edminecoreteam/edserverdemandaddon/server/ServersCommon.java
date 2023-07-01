package fr.edminecoreteam.edserverdemandaddon.server;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import fr.edminecoreteam.edserverdemandaddon.Main;
import fr.edminecoreteam.edserverdemandaddon.mysql.MySQL;

import java.sql.ResultSet;

public class ServersCommon
{
    public static String table;
    private static String displayName;
    int getID;

    static {
        ServersCommon.table = "ed_servers";
    }

    public ServersCommon(String displayName) {
        this.getID = Main.randomID;
        ServersCommon.displayName = displayName;
    }

    public void loadServer(String address, int port, String motd, int onlines, int id) {

        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT server_status FROM " + ServersCommon.table + " WHERE server_name = ?");
            preparedStatement.setString(1, ServersCommon.displayName);
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next()) {
                preparedStatement.close();
                preparedStatement = MySQL.getConnection().prepareStatement("INSERT INTO " + ServersCommon.table + " (server_name, server_status, server_port, server_ip, server_motd, server_onlines, server_id) VALUES (?, ?, ?, ?, ?, ?, ?)");
                preparedStatement.setString(1, ServersCommon.displayName);
                preparedStatement.setInt(2, 1);
                preparedStatement.setInt(3, port);
                preparedStatement.setString(4, address);
                preparedStatement.setString(5, motd);
                preparedStatement.setInt(6, 0);
                preparedStatement.setInt(7, 0);
                preparedStatement.execute();
                preparedStatement.close();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setStatus(int status) {
        try {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE " + ServersCommon.table + " SET server_status = ? WHERE server_name = ?");
            preparedStatement.setInt(1, status);
            preparedStatement.setString(2, ServersCommon.displayName);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void setMotd(String s) {
        try {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE " + ServersCommon.table + " SET server_motd = ? WHERE server_name = ?");
            preparedStatement.setString(1, s);
            preparedStatement.setString(2, ServersCommon.displayName);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setOnlinesPlayers(int s) {
        try {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE " + ServersCommon.table + " SET server_onlines = ? WHERE server_name = ?");
            preparedStatement.setInt(1, s);
            preparedStatement.setString(2, ServersCommon.displayName);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getProxyStatus()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT server_status FROM " + ServersCommon.table + " WHERE server_name = ?");
            preparedStatement.setString(1, "ProxyNetwork");
            ResultSet rs = preparedStatement.executeQuery();
            int status = 0;
            while (rs.next())
            {
                status = rs.getInt("server_status");
            }
            preparedStatement.close();
            return status;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void setID(int s) {
        try {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE " + ServersCommon.table + " SET server_id = ? WHERE server_name = ?");
            preparedStatement.setInt(1, s);
            preparedStatement.setString(2, ServersCommon.displayName);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getMotd()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT server_motd FROM " + table + " WHERE server_name = ?");
            preparedStatement.setString(1, displayName);
            ResultSet rs = preparedStatement.executeQuery();
            String port = "";
            while (rs.next())
            {
                port = rs.getString("server_motd");
            }
            preparedStatement.close();
            return port;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

}
