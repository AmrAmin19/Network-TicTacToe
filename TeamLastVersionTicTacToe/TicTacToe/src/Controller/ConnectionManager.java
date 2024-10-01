package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class ConnectionManager {
    private static ConnectionManager instance;
    private Socket socket;
    public BufferedReader br;
    public PrintStream ps;
   private final List<MessageListener> listeners = new ArrayList<>();
    //private   final List<MessageListener> listeners = new CopyOnWriteArrayList<>();
    private volatile boolean running = true; // Volatile flag to control the thread
  //  private JSONObject json;

    private ConnectionManager() {
        try {
            socket = new Socket(EnterIpBase.Ip, 9081);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ps = new PrintStream(socket.getOutputStream());
            new Thread(() -> listenForMessages()).start();
//new Thread(this::listenForMessages).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }

    public void sendMessage(JSONObject message) {
        ps.println(message.toString());
    }

    public  void addMessageListener(MessageListener listener) {
        listeners.add(listener);
    }

//    private void listenForMessages() {
//        while (running) {
//            try {
//                String message = br.readLine();
//                System.out.println("Received message: " + message); // Debugging statement
//                JSONObject json = new JSONObject(message);
//
//                // Notify all listeners about the new message
//                
//                    for (MessageListener listener : listeners) {
//                        listener.onMessageReceived(json);
//                    }
//                
//            } catch (IOException | JSONException e) {
//                e.printStackTrace();
//                break;
//            }
//        }
//    }
    
    private void listenForMessages() {
        while (running) {
            try {
                String message = br.readLine();
                if (message != null) {
                   
                    JSONObject json = new JSONObject(message);

                    for (MessageListener listener : listeners) {
                        listener.onMessageReceived(json);
                    }
                } else {
                 
                    break;
                }
            } catch (IOException | JSONException e) {
               
                break;
            }
        }
        cleanup();
    }


    public void stop() {
        running = false; // Set the flag to false to stop the thread
        try {
            socket.close(); // Close the socket to unblock the read operation
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    
    

    private void cleanup() {
        try {
            if (br != null) {
                br.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public interface MessageListener {
        void onMessageReceived(JSONObject message);
    }
}
