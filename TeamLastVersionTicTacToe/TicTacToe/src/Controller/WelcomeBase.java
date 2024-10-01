package Controller;

import Controller.ChooseModeBase;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WelcomeBase extends AnchorPane {

    protected final Text tic;
    protected final Text tac;
    protected final Text toe;
    protected final Button startbtn;
    protected final ImageView gif;
    private final Stage stage;

    public WelcomeBase(Stage stage) {
        this.stage = stage;

        tic = new Text();
        tac = new Text();
        toe = new Text();
        startbtn = new Button();
        gif = new ImageView();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        tic.setFill(javafx.scene.paint.Color.valueOf("#04b1b8"));
        tic.setLayoutX(206.0);
        tic.setLayoutY(65.0);
        tic.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        tic.setStrokeWidth(0.0);
        tic.setText("TIC");
        tic.setFont(new Font("Agency FB Bold", 45.0));

        tac.setFill(javafx.scene.paint.Color.valueOf("#fd6801"));
        tac.setLayoutX(264.0);
        tac.setLayoutY(65.0);
        tac.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        tac.setStrokeWidth(0.0);
        tac.setText("TAC");
        tac.setFont(new Font("Agency FB Bold", 45.0));

        toe.setFill(javafx.scene.paint.Color.valueOf("#04b1b8"));
        toe.setLayoutX(334.0);
        toe.setLayoutY(65.0);
        toe.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        toe.setStrokeWidth(0.0);
        toe.setText("TOE");
        toe.setFont(new Font("Agency FB Bold", 45.0));

        startbtn.setDefaultButton(true);
        startbtn.setLayoutX(229.0);
        startbtn.setLayoutY(308.0);
        startbtn.setMnemonicParsing(false);
        startbtn.setPrefHeight(44.0);
        startbtn.setPrefWidth(143.0);
        startbtn.setText("Let's Start!");
        startbtn.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        startbtn.setFont(new Font("Agency FB Bold", 20.0));
        startbtn.setOpaqueInsets(new Insets(0.0));
        startbtn.setStyle("-fx-background-color: #2A9DB8; -fx-text-fill: #ffffff;");
        startbtn.setOnAction(e -> navigate());

        gif.setFitHeight(245.0);
        gif.setFitWidth(225.0);
        gif.setLayoutX(181.0);
        gif.setLayoutY(83.0);
        gif.setPickOnBounds(true);
        gif.setPreserveRatio(true);

        // Ensure the path is correct and the image exists at the given location
        try {
            gif.setImage(new Image(getClass().getResource("/resources/.gif").toExternalForm()));
        } catch (Exception e) {
            System.out.println("Image not found. Check the path: " + e.getMessage());
        }

        getChildren().add(tic);
        getChildren().add(tac);
        getChildren().add(toe);
        getChildren().add(startbtn);
        getChildren().add(gif);

       
    }

    private void navigate() {
        ChooseModeBase chooseMode = new ChooseModeBase(stage);
        Scene modeScene = new Scene(chooseMode, 600, 400);
        stage.setScene(modeScene);
    }
}
