package fr.edminecoreteam.edserverdemandaddon.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import fr.edminecoreteam.edserverdemandaddon.Main;
import fr.edminecoreteam.edserverdemandaddon.mysql.MySQL;

public class FoundLobby
{
    public static Main api = Main.getInstance();

    public static void foundLobby(Player player)
    {
        ChangeHubInfo srvInfo = new ChangeHubInfo("Lobby");
        List<String> srvList = srvInfo.getServer();
        List<String> srvFaible = new ArrayList<String>();
        List<String> srvMoyen = new ArrayList<String>();
        List<String> srvFort = new ArrayList<String>();

        for (String s : srvList)
        {
            ChangeHubInfo sInfo = new ChangeHubInfo(s);
            if (sInfo.getServerMotd().equalsIgnoreCase("Faible"))
            {
                srvFaible.add(s);
            }
            else if (sInfo.getServerMotd().equalsIgnoreCase("Moyen"))
            {
                srvMoyen.add(s);
            }
            else if (sInfo.getServerMotd().equalsIgnoreCase("Fort"))
            {
                srvFort.add(s);
            }
        }

        if (srvFaible.size() != 0)
        {
            String srv = srvFaible.get(0);
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF(srv);
            player.sendPluginMessage((Plugin)api, "BungeeCord", out.toByteArray());
        }
        else
        {
            if (srvMoyen.size() != 0)
            {
                String srv = srvMoyen.get(0);
                ByteArrayDataOutput out = ByteStreams.newDataOutput();
                out.writeUTF("Connect");
                out.writeUTF(srv);
                player.sendPluginMessage((Plugin)api, "BungeeCord", out.toByteArray());
            }
            else
            {
                if (srvFort.size() != 0)
                {
                    String srv = srvMoyen.get(0);
                    ByteArrayDataOutput out = ByteStreams.newDataOutput();
                    out.writeUTF("Connect");
                    out.writeUTF(srv);
                    player.sendPluginMessage((Plugin)api, "BungeeCord", out.toByteArray());
                }
                else
                {
                    player.sendMessage("§cErreur, il semble que aucun Lobby ne soit disponible...");
                }
            }
        }
    }

    public int getServerPerGroup()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT server_id FROM ed_servers WHERE server_name = ?");
            preparedStatement.setString(1, "ProxyNetwork");
            int result = 0;
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                result = rs.getInt("server_id");
            }
            preparedStatement.close();
            return result;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
    }
}
