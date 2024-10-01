package Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Data.SharedData;
import Dialogs.alertmode2Base;
import Dialogs.nowinnermode1Base;

public class BoardScene extends AnchorPane {

    protected final GridPane gridPane;
    protected final ColumnConstraints columnConstraints;
    protected final ColumnConstraints columnConstraints0;
    protected final ColumnConstraints columnConstraints1;
    protected final RowConstraints rowConstraints;
    protected final RowConstraints rowConstraints0;
    protected final RowConstraints rowConstraints1;
    protected final Button btn1;
    protected final Button btn2;
    protected final Button btn3;
    protected final Button btn4;
    protected final Button btn5;
    protected final Button btn6;
    protected final Button btn7;
    protected final Button btn8;
    protected final Button btn9;
    protected final Text text;
    protected final Text text0;
    protected final Text text1;
    protected final Text playerTxt1;
    protected final Text playerTxt2;
    protected final Text scoreTxt1;
    protected final Text scoreTxt2;
    protected final ImageView arrow;
    SharedData data;

    int xWinsScore;
    int oWinsScore;

    boolean xWins;
    boolean oWins;

    private final Stage stage;
    protected final Alert alert;

    boolean isPlayerOneTurn = true;
//    boolean playerOne=true;
//   boolean playerTwo=false;

    //boolean btnPlayed=true;
    String[][] board = new String[3][3];

    public void setSharedData(SharedData data) {
        this.data = data;
    }

    public BoardScene(Stage stage, SharedData data) {
        this.data = data;
        this.stage = stage;
        gridPane = new GridPane();
        arrow = new ImageView();
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        columnConstraints1 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        rowConstraints1 = new RowConstraints();
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
        playerTxt1 = new Text();
        playerTxt2 = new Text();
        scoreTxt1 = new Text();
        scoreTxt2 = new Text();

        oWinsScore = 0;
        xWinsScore = 0;

        xWins = false;
        oWins = false;

        alert = new Alert(Alert.AlertType.NONE);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = "";
            }
        }

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

        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMinWidth(10.0);
        columnConstraints.setPrefWidth(100.0);

        columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints0.setMinWidth(10.0);
        columnConstraints0.setPrefWidth(100.0);

        columnConstraints1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints1.setMinWidth(10.0);
        columnConstraints1.setPrefWidth(100.0);

        rowConstraints.setMinHeight(10.0);
        rowConstraints.setPrefHeight(30.0);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints0.setMinHeight(10.0);
        rowConstraints0.setPrefHeight(30.0);
        rowConstraints0.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints1.setMinHeight(10.0);
        rowConstraints1.setPrefHeight(30.0);
        rowConstraints1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        btn1.setMnemonicParsing(false);
        btn1.setPrefHeight(85.0);
        btn1.setPrefWidth(180.0);
        btn1.setStyle("-fx-background-color: #8DBFBC; -fx-text-fill: #000000;");
        btn1.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String let = getLetter();
                btn1.setText(let);

                btn1.setFont(new Font("Arial", 30));
                btn1.setDisable(true);

                board[0][0] = new String(let);

                chekingForWins();
//                
//                CheckOwin();
//                CheckXwin();
            }
        });

        GridPane.setColumnIndex(btn2, 1);
        btn2.setMnemonicParsing(false);
        btn2.setPrefHeight(104.0);
        btn2.setPrefWidth(161.0);

        btn2.setStyle("-fx-background-color: #8DBFBC; -fx-text-fill: #000000;");
        btn2.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String let = getLetter();
                btn2.setText(let);

                btn2.setFont(new Font("Arial", 30));
                btn2.setDisable(true);

                board[0][1] = new String(let);

                chekingForWins();
//                   CheckOwin();
//                CheckXwin();
            }
        });

        GridPane.setColumnIndex(btn3, 2);
        btn3.setMnemonicParsing(false);
        btn3.setPrefHeight(62.0);
        btn3.setPrefWidth(130.0);

        btn3.setStyle("-fx-background-color: #8DBFBC; -fx-text-fill: #000000;");
        btn3.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String let = getLetter();
                btn3.setText(let);
                btn3.setFont(new Font("Arial", 30));
                btn3.setDisable(true);
                board[0][2] = new String(let);

                chekingForWins();
//                   CheckOwin();
//                CheckXwin();
            }
        });

        GridPane.setRowIndex(btn4, 1);
        btn4.setMnemonicParsing(false);
        btn4.setPrefHeight(132.0);
        btn4.setPrefWidth(168.0);

        btn4.setStyle("-fx-background-color: #8DBFBC; -fx-text-fill: #000000;");
        btn4.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String let = getLetter();
                btn4.setText(let);
                btn4.setFont(new Font("Arial", 30));
                btn4.setDisable(true);
                board[1][0] = new String(let);

                chekingForWins();

