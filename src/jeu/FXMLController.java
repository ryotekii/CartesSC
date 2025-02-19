/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package jeu;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import jeu.console.Cartes.Carte;
import jeu.console.Parametres;

/**
 * FXML Controller class
 * 
 * @author salom
 */
public class FXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void afficherCartesJoueurPrincipal(HBox hb,ArrayList<Carte> cartes){
        int nb = cartes.size();
        int largeurCarte = 85;

        hb.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            double espace = ((newWidth.doubleValue()-largeurCarte)/(nb-1))-largeurCarte;
            hb.setSpacing(espace);
        });


        hb.getChildren().clear();
        hb.setAlignment(Pos.CENTER);
        for (Carte carte : cartes) {
            ImageView image = new ImageView(new Image(carte.nomImage()));
            image.setFitWidth(largeurCarte);
            image.setPreserveRatio(true);
            hb.getChildren().add(image);
        }
    }
    
    public void afficherCartesJoueurDevant(HBox hb,int nb){
        int largeurCarte = 85;

        hb.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            double espace = ((newWidth.doubleValue()-largeurCarte)/(nb-1))-largeurCarte;
            hb.setSpacing(espace);
        });

        hb.getChildren().clear();
        hb.setAlignment(Pos.CENTER);
        for (int i=0;i<nb;i++) {
            ImageView image = new ImageView(new Image(Parametres.IMAGES+"dos.png"));
            image.setFitWidth(largeurCarte);
            image.setPreserveRatio(true);
            hb.getChildren().add(image);
        }
    }
    
        public void afficherCartesJoueurGauche(VBox vb,int nb){
            int largeurCarte = 85;

            vb.heightProperty().addListener((obs, oldHeight, newHeight) -> {
                double espace = ((newHeight.doubleValue()-largeurCarte)/(nb-1))-1.5*largeurCarte;
                vb.setSpacing(espace);
            });

            vb.getChildren().clear();
            vb.setAlignment(Pos.CENTER);
            for (int i=0;i<nb;i++) {
                ImageView image = new ImageView(new Image(Parametres.IMAGES+"dos.png"));
                image.setFitWidth(largeurCarte);
                image.setPreserveRatio(true);
                image.setRotate(90);
                vb.getChildren().add(image);
            }
        }
            
        public void afficherCartesJoueurDroit(VBox vb,int nb){
        int largeurCarte = 85;

        vb.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            double espace = ((newHeight.doubleValue()-largeurCarte)/(nb-1))-1.5*largeurCarte;
            vb.setSpacing(espace);
        });

        vb.getChildren().clear();
        vb.setAlignment(Pos.CENTER);
        for (int i=0;i<nb;i++) {
            ImageView image = new ImageView(new Image(Parametres.IMAGES+"dos.png"));
            image.setFitWidth(largeurCarte);
            image.setPreserveRatio(true);
            image.setRotate(270);
            vb.getChildren().add(image);
        }
    }
}
