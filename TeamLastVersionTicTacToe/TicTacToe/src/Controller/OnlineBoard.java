package Controller;

import Controller.ConnectionManager.MessageListener;
import static Controller.PlayerStatusBase.PlayerStatusdata;
import Dialogs.losermsgmode1Base;
import Dialogs.nowinnermode1Base;
import Dialogs.winnermsgmode1Base;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import javafx.stage.Modality;
import javafx.stage.WindowEvent;

public class OnlineBoard extends AnchorPane {

    private final GridPane gridPane;
    private final Button btn1;
    private final Button btn2;
    private final Button btn3;
    private final Button btn4;
    private final Button btn5;
    private final Button btn6;
    private final Button btn7;
    private final Button btn8;
    private final Button btn9;
    private final Text text;
    private final Text text0;
    private final Text text1;
    private final ImageView arrow;
    public static boolean playerTurn = false; // True for X's turn, False for O's turn
    private final Stage stage;
    public  String playerSymbol; // X or O
    public  String opponentSymbol; // O or X
    private  ConnectionManager connectionManager;
    
  
     private String moves;
    
     
 private boolean isDialogOpen = false;

     
    int score=PlayerStatusdata.getScore();;
    public static OnlineBoard onlineBoard;

    
    
    
    public OnlineBoard(Stage stage )   {
        this.stage = stage;
        onlineBoard  = this;
        playerSymbol = "x";
        moves= "";
       connectionManager = ConnectionManager.getInstance();

        gridPane = new GridPane();
     //   RecImageView = new ImageView();
        btn1 = new Button();
        btn2 = new Button();
        btn3 = new Button();
        btn4 = new Button();
        btn5 = new Button();
        btn6 = new Button();
        btn7 = new Button();
        btn8 = new Button();
        btn9 = new Button();
        text = new Text();
        text0 = new Text();
        text1 = new Text();
        arrow = new ImageView();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        gridPane.setHgap(4.0);
        gridPane.setLayoutX(164.0);
        gridPane.setLayoutY(147.0);
        gridPane.setPrefHeight(184.0);
        gridPane.setPrefWidth(273.0);
        gridPane.setVgap(4.0);

        initializeButton(btn1, 0, 0);
        initializeButton(btn2, 1, 0);
        initializeButton(btn3, 2, 0);
        initializeButton(btn4, 0, 1);
        initializeButton(btn5, 1, 1);
        initializeButton(btn6, 2, 1);
        initializeButton(btn7, 0, 2);
        initializeButton(btn8, 1, 2);
        initializeButton(btn9, 2, 2);

        text.setFill(javafx.scene.paint.Color.valueOf("#04b1b8"));
        text.setLayoutX(206.0);
        text.setLayoutY(65.0);
        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);
        text.setText("TIC");
        text.setWrappingWidth(50.1365966796875);
        text.setFont(new Font("Agency FB Bold", 45.0));

        text0.setFill(javafx.scene.paint.Color.valueOf("#fd6801"));
        text0.setLayoutX(264.0);
        text0.setLayoutY(65.0);
        text0.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text0.setStrokeWidth(0.0);
        text0.setText("TAC");
        text0.setFont(new Font("Agency FB Bold", 45.0));

        text1.setFill(javafx.scene.paint.Color.valueOf("#04b1b8"));
        text1.setLayoutX(334.0);
        text1.setLayoutY(65.0);
        text1.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text1.setStrokeWidth(0.0);
        text1.setText("TOE");
        text1.setFont(new Font("Agency FB Bold", 45.0));

        arrow.setAccessibleRole(javafx.scene.AccessibleRole.BUTTON);
        arrow.setFitHeight(42.0);
        arrow.setFitWidth(54.0);
        arrow.setLayoutX(14.0);
        arrow.setLayoutY(20.0);
        arrow.setPickOnBounds(true);
        arrow.setPreserveRatio(true);
        arrow.setImage(new Image(getClass().getResource("/resources/arrow2.png").toExternalForm()));
        setPadding(new Insets(10.0));
        arrow.setOnMouseClicked(e -> navigateback());
        
        
        


