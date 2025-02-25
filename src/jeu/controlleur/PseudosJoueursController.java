package jeu.controlleur;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
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
    @FXML private Button boutonValider;
    private final ArrayList<TextField> joueurs = new ArrayList<>();
    
    
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
        ajouterJoueur();
        ajouterJoueur();
        boutonAjouter.setOnMouseClicked(event ->{
            ajouterJoueur();
        });
        boutonValider.setOnMouseClicked(event ->{
            System.out.println(partie);
            defNbJoueurs();
            creerListeJoueurs();
            partie.distribuer(7,null);
            try{
                lancerPartie();
            }catch(Exception e){
                System.out.println("erreur lancement partie");
                e.printStackTrace();
            }
        });
        
    }
    
    public void setPartie(Partie p){
        this.partie=p;
    }
    
    private void ajouterJoueur(){
        if(joueurs.size()<4){
            HBox joueur = new HBox(20);
            joueur.setStyle("-fx-alignment: center;"); 
            TextField zoneTexte = new TextField();
            zoneTexte.textProperty().addListener((observable, oldValue, newValue) -> {
                boutonValider.setDisable(!verifierPseudos());
            });
            zoneTexte.setPromptText("Pseudo du joueur " + (joueurs.size() + 1));
            joueurs.add(zoneTexte);
            boxJoueurs.getChildren().remove(boutonAjouter);
            boxJoueurs.getChildren().add(joueur);

            if (joueurs.size() > 2) {
                Button enlever = new Button("-");
                enlever.setOnAction(e -> {
                    supprimerJoueur();
                    if(!boxJoueurs.getChildren().contains(boutonAjouter)){
                        boxJoueurs.getChildren().add(boutonAjouter);
                    }
            
                });
                joueur.getChildren().add(enlever);
            }
            joueur.getChildren().add(zoneTexte);
            if(joueurs.size()<4){
                boxJoueurs.getChildren().add(boutonAjouter);
            }
        }
        boutonValider.setDisable(true);
    }
    
    private boolean verifierPseudos(){
        HashSet<String> pseudos = new HashSet<>();
        
        for (TextField joueur : joueurs) {
                    String pseudo = joueur.getText().trim();
                    if (pseudo.isEmpty() || !pseudos.add(pseudo)) {
                        return false;
                    }
                }        
        return true;
    }
    
    private void creerListeJoueurs(){
        int i=0;
        for(TextField joueur:joueurs){
            String pseudo = joueur.getText();
            partie.setPseudo(i, pseudo);
            i++;
        }
    }
    
    private void defNbJoueurs(){
        this.partie.ajouterJoueurs(joueurs.size());
        System.out.println(partie.getNombreJoueurs());
    }

    private void supprimerJoueur(){
        System.out.println(joueurs);
        joueurs.removeLast();
        System.out.println(joueurs);
        System.out.println(boxJoueurs.getChildren());
        if (boxJoueurs.getChildren().contains(boutonAjouter)){
            boxJoueurs.getChildren().remove(2);
        } else {
            boxJoueurs.getChildren().remove(3);
        }
        System.out.println(boxJoueurs.getChildren());
        /*boutonValider.setDisable(!verifierPseudos());*/
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
    
    private void lancerPartie() throws Exception {
        FXMLLoader loader = new FXMLLoader(new File("src/jeu/controlleur/FXML.fxml").toURI().toURL());
        Parent root = loader.load();
    
        FXMLController controller = loader.getController();
        controller.setPartie(partie);
        controller.init();

        Stage stage = new Stage();
        stage.setTitle("Partie");
        stage.setScene(new Scene(root,800,600));
        stage.setMinHeight(600);
        stage.setMinWidth(800);
        
        stage.show();
        Stage fenetreBase = (Stage) boutonRetour.getScene().getWindow();
        fenetreBase.close();
    }
}
