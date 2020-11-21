/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyest;

import entities.*;
import java.util.InputMismatchException;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author PITA ESTRELLA JORGE BRYAN
 */
public class InitializeGame {

    private final Label label;
    private final StackPane root;
    private final Scene scene;
    private final TextField comboBox;
    private final GridPane gridPane;
    private final Button saveBtn;
    private final Button returnBtn;
    public static Game game;
    private final ImageView background;
    private final Image imageBackground;

    public InitializeGame(Stage stage) {
        this.root = new StackPane();
        this.label = new Label("Número de personas: ");
        this.saveBtn = new Button("Guardar");
        this.returnBtn = new Button("Regresar");
        this.comboBox = new TextField();;
        this.gridPane = new GridPane();
        imageBackground = new Image(getClass().getResourceAsStream("/image/fondo.png"));
        background = new ImageView(imageBackground);
        background.setFitHeight(500);
        background.setFitWidth(500);
        gridPane.add(label, 1, 1);
        gridPane.add(comboBox, 3, 1);
        gridPane.add(saveBtn, 1, 2);
        gridPane.add(returnBtn, 3, 2);
        root.getChildren().addAll(background, gridPane);
        root.setAlignment(Pos.CENTER);
        this.scene = new Scene(root, 500, 500);

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(25);
        gridPane.setVgap(50);
        events(stage);
    }

    public Scene getScene() {
        return scene;
    }

    public void events(Stage stage) {
        returnBtn.setOnAction((ActionEvent e) -> {
            PaneOrganizer rootContenedor = new PaneOrganizer(stage);
            StackPane root = rootContenedor.getRoot();
            Scene s = new Scene(root, 1000, 800);
            stage.setTitle("Musical Chairs");
            stage.setScene(s);
            stage.show();
            rootContenedor.setGame(InitializeGame.game);
        });

        saveBtn.setOnAction((ActionEvent e) -> {

            try {
                int n = Integer.parseInt(this.comboBox.getText());
                if (n <= 1) {
                    throw new NumberFormatException();
                }
                InitializeGame.game = new Game(n);

            } catch (NumberFormatException exc) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("ATENCIÓN: ");
                alert.setTitle("Error");
                alert.setContentText("Debe ingresar un numero mayor a 1");
                alert.showAndWait();
            }
        });

    }

}