        getChildren().add(gridPane);
        getChildren().add(text);
        getChildren().add(text0);
        getChildren().add(text1);
        getChildren().add(arrow);

    }
    
    public void playMove(int index, String player) {
    Button button = getButtonByIndex(index);
    if (button != null) {
        button.setText(player);
          moves += getButtonIndex(button) + player + ",";
        button.setDisable(true);
        
        playerTurn = true;

        if (player.equals(opponentSymbol)) {
//            playerTurn = true;
            if (checkWin(opponentSymbol)) {
                Platform.runLater(this::alertShowO); // Opponent wins
            } else if (isBoardFull()) {
                Platform.runLater(this::alertShowDraw); // Game is a draw
            }
        }
    }
}

    private void initializeButton(Button button, int col, int row) {
    button.setMnemonicParsing(false);
    button.setPrefSize(100, 100);
    button.setText("");
    button.setStyle("-fx-background-color: #8DBFBC; -fx-text-fill: #000000;");
    button.setFont(new Font("Arial", 30));
    button.setOnAction(e -> handleButtonClick(button));
    GridPane.setColumnIndex(button, col);
    GridPane.setRowIndex(button, row);
    gridPane.add(button, col, row);  // Use add method instead of getChildren().add
}


   private void handleButtonClick(Button button) {
    if (playerTurn && button.getText().isEmpty()) {
        // Player makes a move
        String currentPlayer = playerSymbol;
        button.setText(currentPlayer);
        button.setDisable(true);
        moves += getButtonIndex(button) + currentPlayer + ",";
        sendMoveToServer(button, currentPlayer);
        playerTurn = false; // Switch turn
         

        // Check for win or draw
        if (checkWin(currentPlayer)) {
            Platform.runLater(() -> {
                if (currentPlayer.equals(playerSymbol)) {
                    alertShowX(); // Current player wins
                     SaveMatch();
                } else {
                    alertShowO();
                     SaveMatch();// Opponent wins
                }
            });
        } else if (isBoardFull()) {
            Platform.runLater(this::alertShowDraw);
             SaveMatch();// Game is a draw
        }
    }
}
    
    


    private void sendMoveToServer(Button button, String player) {
        try {
            int index = getButtonIndex(button);
            JSONObject move = new JSONObject();
            move.put("query", "move");
            move.put("index", index);
            move.put("player", player);
            connectionManager.sendMessage(move);
        } catch (JSONException e) {
            Logger.getLogger(OnlineBoard.class.getName()).log(Level.SEVERE, null, e);
        }
    }


    private int getButtonIndex(Button button) {
        if (button == btn1) {
            return 0;
        }
        if (button == btn2) {
            return 1;
        }
        if (button == btn3) {
            return 2;
        }
        if (button == btn4) {
            return 3;
        }
        if (button == btn5) {
            return 4;
        }
        if (button == btn6) {
            return 5;
        }
        if (button == btn7) {
            return 6;
        }
        if (button == btn8) {
            return 7;
        }
        if (button == btn9) {
            return 8;
        }
        return -1;
    }

    private Button getButtonByIndex(int index) {
        switch (index) {
            case 0:
                return btn1;
            case 1:
                return btn2;
            case 2:
                return btn3;
            case 3:
                return btn4;
            case 4:
                return btn5;
            case 5:
                return btn6;
            case 6:
                return btn7;
            case 7:
                return btn8;
            case 8:
                return btn9;
            default:
                return null;
        }
    }
    

   

private void alertShowX() {
    if (isDialogOpen) return;
    isDialogOpen = true;

    Platform.runLater(() -> {
        winnermsgmode1Base winDialog = new winnermsgmode1Base();
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.initOwner(stage);
        Scene dialogScene = new Scene(winDialog, 500, 300);
        dialogStage.setScene(dialogScene);

        Button playAgainButton = winDialog.getPlayAgainButton();
        playAgainButton.setOnAction(e -> {
            winDialog.stopMediaPlayer();
            dialogStage.close();
            restartGame();
            isDialogOpen = false; // Reset the flag when the dialog is closed
        });

        dialogStage.setOnCloseRequest((WindowEvent we) -> {
            winDialog.stopMediaPlayer();
            stage.setScene(new Scene(new PlayerStatusBase(stage)));
             
          
          //  terminateObj();
            isDialogOpen = false; // Reset the flag when the dialog is closed
        });

        dialogStage.show();
    });
}


