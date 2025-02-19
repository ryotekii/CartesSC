package jeu;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jeu.console.Cartes.Carte;
import jeu.console.Cartes.CarteSimple;
import jeu.console.Cartes.CriseEpileptique;
import jeu.console.Cartes.TroubleEquilibre;

public class testsGraphiques extends Application {
    Carte c1 = new CarteSimple(0,"bleu");
    Carte c2 = new CarteSimple(5,"rouge");
    Carte c3 = new CriseEpileptique();
    Carte c4 = new TroubleEquilibre("rouge");
    Carte c5 = new CarteSimple(2,"vert");
    ArrayList<Carte> paquet = new ArrayList<>(List.of(c1,c2,c3,c4,c5,c5,c5,c5,c5,c5,c5,c5,c5,c5,c5,c5));
    
    private HBox cartesJoueurPrincipal;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        FXMLLoader loader = new FXMLLoader(new File("src/jeu/FXML.fxml").toURI().toURL());

        AnchorPane root = loader.load();

        FXMLController controller = loader.getController();


        HBox cartesJoueurPrincipal = (HBox) root.lookup("#cartesJoueurPrincipal");
        controller.afficherCartesJoueurPrincipal(cartesJoueurPrincipal, paquet);
        
        HBox cartesJoueurDevant = (HBox) root.lookup("#cartesJoueurDevant");
        controller.afficherCartesJoueurDevant(cartesJoueurDevant,1);
        
        VBox cartesJoueurGauche = (VBox) root.lookup("#cartesJoueurGauche");
        controller.afficherCartesJoueurGauche(cartesJoueurGauche,5);
        
        VBox cartesJoueurDroit = (VBox) root.lookup("#cartesJoueurDroit");
        controller.afficherCartesJoueurDroit(cartesJoueurDroit,8);

        // Créer et afficher la scène
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Test FXML");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
    Carte c1 = new CarteSimple(0,"bleu");
    Carte c2 = new CarteSimple(5,"rouge");
    Carte c3 = new CriseEpileptique();
    Carte c4 = new TroubleEquilibre("rouge");
        System.out.println(c3.nomImage());
        launch(args);
    }
}
    
