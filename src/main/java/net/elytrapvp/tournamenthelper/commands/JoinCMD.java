package net.elytrapvp.tournamenthelper.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * This class runs the join command, which brings players to the tournament server.
 */
public class JoinCMD extends Command {

    /**
     * Creates the /join command with no permissions or aliases.
     */
    public JoinCMD() {
        super("join");
    }

    /**
     * This is the code that runs when the command is sent.
     * @param sender The player (or console) that sent the command.
     * @param args The arguments of the command.
     */
    @Override
    public void execute(CommandSender sender, String[] args) {
        // We can only send players to servers.
        if(!(sender instanceof ProxiedPlayer player)) {
            return;
        }

        // Sends that player to the tournament server.
        player.connect(ProxyServer.getInstance().getServerInfo("tournament"));
    }
}