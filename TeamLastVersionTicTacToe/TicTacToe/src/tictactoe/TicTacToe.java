/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import Controller.WelcomeBase;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Mai Ibrahem
 */
public class TicTacToe extends Application {
    
     @Override
    public void start(Stage primaryStage) throws Exception {
        /*Parent root = new Boardmode1medium(primaryStage);
        Scene mainScene = new Scene(root, 600, 400);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(mainScene);
        primaryStage.show();*/
        
        
       //Initialize the ChooseModeBase screen and pass the primary stage
        WelcomeBase Wb = new WelcomeBase(primaryStage);
        Scene mainScene = new Scene(Wb, 600, 400);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(mainScene);
        primaryStage.show();
        
       

    /**
     * @param the command line arguments
     */
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}