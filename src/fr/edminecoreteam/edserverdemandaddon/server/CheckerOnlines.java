package fr.edminecoreteam.edserverdemandaddon.server;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import fr.edminecoreteam.edserverdemandaddon.Main;

public class CheckerOnlines
{
    private static Main main = Main.getInstance();

    public static void checkerOnlinesPlayerWhereServer() {
        if (main.getConfig().getString("Server.Type").equalsIgnoreCase("LOBBY"))
        {
            new BukkitRunnable() {
                int t = 0;

                public void run() {
                    ServersCommon.setOnlinesPlayers(Bukkit.getOnlinePlayers().size());
                    int size = Bukkit.getOnlinePlayers().size();
                    if (size < 40)
                    {
                        ServersCommon.setMotd("Faible");
                    }
                    if (size >= 40 && size < 60)
                    {
                        ServersCommon.setMotd("Moyen");
                    }
                    if (size >= 60)
                    {
                        ServersCommon.setMotd("Fort");
                    }
                    ++this.t;
                    if (this.t == 100) {
                        this.t = 0;
                    }
                }
            }.runTaskTimer((Plugin)Main.getInstance(), 0L, 100L);
        }
        if (main.getConfig().getString("Server.Type").equalsIgnoreCase("GAME"))
        {
            new BukkitRunnable() {
                int t = 0;

                public void run() {
                    ServersCommon.setOnlinesPlayers(Bukkit.getOnlinePlayers().size());
                    ++this.t;
                    if (this.t == 100) {
                        this.t = 0;
                    }
                }
            }.runTaskTimer((Plugin)Main.getInstance(), 0L, 40L);
        }
    }

    public static void checkerIfProxyIsOnline() {
        new BukkitRunnable() {
            int t = 0;

            public void run() {
                int status = ServersCommon.getProxyStatus();
                if (status == 0)
                {
                    Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "stop");
                    cancel();
                }
                ++this.t;
                if (this.t == 3600) {
                    this.run();
                }
            }
        }.runTaskTimer((Plugin)Main.getInstance(), 0L, 3600L);
    }
}
