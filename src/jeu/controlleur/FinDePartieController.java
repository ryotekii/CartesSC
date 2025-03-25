package jeu.controlleur;

import jeu.vue.BoutonTheme;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import jeu.modele.BDD;
import jeu.modele.Partie;

public class FinDePartieController{
    @FXML private Button boutonQuitter;
    @FXML private Button boutonRejouer;
    @FXML private Button boutonAccueil;
    private String gagnant = "";
    @FXML private Label labelGagnant;
    private Partie partie = new Partie();
    @FXML private StackPane placeBouton;
    
    /**
     * Définit le gagnant.
     * @param g le gagnant.
     */
    public void setGagnant(String g){
        this.gagnant=g;
    }
    
    /**
     * Initialise la page avec le nom du gagnant.
     */
    public void init(){
        placeBouton.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                BoutonTheme boutonTheme = new BoutonTheme(newScene);
                placeBouton.getChildren().add(boutonTheme);
            }
        });
        labelGagnant.setText(gagnant + " a remporté la partie !");
        BDD.ajouterVictoire(gagnant);
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