private void alertShowO() {
    if (isDialogOpen) return;
    isDialogOpen = true;

    Platform.runLater(() -> {
        losermsgmode1Base loseDialog = new losermsgmode1Base();
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.initOwner(stage);
        Scene dialogScene = new Scene(loseDialog, 500, 300);
        dialogStage.setScene(dialogScene);

        Button takeRevengeButton = loseDialog.getPlayAgainButton();
        takeRevengeButton.setOnAction(e -> {
            loseDialog.stopMediaPlayer();
            dialogStage.close();
            restartGame();
            isDialogOpen = false; // Reset the flag when the dialog is closed
        });

        dialogStage.setOnCloseRequest((WindowEvent we) -> {
            loseDialog.stopMediaPlayer();
            stage.setScene(new Scene(new PlayerStatusBase(stage)));
            
            isDialogOpen = false; // Reset the flag when the dialog is closed
        });

        dialogStage.show();
    });
}

private void alertShowDraw() {
    if (isDialogOpen) return;
    isDialogOpen = true;

    Platform.runLater(() -> {
        nowinnermode1Base drawDialog = new nowinnermode1Base();
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.initOwner(stage);
        Scene dialogScene = new Scene(drawDialog, 500, 300);
        dialogStage.setScene(dialogScene);

        Button playAgainButton = drawDialog.getPlayAgainButton();
        playAgainButton.setOnAction(e -> {
            drawDialog.stopMediaPlayer();
            dialogStage.close();
            restartGame();
            isDialogOpen = false; // Reset the flag when the dialog is closed
        });

        dialogStage.setOnCloseRequest((WindowEvent we) -> {
            drawDialog.stopMediaPlayer();
            stage.setScene(new Scene(new PlayerStatusBase(stage)));
            
            isDialogOpen = false; // Reset the flag when the dialog is closed
        });

        dialogStage.show();
    });
}


    private boolean checkWin(String symbol) {
        int[][] winningCombinations = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columns
            {0, 4, 8}, {2, 4, 6} // Diagonals
        };

        String[] board = getBoardState();
        for (int[] combination : winningCombinations) {
            if (board[combination[0]].equals(symbol)
                    && board[combination[1]].equals(symbol)
                    && board[combination[2]].equals(symbol)) {
             if(playerSymbol==symbol)
            {
              score+=10;
                PlayerStatusdata.setScore(score);
                updateScore(score);
                 System.out.println();
            }
            //terminateObj();
              handelGameFinished();
                return true;
            }
        }
        return false;
    }

    private String[] getBoardState() {
        return new String[]{
            btn1.getText(), btn2.getText(), btn3.getText(),
            btn4.getText(), btn5.getText(), btn6.getText(),
            btn7.getText(), btn8.getText(), btn9.getText()
        };
    }

    private boolean isBoardFull() {
        for (String cell : getBoardState()) {
            if (cell.isEmpty()) {
                return false;
            }
        }
        return true;
    }
    




    private void restartGame() {
    Platform.runLater(() -> {
        
//         JSONObject request = new JSONObject();
//        try {
//            request.put("query", "request");
//            request.put("toPlayer", PlayerStatusBase.opToPlayer);
//             request.put("fromPlayer", PlayerStatusBase.meFromPlayer);
//            connectionManager.sendMessage(request);
//        } catch (JSONException ex) {
//            Logger.getLogger(PlayerStatusBase.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//                resetBoard();
//                resetVariables();
//        playerTurn = playerSymbol.equals("X");

//        try {
//            // Replace the current scene with a new instance of OnlineBoard
//            stage.setScene(new Scene(new OnlineBoard(stage, "192.168.1.11", playerSymbol))); // Pass appropriate parameters
//        } catch (IOException ex) {
//            Logger.getLogger(OnlineBoard.class.getName()).log(Level.SEVERE, null, ex);
//        }
    });
}


    private void navigateback() {
        // Implement your navigation logic here
    }
    
    private void resetBoard() {
    for (Button button : new Button[]{btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9}) {
        button.setText("");
        button.setDisable(false);
    }
}
    
  private void updateScore(int s)
  {
        try {
            JSONObject setScore= new JSONObject();
            setScore.put("query", "setScore");
            setScore.put("email", PlayerStatusdata.getEmail());
            setScore.put("score", s);
            connectionManager.sendMessage(setScore);
        } catch (JSONException ex) {
            Logger.getLogger(OnlineBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
  }

  public void resetVariables() {
     // Ensure thread safety if accessed by multiple threads
    //    playerTurn = false;
        playerSymbol = "";
        opponentSymbol = "";
    
}
  
  //Mai Recording
  
    private void showRecordingAlert() {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Record Game");
            alert.setHeaderText("Do you want to record the game?");
            ButtonType acceptButton = new ButtonType("Yes");
            ButtonType declineButton = new ButtonType("No");
            alert.getButtonTypes().setAll(acceptButton, declineButton);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == acceptButton) {
                SaveMatch();
            }
        });
    }
    

    
     public void handleOpponentMove(int col, int row) {
        Platform.runLater(() -> {
            Button button = getButtonAt(col, row);
            if (button != null && button.getText().isEmpty()) {
                String opponentSymbol = playerSymbol.equals("X") ? "O" : "X";
                button.setText(opponentSymbol);
                button.setDisable(true);
                moves += getButtonIndex(button) + opponentSymbol + ",";
                playerTurn = true;

                if (checkWin(opponentSymbol)) {
                    alertShowO(); // Show winner dialog for opponent
                } else if (isBoardFull()) {
                    alertShowDraw(); // Game is a draw
                }
            }
        });
    }
     
      private Button getButtonAt(int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return (Button) node;
            }
        }
        return null;
    }

      
 
