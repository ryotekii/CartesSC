package jeu.controlleur;

import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.stage.Stage;

public class App extends Application {
    
    /**
     * Lance l'interface graphique de la page d'accueil.
     * @param stage
     * @throws Exception si la fenêtre ne s'ouvre pas correctement.
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Demarrage.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Démarrage");
        stage.setScene(scene);
        stage.show();
    }
    
    
    public static void main(String[] args){
        launch(args);
    }
    
}
