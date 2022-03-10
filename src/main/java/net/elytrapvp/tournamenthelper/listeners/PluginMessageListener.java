package net.elytrapvp.tournamenthelper.listeners;

import net.elytrapvp.tournamenthelper.TournamentHelper;
import net.elytrapvp.tournamenthelper.utils.ChatUtils;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * This listens to the PluginMessageEvent event, which is called every time a plugin sends a message to bungeecord.
 * We use this to have the tournament plugin tell bungeecord when an event is created in order to announce it to all
 * players on the network.
 */
public class PluginMessageListener implements Listener {
    private final TournamentHelper plugin;

    /**
     * To be able to access the configuration files, we need to pass an instance of the plugin to our listener.
     * This is known as Dependency Injection.
     * @param plugin Instance of the plugin.
     */
    public PluginMessageListener(TournamentHelper plugin) {
        this.plugin = plugin;
    }

    /**
     * Runs when the event is called.
     * @param event PluginMessageEvent.
     */
    @EventHandler
    public void onMessage(PluginMessageEvent event) {
        if(!event.getTag().equalsIgnoreCase("Tournament")) {
            return;
        }

        // Used to accept the message from the tournament server.
        DataInputStream input = new DataInputStream(new ByteArrayInputStream(event.getData()));
        try {
            // Reads the message and splits it into an array.
            String[] results = input.readUTF().split("-");

            // Checks what the first part of the message says.
            switch (results[0]) {
                // If the message is cancel, it cancels the tournament.
                case "cancel" -> plugin.getTournamentManager().resetTournament();

                // If the message is host, it reads the rest of the message.
                case "host" -> {
                    // If the message isn't complete, it is ignored.
                    if(results.length != 3) {
                        return;
                    }

                    // Tell tournament manager about the change
                    plugin.getTournamentManager().setHost(results[1]);
                    plugin.getTournamentManager().setKit(results[1]);

                    String host = results[1];
                    String kit = results[2];

                    // Loops through all online players to announce the creation of the tournament.
                    for(ProxiedPlayer player : plugin.getProxy().getPlayers()) {
                        // Going to leave this section commented out for now in case we want to re-instate it.
                        /*
                        // Checks if the player is in the tournament server already
                        if(player.getServer().getInfo().getName().equalsIgnoreCase("tournament") {
                            continue;
                        }
                         */
                        ChatUtils.chat(player, "&a&lTournament &8» &f" + host + " &ais hosting a &f" + kit + " &atournament! &f/join &ato join.");
                    }
                }
            }
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
