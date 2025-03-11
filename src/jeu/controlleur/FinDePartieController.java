package jeu.controlleur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import jeu.modele.Partie;

public class FinDePartieController{
    @FXML private Button boutonQuitter;
    @FXML private Button boutonRejouer;
    @FXML private Button boutonAccueil;
    private String gagnant = "";
    @FXML private Label labelGagnant;
    private Partie partie = new Partie();
    
    public void setGagnant(String g){
        this.gagnant=g;
    }
    
    public void init(){
        labelGagnant.setText(gagnant + " a remporté la partie !");
        boutonAccueil.setOnAction(event ->{
            try{
                retournerDemarrage();
            } catch (Exception e){
                System.out.println("erreur retour demarrage");
            }
        });
            
        boutonQuitter.setOnAction(event ->{
            Stage stage = (Stage) boutonQuitter.getScene().getWindow();
            stage.close();
        });
        
        boutonRejouer.setOnAction(event ->{
            try{
                ouvrirFenetrePseudos();
            }catch(Exception e){}
        });
    }
    
    /**
     * Ouvre la page de sélection des pseudos et ferme celle de fin de partie.
     * Transmet la partie créée au controlleur des pseudos.
     * @throws Exception si la fenêtre des pseudos ne s'ouvre pas.
     */
    private void ouvrirFenetrePseudos() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PseudosJoueurs.fxml"));
        Parent root = loader.load();
    
        PseudosJoueursController controller = loader.getController();
        controller.setPartie(this.partie);

        Stage stage = new Stage();
        stage.setTitle("Saisie des pseudos");
        stage.setScene(new Scene(root,800,600));
        
        stage.show();
        Stage fenetreBase = (Stage) boutonRejouer.getScene().getWindow();
        fenetreBase.close();
    }
    
    /**
     * Retourne à l'écran de démarrage.
     * @throws Exception 
     */
    private void retournerDemarrage() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Demarrage.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Démarrage");
        stage.setScene(new Scene(root,800,600));
        
        stage.show();
        Stage fenetreBase = (Stage) boutonAccueil.getScene().getWindow();
        fenetreBase.close();
    }
    
    
    
}
