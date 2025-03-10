package jeu.controlleur;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import jeu.modele.Joueur;
import jeu.modele.Partie;
import jeu.modele.Serialisation;

public class SauvegardesController{
    @FXML private Button boutonRetour;
    @FXML private Button emplacement1;
    @FXML private Button emplacement2;
    @FXML private Button emplacement3;
    @FXML private Label labelSauvegardes;
    @FXML private Label label1;
    @FXML private Label label2;
    @FXML private Label label3;
    private Partie partie;
    
    public void changerLabelPrincipal(String s){
        if (s.equals("sauvegarder")){
            labelSauvegardes.setText("Choisissez un emplacement :");
        } else if (s.equals("reprendre")){
            labelSauvegardes.setText("Choisissez une partie :");
        }
    }
    
    public void definirSauvegarde(int n,Partie p){
        Label[] labels = new Label[]{label1,label2,label3};
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yy HH:mm"));
        String pseudos = "";
        for (Joueur joueur : p.getListeJoueurs()){
            pseudos += "\n"+joueur;
        }
        labels[n-1].setText("Sauvegarde "+n+" :\n"+date+pseudos);
        Serialisation.serialiser(p,n);
        String[] textesLabels = {label1.getText(), label2.getText(), label3.getText()};
        Serialisation.serialiserLabels(textesLabels);
    }
    
    public void setPartie(Partie p){
        this.partie=p;
    }
    public void initSauvegarder(){
        try{
            mettreAJourLabels();
        }catch (Exception e){}
        changerLabelPrincipal("sauvegarder");
        boutonRetour.setOnAction(e ->{
            retournerOptions();
        });
        emplacement1.setOnAction(e ->{
            definirSauvegarde(1,partie);
        });
        emplacement2.setOnAction(e ->{
            definirSauvegarde(2,partie);
        });
        emplacement3.setOnAction(e ->{
            definirSauvegarde(3,partie);
        });
    }
    
    public void initReprendre(){
        try{
            mettreAJourLabels();
        }catch (Exception e){}
        changerLabelPrincipal("reprendre");
        boutonRetour.setOnAction(e ->{
            try{
                retournerDemarrage();
            } catch (Exception ex){
                System.out.println("erreur retour démarrage");
            }
        });
        emplacement1.setOnAction(e ->{
            try{
                relancerPartie(1);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        });
        emplacement2.setOnAction(e ->{
            try{
                relancerPartie(2);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        });
        emplacement3.setOnAction(e ->{
            try{
                relancerPartie(3);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        });
    }
    
    /**
     * Ouvre la fenêtre de démarrage et ferme celle des sauvegardes.
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
    
    private void retournerOptions(){
        Stage stage = (Stage) boutonRetour.getScene().getWindow();
        stage.close();
    }
    
    private void mettreAJourLabels(){
        label1.setText(Serialisation.recupererLabels()[0]);
        label2.setText(Serialisation.recupererLabels()[1]);
        label3.setText(Serialisation.recupererLabels()[2]);
    }
    
    private void relancerPartie(int n) throws Exception {
        Partie partie = Serialisation.recuperer(n);
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
