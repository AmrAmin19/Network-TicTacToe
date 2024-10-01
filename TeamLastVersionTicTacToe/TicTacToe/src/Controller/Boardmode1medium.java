package Controller;

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

public class Boardmode1medium extends AnchorPane {

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
    Alert alert;
    private boolean playerTurn = true;
    private final Stage stage;

    public Boardmode1medium(Stage stage) {
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

    private void computerMove() {
        if (makeWinningMove("O") || blockWinningMove("X") || takeCenter() || takeCorner() || takeSide()) {
            if (checkWin("O")) {
                alertShowO();
            } else if (isBoardFull()) {
                alertShowDraw();
            }
            playerTurn = true;
        }
    }

    private boolean makeWinningMove(String symbol) {
        return makeStrategicMove(symbol);
    }

    private boolean blockWinningMove(String symbol) {
        return makeStrategicMove(symbol);
    }

    private boolean makeStrategicMove(String symbol) {
        // Define all winning combinations
        int[][] winningCombinations = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columns
            {0, 4, 8}, {2, 4, 6} // Diagonals
        };

        String[] board = getBoardState();
        int emptyIndex = -1;
        for (int[] combination : winningCombinations) {
            int count = 0;
            for (int index : combination) {
                if (board[index].equals(symbol)) {
                    count++;
                } else if (board[index].isEmpty()) {
                    emptyIndex = index;
                }
            }
            if (count == 2 && emptyIndex != -1) {
                // Make the move in the empty spot
                setButtonText(emptyIndex, "O");
                return true;
            }
        }
        return false;
    }

    private boolean takeCenter() {
        if (getButtonText(4).isEmpty()) {
            setButtonText(4, "O");
            return true;
        }
        return false;
    }

    private boolean takeCorner() {
        int[] corners = {0, 2, 6, 8};
        for (int corner : corners) {
            if (getButtonText(corner).isEmpty()) {
                setButtonText(corner, "O");
                return true;
            }
        }
        return false;
    }

    private boolean takeSide() {
        int[] sides = {1, 3, 5, 7};
        for (int side : sides) {
            if (getButtonText(side).isEmpty()) {
                setButtonText(side, "O");
                return true;
            }
        }
        return false;
    }

    private String[] getBoardState() {
        String[] board = new String[9];
        for (int i = 0; i < 9; i++) {
            board[i] = getButtonText(i);
        }
        return board;
    }

    private String getButtonText(int index) {
        switch (index) {
            case 0:
                return btn1.getText();
            case 1:
                return btn2.getText();
            case 2:
                return btn3.getText();
            case 3:
                return btn4.getText();
            case 4:
                return btn5.getText();
            case 5:
                return btn6.getText();
            case 6:
                return btn7.getText();
            case 7:
                return btn8.getText();
            case 8:
                return btn9.getText();
            default:
                return "";
        }
    }

    private void setButtonText(int index, String text) {
        switch (index) {
            case 0:
                btn1.setText(text);
                btn1.setDisable(true);
                break;
            case 1:
                btn2.setText(text);
                btn2.setDisable(true);
                break;
            case 2:
                btn3.setText(text);
                btn3.setDisable(true);
                break;
            case 3:
                btn4.setText(text);
                btn4.setDisable(true);
                break;
            case 4:
                btn5.setText(text);
                btn5.setDisable(true);
                break;
            case 5:
                btn6.setText(text);
                btn6.setDisable(true);
                break;
            case 6:
                btn7.setText(text);
                btn7.setDisable(true);
                break;
            case 7:
                btn8.setText(text);
                btn8.setDisable(true);
                break;
            case 8:
                btn9.setText(text);
                btn9.setDisable(true);
                break;
        }
    }

    private boolean isBoardFull() {
        String[] board = getBoardState();
        for (String cell : board) {
            if (cell.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private boolean checkWin(String symbol) {
        // Define all winning combinations
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
                return true;
            }
        }
        return false;
    }

    private void alertShowX() {
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
                stage.setScene(new Scene(new Boardmode1medium(stage)));
            });

            // Handle dialog stage close request
            dialogStage.setOnCloseRequest((WindowEvent we) -> {
                winnerDialog.stopMediaPlayer();
                stage.setScene(new Scene(new ChooseModeBase(stage)));
            });

            dialogStage.showAndWait(); // Show the modal dialog and wait for it to close
        });

    }

    private void alertShowO() {
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
                stage.setScene(new Scene(new Boardmode1medium(stage)));
            });

            // Handle dialog stage close request
            dialogStage.setOnCloseRequest((WindowEvent we) -> {
                losserDialog.stopMediaPlayer();
                stage.setScene(new Scene(new ChooseModeBase(stage)));
            });

            dialogStage.showAndWait(); // Show the modal dialog and wait for it to close
        });
    }

    private void alertShowDraw() {
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
                stage.setScene(new Scene(new Boardmode1medium(stage)));
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

    }

    public void navigateback() {
        chooseDiffuculty diff = new chooseDiffuculty(stage);
        Scene difScene = new Scene(diff, 600, 400);
        stage.setScene(difScene);
    }
}
