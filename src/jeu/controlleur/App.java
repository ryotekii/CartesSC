package jeu.controlleur;

import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(new File("src/jeu/controlleur/Demarrage.fxml").toURI().toURL());
        Parent root = loader.load();
    
        DemarrageController controller = loader.getController();

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Démarrage");
        stage.setScene(scene);
        stage.show();
    }
    
    
    public static void main(String[] args){
        launch(args);
    }
    
}
