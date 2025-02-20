package jeu.controlleur;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jeu.modele.Cartes.Carte;
import jeu.modele.Cartes.CarteSimple;
import jeu.modele.Cartes.CriseEpileptique;
import jeu.modele.Cartes.TroubleEquilibre;

public class testsGraphiques extends Application {
    Carte c1 = new CarteSimple(0,"bleu");
    Carte c2 = new CarteSimple(5,"rouge");
    Carte c3 = new CriseEpileptique();
    Carte c4 = new TroubleEquilibre("rouge");
    Carte c5 = new CarteSimple(2,"vert");
    Carte c6 = new CarteSimple(2,"vert");
    ArrayList<Carte> paquet = new ArrayList<>(List.of(c1,c2,c3,c4,c5,c5,c5,c6));
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        FXMLLoader loader = new FXMLLoader(new File("src/jeu/controlleur/FXML.fxml").toURI().toURL());

        AnchorPane root = loader.load();

        FXMLController controller = loader.getController();


        HBox cartesJoueurPrincipal = (HBox) root.lookup("#cartesJoueurPrincipal");
        controller.afficherCartesJoueurPrincipal(cartesJoueurPrincipal, paquet);
        
        HBox cartesJoueurDevant = (HBox) root.lookup("#cartesJoueurDevant");
        controller.afficherCartesJoueurDevant(cartesJoueurDevant,35);
        
        VBox cartesJoueurGauche = (VBox) root.lookup("#cartesJoueurGauche");
        controller.afficherCartesJoueurGauche(cartesJoueurGauche,3);
        
        VBox cartesJoueurDroit = (VBox) root.lookup("#cartesJoueurDroit");
        controller.afficherCartesJoueurDroit(cartesJoueurDroit,30);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);
        primaryStage.setTitle("Test FXML");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Carte c3 = new CriseEpileptique();
        System.out.println(c3.nomImage());
        launch(args);
    }
}
    
