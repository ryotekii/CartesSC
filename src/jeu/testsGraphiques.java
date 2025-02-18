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
    
    private FlowPane cartesJoueurPrincipal;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Charger le fichier FXML
        FXMLLoader loader = new FXMLLoader(new File("src/jeu/FXML.fxml").toURI().toURL());

        // Créer la scène avec l'FXML chargé
        AnchorPane root = loader.load();

        // Récupérer le contrôleur du FXML
        // Récupérer le contrôleur du FXML
        FXMLController controller = loader.getController();

        // Récupérer le FlowPane dans le fichier FXML
        FlowPane cartesJoueurPrincipal = (FlowPane) root.lookup("#cartesJoueurPrincipal");

        // Appeler la fonction de test directement
        controller.afficherCartesJoueur(cartesJoueurPrincipal,true, paquet);

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
    
