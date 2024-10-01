package Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Data.SharedData;

public class RegestirMode2Base extends AnchorPane {

    protected final Text text;
    protected final Text text0;
    protected final Text text1;
    protected final Text text2;
    protected final Text text3;
    protected final TextField Player1TextField;
    protected final TextField Player2TextField;
    protected final Button PlayBtn;
    protected final ImageView arrow;
    private final Stage stage;
    SharedData data;
    
    public void setSharedData(SharedData data)
    {
        this.data=data;
    }
    
    public RegestirMode2Base(Stage stage) {
        this.stage = stage;
        text = new Text();
        text0 = new Text();
        text1 = new Text();
        text2 = new Text();
        text3 = new Text();
        Player1TextField = new TextField();
        Player2TextField = new TextField();
        PlayBtn = new Button();
        arrow = new ImageView();
        data=new SharedData();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

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
        text0.setText("TAC");
        text0.setFont(new Font("Agency FB Bold", 45.0));

        text1.setFill(javafx.scene.paint.Color.valueOf("#fd6801"));
        text1.setLayoutX(264.0);
        text1.setLayoutY(65.0);
        text1.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text1.setStrokeWidth(0.0);
        text1.setText("TOE");
        text1.setFont(new Font("Agency FB Bold", 45.0));

        text2.setFill(javafx.scene.paint.Color.valueOf("#f4a24c"));
        text2.setLayoutX(70.0);
        text2.setLayoutY(143.0);
        text2.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text2.setStrokeWidth(0.0);
        text2.setText("ENTER PLAYER 1 NAME");
        text2.setFont(new Font("Agency FB Bold", 35.0));

        text3.setFill(javafx.scene.paint.Color.valueOf("#f4a24c"));
        text3.setLayoutX(67.0);
        text3.setLayoutY(276.0);
        text3.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text3.setStrokeWidth(0.0);
        text3.setText("ENTER PLAYER 2 NAME");
        text3.setFont(new Font("Agency FB Bold", 35.0));

        Player1TextField.setLayoutX(67.0);
        Player1TextField.setLayoutY(159.0);
        Player1TextField.setPrefHeight(42.0);
        Player1TextField.setPrefWidth(267.0);

        Player2TextField.setLayoutX(66.0);
        Player2TextField.setLayoutY(289.0);
        Player2TextField.setPrefHeight(42.0);
        Player2TextField.setPrefWidth(267.0);

        PlayBtn.setLayoutX(480.0);
        PlayBtn.setLayoutY(337.0);
        PlayBtn.setMnemonicParsing(false);
        PlayBtn.setPrefHeight(45.0);
        PlayBtn.setPrefWidth(101.0);
        PlayBtn.setText("PLAY");
        PlayBtn.setFont(new Font("Agency FB Bold", 24.0));
        PlayBtn.setStyle("-fx-background-color: #2A9DB8; -fx-text-fill: #ffffff;");
        PlayBtn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              
             data.setPlayerName1(Player1TextField.getText());
             data.setPlayerName2(Player2TextField.getText());
             
              navigate();
               
            }
        });

        arrow.setAccessibleRole(javafx.scene.AccessibleRole.BUTTON);
        arrow.setFitHeight(34.0);
        arrow.setFitWidth(37.0);
        arrow.setLayoutX(24.0);
        arrow.setLayoutY(30.0);
        arrow.setPickOnBounds(true);
        arrow.setSmooth(false);
        arrow.setImage(new Image(getClass().getResource("/resources/arrow2.png").toExternalForm()));
         arrow.setOnMouseClicked(e -> navigateback());

        getChildren().add(text);
        getChildren().add(text0);
        getChildren().add(text1);
        getChildren().add(text2);
        getChildren().add(text3);
        getChildren().add(Player1TextField);
        getChildren().add(Player2TextField);
        getChildren().add(PlayBtn);
        getChildren().add(arrow);
        
      
    }

   public void navigate() {
       BoardScene boardScene = new BoardScene(stage,data);
        Scene modScene = new Scene(boardScene, 600, 400);
        stage.setScene(modScene);
    }
      public void navigateback() {
        ChooseModeBase mode = new ChooseModeBase(stage);
        Scene modScene = new Scene(mode, 600, 400);
        stage.setScene(modScene);
    }

  }
