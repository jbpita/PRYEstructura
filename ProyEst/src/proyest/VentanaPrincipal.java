/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyest;

import TDAs.LCDE;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sun.font.TextLabel;

/**
 *
 * @author laptop
 */
public class VentanaPrincipal {

    ScrollPane root;
    VBox juego;
    Stage stage;
    LCDE<ImageView> personas = new LCDE<>();
    LCDE<StackPane> sillas = new LCDE<>();

    public VentanaPrincipal(int numParticipantes, Stage stage) {
        this.stage = stage;
        createContent(numParticipantes);
    }

    private void createContent(int numParticipantes) {
        //Llenamos la lista con imagenes de las personas y la lista de sillas
        int p = 1;
        for (int i = 0; i < numParticipantes; i++) {
            Image personasn = new Image(getClass().getResourceAsStream("/image/p" + p + ".png"));
            ImageView view = new ImageView(personasn);
            view.setFitHeight(80);
            view.setFitWidth(80);
            personas.add(i, view);
            p++;
            if (p == 4) {
                p = 1;
            }
            if (i < numParticipantes - 1) {
                Image silla = new Image(getClass().getResourceAsStream("/image/silla.png"));
                ImageView views = new ImageView(silla);
                views.setFitHeight(120);
                views.setFitWidth(120);
                StackPane s = new StackPane();
                s.getChildren().add(views);
                sillas.add(i, s);
            }
        }
        //Pantalla Principal
        root = new ScrollPane();
        VBox V1 = new VBox();
        StackPane p1 = new StackPane();
        GridPane GP = new GridPane();

        // Image silla = new Image(getClass().getResourceAsStream("/image/silla.png"));
        // ImageView views = new ImageView(silla);
        //views.setFitHeight(120);
        //views.setFitWidth(120);
        //Image persona = new Image(getClass().getResourceAsStream("/image/p1.png"));
        //ImageView viewp1 = new ImageView(persona);
        //viewp1.setFitHeight(80);
        //viewp1.setFitWidth(80);
        juego = new VBox();
        juego.setAlignment(Pos.CENTER);
        //p1.getChildren().addAll(views, viewp1);
        Button bstart = new Button("Iniciar");
        Button bganar = new Button("GANAR");
        Button badelante = new Button("Adelante");
        Button batras = new Button("Atras");
        Button salir = new Button("Salir");
        HBox botones = new HBox();
        botones.getChildren().addAll(bstart,bganar, badelante, batras, salir);
        botones.setAlignment(Pos.CENTER);
        botones.setPadding(new Insets(25,25,25,25));
        botones.setSpacing(40);
        
        bstart.setMaxWidth(100);
        batras.setMaxWidth(100);
        badelante.setMaxWidth(100);
        salir.setMaxWidth(100);
        // bganar.setDisable(true);
        // V1.getChildren().add(p1);
        V1.getChildren().addAll(botones);
        V1.getChildren().add(juego);
       
        V1.setSpacing(10);
        for (int i = 0; i < personas.size(); i++) {
            V1.getChildren().add(personas.get(i));
            GP.add(personas.get(i), i, i);
        }
        GP.setAlignment(Pos.CENTER);
        V1.getChildren().add(GP);
        V1.setMinWidth(1000);
        V1.setMinHeight(800);
        BackgroundImage myBI= new BackgroundImage(new Image("/image/fiesta.jpg",1000,800,false,true),
        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
          BackgroundSize.DEFAULT);
//then you set to your node
        V1.setBackground(new Background(myBI));
        V1.setAlignment(Pos.CENTER);

        root = new ScrollPane(V1);
        salir.setOnAction(e -> {
            StackPane pane = new PaneOrganizer(this.stage).getRoot();
            Scene scene = new Scene(pane, 1000, 1000);
            this.stage.setScene(scene);
            this.stage.setTitle("Musical Chairs");
        });

        bstart.setOnAction(e -> {

            mover();
            // bstart.setDisable(true);
            //bganar.setDisable(false);
        });
        bganar.setOnAction(e -> {
            
           // mover();
            sentar();
            //bstart.setDisable(false);
            //bganar.setDisable(true);
        });
        badelante.setOnAction(e -> {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Runnable updater = new Runnable() {
                        @Override
                        public void run() {
                            personas.moverAdelante();
                            mover();
                        }
                    };
                    for (int i = 0; i < 10; i++) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Platform.runLater(updater);
                    }

            try{
           sentar();
            
            }catch(Exception e){
                
                }
                }

            });

            thread.setDaemon(true);
            thread.start();

        });
        batras.setOnAction(e -> {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Runnable updater = new Runnable() {
                        @Override
                        public void run() {
                            personas.moverAtras();
                            mover();
                        }
                    };
                    for (int i = 0; i < 10; i++) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Platform.runLater(updater);
                    }

       
            try{
           sentar();
            
            }catch(Exception e){}
                    

                }

            });

            thread.setDaemon(true);
            thread.start();

        });

    }

    public ScrollPane getRoot() {
        return root;
    }

    public void mover() {
        juego.getChildren().clear();
        
        GridPane inicio = new GridPane();
        inicio.getChildren().clear();
        if (personas.size()==1) {
              
               Label ganartexto = new Label("GANADOR!");
                ganartexto.setStyle("-fx-font-size:5em;-fx-text-fill:White;-fx-font-weight:bolder;");
               inicio.setAlignment(Pos.CENTER);
                inicio.add(personas.get(0), 1, 1);
                inicio.add(ganartexto,1,2);
            juego.getChildren().add(inicio);
        }else if (personas.size() == 2) {

            inicio.add(personas.get(0), 0, 1);
            inicio.add(sillas.get(0), 1, 1);
            inicio.add(personas.get(1), 2, 1);
            inicio.setAlignment(Pos.CENTER);
            juego.getChildren().add(inicio);

        } else if (personas.size() == 3) {
            inicio.add(personas.get(0), 2, 0);
            inicio.add(personas.get(2), 0, 1);
            inicio.add(sillas.get(0), 1, 1);
            inicio.add(sillas.get(1), 3, 1);
            inicio.add(personas.get(1), 4, 1);
            inicio.setAlignment(Pos.CENTER);
            juego.getChildren().add(inicio);

        } else if (personas.size() == 4) {
            inicio.add(personas.get(0), 2, 0);
            inicio.add(personas.get(1), 4, 1);
            inicio.add(sillas.get(0), 1, 1);
            inicio.add(sillas.get(1), 3, 1);
            inicio.add(personas.get(2), 2, 3);
            inicio.add(sillas.get(2), 2, 2);
            inicio.add(personas.get(3), 0, 1);
            inicio.setAlignment(Pos.CENTER);
            juego.getChildren().add(inicio);

        } else {
            //Numero de participantes Par

            if (personas.size() % 2 == 0) {
                int iteraciones = (personas.size() / 2) + 2;
                for (int i = 0; i < iteraciones; i++) {
                    if (i == 0) {
                        inicio.add(personas.get(0), 0, 2);
                    } else if (i == personas.size() / 2) {

                        inicio.add(sillas.get(personas.size() - 2), i, 2);
                    } else if (i == (personas.size() / 2) + 1) {

                        inicio.add(personas.get(personas.size() / 2), i, 2);
                    } else {

                        inicio.add(personas.get(i), i, 0);
                        inicio.add(sillas.get(i * 2 - 2), i, 1);
                        inicio.add(sillas.get(i * 2 - 1), i, 3);
                        inicio.add(personas.get(personas.size() - i), i, 4);

                    }

                }
                inicio.setAlignment(Pos.CENTER);
                juego.getChildren().add(inicio);

            } else {
                //Numero de participantes impar
                int iteraciones = ((personas.size() - 1) / 2) + 1;
                for (int i = 0; i < iteraciones; i++) {
                    if (i == 0) {
                        inicio.add(personas.get(0), 0, 2);
                    } else {

                        inicio.add(personas.get(i), i, 0);
                        inicio.add(sillas.get(i * 2 - 2), i, 1);
                        inicio.add(sillas.get(i * 2 - 1), i, 3);
                        inicio.add(personas.get(personas.size() - i), i, 4);

                    }

                }
                inicio.setAlignment(Pos.CENTER);
                juego.getChildren().add(inicio);

            }

        }

    }
        public void sentar(){
            int max = personas.size()-1;
            int min = 0;
            int random_int = (int)(Math.random() * (max - min + 1) + min);
           ImageView perdedor = personas.remove(random_int);
          
            for (int i = 0; i < personas.size(); i++) {
                  Image silla = new Image(getClass().getResourceAsStream("/image/silla.png"));
                ImageView views = new ImageView(silla);
                views.setFitHeight(120);
                views.setFitWidth(120);
                StackPane s = new StackPane();
                s.getChildren().add(views);
                sillas.add(i, s);
                
            }
                    juego.getChildren().clear();
            for (int i = 0; i < personas.size(); i++) {
                sillas.get(i).getChildren().add(personas.get(i));
               
            }
           
        GridPane inicio = new GridPane();
        inicio.getChildren().clear();
        if (personas.size() +1== 2) {

            inicio.add(perdedor, 0, 1);
            inicio.add(sillas.get(0), 1, 1);
            
            inicio.setAlignment(Pos.CENTER);
            juego.getChildren().add(inicio);
        for (int i = 0; i < personas.size()-1; i++) {
                  Image silla = new Image(getClass().getResourceAsStream("/image/silla.png"));
                ImageView views = new ImageView(silla);
                views.setFitHeight(120);
                views.setFitWidth(120);
                StackPane s = new StackPane();
                s.getChildren().add(views);
                sillas.add(i, s);
                
            }
        } else if (personas.size()+1 == 3) {
            inicio.add(perdedor, 2, 0);
            
            inicio.add(sillas.get(0), 1, 1);
            inicio.add(sillas.get(1), 3, 1);
          
            inicio.setAlignment(Pos.CENTER);
            juego.getChildren().add(inicio);
                    sillas.clear();
            for (int i = 0; i < personas.size()-1; i++) {
                  Image silla = new Image(getClass().getResourceAsStream("/image/silla.png"));
                ImageView views = new ImageView(silla);
                views.setFitHeight(120);
                views.setFitWidth(120);
                StackPane s = new StackPane();
                s.getChildren().add(views);
                sillas.add(i, s);
                
            }
        } else if (personas.size()+1 == 4) {
            inicio.add(perdedor, 2, 0);
         
            inicio.add(sillas.get(0), 1, 1);
            inicio.add(sillas.get(1), 3, 1);
           
            inicio.add(sillas.get(2), 2, 2);
           
            inicio.setAlignment(Pos.CENTER);
            juego.getChildren().add(inicio);
              for (int i = 0; i < personas.size()-1; i++) {
                  Image silla = new Image(getClass().getResourceAsStream("/image/silla.png"));
                ImageView views = new ImageView(silla);
                views.setFitHeight(120);
                views.setFitWidth(120);
                StackPane s = new StackPane();
                s.getChildren().add(views);
                sillas.add(i, s);
                
            }
        } else {
            //Numero de participantes Par

            if ((personas.size()+1) % 2 == 0) {
                
                int iteraciones = ((personas.size()+1)/2)+1;
                for (int i = 0; i < iteraciones; i++) {
                     System.out.println(personas.size());
                      System.out.println(i);
                    if (i == 0) {
                        inicio.add(perdedor, 0, 2);
                    } else if (i == (personas.size() +1)/ 2) {
                        System.out.println("Se llega?");
                        inicio.add(sillas.get(personas.size() - 1), i, 2);
                    }  else {

                       
                        inicio.add(sillas.get(i * 2 - 2), i, 1);
                        inicio.add(sillas.get(i * 2 - 1), i, 3);
                       

                    }

                }
                inicio.setAlignment(Pos.CENTER);
                juego.getChildren().add(inicio);
                for (int i = 0; i < personas.size()-1; i++) {
                  Image silla = new Image(getClass().getResourceAsStream("/image/silla.png"));
                ImageView views = new ImageView(silla);
                views.setFitHeight(120);
                views.setFitWidth(120);
                StackPane s = new StackPane();
                s.getChildren().add(views);
                sillas.add(i, s);
                
            }
            } else {
                System.out.println("Impar?");
                //Numero de participantes impar
                int iteraciones = ((personas.size() ) / 2) + 1;
                for (int i = 0; i < iteraciones; i++) {
                    if (i == 0) {
                        inicio.add(perdedor, 0, 2);
                    } else {

                      
                        inicio.add(sillas.get(i * 2 - 2), i, 1);
                        inicio.add(sillas.get(i * 2 - 1), i, 3);
                      

                    }

                }
                inicio.setAlignment(Pos.CENTER);
                juego.getChildren().add(inicio);
       for (int i = 0; i < personas.size()-1; i++) {
                  Image silla = new Image(getClass().getResourceAsStream("/image/silla.png"));
                ImageView views = new ImageView(silla);
                views.setFitHeight(120);
                views.setFitWidth(120);
                StackPane s = new StackPane();
                s.getChildren().add(views);
                sillas.add(i, s);
                
            }
            }

        }
            
    }
}
