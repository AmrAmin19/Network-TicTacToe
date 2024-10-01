package Controller;


import Controller.ConnectionManager;
import Controller.LoginBase;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import Data.SharedPlayer;
import static javafx.scene.layout.Region.USE_PREF_SIZE;

public class userRegisterBase extends AnchorPane {

    protected final Text text;
    protected final Text text0;
    protected final Text text1;
    protected final Text text2;
    protected final Text text3;
    protected final Text text4;
    protected final TextField NewUserNameTxtField;
    protected final TextField NewPassTxtField;
    protected final Button NewRegisterBtn;
    protected final ImageView arrow;
    protected final Text text5;
    protected final TextField NewEmailTxtField;
    protected final Stage stage;
    protected SharedPlayer data;
    private ConnectionManager connectionManager;

    public userRegisterBase(Stage stage) {
        this.stage = stage;
         connectionManager = ConnectionManager.getInstance();
        data =new SharedPlayer();
        text = new Text();
        text0 = new Text();
        text1 = new Text();
        text2 = new Text();
        text3 = new Text();
        text4 = new Text();
        NewUserNameTxtField = new TextField();
        NewPassTxtField = new TextField();
        NewRegisterBtn = new Button();
        arrow = new ImageView();
        text5 = new Text();
        NewEmailTxtField = new TextField();

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
        text0.setText("TOE");
        text0.setFont(new Font("Agency FB Bold", 45.0));

        text1.setFill(javafx.scene.paint.Color.valueOf("#fd6801"));
        text1.setLayoutX(264.0);
        text1.setLayoutY(65.0);
        text1.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text1.setStrokeWidth(0.0);
        text1.setText("TAC");
        text1.setFont(new Font("Agency FB Bold", 45.0));

        text2.setFill(javafx.scene.paint.Color.valueOf("#f4a24c"));
        text2.setLayoutX(51.0);
        text2.setLayoutY(242.0);
        text2.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text2.setStrokeWidth(0.0);
        text2.setText("ENTER USERNAME ");
        text2.setFont(new Font("Agency FB Bold", 35.0));

        text3.setFill(javafx.scene.paint.Color.valueOf("#f4a24c"));
        text3.setLayoutX(51.0);
        text3.setLayoutY(310.0);
        text3.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text3.setStrokeWidth(0.0);
        text3.setText("ENTER PASSWORD");
        text3.setFont(new Font("Agency FB Bold", 35.0));

        text4.setFill(javafx.scene.paint.Color.valueOf("#04b1b8"));
        text4.setLayoutX(159.0);
        text4.setLayoutY(122.0);
        text4.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text4.setStrokeWidth(0.0);
        text4.setText("CREATE ACCOUNT");
        text4.setFont(new Font("Agency FB Bold", 45.0));

        NewUserNameTxtField.setLayoutX(300.0);
        NewUserNameTxtField.setLayoutY(208.0);
        NewUserNameTxtField.setPrefHeight(42.0);
        NewUserNameTxtField.setPrefWidth(267.0);

        NewPassTxtField.setLayoutX(300.0);
        NewPassTxtField.setLayoutY(275.0);
        NewPassTxtField.setPrefHeight(42.0);
        NewPassTxtField.setPrefWidth(267.0);

        NewRegisterBtn.setLayoutX(230.0);
        NewRegisterBtn.setLayoutY(334.0);
        NewRegisterBtn.setMnemonicParsing(false);
        NewRegisterBtn.setPrefHeight(42.0);
        NewRegisterBtn.setPrefWidth(118.0);
        NewRegisterBtn.setText("SIGN UP");
        NewRegisterBtn.setFont(new Font("Agency FB Bold", 18.0));
        NewRegisterBtn.setStyle("-fx-background-color: #2A9DB8; -fx-text-fill: #ffffff;");
        // Handle register button
        NewRegisterBtn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    handleSignUp();
                    navigatetolog();
                } catch (JSONException ex) {
                    Logger.getLogger(userRegisterBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        arrow.setAccessibleRole(javafx.scene.AccessibleRole.BUTTON);
        arrow.setFitHeight(34.0);
        arrow.setFitWidth(37.0);
        arrow.setLayoutX(34.0);
        arrow.setLayoutY(40.0);
        arrow.setPickOnBounds(true);
        arrow.setSmooth(false);
        arrow.setImage(new Image(getClass().getResource("/resources/arrow2.png").toExternalForm()));

        text5.setFill(javafx.scene.paint.Color.valueOf("#f4a24c"));
        text5.setLayoutX(53.0);
        text5.setLayoutY(174.0);
        text5.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text5.setStrokeWidth(0.0);
        text5.setText("ENTER EMAIL ");
        text5.setFont(new Font("Agency FB Bold", 35.0));

        NewEmailTxtField.setLayoutX(300.0);
        NewEmailTxtField.setLayoutY(140.0);
        NewEmailTxtField.setPrefHeight(42.0);
        NewEmailTxtField.setPrefWidth(267.0);

        getChildren().add(text);
        getChildren().add(text0);
        getChildren().add(text1);
        getChildren().add(text2);
        getChildren().add(text3);
        getChildren().add(text4);
        getChildren().add(NewUserNameTxtField);
        getChildren().add(NewPassTxtField);
        getChildren().add(NewRegisterBtn);
        getChildren().add(arrow);
        getChildren().add(text5);
        getChildren().add(NewEmailTxtField);
        
        
      // connectionManager.addMessageListener(this);
    }

  

    public void navigatetolog() {
        LoginBase mode = new LoginBase(stage);
        Scene modScene = new Scene(mode, 600, 400);
        stage.setScene(modScene);
    }
    
    
    
     private void handleSignUp() throws JSONException
    {
         JSONObject signupData = new JSONObject();
         
                    signupData.put("query", "signUp");
                    signupData.put("email",NewEmailTxtField.getText());
                    System.out.println(NewEmailTxtField.getText());
                    signupData.put("username", NewUserNameTxtField.getText());
                     System.out.println(NewUserNameTxtField.getText());
                    signupData.put("password", NewPassTxtField.getText());
                     System.out.println(NewPassTxtField.getText());
                    
        connectionManager.sendMessage(signupData);
    }
}