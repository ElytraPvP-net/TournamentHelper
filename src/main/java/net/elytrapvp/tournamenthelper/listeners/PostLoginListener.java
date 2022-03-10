package net.elytrapvp.tournamenthelper.listeners;

import net.elytrapvp.tournamenthelper.TournamentHelper;
import net.elytrapvp.tournamenthelper.utils.ChatUtils;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.concurrent.TimeUnit;

/**
 * This listens to the PostLoginEvent event, which is called every time a player successfully joins the server.
 * We will use this to tell players about an active tournament when they first join.
 */
public class PostLoginListener implements Listener {
    private final TournamentHelper plugin;

    /**
     * To be able to access the configuration files, we need to pass an instance of the plugin to our listener.
     * This is known as Dependency Injection.
     * @param plugin Instance of the plugin.
     */
    public PostLoginListener(TournamentHelper plugin) {
        this.plugin = plugin;
    }

    /**
     * Runs when the event is called.
     * @param event PostLoginEvent.
     */
    @EventHandler
    public void onJoin(PostLoginEvent event) {
        // Make sure there is a tournament waiting.
        if(!plugin.getTournamentManager().isTournamentWaiting()) {
            return;
        }

        // If there is, announce the message delayed so that it is the most recent message after joining.
        plugin.getProxy().getScheduler().schedule(plugin, () -> {
            ProxiedPlayer player = event.getPlayer();
            String host = plugin.getTournamentManager().getHost();
            String kit = plugin.getTournamentManager().getKit();

            ChatUtils.chat(player, "&a&lTournament &8» &f" + host + " &ais hosting a &f" + kit + " &atournament! &f/join &ato join.");
        }, 1, TimeUnit.SECONDS);
    }
}