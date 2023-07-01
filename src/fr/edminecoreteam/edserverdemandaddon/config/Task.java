package fr.edminecoreteam.edserverdemandaddon.config;


import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import fr.edminecoreteam.edserverdemandaddon.Main;
import fr.edminecoreteam.edserverdemandaddon.State;
import fr.edminecoreteam.edserverdemandaddon.server.ServersCommon;

public class Task extends BukkitRunnable
{
    public int timer;
    private Main main;


    public Task(Main main) {
        this.timer = 20;
        this.main = main;
    }

    public void run() {

        if (timer == 20 || timer == 15 || timer == 10 || timer == 5)
        {
            if (main.getConfig().getString("Server.Type").equalsIgnoreCase("LOBBY"))
            {
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
            }
            else if (main.getConfig().getString("Server.Type").equalsIgnoreCase("GAME"))
            {
                ServersCommon.setOnlinesPlayers(Bukkit.getOnlinePlayers().size());
                ServersCommon srvCommon = new ServersCommon(Bukkit.getServer().getName());
                if (srvCommon.getMotd().equalsIgnoreCase("WAITING"))
                {
                    main.setState(State.ATTENTE);
                }
                if (srvCommon.getMotd().equalsIgnoreCase("STARTING"))
                {
                    main.setState(State.DEMARRAGE);
                }
                if (srvCommon.getMotd().equalsIgnoreCase("INGAME"))
                {
                    main.setState(State.ENJEU);
                }
                if (srvCommon.getMotd().equalsIgnoreCase("FINISH"))
                {
                    main.setState(State.FINI);
                }
            }
        }

        if (timer == 0)
        {
            timer = 20;
        }
        --timer;
    }
}

