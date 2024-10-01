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

public class RecordBoard extends AnchorPane {

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
    //  private Thread listenerThread;
    public  String playerSymbol; // X or O
    public  String opponentSymbol; // O or X
    private  ConnectionManager connectionManager;

   
    private String moves;



   




    public RecordBoard(Stage stage )   {
        this.stage = stage;
       
        

        gridPane = new GridPane();
     
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
//        arrow.setOnMouseClicked(e -> navigateback());




        getChildren().add(gridPane);
        getChildren().add(text);
        getChildren().add(text0);
        getChildren().add(text1);
        getChildren().add(arrow);
       

    }

    
    

    public void initializeButton(Button button, int col, int row) {
        button.setMnemonicParsing(false);
        button.setPrefSize(100, 100);
        button.setText("");
        button.setStyle("-fx-background-color: #8DBFBC; -fx-text-fill: #000000;");
        button.setFont(new Font("Arial", 30));
       // button.setOnAction(e -> handleButtonClick(button));
        GridPane.setColumnIndex(button, col);
        GridPane.setRowIndex(button, row);
        gridPane.add(button, col, row);  // Use add method instead of getChildren().add
    }


   




    

    public int getButtonIndex(Button button) {
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

    public Button getButtonByIndex(int index) {
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

  

    

    public String[] getBoardState() {
        return new String[]{
                btn1.getText(), btn2.getText(), btn3.getText(),
                btn4.getText(), btn5.getText(), btn6.getText(),
                btn7.getText(), btn8.getText(), btn9.getText()
        };
    }

    public boolean isBoardFull() {
        for (String cell : getBoardState()) {
            if (cell.isEmpty()) {
                return false;
            }
        }
        return true;
    }


    public Button getButtonAt(int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return (Button) node;
            }
        }
        return null;
    }


   


   
   


}