package Controller;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ChooseModeBase extends AnchorPane {

    protected final Text text;
    protected final Text text0;
    protected final Text text1;
    protected final Text text2;
    protected final Button OfflineBtn;
    protected final Button LocalBtn;
    protected final Button OnlineBtn;
    private final Stage stage;

    public ChooseModeBase(Stage stage) {
        this.stage = stage;
        text = new Text();
        text0 = new Text();
        text1 = new Text();
        text2 = new Text();
        OfflineBtn = new Button();
        LocalBtn = new Button();
        OnlineBtn = new Button();

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

        text2.setFill(javafx.scene.paint.Color.valueOf("#ecc150"));
        text2.setLayoutX(184.0);
        text2.setLayoutY(162.0);
        text2.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text2.setStrokeWidth(0.0);
        text2.setText("CHOOSE MODE");
        text2.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        text2.setWrappingWidth(231.830078125);
        text2.setFont(new Font("Agency FB Bold", 45.0));

        OfflineBtn.setLayoutX(243.0);
        OfflineBtn.setLayoutY(200.0);
        OfflineBtn.setMnemonicParsing(false);
        OfflineBtn.setPrefHeight(51.0);
        OfflineBtn.setPrefWidth(124.0);
        OfflineBtn.setText("OFFLINE");
        OfflineBtn.setFont(new Font("Agency FB Bold", 20.0));
        OfflineBtn.setStyle("-fx-background-color: #2A9DB8; -fx-text-fill: #ffffff;");

        LocalBtn.setLayoutX(243.0);
        LocalBtn.setLayoutY(260.0);
        LocalBtn.setMnemonicParsing(false);
        LocalBtn.setPrefHeight(51.0);
        LocalBtn.setPrefWidth(124.0);
        LocalBtn.setText("LOCAL");
        LocalBtn.setFont(new Font("Agency FB Bold", 20.0));
        LocalBtn.setStyle("-fx-background-color: #2A9DB8; -fx-text-fill: #ffffff;");

        OnlineBtn.setLayoutX(243.0);
        OnlineBtn.setLayoutY(320.0);
        OnlineBtn.setMnemonicParsing(false);
        OnlineBtn.setPrefHeight(51.0);
        OnlineBtn.setPrefWidth(124.0);
        OnlineBtn.setText("ONLINE");
        OnlineBtn.setFont(new Font("Agency FB Bold", 20.0));
        OnlineBtn.setStyle("-fx-background-color: #2A9DB8; -fx-text-fill: #ffffff;");

        getChildren().add(text);
        getChildren().add(text0);
        getChildren().add(text1);
        getChildren().add(text2);
        getChildren().add(OfflineBtn);
        getChildren().add(LocalBtn);
        getChildren().add(OnlineBtn);
// Set button event handlers
        OfflineBtn.setOnAction(e -> navigate());
       
       LocalBtn.setOnAction(e -> navigatetoregistermode2());
        OnlineBtn.setOnAction(e -> navigateToEnterIP());
        
    }

    public void navigate() {
        chooseDiffuculty difficultyScreen = new chooseDiffuculty(stage);
        Scene difficultyScene = new Scene(difficultyScreen, 600, 400);
        stage.setScene(difficultyScene);
    }
    
    public void navigateToEnterIP(){
    EnterIpBase  enterIpscreen = new EnterIpBase(stage);
   Scene enterIPScene = new Scene(enterIpscreen, 600, 400);
        stage.setScene(enterIPScene);
    }
    public void navigatetoregistermode2(){
    RegestirMode2Base  regismod2 = new RegestirMode2Base(stage);
   Scene mode2regis = new Scene(regismod2, 600, 400);
        stage.setScene(mode2regis);
    }
}