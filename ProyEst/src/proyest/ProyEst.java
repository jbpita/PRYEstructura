/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyest;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
/**
 *
 * @author erwin
 */
public class ProyEst extends Application {

    @Override
    public void start(Stage primaryStage) {
        PaneOrganizer rootContenedor = new PaneOrganizer(primaryStage);
        StackPane root = rootContenedor.getRoot();        
        Scene scene = new Scene(root, 1000,800);
        //scene.getStylesheets().add("proyest/stylesHome.css");
        primaryStage.setTitle("Musical Chairs");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     
        launch(args);
    }

}
