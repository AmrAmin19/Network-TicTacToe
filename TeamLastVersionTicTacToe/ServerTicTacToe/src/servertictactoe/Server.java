/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servertictactoe;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import static servertictactoe.PlayersHandler.PlayersList;

/**
 *
 * @author amram
 */
public class Server {
    
     private static Server server;
    private ServerSocket serverSocket;
    private Thread listener;
    // private static List<PlayersHandler> clients = new ArrayList<>();

    private Server() {
    }
     
    public static Server getServer() {
        if (server == null) {
            server = new Server();
        }
        return server;
    }
    
    public void initServer() {
        try {
            serverSocket = new ServerSocket(9081);
            System.out.println("Server IP: " + Inet4Address.getLocalHost().getHostAddress());
            
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("HERE IS YOUR IP ADRESS");
        alert.setContentText(Inet4Address.getLocalHost().getHostAddress());
        alert.show();
            
            listener = new Thread(() -> {
                while (true) {
                    try {
                        Socket socket = serverSocket.accept();
                        new PlayersHandler(socket); // Not mandatory to be online
                    } catch (IOException ex) {
                        System.out.println("Problem in connecting players");
                    }
                }
            });
            listener.start();
        } catch (IOException ex) {
            System.out.println("Server exception");
            ex.printStackTrace();
        }
    }
    
//     public void stopServer() {
//        try {
//            listener.stop();
//            serverSocket.close();
//            serverSocket = null;
//            synchronized (PlayersList) {
//                Iterator<PlayersHandler> iterator = PlayersList.iterator();
//                while (iterator.hasNext()) {
//                    PlayersHandler player = iterator.next();
//                    player.closeConnection();
//                }
//            }
//        } catch (IOException e) {
//            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
//        }
//    }
    
     public void stopServer() {
        try {
            listener.stop();
            serverSocket.close();
             serverSocket = null;
             for (PlayersHandler player : PlayersList) {
           player.closeConnection();
//           player.stop();
        }
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, "Error closing server", ex);
        }
    }
    
}
 