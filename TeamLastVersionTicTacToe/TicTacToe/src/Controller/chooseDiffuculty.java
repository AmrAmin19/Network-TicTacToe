package Controller;

import Controller.ChooseModeBase;
import Controller.Boardmode1Easy;
import Controller.BoardMode1Difficult;
import Controller.Boardmode1medium;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class chooseDiffuculty extends AnchorPane {

    protected final Text tictext;
    protected final Text tactext;
    protected final Text toetext;
    protected final Text choosedifftext;
    protected final Button btnEasy;
    protected final Button btnMedium;
    protected final Button btnDifficult;
    protected final ImageView arrow;
    private final Stage stage;

    public chooseDiffuculty(Stage stage) {
        this.stage = stage;

        tictext = new Text();
        tactext = new Text();
        toetext = new Text();
        choosedifftext = new Text();
        btnEasy = new Button();
        btnMedium = new Button();
        btnDifficult = new Button();
        arrow = new ImageView();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        AnchorPane.setBottomAnchor(tictext, 325.99);
        AnchorPane.setLeftAnchor(tictext, 206.0);
        AnchorPane.setRightAnchor(tictext, 346.03);
        AnchorPane.setTopAnchor(tictext, 20.008);
        tictext.setFill(javafx.scene.paint.Color.valueOf("#3bbbbe"));
        tictext.setLayoutX(206.0);
        tictext.setLayoutY(65.0);
        tictext.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        tictext.setStrokeWidth(0.0);
        tictext.setText("TIC");
        tictext.setWrappingWidth(50.70849609375);
        tictext.setFont(new Font("Agency FB Bold", 45.0));

        tactext.setFill(javafx.scene.paint.Color.valueOf("#fd6801"));
        tactext.setLayoutX(264.0);
        tactext.setLayoutY(65.0);
        tactext.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        tactext.setStrokeWidth(0.0);
        tactext.setText("TAC");
        tactext.setFont(new Font("Agency FB Bold", 45.0));

        toetext.setFill(javafx.scene.paint.Color.valueOf("#3bbbbe"));
        toetext.setLayoutX(334.0);
        toetext.setLayoutY(65.0);
        toetext.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        toetext.setStrokeWidth(0.0);
        toetext.setText("TOE");
        toetext.setFont(new Font("Agency FB Bold", 45.0));

        AnchorPane.setBottomAnchor(choosedifftext, 230.9921875);
        AnchorPane.setLeftAnchor(choosedifftext, 166.0);
        AnchorPane.setRightAnchor(choosedifftext, 156.265625);
        AnchorPane.setTopAnchor(choosedifftext, 121.1171875);
        choosedifftext.setFill(javafx.scene.paint.Color.valueOf("#ecc150"));
        choosedifftext.setLayoutX(166.0);
        choosedifftext.setLayoutY(161.0);
        choosedifftext.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        choosedifftext.setStrokeWidth(0.0);
        choosedifftext.setText("CHOOSE DIFFICULTY ");
        choosedifftext.setFont(new Font("Agency FB Bold", 40.0));

        btnEasy.setLayoutX(243.0);
        btnEasy.setLayoutY(200.0);
        btnEasy.setMnemonicParsing(false);
        btnEasy.setPrefHeight(51.0);
        btnEasy.setPrefWidth(124.0);
        btnEasy.setText("EASY ");
        btnEasy.setFont(new Font("Agency FB Bold", 20.0));
        btnEasy.setStyle("-fx-background-color: #2A9DB8; -fx-text-fill: #ffffff;");
        btnEasy.setOnAction(e -> navigatetoboardmode1easy());
        
        btnMedium.setLayoutX(243.0);
        btnMedium.setLayoutY(260.0);
        btnMedium.setMnemonicParsing(false);
        btnMedium.setPrefHeight(51.0);
        btnMedium.setPrefWidth(124.0);
        btnMedium.setText("MEDIUM");
        btnMedium.setFont(new Font("Agency FB Bold", 20.0));
        btnMedium.setStyle("-fx-background-color: #2A9DB8; -fx-text-fill: #ffffff;");
        btnMedium.setOnAction(e -> navigatemedium());

        btnDifficult.setLayoutX(243.0);
        btnDifficult.setLayoutY(320.0);
        btnDifficult.setMnemonicParsing(false);
        btnDifficult.setPrefHeight(51.0);
        btnDifficult.setPrefWidth(124.0);
        btnDifficult.setText("DIFFICULT");
        btnDifficult.setFont(new Font("Agency FB Bold", 21.0));
        btnDifficult.setStyle("-fx-background-color: #2A9DB8; -fx-text-fill: #ffffff;");
        btnDifficult.setOnAction(e -> navigatediff());

        arrow.setAccessibleRole(javafx.scene.AccessibleRole.BUTTON);
        arrow.setFitHeight(34.0);
        arrow.setFitWidth(37.0);
        arrow.setLayoutX(36.0);
        arrow.setLayoutY(30.0);
        arrow.setPickOnBounds(true);
        arrow.setSmooth(false);
        arrow.setImage(new Image(getClass().getResource("/resources/arrow.png").toExternalForm()));
        arrow.setOnMouseClicked(e -> navigateback());

        getChildren().add(tictext);
        getChildren().add(tactext);
        getChildren().add(toetext);
        getChildren().add(choosedifftext);
        getChildren().add(btnEasy);
        getChildren().add(btnMedium);
        getChildren().add(btnDifficult);
        getChildren().add(arrow);
    }

    public void navigateback() {
        ChooseModeBase mode = new ChooseModeBase(stage);
        Scene modScene = new Scene(mode, 600, 400);
        stage.setScene(modScene);
    }

    public void navigatemedium() {
        Boardmode1medium boardMode = new Boardmode1medium(stage);
        Scene borScene = new Scene(boardMode, 600, 400);
        stage.setScene(borScene);
    }

   public void navigatetoboardmode1easy() {
        Boardmode1Easy emode1 = new Boardmode1Easy(stage);
        Scene boardscene = new Scene(emode1, 600, 400);
        stage.setScene(boardscene);
    }
   public void navigatediff() {
        BoardMode1Difficult diffmod = new BoardMode1Difficult(stage);
        Scene diScene = new Scene(diffmod, 600, 400);
        stage.setScene(diScene);
    }
}
