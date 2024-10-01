/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servertictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author amram
 */
public class ServerTicTacToe extends Application {
    
  
    
       @Override
    public void start(Stage primaryStage) throws Exception {
        /*Parent root = new Boardmode1medium(primaryStage);
        Scene mainScene = new Scene(root, 600, 400);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(mainScene);
        primaryStage.show();*/
        
      ServerBase mode = new ServerBase(primaryStage);
        Scene mainScene = new Scene(mode, 600, 400);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(mainScene);
        primaryStage.show();
        
       

    /**
     * @param the command line arguments
     */
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
