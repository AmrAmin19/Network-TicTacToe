package Controller;

import Controller.ConnectionManager.MessageListener;
import org.json.JSONObject;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.json.JSONArray;
import org.json.JSONException;
import Data.SharedData;
import Data.SharedPlayer;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.stage.Modality;

public class PlayerStatusBase extends AnchorPane implements MessageListener{
    protected final Text text;
    protected final Text text0;
    protected final Text text1;
    protected final Text text2;
    protected final Text text3;
    protected final Text text4;
    protected final ImageView RecImageView;
    protected final ImageView BackImageView;
    protected final ScrollPane scrollPane;
    protected final AnchorPane anchorPane2;
    protected final VBox vBox;
    protected final Stage stage;
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
     private ConnectionManager connectionManager;
     
    
     
   // static int index;
    static String opponent;
    private Thread listner;
    
//    static String opToPlayer=new String();
//    static String meFromPlayer=new String();
    
   static SharedPlayer PlayerStatusdata=new SharedPlayer();

    public PlayerStatusBase(Stage stage)  {
        
     
        
        this.stage = stage;

         connectionManager = ConnectionManager.getInstance();
          connectionManager.addMessageListener(this);
       
      //  new Thread(() -> listenForMessages()).start();
        
       text = new Text();
        text0 = new Text();
        text1 = new Text();
        text2 = new Text();
        text3 = new Text();
        text4 = new Text();
        RecImageView = new ImageView();
        BackImageView = new ImageView();
        scrollPane = new ScrollPane();
        anchorPane2 = new AnchorPane();
        vBox = new VBox(10); // Vertical box with spacing of 10

        text.setFill(javafx.scene.paint.Color.valueOf("#04b1b8"));
        text.setLayoutX(206.0);
        text.setLayoutY(65.0);
        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);
        text.setText("TIC");
        text.setFont(new Font("Agency FB Bold", 45.0));

        text0.setFill(javafx.scene.paint.Color.valueOf("#04b1b8"));
        text0.setLayoutX(330.0);
        text0.setLayoutY(65.0);
        text0.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text0.setStrokeWidth(0.0);
        text0.setText("TOE");
        text0.setFont(new Font("Agency FB Bold", 45.0));

        text1.setFill(javafx.scene.paint.Color.valueOf("#fd6801"));
        text1.setLayoutX(264.0);
        text1.setLayoutY(65.0);
        text1.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text1.setStrokeWidth(0.0);
        text1.setText("TAC");
        text1.setFont(new Font("Agency FB Bold", 45.0));

        text2.setFill(javafx.scene.paint.Color.valueOf("#04b1b8"));
        text2.setLayoutX(26.0);
        text2.setLayoutY(113.0);
        text2.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text2.setStrokeWidth(0.0);
        text2.setText(PlayerStatusdata.getUsername()!=null? PlayerStatusdata.getUsername():" ");
        text2.setFont(new Font("Agency FB Bold", 35.0));

        text3.setFill(javafx.scene.paint.Color.valueOf("#f4a24c"));
        text3.setLayoutX(432.0);
        text3.setLayoutY(114.0);
        text3.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text3.setStrokeWidth(0.0);
        text3.setText("SCORE ");
        text3.setFont(new Font("Agency FB Bold", 35.0));

        text4.setFill(javafx.scene.paint.Color.valueOf("#04b1b8"));
        text4.setLayoutX(542.0);
        text4.setLayoutY(113.0);
        text4.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text4.setStrokeWidth(0.0);
        text4.setText(String.valueOf(PlayerStatusdata.getScore()));
        text4.setFont(new Font("Agency FB Bold", 35.0));

        RecImageView.setAccessibleRole(javafx.scene.AccessibleRole.BUTTON);
        RecImageView.setFitHeight(50.0);
        RecImageView.setFitWidth(50.0);
        RecImageView.setLayoutX(533.0);
        RecImageView.setLayoutY(336.0);
        RecImageView.setPickOnBounds(true);
        RecImageView.setPreserveRatio(true);
        RecImageView.setImage(new Image(getClass().getResource("/resources/rec.png").toExternalForm()));
        RecImageView.setOnMouseClicked(e -> showRecordingMenu());