//                   CheckOwin();
//                CheckXwin();
            }
        });

        GridPane.setColumnIndex(btn5, 1);
        GridPane.setRowIndex(btn5, 1);
        btn5.setMnemonicParsing(false);
        btn5.setPrefHeight(105.0);
        btn5.setPrefWidth(156.0);

        btn5.setStyle("-fx-background-color: #8DBFBC; -fx-text-fill: #000000;");
        btn5.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String let = getLetter();
                btn5.setText(let);
                btn5.setFont(new Font("Arial", 30));
                btn5.setDisable(true);
                board[1][1] = new String(let);

                chekingForWins();

//                   CheckOwin();
//                CheckXwin();
            }
        });

        GridPane.setColumnIndex(btn6, 2);
        GridPane.setRowIndex(btn6, 1);
        btn6.setMnemonicParsing(false);
        btn6.setPrefHeight(77.0);
        btn6.setPrefWidth(89.0);

        btn6.setStyle("-fx-background-color: #8DBFBC; -fx-text-fill: #000000;");
        btn6.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String let = getLetter();
                btn6.setText(let);
                btn6.setFont(new Font("Arial", 30));
                btn6.setDisable(true);
                board[1][2] = new String(let);

                chekingForWins();

//                   CheckOwin();
//                CheckXwin();
            }
        });

        GridPane.setRowIndex(btn7, 2);
        btn7.setMnemonicParsing(false);
        btn7.setPrefHeight(148.0);
        btn7.setPrefWidth(195.0);

        btn7.setStyle("-fx-background-color: #8DBFBC; -fx-text-fill: #000000;");
        btn7.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String let = getLetter();
                btn7.setText(let);
                btn7.setFont(new Font("Arial", 30));
                btn7.setDisable(true);
                board[2][0] = new String(let);

                chekingForWins();

//                   CheckOwin();
//                CheckXwin();
            }
        });

        GridPane.setColumnIndex(btn8, 1);
        GridPane.setRowIndex(btn8, 2);
        btn8.setMnemonicParsing(false);
        btn8.setPrefHeight(122.0);
        btn8.setPrefWidth(170.0);

        btn8.setStyle("-fx-background-color: #8DBFBC; -fx-text-fill: #000000;");
        btn8.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String let = getLetter();
                btn8.setText(let);
                btn8.setFont(new Font("Arial", 30));
                btn8.setDisable(true);
                board[2][1] = new String(let);

                chekingForWins();

//                   CheckOwin();
//                CheckXwin();
            }
        });

        GridPane.setColumnIndex(btn9, 2);
        GridPane.setRowIndex(btn9, 2);
        btn9.setMnemonicParsing(false);
        btn9.setPrefHeight(108.0);
        btn9.setPrefWidth(187.0);
        btn9.setStyle("-fx-background-color: #8DBFBC; -fx-text-fill: #000000;");
        btn9.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String let = getLetter();
                btn9.setText(let);
                btn9.setFont(new Font("Arial", 30));
                btn9.setDisable(true);

                board[2][2] = new String(let);

                chekingForWins();

