package fr.edminecoreteam.edserverdemandaddon.mysql;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import fr.edminecoreteam.edserverdemandaddon.Main;

public class SQLTasks extends BukkitRunnable
{
    public int timer;
    private Main main;
    public MySQL database;

    public SQLTasks(Main api, MySQL database) {
        this.database = database;
        this.main = api;
        this.timer = 10;
    }

    @Override
    public void run() {
        if (timer == 0)
        {
            if (!main.database.isOnline())
            {
                main.MySQLConnect();
                cancel();
            }
            else
            {
                SQLTasks start = new SQLTasks(main, database);
                start.runTaskTimer((Plugin)main, 0L, 20L);
                main.database.isOnline();
                cancel();
            }
        }
        --timer;
    }
}
