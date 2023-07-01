package fr.edminecoreteam.edserverdemandaddon.config;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import fr.edminecoreteam.edserverdemandaddon.Main;
import fr.edminecoreteam.edserverdemandaddon.State;
import fr.edminecoreteam.edserverdemandaddon.mysql.MySQL;

public class MOTDData
{
    private Main main;

    public MOTDData()
    {
        this.main = Main.getInstance();
    }

    public void setMOTD(String status)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_servers SET server_motd = ? WHERE server_name = ?");

            if (status.equalsIgnoreCase("whitelist"))
            {
                preparedStatement.setString(1, "Whitelist");
                main.setState(State.WHITELIST);
            }
            else if (status.equalsIgnoreCase("faible"))
            {
                preparedStatement.setString(1, "Faible");
                main.setState(State.FAIBLE);
            }
            else if (status.equalsIgnoreCase("moyen"))
            {
                preparedStatement.setString(1, "Moyen");
                main.setState(State.MOYEN);
            }
            else if (status.equalsIgnoreCase("fort"))
            {
                preparedStatement.setString(1, "Fort");
                main.setState(State.FORT);
            }
            else if (status.equalsIgnoreCase("complet"))
            {
                preparedStatement.setString(1, "Complet");
                main.setState(State.COMPLET);
            }
            else if (status.equalsIgnoreCase("attente"))
            {
                preparedStatement.setString(1, "Attente");
                main.setState(State.ATTENTE);
            }
            else if (status.equalsIgnoreCase("demarrage"))
            {
                preparedStatement.setString(1, "Démarrage");
                main.setState(State.DEMARRAGE);
            }
            else if (status.equalsIgnoreCase("enjeu"))
            {
                preparedStatement.setString(1, "En-Jeu");
                main.setState(State.ENJEU);
            }
            else if (status.equalsIgnoreCase("fini"))
            {
                preparedStatement.setString(1, "Fini");
                main.setState(State.FINI);
            }
            preparedStatement.setString(2, main.getServer().getServerName().toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
