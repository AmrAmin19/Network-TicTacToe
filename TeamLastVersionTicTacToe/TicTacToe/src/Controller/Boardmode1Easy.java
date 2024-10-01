package Controller;

import java.util.Random;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import Dialogs.losermsgmode1Base;
import Dialogs.nowinnermode1Base;
import Dialogs.winnermsgmode1Base;

public class Boardmode1Easy extends AnchorPane {

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
    protected final ImageView arrow;
    protected final Stage stage;
    private boolean playerTurn = true; // Track player's turn
    private Random random = new Random();
    Alert alert;

    public Boardmode1Easy(Stage stage) {
        this.stage = stage;

        gridPane = new GridPane();
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
        arrow = new ImageView();
        alert = new Alert(AlertType.NONE);

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

        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getColumnConstraints().add(columnConstraints0);
        gridPane.getColumnConstraints().add(columnConstraints1);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getRowConstraints().add(rowConstraints0);
        gridPane.getRowConstraints().add(rowConstraints1);

        getChildren().add(gridPane);
        getChildren().add(text);
        getChildren().add(text0);
        getChildren().add(text1);
        getChildren().add(arrow);

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
        gridPane.getChildren().add(button);
    }

    private void handleButtonClick(Button button) {
        button.setDisable(true);
        button.setText("X");
        playerTurn = false;

        if (checkWin("X")) {
            alertShowX();
        } else if (isBoardFull()) {
            alertShowDraw();
        } else {
            // Computer makes a move after 1 second
            new Thread(() -> {
                try {
                    Thread.sleep(500); // 1 second delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(this::computerMove);
            }).start();
        }
    }

    private boolean checkWin(String symbol) {
        Button[][] combinations = {
            {btn1, btn2, btn3},
            {btn4, btn5, btn6},
            {btn7, btn8, btn9},
            {btn1, btn4, btn7},
            {btn2, btn5, btn8},
            {btn3, btn6, btn9},
            {btn1, btn5, btn9},
            {btn3, btn5, btn7},};

        for (Button[] line : combinations) {
            if (line[0].getText().equals(symbol) && line[1].getText().equals(symbol) && line[2].getText().equals(symbol)) {
                System.out.println(symbol + " wins!");
                return true;
            }
        }
        return false;
    }

      public void alertShowO() {
          Platform.runLater(() -> {
            // Create the winner message dialog
            losermsgmode1Base losserDialog = new losermsgmode1Base();

            // Create a modal dialog to display the winner message
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(stage);
            Scene dialogScene = new Scene(losserDialog, 500, 300);
            dialogStage.setScene(dialogScene);

            // Get the "PLAY AGAIN" button from the dialog
            Button playAgainButton = losserDialog.getPlayAgainButton();
            playAgainButton.setOnAction(e -> {
                losserDialog.stopMediaPlayer();
                dialogStage.close();
                stage.setScene(new Scene(new Boardmode1Easy(stage)));
            });

            // Handle dialog stage close request
            dialogStage.setOnCloseRequest((WindowEvent we) -> {
                losserDialog.stopMediaPlayer();
                stage.setScene(new Scene(new ChooseModeBase(stage)));
            });

            dialogStage.showAndWait(); // Show the modal dialog and wait for it to close
        });
    }

    public void alertShowX() {
        Platform.runLater(() -> {
            // Create the winner message dialog
            winnermsgmode1Base winnerDialog = new winnermsgmode1Base();

            // Create a modal dialog to display the winner message
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(stage);
            Scene dialogScene = new Scene(winnerDialog, 500, 300);
            dialogStage.setScene(dialogScene);

            // Get the "PLAY AGAIN" button from the dialog
            Button playAgainButton = winnerDialog.getPlayAgainButton();
            playAgainButton.setOnAction(e -> {
                winnerDialog.stopMediaPlayer();
                dialogStage.close();
                stage.setScene(new Scene(new Boardmode1Easy(stage)));
            });

            // Handle dialog stage close request
            dialogStage.setOnCloseRequest((WindowEvent we) -> {
                winnerDialog.stopMediaPlayer();
                stage.setScene(new Scene(new ChooseModeBase(stage)));
            });

            dialogStage.showAndWait(); // Show the modal dialog and wait for it to close
        });
    }
    
    public void alertShowDraw() {
        Platform.runLater(() -> {
            // Create the winner message dialog
            nowinnermode1Base draw = new nowinnermode1Base();

            // Create a modal dialog to display the winner message
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(stage);
            Scene dialogScene = new Scene(draw, 500, 300);
            dialogStage.setScene(dialogScene);

            // Get the "PLAY AGAIN" button from the dialog
            Button playAgainButton = draw.getPlayAgainButton();
            playAgainButton.setOnAction(e -> {
                draw.stopMediaPlayer();
                dialogStage.close();
                stage.setScene(new Scene(new Boardmode1Easy(stage)));
            });

            // Handle dialog stage close request
            dialogStage.setOnCloseRequest((WindowEvent we) -> {
                draw.stopMediaPlayer();
                stage.setScene(new Scene(new ChooseModeBase(stage)));
            });

            dialogStage.showAndWait(); // Show the modal dialog and wait for it to close
        });
    }
    

    public void playAgain() {
        resetBoard();  // Ensure the board is reset
        btn1.setDisable(false);
        btn2.setDisable(false);
        btn3.setDisable(false);
        btn4.setDisable(false);
        btn5.setDisable(false);
        btn6.setDisable(false);
        btn7.setDisable(false);
        btn8.setDisable(false);
        btn9.setDisable(false);
    }

    private void resetBoard() {
        for (Button button : new Button[]{btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9}) {
            button.setText("");
        }
        playerTurn = true;
    }

    private boolean isBoardFull() {
        for (Button button : new Button[]{btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9}) {
            if (button.getText().isEmpty()) {
                return false;
            }
        }
        return true;

    }

    private void computerMove() {
        Button[] buttons = {btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9};
        boolean moved = false;

        for (Button button : buttons) {
            if (button.getText().isEmpty()) {
                button.setText("O");
                button.setDisable(true);
                moved = true;
                break;
            }
        }

        if (!moved) {
            return; // Exit if no move was made
        }
        if (checkWin("O")) {
            alertShowO();
        } else if (isBoardFull()) {
            alertShowDraw();
        } else {
            playerTurn = true; // Allow player to move again
        }
    }

    private void navigateback() {
        chooseDiffuculty choosediff = new chooseDiffuculty(stage);
        Scene diffScene = new Scene(choosediff, 600, 400);
        stage.setScene(diffScene);

    }
}