        BackImageView.setAccessibleRole(javafx.scene.AccessibleRole.BUTTON);
        BackImageView.setFitHeight(50.0);
        BackImageView.setFitWidth(50.0);
        BackImageView.setLayoutX(26.0);
        BackImageView.setLayoutY(22.0);
        BackImageView.setPickOnBounds(true);
        BackImageView.setPreserveRatio(true);
        BackImageView.setImage(new Image(getClass().getResource("/resources/arrow2.png").toExternalForm()));
        BackImageView.setOnMouseClicked(e -> navigateBack());

        scrollPane.setHbarPolicy(javafx.scene.control.ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setLayoutX(90.0);
        scrollPane.setLayoutY(136.0);
        scrollPane.setPrefHeight(204.0);
        scrollPane.setPrefWidth(385.0);
        scrollPane.setVbarPolicy(javafx.scene.control.ScrollPane.ScrollBarPolicy.ALWAYS);

        anchorPane2.setMinHeight(0.0);
        anchorPane2.setMinWidth(0.0);
        anchorPane2.setPrefHeight(344.0);
        anchorPane2.setPrefWidth(426.0);

        scrollPane.setContent(vBox);

        getChildren().add(text);
        getChildren().add(text0);
        getChildren().add(text1);
        getChildren().add(text2);
        getChildren().add(text3);
        getChildren().add(text4);
        getChildren().add(RecImageView);
        getChildren().add(BackImageView);
        getChildren().add(scrollPane);

        fetchPlayerList();
        

    }
    
 
    
 
    
    
    @Override
    public void onMessageReceived(JSONObject message) {
    Platform.runLater(() -> {
        try {
            System.out.println("Received message: " +message);

            if (!message.has("response")) {
                if (message.getString("query").equals("request")) {
                    handleGameRequest(message.getString("fromPlayer"));
                } else {
                    System.out.println("Unknown query received without response key: " + message.toString());
                }
                return;
            }

            String response = message.getString("response");

            switch (response) {
                case "signIn":
                    System.out.println("Sign In");
                    getData();
                    break;
                case "playerlist":
//                    vBox.getChildren().clear();
//                    JSONArray players = message.getJSONArray("players");
//                    for (int i = 0; i < players.length(); i++) {
//                        String player = players.isNull(i) ? null : players.getString(i);
//                        if (player != null) {
//                            Button playerButton = new Button(player);
//                            playerButton.setOnAction(event -> sendGameRequest(player));
//                            vBox.getChildren().add(playerButton);
                     updatePlayerList(message);
//                    
//                        }
//                    }
                   // System.out.println("Updated player list: " + players);
                    break;
                case "request":
                    handleGameRequest(message.getString("fromPlayer"));
                    break;
                case "accept":
                    startGame();
                    break;
                case "decline":
                    notifyRequestDeclined();
                    break;
                case "move":
                   
                    System.out.println("Move received");
                    Platform.runLater(() -> {  try {
                        OnlineBoard.onlineBoard.playMove(message.getInt("index"),message.getString("player"));
                       OnlineBoard.onlineBoard.  opponentSymbol =message.getString("player");
                      OnlineBoard.onlineBoard. playerSymbol = OnlineBoard.onlineBoard. opponentSymbol.equals("x") ? "o" : "x";
                        
                } catch (JSONException ex) {
                    Logger.getLogger(PlayerStatusBase.class.getName()).log(Level.SEVERE, null, ex);
                }
});
//                    OnlineBoard.index = message.getInt("index");
//                    OnlineBoard.player = message.getString("player");
//                   // OnlineBoard.playerSymbol = message.getString("player").equals("x") ? "o" : "x";
////                    OnlineBoard.opponentSymbol = message.getString("player").equals("x") ? "x" : "o";
//                     OnlineBoard.opponentSymbol =message.getString("player");
//                     OnlineBoard.playerSymbol =OnlineBoard.opponentSymbol.equals("x") ? "o" : "x";
//                    OnlineBoard.x = 0;
//                    OnlineBoard.playerTurn = true;  // It's now the player's turn
                    break;

                case "yourTurn":
                    OnlineBoard.playerTurn = true;
                    System.out.println("Your turn");
                    
                    break;
                case "data":
                    setData(message);
                    break;
                case "serverClosed":
                    showClosingAlert();
                    break;
                case "gameFinished":
                    System.out.println("player is now not in game");
                    break;
               
                default:
                    System.out.println("Unknown response: " + response);
                    break;
            }
        } catch (JSONException ex) {
            Logger.getLogger(PlayerStatusBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    });
}
    

     
    private void fetchPlayerList() {
        JSONObject request = new JSONObject();
        try {
            request.put("query", "playerlist");
            connectionManager.sendMessage(request);
        } catch (JSONException ex) {
            Logger.getLogger(PlayerStatusBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updatePlayerList(JSONObject json) throws JSONException {
        
          vBox.getChildren().clear();
                    JSONArray players = json.getJSONArray("players");
                    for (int i = 0; i < players.length(); i++) {
                        String player = players.isNull(i) ? null : players.getString(i);
                        if (player != null) {
//                            Button playerButton = new Button(player);
//                           
//                            playerButton.setOnAction(event -> sendGameRequest(player));
//                            vBox.getChildren().add(playerButton);
                vBox.getChildren().add(createPlayerEntry(player)); // Add each player entry to the VBox


//                              Text PlayerName=new Text(player);
//                             Button playerButton = new Button("challange");
//                             playerButton.setOnAction(event -> sendGameRequest(player));
//                             vBox.getChildren().add(playerButton);
//                              vBox.getChildren().add(PlayerName);
                             
                        }
                    }
                    System.out.println("Updated player list: " + players);
        
//        vBox.getChildren().clear();
//        String[] players = json.getString("players").split(",");
//        for (String player : players) {
//            Text PlayerName=new Text(player);
//            Button playerButton = new Button("challange");
//            playerButton.setOnAction(event -> sendGameRequest(player));
//            vBox.getChildren().add(playerButton);
//            vBox.getChildren().add(PlayerName);
//        }
    }

    private void sendGameRequest(String toPlayer) {
        JSONObject request = new JSONObject();
        try {
            request.put("query", "request");
            request.put("toPlayer", toPlayer);
            connectionManager.sendMessage(request);
        } catch (JSONException ex) {
            Logger.getLogger(PlayerStatusBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    

    private   void handleGameRequest(String fromPlayer) {
        
        
        
         Platform.runLater(() -> {
            try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Game Request");
                alert.setHeaderText("Game Request from " + fromPlayer);
                alert.setContentText("Do you want to accept the game request?");

                ButtonType acceptButton = new ButtonType("Accept");
                ButtonType declineButton = new ButtonType("Decline");

                alert.getButtonTypes().setAll(acceptButton, declineButton);

                Optional<ButtonType> result = alert.showAndWait();
                boolean accept = result.isPresent() && result.get() == acceptButton;

                JSONObject responseJson = new JSONObject();
                responseJson.put("query", accept ? "accept" : "decline");
                responseJson.put("fromPlayer", fromPlayer);
                connectionManager.sendMessage(responseJson);


            } catch (JSONException ex) {
                Logger.getLogger(PlayerStatusBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
   
    }

    private void sendAcceptResponse(String fromPlayer) {
        JSONObject response = new JSONObject();
        try {
            response.put("query", "accept");
            response.put("fromPlayer", fromPlayer);
            connectionManager.sendMessage(response);
        } catch (JSONException ex) {
            Logger.getLogger(PlayerStatusBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sendDeclineResponse(String fromPlayer) {
        JSONObject response = new JSONObject();
        try {
            response.put("query", "decline");
            response.put("fromPlayer", fromPlayer);
             connectionManager.sendMessage(response);
        } catch (JSONException ex) {
            Logger.getLogger(PlayerStatusBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void startGame() {
        // Logic to start the game
         switchToGameScene();
    }

    private void notifyRequestDeclined() {
        // Notify the user that their request was declined
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Game refused");
                alert.setHeaderText("Game Request from ..... deied" );
                alert.setContentText("sh 7l3b m3ak");
                alert.show();
    }
    
    
      private void switchToGameScene() {
          
            OnlineBoard boardScene = new OnlineBoard(stage);
            Scene modScene = new Scene(boardScene, 600, 400);
            stage.setScene(modScene);
//        Platform.runLater(() -> {
//           
//
//            
//             OnlineBoard boardScene = new OnlineBoard(stage);
//            Scene modScene = new Scene(boardScene, 600, 400);
//            stage.setScene(modScene);
//        });
    }
      
       private void navigateBack() {
          
        try {
            handleLogOut();
        } catch (JSONException ex) {
            Logger.getLogger(PlayerStatusBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
          LoginBase mode = new LoginBase(stage);
        Scene modScene = new Scene(mode, 600, 400);
        stage.setScene(modScene);
            }
       
        private void handleLogOut() throws JSONException
     {
          JSONObject logOutData = new JSONObject();
         
                    logOutData.put("query", "logout");
                    logOutData.put("email",PlayerStatusdata.getEmail());
                    
                    
        connectionManager.sendMessage(logOutData);
     }
        
        
        
        // UI PART
        
         
    AnchorPane createPlayerEntry(String playerName) {
        AnchorPane playerPane = new AnchorPane();
        playerPane.setStyle("-fx-background-color: #2A9DB8;");
        playerPane.setPrefHeight(50.0);
        playerPane.setPrefWidth(366.0);

        // Player name text
        Text playerText = new Text(playerName);
        playerText.setFill(javafx.scene.paint.Color.valueOf("#ffffff"));
        playerText.setLayoutX(39.0);
        playerText.setLayoutY(30.0);
        playerText.setFont(new Font("Agency FB Bold", 24.0));

        // Player avatar image
        ImageView playerImage = new ImageView();
        playerImage.setFitHeight(34.0);
        playerImage.setFitWidth(34.0);
        playerImage.setLayoutX(4.0);
        playerImage.setLayoutY(8.0);
        playerImage.setPickOnBounds(true);
        playerImage.setPreserveRatio(true);
        playerImage.setImage(new Image(getClass().getResource("/resources/person.png").toExternalForm())); // Ensure the image exists in your resources

        // Challenge button
        Button challengeButton = new Button("challenge");
        challengeButton.setLayoutX(264.0);
        challengeButton.setLayoutY(10.0);
        challengeButton.setMnemonicParsing(false);
        challengeButton.setFont(new Font("Agency FB Bold", 18.0));
        challengeButton.setStyle("-fx-background-color: #ffd700;");
        challengeButton.setOnAction(event -> sendGameRequest(playerName));

        // Add components to the player pane
        playerPane.getChildren().add(playerText);
        playerPane.getChildren().add(playerImage);
        playerPane.getChildren().add(challengeButton);

        return playerPane;
    }

    private void getData() throws JSONException {
        JSONObject reqData = new JSONObject();
        reqData.put("query", "data");
        reqData.put("email", PlayerStatusdata.getEmail());
        connectionManager.sendMessage(reqData);
    }
    
    
    private void setData(JSONObject data) throws JSONException
    {
     String username= data.getString("username");
     int score=data.getInt("score");
     
        System.out.println(username);
        System.out.println(score);
        
        PlayerStatusdata.setUsername(username);
        PlayerStatusdata.setScore(score);
        
         text2.setText(username);
           text4.setText(String.valueOf(score));
    }
    
    

    private void showClosingAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert .setContentText("Server closed ");
        alert.show();
    }
    
    
     private void showRecordingMenu() {
        Platform.runLater(() -> {
            ListView<String> recordedGamesListView = new ListView<>();
            File recordedGamesDir = new File("recorded_games");

            if (recordedGamesDir.exists() && recordedGamesDir.isDirectory()) {
                for (File file : recordedGamesDir.listFiles()) {
                    recordedGamesListView.getItems().add(file.getName());
                }
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Recorded Games");
            alert.setHeaderText("Select a game to replay:");
            alert.getDialogPane().setContent(recordedGamesListView);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait().ifPresent(response -> {
                String selectedGame = recordedGamesListView.getSelectionModel().getSelectedItem();
                if (selectedGame != null) {
                    try {
                        String gameMoves = new String(Files.readAllBytes(Paths.get("recorded_games/" + selectedGame)));
                      OnlineBoard.displayRecordedGameOnNewBoard(gameMoves);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        });
    }
    

   
}