public static void displayRecordedGameOnNewBoard(String gameMoves) {
    
       Stage recordStage=new  Stage();
         RecordBoard mode = new RecordBoard(recordStage);
        Scene modScene = new Scene(mode, 600, 400);
        recordStage.setScene(modScene);
 
        
       
        recordStage.setTitle("Recorded Game");
        recordStage.setScene(modScene);
        recordStage.show();

        String[] movesArray = gameMoves.split(",");
        new Thread(() -> {
            //Platform.runLater(controller::resetBoard);
            for (String move : movesArray) {
                if (move != null && !move.trim().isEmpty()) {
                    int index = Integer.parseInt(move.substring(0, move.length() - 1));
                    String player = move.substring(move.length() - 1);
                    Platform.runLater(() -> {
                        Button button = mode.getButtonByIndex(index);
                        if (button != null) {
                            button.setText(player);
                            button.setDisable(true);
                        }
                    });
                    try {
                        Thread.sleep(500); // 0.5 seconds delay
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }
        }).start();
    
}



   private void SaveMatch() {
    String fileName = "match_" + System.currentTimeMillis() + ".txt";
    File recordedGamesDir = new File("recorded_games");
    if (!recordedGamesDir.exists()) {
        recordedGamesDir.mkdir();
    }
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(recordedGamesDir, fileName)))) {
        writer.write(moves);
        System.out.println("Match saved successfully.");
    } catch (IOException e) {
        System.out.println("Failed to save the match.");
    }
}
   
   public void handelGameFinished()
    {
        try {
            JSONObject gameFinished= new JSONObject();
            gameFinished.put("query", "gameFinished");
            connectionManager.sendMessage(gameFinished);
        } catch (JSONException ex) {
            Logger.getLogger(PlayerStatusBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
   public void terminateObj()
{
     if (this.onlineBoard != null) {
          this.  onlineBoard = null;
            System.out.println("Game finished. Online game object deleted.");
        }
}


}