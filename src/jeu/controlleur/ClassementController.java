package jeu.controlleur;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClassementController implements Initializable {
    @FXML private Button boutonRetour;
    @FXML private VBox boxClassement;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        boutonRetour.setOnAction(event ->{
            try{
                retournerDemarrage();
            }catch(Exception e){
                System.out.println("erreur retour démarrage");
            }
        });
    }
    
    /**
     * Ouvre la fenêtre de démarrage et ferme celle du classement.
     * @throws Exception si la fenêtre de démarrage ne s'ouvre pas.
     */
    private void retournerDemarrage() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Demarrage.fxml"));
        Parent root = loader.load();
    
        DemarrageController controller = loader.getController();

        Stage stage = new Stage();
        stage.setTitle("Démarrage");
        stage.setScene(new Scene(root,800,600));
        
        stage.show();
        Stage fenetreBase = (Stage) boutonRetour.getScene().getWindow();
        fenetreBase.close();
    }
    
    private void afficherClassement(){
        
    }
}
