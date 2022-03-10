package net.elytrapvp.tournamenthelper;

/**
 * Manages the current active tournament from BungeeCord's perspective.
 */
public class TournamentManager {
    private String host;
    private String kit;

    /**
     * Sets up the tournament manager.
     */
    public TournamentManager() {
        host = "";
        kit = "";
    }

    /**
     * Get the host of the tournament.
     * @return Tournament host username.
     */
    public String getHost() {
        return host;
    }

    /**
     * Get the kit of the tournament.
     * @return Tournament kit name.
     */
    public String getKit() {
        return kit;
    }

    /**
     * Get if their is currently a tournament waiting to be started.
     * @return Whether there is a tournament waiting.
     */
    public boolean isTournamentWaiting() {
        return !host.equals("") && !kit.equals("");
    }

    /**
     * Resets the current tournament.
     * Used if the tournament is canceled or started.
     */
    public void resetTournament() {
        host = "";
        kit = "";
    }

    /**
     * Set the host of the waiting tournament.
     * @param host Host of the tournament.
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * Set the kit the waiting tournament is using.
     * @param kit Kit the tournament is using.
     */
    public void setKit(String kit) {
        this.kit = kit;
    }
}