package fr.edminecoreteam.edserverdemandaddon.server;

public enum ServerStatus
{
    NEEDSTART("NEEDSTART", 0, 2),
    ONLINE("ONLINE", 1, 1),
    OFFLINE("OFFLINE", 2, 0);

    public int status;
    private ServerStatus(String name, int ordinal, int status)
    {
        this.status = status;
    }
}
