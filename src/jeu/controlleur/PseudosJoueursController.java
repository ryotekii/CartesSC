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
import jeu.modele.Cartes.Carte;
import jeu.modele.Partie;

/**
 * Page de définition des pseudos des joueurs.
 */
public class PseudosJoueursController implements Initializable {
    @FXML private Button boutonRetour;
    @FXML private VBox boxJoueurs;
    @FXML private Button boutonAjouter;
    @FXML private Button boutonValider;
    private final ArrayList<TextField> joueurs = new ArrayList<>();
    
    
    private Partie partie;
    
    /**
     * Initialise la saisie des pseudos. Créé deux champs de saisie et un bouton
     * "+" pour ajouter des joueurs. Le bouton valider initialise la partie et
     * lui donne les informations sur les joueurs.
     * @param url
     * @param rb 
     */
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
            // partie.poserPremiereCarte();
            partie.poserPremiereCarte();
            try{
                lancerPartie();
            }catch(Exception e){
                System.out.println("erreur lancement partie");
                e.printStackTrace();
            }
        });
        
    }
    
    /**
     * Définit la partie en cours.
     * @param p la aprtie.
     */
    public void setPartie(Partie p){
        this.partie=p;
    }
    
    /**
     * Créé une zone de saisie de pseudo, dans une limite de 4 à l'écran.
     * S'il y en a plus de deux, ajoute un bouton supprimer. Ajoute les pseudos des
     * joueurs dans une liste.
     */
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
    
    /**
     * Vérifie si les champs de pseudos sont remplis et s'ils sont tous différents.
     * @return <code>true</code> si les pseudos sont remplis et différents, <code>false</code> finon.
     */
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
    
    /**
     * Donne les pseudos à la partie pour qu'elle les associe les joueurs.
     */
    private void creerListeJoueurs(){
        int i=0;
        for(TextField joueur:joueurs){
            String pseudo = joueur.getText();
            partie.setPseudo(i, pseudo);
            i++;
        }
    }
    
    /**
     * Donne le nombre de joueurs à la partie pour qu'elle créée les joueurs.
     */
    private void defNbJoueurs(){
        this.partie.ajouterJoueurs(joueurs.size());
        System.out.println(partie.getNombreJoueurs());
    }

    /**
     * Supprime le dernier joueur de la liste et de l'écran.
     */
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
    
    /**
     * Retourne à l'écran de démarrage et abandonne la saisie des pseudos.
     * @throws Exception 
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
    
    /**
     * Lance l'affichage de la table de jeu avec les informations des joueurs.
     * @throws Exception si la table de jeu ne s'affiche pas.
     */
    private void lancerPartie() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML.fxml"));
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
