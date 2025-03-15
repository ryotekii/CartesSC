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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jeu.modele.Partie;
import jeu.vue.BoutonTheme;

public class DemarrageController implements Initializable {
    @FXML private Button nouvellePartie;
    private Partie partie;
    @FXML private Button boutonQuitter;
    @FXML private Button boutonClassement;
    @FXML private Button boutonReprendre;
    @FXML private StackPane placeBouton;
    @FXML private Button boutonRejoindre;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        placeBouton.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) { // Vérifier que la scène est bien initialisée
                BoutonTheme boutonTheme = new BoutonTheme(newScene);
                placeBouton.getChildren().add(boutonTheme);
            }
        });
        nouvellePartie.setOnMouseClicked(event ->{
            creerPartie();
            System.out.println(this.partie);
            try{
                ouvrirFenetrePseudos();
            } catch (Exception e){
                System.out.println("erreur de chargement pseudos");
            }
        });
        
        boutonQuitter.setOnAction(event ->{
            Stage stage = (Stage) boutonQuitter.getScene().getWindow();
            stage.close();
        });
        
        boutonClassement.setOnAction(event ->{
            try{
                ouvrirClassement();
            }catch(Exception e){
                System.out.println("erreur affichage classement");
                e.printStackTrace();
            }
        });
        
        boutonRejoindre.setOnAction(event ->{
            try{
                ouvrirRejoindre();
            }catch(Exception e){
                e.printStackTrace();
            }
        });
        
        boutonReprendre.setOnAction(event ->{
            try{
                ouvrirSauvegardes();
            }catch(Exception e){
                System.out.println("erreur affichage sauvegardes");
                e.printStackTrace();
            }
        });
    }
    
    /**
     * Crée une nouvelle partie du modèle.
     */
    public void creerPartie(){
        this.partie = new Partie();
    }
    
    /**
     * Renvoie la partie créée.
     * @return la aprtie associée.
     */
    public Partie getPartie(){
        return this.partie;
    }
    
    /**
     * Ouvre la page du classement et ferme celle du démarrage.
     * @throws Exception si la page du classsement ne s'ouvre pas.
     */
    private void ouvrirClassement() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Classement.fxml"));
        Parent root = loader.load();
    
        //DemarrageController controller = loader.getController();

        Stage stage = new Stage();
        stage.setTitle("Classement");
        stage.setScene(new Scene(root,800,600));
        
        stage.show();
        Stage fenetreBase = (Stage) boutonClassement.getScene().getWindow();
        fenetreBase.close();
    }
    
    /**
     * Ouvre la page de sélection des pseudos et ferme celle de démarrage.
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
        Stage fenetreBase = (Stage) nouvellePartie.getScene().getWindow();
        fenetreBase.close();
    }
    
    /**
     * Ouvre la page des sauvegardes et ferme celle du démarrage.
     * @throws Exception si la page du classsement ne s'ouvre pas.
     */
    private void ouvrirSauvegardes() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Sauvegardes.fxml"));
        Parent root = loader.load();

        SauvegardesController controller = loader.getController();
        controller.initReprendre();
        
        Stage stage = new Stage();
        stage.setTitle("Sauvegardes");
        stage.setScene(new Scene(root,800,600));
        
        stage.show();
        Stage fenetreBase = (Stage) boutonReprendre.getScene().getWindow();
        fenetreBase.close();
    }
    
    /**
     * Ouvre la page de multijoueur et ferme celle du démarrage.
     * @throws Exception si la page du classsement ne s'ouvre pas.
     */
    private void ouvrirRejoindre() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Rejoindre.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Sauvegardes");
        stage.setScene(new Scene(root,800,600));
        
        stage.show();
        Stage fenetreBase = (Stage) boutonRejoindre.getScene().getWindow();
        fenetreBase.close();
    }
}

