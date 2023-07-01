package fr.edminecoreteam.edserverdemandaddon.mysql;

import java.sql.SQLException;

import org.bukkit.plugin.Plugin;

import fr.edminecoreteam.edserverdemandaddon.Main;
import java.sql.DriverManager;
import java.sql.Connection;

public class MySQL
{
    private MySQL instance;
    private Main main;
    private String urlBase;
    private String host;
    private String database;
    private String userName;
    private String password;
    private static Connection connection;

    public MySQL(Main main,String urlBase, String host, String database, String userName, String password) {
        this.main = main;
        this.urlBase = urlBase;
        this.host = host;
        this.database = database;
        this.userName = userName;
        this.password = password;
        this.connexion();
    }

    public static Connection getConnection() {
        return MySQL.connection;
    }

    public void connexion() {
        if (!isOnline()) {
            try {
                MySQL.connection = DriverManager.getConnection(String.valueOf(this.urlBase) + this.host + "/" + this.database, this.userName, this.password);

                SQLTasks start = new SQLTasks(main, instance);
                start.runTaskTimer((Plugin)main, 0L, 20L);

                message("connectMSG");
            }
            catch (SQLException e) {
                e.toString();
                System.out.println("§cErreur de connexion a la base de donnée...");
            }
        }
    }

    public void deconnexion() {
        if (!isOnline()) {
            try {
                MySQL.connection.close();
                message("disconnectMSG");
            }
            catch (SQLException e) {
                e.toString();
            }
        }
    }

    public boolean isOnline() {
        try {
            return MySQL.connection != null && !MySQL.connection.isClosed();
        }
        catch (SQLException e) {
            e.toString();
            return false;
        }
    }

    private void message(String condition) {
        if (condition == "connectMSG")
        {
            System.out.println("+--------------------+");
            System.out.println("ED-SERVER-DEMAND");
            System.out.println("ORM: Enable");
            System.out.println("ORM-DATABASE: Connected");
            System.out.println("+--------------------+");
        }
        if (condition == "disconnectMSG")
        {
            System.out.println("+--------------------+");
            System.out.println("ED-SERVER-DEMAND");
            System.out.println("ORM: Disable");
            System.out.println("ORM-DATABASE: Disconnected");
            System.out.println("+--------------------+");
        }
    }
}
