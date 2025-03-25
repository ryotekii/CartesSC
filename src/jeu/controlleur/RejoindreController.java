package jeu.controlleur;

import jeu.vue.BoutonTheme;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import jeu.modele.Joueur;

public class RejoindreController implements Initializable {
    @FXML private Label label;
    @FXML private Button boutonRetour;
    @FXML Button boutonTest;
    @FXML Button boutonRejoindre;
    @FXML TextField pseudoField;
    @FXML Button boutonQuitter;
    private Joueur joueur = new Joueur();
    @FXML StackPane placeBouton;
    
    private InetAddress ip;
    
    /**
     * Ajoute les effets sur les éléments graphiques.
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        boutonRejoindre.setDisable(true);
        boutonQuitter.setDisable(true);
        pseudoField.textProperty().addListener((observable, oldValue, newValue) -> {
            boutonRejoindre.setDisable(newValue.trim().isEmpty());
        });
        
        placeBouton.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                BoutonTheme boutonTheme = new BoutonTheme(newScene);
                placeBouton.getChildren().add(boutonTheme);
            }
        });
        
        boutonRetour.setOnAction(e ->{
            try{
                retournerDemarrage();
            } catch (Exception ex){
                System.out.println("erreur retour démarrage");
            }
        });
        
        boutonRejoindre.setOnAction(e -> {
            if (voirPartie()) {
                joueur.setPseudo(pseudoField.getText());
                //Serveur.rejoindrePartie(joueur);
            } else {
            label.setText("Aucune partie disponible.");
            }
        });
        
        boutonQuitter.setOnAction(e ->{
            //Serveur.quitterPartie(joueur);
        });

    }
    
    /**
     * Récupère l'IP de l'ordinateur.
     */
    private void getIp(){
        try {
            ip = InetAddress.getLocalHost();
            System.out.println(ip);
        } catch (UnknownHostException ex) {
            System.out.println("pas d'ip trouvée");
        }
    }
    
    /**
     * Permet de savoir s'il y a une partie en cours.
     * @return true ou false.
     */
    private boolean voirPartie() {
        getIp();
        try (Socket socket = new Socket(ip, 5000)) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    
    /**
     * Ouvre la fenêtre de démarrage et ferme celle du multijoueur.
     * @throws Exception si la fenêtre de démarrage ne s'ouvre pas.
     */
    private void retournerDemarrage() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Demarrage.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Démarrage");
        stage.setScene(new Scene(root,800,600));
        
        stage.show();
        Stage fenetreBase = (Stage) boutonRetour.getScene().getWindow();
        fenetreBase.close();
    }

}
