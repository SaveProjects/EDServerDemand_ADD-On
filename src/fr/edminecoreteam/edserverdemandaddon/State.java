//
// Decompiled by Procyon v0.5.36
//

package fr.edminecoreteam.edserverdemandaddon;

public enum State
{
    WHITELIST("WHITELIST", 0),
    FAIBLE("FAIBLE", 1),
    MOYEN("MOYEN", 2),
    FORT("FORT", 3),
    COMPLET("COMPLET", 4),
    ATTENTE("ATTENTE", 5),
    DEMARRAGE("DÉMARRAGE", 6),
    ENJEU("ENJEU", 7),
    FINI("FINI", 8);

    private State(String name, int ordinal) {
    }
}
