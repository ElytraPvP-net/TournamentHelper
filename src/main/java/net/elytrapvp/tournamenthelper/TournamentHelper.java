package net.elytrapvp.tournamenthelper;

import net.elytrapvp.tournamenthelper.commands.JoinCMD;
import net.elytrapvp.tournamenthelper.listeners.PluginMessageListener;
import net.elytrapvp.tournamenthelper.listeners.PostLoginListener;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

/**
 * This plugin receives messages from ElytraTournament and announces create tournaments to all players on a
 * Bungeecord Network.
 */
public final class TournamentHelper extends Plugin implements Listener {
    private TournamentManager tournamentManager;

    /**
     * This is called when BungeeCord first loads the plugin.
     */
    @Override
    public void onEnable() {
        // Plugin startup logic
        tournamentManager = new TournamentManager();

        // We need to tell BungeeCord that our listeners exist for them to work.
        getProxy().getPluginManager().registerListener(this, new PluginMessageListener(this));
        getProxy().getPluginManager().registerListener(this, new PostLoginListener(this));

        // Tells BungeeCord to listen to messages sent on the "Tournament" channel.
        getProxy().registerChannel("Tournament");

        // We must also tell BungeeCord that our commands exist.
        getProxy().getPluginManager().registerCommand(this, new JoinCMD());
    }

    /**
     * Get the tournament manager, which hold what host and kit is being used for a tournament.
     * @return Tournament manager.
     */
    public TournamentManager getTournamentManager() {
        return tournamentManager;
    }
}
