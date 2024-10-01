package servertictactoe;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Enumeration;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import database.TicTacToeDataBase;
import java.util.ConcurrentModificationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

public class ServerBase extends AnchorPane {

    protected final Text text;
    protected final Text text0;
    protected final Text text1;
    protected final Text text2;
    protected final ToggleButton startbtn;
    protected final ImageView graphbtn;
    protected final Text graphTxt;
    protected final Text activateTxt;
   protected Server server;
   private Stage stage;

    private ServerSocket serverSocket;

    public ServerBase(Stage stage) {
        
        this.stage=stage;

        text = new Text();
        text0 = new Text();
        text1 = new Text();
        text2 = new Text();
        startbtn = new ToggleButton();
        graphbtn = new ImageView();
        graphTxt = new Text();
        activateTxt = new Text();

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

        text2.setFill(javafx.scene.paint.Color.valueOf("#ebbf50"));
        text2.setLayoutX(241.0);
        text2.setLayoutY(151.0);
        text2.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text2.setStrokeWidth(0.0);
        text2.setText("SERVER");
        text2.setFont(new Font("Agency FB Bold", 45.0));

        // Initialize images
        Image startImage = new Image(getClass().getResource("/resources/start-here.jpeg").toExternalForm());
        Image stopImage = new Image(getClass().getResource("/resources/stopbtn.jpeg").toExternalForm());
        Image graphImage = new Image(getClass().getResource("/resources/growth.jpeg").toExternalForm());

        // Check for errors in loading images
        if (startImage.isError()) System.err.println("Error loading start image.");
        if (stopImage.isError()) System.err.println("Error loading stop image.");
        if (graphImage.isError()) System.err.println("Error loading graph image.");

        // Configure startbtn as a ToggleButton
        startbtn.setLayoutX(112.0);
        startbtn.setLayoutY(184.0);
        startbtn.setPrefSize(120.0, 118.0);

        ImageView startImageView = new ImageView(startImage);
        startImageView.setFitWidth(120.0); // Ensure ImageView respects the button size
        startImageView.setFitHeight(118.0);
        startbtn.setGraphic(startImageView);

        // Toggle button behavior
        startbtn.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            System.out.println("ToggleButton state changed. Is now selected: " + isNowSelected);
            if (isNowSelected) {
                ImageView stopImageView = new ImageView(stopImage);
                stopImageView.setFitWidth(120.0); // Ensure ImageView respects the button size
                stopImageView.setFitHeight(118.0);
                startbtn.setGraphic(stopImageView);
                activateTxt.setText("Deactivate");
                try {
                    activateServer();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.err.println("Failed to activate server.");
                }
            } else {
                startImageView.setFitWidth(120.0); // Ensure ImageView respects the button size
                startImageView.setFitHeight(118.0);
                startbtn.setGraphic(startImageView);
                activateTxt.setText("Activate");
                 deactivateServer();
            }
        });

        // Configure graphbtn
        graphbtn.setAccessibleRole(javafx.scene.AccessibleRole.BUTTON);
        graphbtn.setFitHeight(118.0);
        graphbtn.setFitWidth(138.0);
        graphbtn.setLayoutX(332.0);
        graphbtn.setLayoutY(184.0);
        graphbtn.setPickOnBounds(true);
        graphbtn.setPreserveRatio(true);
        graphbtn.setImage(graphImage);

        // Add event handler for graphbtn
        graphbtn.setOnMouseClicked(event -> showChart());

        // Configure graphTxt
        graphTxt.setFill(javafx.scene.paint.Color.valueOf("#0d888c"));
        graphTxt.setLayoutX(360.0);
        graphTxt.setLayoutY(332.0);
        graphTxt.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        graphTxt.setStrokeWidth(0.0);
        graphTxt.setText("Graph");
        graphTxt.setFont(new Font("Agency FB Bold", 30.0));

        // Configure activateTxt
        activateTxt.setFill(javafx.scene.paint.Color.valueOf("#0d888c"));
        activateTxt.setLayoutX(129.0);
        activateTxt.setLayoutY(332.0);
        activateTxt.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        activateTxt.setStrokeWidth(0.0);
        activateTxt.setText("Activate");
        activateTxt.setFont(new Font("Agency FB Bold", 30.0));

        getChildren().add(text);
        getChildren().add(text0);
        getChildren().add(text1);
        getChildren().add(text2);
        getChildren().add(startbtn);
        getChildren().add(graphbtn);
        getChildren().add(graphTxt);
        getChildren().add(activateTxt);
    }

    public void activateServer() throws IOException {
       try {
             server = Server.getServer();
            server.initServer();
              System.out.println("sucsess");
        } catch (Exception e) {
            e.printStackTrace();
              System.err.println("\"Server Status\", \"Failed to start the server!\"");
        }
       
        
    }

    public void deactivateServer()  {
        
       // server.stopServer();
        
       try{
        server.stopServer();
       }
       catch(ConcurrentModificationException c){
       
           System.out.println("cant close");
       }
    }

   

  
   
    private void showChart() {
        try {
            // Fetch the counts of online and offline players from the database
            TicTacToeDataBase tic = TicTacToeDataBase.getInstance();
            int onlinePlayers = 0;
            int offlinePlayers = 0;
            
            onlinePlayers = tic.getActivePlayerCount();
            offlinePlayers = tic.getInactivePlayerCount();
            
            // Create a PieChart to display online and offline players
            PieChart pieChart = new PieChart();
            pieChart.setTitle("Player Status");
            
            // Add data to the PieChart
            PieChart.Data onlineData = new PieChart.Data("Online", onlinePlayers);
            PieChart.Data offlineData = new PieChart.Data("Offline", offlinePlayers);
            pieChart.getData().addAll(onlineData, offlineData);
            
            // Create a new Stage (window) for the chart
            Stage chartStage = new Stage();
            chartStage.setTitle("Player Status Chart");
            
            // Set the Scene for the new Stage
            Scene scene = new Scene(new AnchorPane(pieChart), 500, 400);
            chartStage.setScene(scene);
            
            // Show the chart window
            chartStage.show();
        } catch (SQLException ex) {
            Logger.getLogger(ServerBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
