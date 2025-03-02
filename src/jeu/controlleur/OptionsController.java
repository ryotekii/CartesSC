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
import javafx.stage.Stage;
import jeu.modele.Partie;

/**
 * La fenêtre d'options en partie.
 */
public class OptionsController implements Initializable {
    @FXML private Button boutonQuitter;
    @FXML private Button boutonReprendre;
    private Partie partie;
    private Stage popupStage;
    private Button boutonFinir;
    
    /**
     * Définit la partie en cours.
     * @param p la aprtie.
     */
    public void setPartie(Partie p){
        this.partie=p;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        boutonReprendre.setOnAction(event ->{
            Stage stage = (Stage) boutonQuitter.getScene().getWindow();
            stage.close();
        });
    }
    
    /**
     * Définit le bouton associée à la partie pour garder en mémoire la fenêtre associée.
     * @param b un bouton présent sur la table de jeu.
     */
    public void setBoutonFinir(Button b){
        this.boutonFinir=b;
        boutonQuitter.setOnAction(event ->{
            try{
                retourAccueil();
            }catch(Exception e){
                e.printStackTrace();
                System.out.println("erreur retour page d'accueil");
            }
        });
    }
    
    
    public void setPopupStage(Stage s){
        this.popupStage = s;
    }
    
    /**
     * Retourne à la page d'accueil et quitte la partie en cours.
     * @throws Exception 
     */
    public void retourAccueil() throws Exception {
        FXMLLoader loader = new FXMLLoader(new File("src/jeu/controlleur/Demarrage.fxml").toURI().toURL());
        Parent root = loader.load();
    
        DemarrageController controller = loader.getController();

        Stage stage = new Stage();
        stage.setTitle("Démarrage");
        stage.setScene(new Scene(root,800,600));
        
        stage.show();
        Stage fenetreBase = (Stage) boutonQuitter.getScene().getWindow();
        fenetreBase.close();
        Stage fenetreJeu = (Stage) boutonFinir.getScene().getWindow();
        fenetreJeu.close();
    }
}
