package servertictactoe;

import database.TicTacToeDataBase;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.json.JSONException;
import org.json.JSONObject;

public class PlayersHandler extends Thread {

    private Server server;
    private BufferedReader br;
    private PrintStream ps;
    private Socket currentSocket;
    public static Vector<PlayersHandler> PlayersList = new Vector<>();
    // public static List<PlayersHandler> PlayersList = new CopyOnWriteArrayList<>();
    private String clientData;
    private String playerName;
    
    private boolean inGame = false;
    private PlayersHandler opponent;
    public TicTacToeDataBase tic;
    
    private int score;

    public PlayersHandler(Socket socket) {
        server = Server.getServer(); // singleton instance
        try {
            tic = TicTacToeDataBase.getInstance();
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ps = new PrintStream(socket.getOutputStream());
            currentSocket = socket;

            this.start();
        } catch (IOException | SQLException ex) {
            ex.printStackTrace();
            try {
                socket.close();
            } catch (IOException ex1) {
                ex1.printStackTrace();
            }
        }
    }

    public void run() {
        while (currentSocket.isConnected()) {
            try {
                clientData = br.readLine();
                System.out.println("Received from client: " + clientData);
                if (clientData != null) {
                    JSONObject json = new JSONObject(clientData);
                    String query = json.optString("query");
                    switch (query) {
                        case "signUp":
                            handleSignUp(json);
                            break;
                        case "signIn":
                            handleSignIn(json);
                            break;
                        case "playerlist":
                            sendPlayerList(this);
                            break;
                        case "request":
                            handleRequest(json.getString("toPlayer"));
                            break;
                        case "accept":
                            handleAccept(json.getString("fromPlayer"));
                            break;
                        case "decline":
                            handleDecline(json.getString("fromPlayer"));
                            break;
                        case "logout":
                            handleLogout(json);
                            break;
                        case "move":
                            handleMove(json);
                            break;
                        case "data":
                            fetchData(json);
                            break;
                        case "setScore":
                            updateScore(json);
                           break;
                        case "gameFinished":
                            handleGameFinished();
                            break;
                        default:
                            handleUnknownQuery();
                            break;
                    }
                }
            } catch (IOException | JSONException ex) {
                System.out.println("Closing connection");
                try {
                    currentSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        if (currentSocket.isClosed()) {
            System.out.println("Connection closed");
            PlayersList.remove(this);
            broadcastPlayerList();
        }
    }

    private void broadcastPlayerList() {
        for (PlayersHandler player : PlayersList) {
            sendPlayerList(player);
        }
    }

    private void sendPlayerList(PlayersHandler playerHandler) {
        try {
            JSONObject response = new JSONObject();
            response.put("response", "playerlist");

            List<String> validPlayers = tic.getActivePlayers().stream()
                    .filter(p -> !p.equals(playerHandler.playerName))
                    .collect(Collectors.toList());

            response.put("players", validPlayers.toArray());

            playerHandler.ps.println(response.toString());
            System.out.println("Player list sent to " + playerHandler.playerName + ": " + response.toString());
        } catch (JSONException | SQLException ex) {
            Logger.getLogger(PlayersHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   

    private void handleRequest(String toPlayerName) throws JSONException {
        if (this.playerName == null) {
            System.out.println("Error: playerName is null for current player");
            return;
        }

        PlayersHandler toPlayer = findPlayerByName(toPlayerName);
        if (toPlayer != null && !toPlayer.inGame) {
            JSONObject request = new JSONObject();
            request.put("query", "request");
            request.put("fromPlayer", playerName);
            toPlayer.ps.println(request.toString());
            System.out.println(playerName + " requested to play with " + toPlayerName);
        } else {
            JSONObject response = new JSONObject();
            response.put("response", "request");
            response.put("status", "failed");
            ps.println(response.toString());
            System.out.println("Request failed: " + toPlayerName + " not available");
        }
    }

    private void handleAccept(String fromPlayerName) throws JSONException {
        if (this.playerName == null) {
            System.out.println("Error: playerName is null for current player");
            return;
        }

        PlayersHandler fromPlayer = findPlayerByName(fromPlayerName);
        if (fromPlayer != null) {
            this.inGame = true;
            this.opponent = fromPlayer;
            fromPlayer.inGame = true;
            fromPlayer.opponent = this;

            JSONObject response = new JSONObject();
            response.put("response", "accept");
            response.put("status", "success");

            // Notify both players
            ps.println(response.toString());
            fromPlayer.ps.println(response.toString());

            notifyPlayerTurn();

            System.out.println(playerName + " accepted request from " + fromPlayerName);
        }
    }

    private void handleDecline(String fromPlayerName) throws JSONException {
        if (this.playerName == null) {
            System.out.println("Error: playerName is null for current player");
            return;
        }

        PlayersHandler fromPlayer = findPlayerByName(fromPlayerName);
        if (fromPlayer != null) {
            JSONObject response = new JSONObject();
            response.put("response", "decline");
            response.put("status", "success");
            fromPlayer.ps.println(response.toString());
            System.out.println(playerName + " declined request from " + fromPlayerName);
        }
    }

    private void handleLogout(JSONObject jsonRequest) throws JSONException {
        try {
            PlayersList.remove(this);
            JSONObject response = new JSONObject();
            response.put("response", "logout");
            response.put("status", "success");
            ps.println(response.toString());
            String email = jsonRequest.getString("email");
            this.playerName = tic.getUsernameByEmail(email);
            tic.updateIsActive(email, false);
            System.out.println("Logout: " + playerName);
            broadcastPlayerList();
        } catch (SQLException ex) {
            Logger.getLogger(PlayersHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void handleUnknownQuery() throws JSONException {
        JSONObject response = new JSONObject();
        response.put("response", "Unknown query");
        ps.println(response.toString());
        System.out.println("Unknown query");
    }

    private PlayersHandler findPlayerByName(String playerName) {
        return PlayersList.stream()
                .filter(p -> p.playerName.equals(playerName))
                .findFirst()
                .orElse(null);
    }

    public void closeConnection() {
        
        try {
            
            JSONObject response = new JSONObject();
            response.put("response", "serverClosed");
            ps.println(response.toString());
            
            try {
                
                currentSocket.close();
                ps.close();
                br.close();
//         PlayersList.remove(this);
                this.stop();
            } catch (IOException e) {
                Logger.getLogger(PlayersHandler.class.getName()).log(Level.SEVERE, null, e);
            }
        } catch (JSONException ex) {
            Logger.getLogger(PlayersHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void handleSignUp(JSONObject json) {
        try {
            String username = json.getString("username");
            String email = json.getString("email");
            String password = json.getString("password");

            tic.SignUp(email, username, password);

            JSONObject response = new JSONObject();
            response.put("response", "signUp");
            response.put("status", "success");
            ps.println(response.toString());
        } catch (SQLException | JSONException ex) {
            Logger.getLogger(PlayersHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void handleSignIn(JSONObject jsonRequest) throws JSONException {
        try {
            String email = jsonRequest.getString("email");
            String password = jsonRequest.getString("password");

            Logger.getLogger(PlayersHandler.class.getName()).log(Level.INFO, "Attempting login for email: " + email);

            if (tic.isCredentialsValid(email, password)) {
                this.playerName = tic.getUsernameByEmail(email);
                tic.updateIsActive(email, true);
                JSONObject jsonResponse = new JSONObject();
                jsonResponse.put("response", "signIn");
                jsonResponse.put("status", "success");
                ps.println(jsonResponse.toString());
                synchronized (PlayersList) {
                    PlayersList.add(this);
                }
                System.out.println("Sign in from player handle Method, player name: " + this.playerName);
                broadcastPlayerList();
            } else {
                JSONObject jsonResponse = new JSONObject();
                jsonResponse.put("response", "signIn");
                jsonResponse.put("status", "failed");
                jsonResponse.put("message", "Invalid email or password.");
                ps.println(jsonResponse.toString());
            }
        } catch (SQLException e) {
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("response", "SignIn response");
            jsonResponse.put("status", "failed");
            jsonResponse.put("message", "Error processing sign-in request.");
            ps.println(jsonResponse.toString());
            Logger.getLogger(PlayersHandler.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void notifyPlayerTurn() {
        try {
            JSONObject turnNotification = new JSONObject();
            turnNotification.put("response", "yourTurn");
            ps.println(turnNotification.toString());
        } catch (JSONException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void handleMove(JSONObject json) throws JSONException {
        int index = json.getInt("index");
        String player = json.getString("player");

        JSONObject move = new JSONObject();
        move.put("response", "move");
        move.put("index", index);
        move.put("player", player);

        // Send the move to the opponent
        if (opponent != null) {
            opponent.ps.println(move.toString());
            
        }

//         Notify the next player to make a move
//        if (opponent != null) {
//            opponent.notifyPlayerTurn();
//        }
    }

    private void fetchData(JSONObject info)  {
        
        try {
            String email = info.getString("email");
            this.score = tic.getScoreByEmail(email);
            JSONObject data = new JSONObject();
            data.put("response", "data");
            data.put("username", playerName);
            data.put("score", score);
            ps.println(data.toString());
        } catch (SQLException ex) {
            Logger.getLogger(PlayersHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(PlayersHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void updateScore(JSONObject scoreData) 
    {
        try {
            String email = scoreData.getString("email");
            
          tic.updateScore(email, scoreData.getInt("score"));
        } catch (JSONException ex) {
            Logger.getLogger(PlayersHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PlayersHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void handleGameFinished() throws JSONException {
        if (this.opponent != null) {
            // Set inGame status to false for both players
            this.inGame = false;
            this.opponent.inGame = false;
            
            // Notify both players
            JSONObject response = new JSONObject();
            response.put("response", "gameFinished");
            response.put("status", "success");

            this.ps.println(response.toString());
            this.opponent.ps.println(response.toString());
            
            System.out.println("Game finished between " + this.playerName + " and " + this.opponent.playerName);

            // Clear the opponent references
            this.opponent.opponent = null;
            this.opponent = null;
        }
    }
   
}
