package jeu.controlleur;

import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jeu.modele.BDD;

public class ClassementController implements Initializable {
    @FXML private Button boutonRetour;
    @FXML private VBox boxClassement;
    @FXML private Label scorePremier;
    @FXML private Label scoreDeuxieme;
    @FXML private Label scoreTroisieme;
    @FXML private Label pseudoPremier;
    @FXML private Label pseudoDeuxieme;
    @FXML private Label pseudoTroisieme;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        boutonRetour.setOnAction(event ->{
            try{
                retournerDemarrage();
            }catch(Exception e){
                System.out.println("erreur retour démarrage");
            }
        });
        recupererJoueurs();
    }
    
    private void recupererJoueurs(){
        LinkedHashMap<String,Integer> joueurs = BDD.recupererJoueurs();
        
        for (Map.Entry<String, Integer> joueur : joueurs.entrySet()){
            ajouterJoueur(joueur.getKey(),joueur.getValue());
        }
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
    
    private void ajouterJoueur(String pseudo, int points){
        if (pseudoPremier.getText().equals("Label")){
            pseudoPremier.setText(pseudo);
            scorePremier.setText(points + " victoires");
        } else if (scoreDeuxieme.getText().equals("Label")){
            pseudoDeuxieme.setText(pseudo);
            scoreDeuxieme.setText(points + " victoires");
        } else if (pseudoTroisieme.getText().equals("Label")){
            pseudoTroisieme.setText(pseudo);
            scoreTroisieme.setText(points + " victoires");
        } else {
            ajouterJoueurApresTop(pseudo,points);
        }
    }
    
    private void ajouterJoueurApresTop(String pseudo, int points){
        if (boxClassement.getChildren().size() <7){
            HBox joueur = new HBox(20);
            joueur.setStyle("-fx-alignment: center;"); 
            Label zonePseudo = new Label();
            Label zoneClassement = new Label();
            Label zoneScore = new Label();
            
            zonePseudo.setText(pseudo);
            zoneClassement.setText(boxClassement.getChildren().size()+4 + "e");
            zoneScore.setText(points + " victoires");
            
            zonePseudo.setStyle("-fx-font-size: 18px;");
            zoneClassement.setStyle("-fx-font-size: 18px;");
            zoneScore.setStyle("-fx-font-size: 18px;");
            
            HBox.setHgrow(zonePseudo, Priority.ALWAYS);
            VBox.setVgrow(joueur, Priority.ALWAYS);
            zonePseudo.setMaxWidth(Double.MAX_VALUE);

            zoneClassement.setAlignment(Pos.CENTER);
            zonePseudo.setAlignment(Pos.CENTER);
            zoneScore.setAlignment(Pos.CENTER);

            joueur.getChildren().addAll(zoneClassement, zonePseudo, zoneScore);
            boxClassement.getChildren().add(joueur);
        }
    }
}