//                   CheckOwin();
//                CheckXwin();
            }
        });

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

        playerTxt1.setFill(javafx.scene.paint.Color.valueOf("#04b1b8"));
        playerTxt1.setLayoutX(93.0);
        playerTxt1.setLayoutY(108.0);
        playerTxt1.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        playerTxt1.setStrokeWidth(0.0);
        playerTxt1.setText(data.getPlayerName1());
        // playerTxt1.setText("Player One");
        playerTxt1.setWrappingWidth(116.05340576171875);
        playerTxt1.setFont(new Font("Agency FB", 25.0));

        playerTxt2.setFill(javafx.scene.paint.Color.valueOf("#ecc150"));
        playerTxt2.setLayoutX(407.0);
        playerTxt2.setLayoutY(108.0);
        playerTxt2.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        playerTxt2.setStrokeWidth(0.0);
        playerTxt2.setText(data.getPlayerName2());
        //playerTxt2.setText("Player Two");
        playerTxt2.setWrappingWidth(147.930419921875);
        playerTxt2.setFont(new Font("Agency FB", 25.0));

        scoreTxt1.setFill(javafx.scene.paint.Color.valueOf("#fd6801"));
        scoreTxt1.setLayoutX(119.0);
        scoreTxt1.setLayoutY(131.0);
        scoreTxt1.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        scoreTxt1.setStrokeWidth(0.0);
        // scoreTxt1.setText("0");
        scoreTxt1.setText(String.valueOf(xWinsScore));
        scoreTxt1.setWrappingWidth(31.3482666015625);
        scoreTxt1.setFont(new Font("Agency FB", 20.0));

        scoreTxt2.setFill(javafx.scene.paint.Color.valueOf("#fd6801"));
        scoreTxt2.setLayoutX(437.0);
        scoreTxt2.setLayoutY(131.0);
        scoreTxt2.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        scoreTxt2.setStrokeWidth(0.0);
        // scoreTxt2.setText("0");
        scoreTxt2.setText(String.valueOf(oWinsScore));
        scoreTxt2.setWrappingWidth(31.348273158073425);
        scoreTxt2.setFont(new Font("Agency FB", 20.0));
        setPadding(new Insets(10.0));

        arrow.setAccessibleRole(javafx.scene.AccessibleRole.BUTTON);
        arrow.setFitHeight(34.0);
        arrow.setFitWidth(37.0);
        arrow.setLayoutX(36.0);
        arrow.setLayoutY(30.0);
        arrow.setPickOnBounds(true);
        arrow.setSmooth(false);
        arrow.setImage(new Image(getClass().getResource("/resources/arrow.png").toExternalForm()));
        arrow.setOnMouseClicked(e -> navigate());

        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getColumnConstraints().add(columnConstraints0);
        gridPane.getColumnConstraints().add(columnConstraints1);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getRowConstraints().add(rowConstraints0);
        gridPane.getRowConstraints().add(rowConstraints1);
        gridPane.getChildren().add(btn1);
        gridPane.getChildren().add(btn2);
        gridPane.getChildren().add(btn3);
        gridPane.getChildren().add(btn4);
        gridPane.getChildren().add(btn5);
        gridPane.getChildren().add(btn6);
        gridPane.getChildren().add(btn7);
        gridPane.getChildren().add(btn8);
        gridPane.getChildren().add(btn9);
        getChildren().add(gridPane);
        getChildren().add(text);
        getChildren().add(text0);
        getChildren().add(text1);
        getChildren().add(playerTxt1);
        getChildren().add(playerTxt2);
        getChildren().add(scoreTxt1);
        getChildren().add(scoreTxt2);
        getChildren().add(arrow);

    }

//    public String getLetter()
//    {
//        String letter=new String();
//        if(playerOne)
//        {
//            letter="o";
//            playerOne=false;
//         //   playerTwo=true;
//        }
////        else if(playerTwo)
////        {
////            letter="o";
////             playerOne=true;
////            playerTwo=false;
////            
////        }
//        else{
//            letter="x";
//            playerOne=true;
//           // letter="";
//        }
//        return letter;
//    }
    public String getLetter() {
        String letter;
        if (isPlayerOneTurn) {
            letter = "x";
        } else {
            letter = "o";
        }
        // Toggle the turn
        isPlayerOneTurn = !isPlayerOneTurn;
        return letter;
    }

    public boolean CheckXwin() {
        //row
        for (int i = 0; i < 3; i++) {
            if ("x".equals(board[i][0])
                    && "x".equals(board[i][1])
                    && "x".equals(board[i][2])) {
                //alertShowX();
                return true;
            }

        }
        //column
        for (int j = 0; j < 3; j++) {
            if ("x".equals(board[0][j])
                    && "x".equals(board[1][j])
                    && "x".equals(board[2][j])) {
                // alertShowX();
                return true;
            }

        }
        //diagonal

        if ("x".equals(board[0][0])
                && "x".equals(board[1][1])
                && "x".equals(board[2][2])) {
            return true;/*alertShowX();*/
        }

        if ("x".equals(board[0][2])
                && "x".equals(board[1][1])
                && "x".equals(board[2][0])) {
            return true;/*alertShowX();*/
        }

        return false;
    }

    public boolean CheckOwin() {
        //row
        for (int i = 0; i < 3; i++) {
            if ("o".equals(board[i][0])
                    && "o".equals(board[i][1])
                    && "o".equals(board[i][2])) {
                //alertShowO();
                return true;
            }

        }
        //column
        for (int j = 0; j < 3; j++) {
            if ("o".equals(board[0][j])
                    && "o".equals(board[1][j])
                    && "o".equals(board[2][j])) {
                // alertShowO();
                return true;
            }

        }
        //diagonal

        if ("o".equals(board[0][0])
                && "o".equals(board[1][1])
                && "o".equals(board[2][2])) {
            return true;/*alertShowO();*/
        }

        if ("o".equals(board[0][2])
                && "o".equals(board[1][1])
                && "o".equals(board[2][0])) {
            return true;/*alertShowO();*/
        }

        return false;

    }

    public void alertShowO() {

        oWinsScore++;
        scoreTxt2.setText(String.valueOf(oWinsScore));

        alert.setTitle("Player Two win");
        alert.setHeaderText(null);

        alertmode2Base customDialogPane = new alertmode2Base(data.getPlayerName2());

        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();

        alertStage.setOnCloseRequest(event -> {
//            event.consume(); // Consume the event to prevent the alert from closing
            navigate();
            alertStage.close();
            // Optionally, show a custom message or perform another action
            System.out.println("Close button disabled.");
        });

        // Set the custom dialog pane as the content of the alert
        alert.setDialogPane(customDialogPane);
        customDialogPane.getPlayAgainButton().setOnAction(e -> {
            // User chose "Play Again"
            System.out.println("Play Again selected.");
            playAgain();
            alertStage.close();
        });
        alertStage.showAndWait();

    }

    public void alertShowX() {

        xWinsScore++;
        scoreTxt1.setText(String.valueOf(xWinsScore));

        alert.setTitle("Player one Win");
        alert.setHeaderText(null);

        alertmode2Base customDialogPane = new alertmode2Base(data.getPlayerName1());

        // Set the custom dialog pane as the content of the alert
        alert.setDialogPane(customDialogPane);

        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();

        alertStage.setOnCloseRequest(event -> {
//            event.consume(); // Consume the event to prevent the alert from closing
            navigate();
            alertStage.close();
            // Optionally, show a custom message or perform another action
            System.out.println("Close button disabled.");
        });

        customDialogPane.getPlayAgainButton().setOnAction(e -> {
            // User chose "Play Again"
            System.out.println("Play Again selected.");
            playAgain();
            alertStage.close();
        });
        alertStage.showAndWait();

    }

    public void alertShowDraw() {

        alert.setTitle("Draw");
        alert.setHeaderText(null);

        nowinnermode1Base customDrawPane = new nowinnermode1Base();

        // Set the custom dialog pane as the content of the alert
        alert.setDialogPane(customDrawPane);

        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();

        alertStage.setOnCloseRequest(event -> {
//            event.consume(); // Consume the event to prevent the alert from closing
            navigate();
            alertStage.close();
            // Optionally, show a custom message or perform another action
            System.out.println("Close button disabled.");
        });

        customDrawPane.getPlayAgainButton().setOnAction(e -> {
            // User chose "Play Again"
            System.out.println("Play Again selected.");
            playAgain();
            alertStage.close();
        });
        alertStage.showAndWait();

    }

    public void playAgain() {
        btn1.setDisable(false);
        btn1.setText("");

        btn2.setDisable(false);
        btn2.setText("");

        btn3.setDisable(false);
        btn3.setText("");

        btn4.setDisable(false);
        btn4.setText("");

        btn5.setDisable(false);
        btn5.setText("");

        btn6.setDisable(false);
        btn6.setText("");

        btn7.setDisable(false);
        btn7.setText("");

        btn8.setDisable(false);
        btn8.setText("");

        btn9.setDisable(false);
        btn9.setText("");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = "";
                //   System.out.println(board[i][j]);
            }
        }
    }
