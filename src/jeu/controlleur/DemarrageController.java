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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jeu.modele.Partie;

public class DemarrageController implements Initializable {
    @FXML private Button nouvellePartie;
    private Partie partie;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        nouvellePartie.setOnMouseClicked(event ->{
            creerPartie();
            System.out.println(this.partie);
            try{
                ouvrirFenetrePseudos();
            } catch (Exception e){
                System.out.println("erreur de chargement pseudos");
            }
        });
    }
    
    public void creerPartie(){
        this.partie = new Partie();
    }
    
    public Partie getPartie(){
        return this.partie;
    }
    
    private void ouvrirFenetrePseudos() throws Exception {
        FXMLLoader loader = new FXMLLoader(new File("src/jeu/controlleur/PseudosJoueurs.fxml").toURI().toURL());
        Parent root = loader.load();
    
        PseudosJoueursController controller = loader.getController();
        controller.setPartie(this.partie);

        Stage stage = new Stage();
        stage.setTitle("Saisie des pseudos");
        stage.setScene(new Scene(root,800,600));
        
        stage.show();
        Stage fenetreBase = (Stage) nouvellePartie.getScene().getWindow();
        fenetreBase.close();
    }
}

