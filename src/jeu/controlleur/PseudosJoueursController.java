package jeu.controlleur;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jeu.modele.Partie;


public class PseudosJoueursController implements Initializable {
    @FXML private Button boutonRetour;
    @FXML private VBox boxJoueurs;
    @FXML private Button boutonAjouter;
    private final ArrayList<HBox> joueurs = new ArrayList<>();
    
    
    private Partie partie;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        boutonRetour.setOnMouseClicked(event ->{
            try{
                retournerDemarrage();
            } catch (Exception e){
                System.out.println("erreur retour demarrage");
            }
        });
        boutonAjouter.setOnMouseClicked(event ->{
            ajouterJoueur();
        });
    }
    
    public void setPartie(Partie p){
        this.partie=p;
    }
    
    private void ajouterJoueur(){
        HBox joueur = new HBox(20);
        TextField zoneTexte = new TextField();
        zoneTexte.setPromptText("Joueur " + (joueurs.size() + 1));
        joueurs.add(joueur);
        boxJoueurs.getChildren().remove(boutonAjouter);
        boxJoueurs.getChildren().addAll(joueur,boutonAjouter);

        if (joueurs.size() > 2) {
            Button enlever = new Button("-");
            enlever.setOnAction(e -> {
                joueurs.remove(joueur);
                boxJoueurs.getChildren().remove(joueur);
            });
            joueur.getChildren().add(enlever);
        }

        joueur.getChildren().add(zoneTexte);
    }
    
    private void retournerDemarrage() throws Exception {
        FXMLLoader loader = new FXMLLoader(new File("src/jeu/controlleur/Demarrage.fxml").toURI().toURL());
        Parent root = loader.load();
    
        DemarrageController controller = loader.getController();

        Stage stage = new Stage();
        stage.setTitle("Démarrage");
        stage.setScene(new Scene(root,800,600));
        
        stage.show();
        Stage fenetreBase = (Stage) boutonRetour.getScene().getWindow();
        fenetreBase.close();
    }
}
