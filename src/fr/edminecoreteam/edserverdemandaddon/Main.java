package fr.edminecoreteam.edserverdemandaddon;

import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scheduler.BukkitRunnable;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import fr.edminecoreteam.edserverdemandaddon.config.Task;
import fr.edminecoreteam.edserverdemandaddon.mysql.MySQL;
import fr.edminecoreteam.edserverdemandaddon.server.CheckerOnlines;
import fr.edminecoreteam.edserverdemandaddon.server.ServersCommon;
import fr.edminecoreteam.edserverdemandaddon.utils.ChangeHubData;
import fr.edminecoreteam.edserverdemandaddon.utils.FoundLobby;


public class Main extends JavaPlugin implements PluginMessageListener
{
    private static Main instance;
    public MySQL database;
    public static int randomID;
    public ServersCommon serverCommon;
    public int srvNumber;
    public int timeStop = 5000;
    int getID;

    private State state;

    static {
        Main.randomID = ThreadLocalRandom.current().nextInt(100000, 999999);
    }

    public Main() {
        this.getID = Main.randomID;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);

        MySQLConnect();
        loadServerInfo();
        startChecker();
        timerForStopServer();
        FoundLobby fLobby = new FoundLobby();
        srvNumber = fLobby.getServerPerGroup();
        CheckerOnlines.checkerOnlinesPlayerWhereServer();
        checkMinLobby();
    }

    @Override
    public void onDisable() {
        unloadServerInfo();
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
        this.getServer().getMessenger().unregisterIncomingPluginChannel(this);

        MySQLDisconnect();
    }

    public void MySQLConnect()
    {
        instance = this;

        (database = new MySQL(instance, "jdbc:mysql://", "45.140.165.235", "22728-database", "22728-database", "S5bV5su4p9")).connexion();
    }

    /*
     * Méthode de déconnexion au serveur SQL.
     */
    private void MySQLDisconnect()
    {
        database.deconnexion();
    }

    private void loadServerInfo()
    {
        Main.instance = this;

        (this.serverCommon = new ServersCommon(Bukkit.getServerName())).loadServer(Bukkit.getIp(), Bukkit.getPort(), "", 0, 0);

        serverCommon.setStatus(1);
        serverCommon.setID(Main.randomID);

        CheckerOnlines.checkerIfProxyIsOnline();
    }

    public void startChecker()
    {
        Main.instance = this;

        Task start = new Task(this);
        start.runTaskTimer((Plugin)this, 0L, 20L);
    }

    public void checkMinLobby()
    {
        if (getConfig().getString("Server.Type").equalsIgnoreCase("LOBBY"))
        {
            ChangeHubData data = new ChangeHubData("Lobby");
            if (data.getServer().size() < 2)
            {
                serverCommon.setStatus(2);
            }
        }
    }

    public void timerForStopServer() {
        new BukkitRunnable() {
            int t = 5000;

            public void run() {

                if (getConfig().getString("Server.Type").equalsIgnoreCase("LOBBY"))
                {
                    if (t == 600)
                    {
                        for (Player p : Bukkit.getServer().getOnlinePlayers())
                        {
                            messageEnd(p, t);
                            p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
                        }
                    }
                    if (t == 300)
                    {
                        for (Player p : Bukkit.getServer().getOnlinePlayers())
                        {
                            messageEnd2(p, t);
                            p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
                        }
                    }
                    if (t == 200)
                    {
                        for (Player p : Bukkit.getServer().getOnlinePlayers())
                        {
                            messageEnd2(p, t);
                            p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
                        }
                    }
                    if (t == 100)
                    {
                        for (Player p : Bukkit.getServer().getOnlinePlayers())
                        {
                            messageEnd2(p, t);
                            p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
                        }
                    }
                    if (t == 60)
                    {
                        for (Player p : Bukkit.getServer().getOnlinePlayers())
                        {
                            messageEnd2(p, t);
                            p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
                        }
                        serverCommon.setStatus(2);
                    }
                    if (t == 30)
                    {
                        for (Player p : Bukkit.getServer().getOnlinePlayers())
                        {
                            messageEnd2(p, t);
                            p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
                        }
                    }
                    if (t == 20)
                    {
                        for (Player p : Bukkit.getServer().getOnlinePlayers())
                        {
                            messageEnd2(p, t);
                            p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
                        }
                    }
                    if (t == 10)
                    {
                        for (Player p : Bukkit.getServer().getOnlinePlayers())
                        {
                            messageEnd2(p, t);
                            p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
                        }
                    }
                    if (t == 5)
                    {
                        for (Player p : Bukkit.getServer().getOnlinePlayers())
                        {
                            messageEnd2(p, t);
                            p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                        }
                    }
                    if (t == 4)
                    {
                        for (Player p : Bukkit.getServer().getOnlinePlayers())
                        {
                            messageEnd2(p, t);
                            p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                        }
                    }
                    if (t == 3)
                    {
                        for (Player p : Bukkit.getServer().getOnlinePlayers())
                        {
                            messageEnd2(p, t);
                            p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                        }
                    }
                    if (t == 2)
                    {
                        for (Player p : Bukkit.getServer().getOnlinePlayers())
                        {
                            messageEnd2(p, t);
                            FoundLobby.foundLobby(p);
                            p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                        }
                    }
                    if (t == 1)
                    {
                        for (Player p : Bukkit.getServer().getOnlinePlayers())
                        {
                            messageEnd2(p, t);
                            FoundLobby.foundLobby(p);
                            p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                        }
                    }
                    if (t == 0)
                    {
                        for (Player p : Bukkit.getServer().getOnlinePlayers())
                        {
                            FoundLobby.foundLobby(p);
                            p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                        }
                        Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "stop");
                        cancel();
                    }
                }
                if (getConfig().getString("Server.Type").equalsIgnoreCase("GAME"))
                {
                    if (isState(State.ATTENTE) && Bukkit.getOnlinePlayers().size() <= 1)
                    {
                        if (t == 5)
                        {
                            for (Player p : Bukkit.getServer().getOnlinePlayers())
                            {
                                messageEnd2(p, t);
                            }
                        }
                        if (t == 4)
                        {
                            for (Player p : Bukkit.getServer().getOnlinePlayers())
                            {
                                messageEnd2(p, t);
                            }
                        }
                        if (t == 3)
                        {
                            for (Player p : Bukkit.getServer().getOnlinePlayers())
                            {
                                messageEnd2(p, t);
                            }
                        }
                        if (t == 2)
                        {
                            for (Player p : Bukkit.getServer().getOnlinePlayers())
                            {
                                messageEnd2(p, t);
                                FoundLobby.foundLobby(p);
                            }
                        }
                        if (t == 1)
                        {
                            for (Player p : Bukkit.getServer().getOnlinePlayers())
                            {
                                messageEnd2(p, t);
                                FoundLobby.foundLobby(p);
                            }
                        }
                        if (t == 0)
                        {
                            for (Player p : Bukkit.getServer().getOnlinePlayers())
                            {
                                FoundLobby.foundLobby(p);
                            }
                            Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "stop");
                            cancel();
                        }
                    }
                }
                if (getConfig().getString("Server.Type").equalsIgnoreCase("LOGIN"))
                {
                    if (t == 5)
                    {
                        for (Player p : Bukkit.getServer().getOnlinePlayers())
                        {
                            messageEnd2(p, t);
                        }
                    }
                    if (t == 4)
                    {
                        for (Player p : Bukkit.getServer().getOnlinePlayers())
                        {
                            messageEnd2(p, t);
                        }
                    }
                    if (t == 3)
                    {
                        for (Player p : Bukkit.getServer().getOnlinePlayers())
                        {
                            messageEnd2(p, t);
                        }
                    }
                    if (t == 2)
                    {
                        for (Player p : Bukkit.getServer().getOnlinePlayers())
                        {
                            messageEnd2(p, t);
                        }
                    }
                    if (t == 1)
                    {
                        for (Player p : Bukkit.getServer().getOnlinePlayers())
                        {
                            messageEnd2(p, t);
                            p.kickPlayer("§cVeuillez vous reconnecter...");
                        }
                    }
                    if (t == 0)
                    {
                        for (Player p : Bukkit.getServer().getOnlinePlayers())
                        {
                            p.kickPlayer("§cVeuillez vous reconnecter...");
                        }
                        Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "stop");
                        cancel();
                    }
                }

                --t;
            }
        }.runTaskTimer((Plugin)this, 0L, 20L);
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean isState(State state) {
        return this.state == state;
    }

    /*
     * Méthode d'envoie d'informations du serveur mc au serveur SQL.
     */
    private void unloadServerInfo() {
        this.serverCommon = new ServersCommon(Bukkit.getServerName());
        serverCommon.setStatus(0);
        serverCommon.setID(0);
        ServersCommon.setOnlinesPlayers(0);
        ServersCommon.setMotd(null);
    }

    public int getTimeStop() {
        return timeStop;
    }

    public void messageEnd(Player p, int t) {
        p.sendMessage("");
        p.sendMessage(" §6⚠ §6§lInformation importante §6⚠");
        p.sendMessage(" §c§lInformations:");
        p.sendMessage(" §7• §fLe serveur où vous êtes");
        p.sendMessage(" §7  §fconnécté a besoin de repos...");
        p.sendMessage("");
        p.sendMessage(" §7• §fCe serveur ferme dans: §e" + convertTime(t));
        p.sendMessage("");
    }

    public void messageEnd2(Player p, int t) {
        p.sendMessage("");
        p.sendMessage(" §7• §fCe serveur ferme dans: §e" + convertTime(t));
        p.sendMessage("");
    }

    public String convertTime(int timeInSeconds) {
        int minutes = timeInSeconds / 60;
        int seconds = timeInSeconds % 60;
        return String.format("%02dm %02ds", minutes, seconds);
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message)
    {
        if (!channel.equals("BungeeCord"))
        {
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();
        if (subchannel.equals("SomeSubChannel"))
        {
            // Use the code sample in the 'Response' sections below to read
            // the data.
        }
    }

    public static Main getInstance() {  return Main.instance; }
    public static Plugin getPlugin() { return null; }
}
