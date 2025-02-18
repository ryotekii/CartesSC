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
import jeu.console.Cartes.Carte;

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

    public void afficherCartesJoueur(FlowPane fp,boolean visible,ArrayList<Carte> cartes){
        int nb = cartes.size();
        /*
        Ajouter un chevauchement dynamique qui s'adapte au nombre de cartes et à la taille du fp.
        */
        int maxChevauchement = -45;
        /*
        Trouver la taille de la fenêtre.
        */
        fp.setHgap(-45);
        
        for (Carte carte:cartes){
            ImageView image = new ImageView(new Image(carte.nomImage()));
            image.setFitWidth(90);
            image.setPreserveRatio(true);
            fp.getChildren().add(image);
        }
    }
}
