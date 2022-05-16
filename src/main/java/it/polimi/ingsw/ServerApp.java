package it.polimi.ingsw;

import it.polimi.ingsw.AlternativeServer.Server;
import it.polimi.ingsw.NetworkUtilities.Server.SocketServer;

import java.io.IOException;

public class ServerApp
{
    public static void main( String[] args ) throws IOException {

        Server server;
        try {
            server = new Server();
            server.run();
        } catch (IOException e) {
            System.err.println("Impossible to initialize the server: " + e.getMessage() + "!");
        }
    }
}
