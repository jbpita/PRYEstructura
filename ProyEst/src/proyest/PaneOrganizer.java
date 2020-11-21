/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyest;

import entities.Game;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author PITA ESTRELLA JORGE BRYAN
 */
public class PaneOrganizer {

    private VBox boxVertical;
    private StackPane root;
    private ImageView Title, background;
    private Button startBtn, inicializarBtn;
    private Game game;
    private Image image;
    private Image imageBackground;

    public PaneOrganizer(Stage stage) {
        createContent(stage);
    }

    public void createContent(Stage stage) {
        this.root = new StackPane();
        image = new Image(getClass().getResourceAsStream("/image/head.png"));
        Title = new ImageView(image);
        Title.setFitHeight(80);
        Title.setFitWidth(200);
        imageBackground = new Image(getClass().getResourceAsStream("/image/fondo.png"));
        background = new ImageView(imageBackground);
        background.setFitHeight(1000);
        background.setFitWidth(1000);
        this.startBtn = new Button("EMPEZAR");
        startBtn.setId("botones");
        this.inicializarBtn = new Button("INICIALIZAR");
        startBtn.setMaxWidth(100);
        inicializarBtn.setMaxWidth(100);
        this.boxVertical = new VBox();
        boxVertical.setSpacing(10);
        boxVertical.setAlignment(Pos.CENTER);
        boxVertical.getChildren().addAll(Title, startBtn, inicializarBtn);
        root.getChildren().add(background);
        root.getChildren().add(boxVertical);
        events(stage);
    }

    public StackPane getRoot() {
        return this.root;
    }

    public void events(Stage stage) {
        this.inicializarBtn.setOnAction(e -> {
            InitializeGame g = new InitializeGame(stage);
            Scene scene = g.getScene();
            stage.setScene(scene);
            this.setGame(InitializeGame.game);
        });

        this.startBtn.setOnAction(e -> {
            try {
                int n = this.getGame().getNumCompetitor();
//                System.out.println(n);
//                VentanaJuego ventanaJuego = new VentanaJuego(this.getGame());               
//                stage.setScene(ventanaJuego.getScene());
                
                VentanaPrincipal principal = new VentanaPrincipal(n,stage);
                startBtn.getScene().setRoot(principal.getRoot());
                //
            
            } catch (NullPointerException exc) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("ATENCIÓN: ");
                alert.setTitle("Error");
                alert.setContentText("Debe Inicializar la partida primero ");
                alert.showAndWait();
                System.out.println(exc);
            }
        });
    }

    /**
     * @return the game
     */
    public Game getGame() {
        return game;
    }

    /**
     * @param game the game to set
     */
    public void setGame(Game game) {
        this.game = game;
    }

}