//     public void chekingForWins()
//     {
//         boolean isBoardFull = true; // Assume the board is full initially
//
//            for (int i = 0; i < 3; i++) {
//                 for (int j = 0; j < 3; j++) {
//                    if (board[i][j].equals("")) {
//                          isBoardFull = false; // Found an empty cell, so the board is not full
//                          break; // Exit the inner loop
//                         }
//                    }
//                    if (!isBoardFull) {
//                             break; // Exit the outer loop
//                            }
//                }
//           if(CheckXwin())
//                {
//                    alertShowX();
//                }
//                if(CheckOwin())
//                {
//                    alertShowO();
//                }
//                if(!CheckXwin()&&!CheckOwin()&&isBoardFull)
//                {
//                    //System.out.println("Draw ");
//                    alertShowDraw();
//                }
//     }

    public void chekingForWins() {
        // First, check if X or O has won
        if (CheckXwin()) {
            alertShowX();
            return; // No need to check further if there's already a win
        } else if (CheckOwin()) {
            alertShowO();
            return; // No need to check further if there's already a win
        }

        // Check if the board is full (i.e., a draw)
        boolean isBoardFull = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals("")) {
                    isBoardFull = false; // Found an empty cell, so the board is not full
                    break; // Exit the inner loop
                }
            }
            if (!isBoardFull) {
                break; // Exit the outer loop
            }
        }

        // If the board is full and there is no winner, it's a draw
        if (isBoardFull) {
            alertShowDraw();
        }
    }

    public void navigate() {
    // Determine which player should get the score
    if (isPlayerOneTurn) {
        // Player One's turn, so Player Two wins
        oWinsScore++;
        scoreTxt2.setText(String.valueOf(oWinsScore));
    } else {
        // Player Two's turn, so Player One wins
        xWinsScore++;
        scoreTxt1.setText(String.valueOf(xWinsScore));
    }

    // Navigate back to the previous screen
    RegestirMode2Base back = new RegestirMode2Base(stage);
    Scene modScene = new Scene(back, 600, 400);
    stage.setScene(modScene);
}

}
