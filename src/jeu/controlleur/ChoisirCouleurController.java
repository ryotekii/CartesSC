package jeu.controlleur;

import jeu.vue.BoutonTheme;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ChoisirCouleurController implements Initializable{
    @FXML private Rectangle choisirBleu;
    @FXML private Rectangle choisirRouge;
    @FXML private Rectangle choisirVert;
    private String couleur;
    private Stage popupStage;
    
    private final DropShadow ombre = new DropShadow();
    private final DropShadow surbrillance = new DropShadow();
    
    /**
     * Initialise les effets sur les composants graphiques.
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        choisirBleu.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                BoutonTheme boutonTheme = new BoutonTheme(newScene);
            }
        });
        ombre.setColor(Color.BLACK);
        ombre.setWidth(21);
        ombre.setHeight(21);
        ombre.setRadius(10);
        ombre.setSpread(0);
        
        surbrillance.setColor(Color.CORAL);
        surbrillance.setRadius(20);
        surbrillance.setSpread(0.6);
        setEffets(choisirBleu);
        setEffets(choisirRouge);
        setEffets(choisirVert);
    }
    
    /**
     * Ajoute les effets visuels et d'actions lorsqu'on passe la souris sur un rectangle.
     * Ajoute un halo et un effet grossissant au survol de l'élément.
     * @param r le rectangle.
     */
    private void setEffets(Rectangle r){                
        r.setOnMouseEntered(event ->{
            r.setScaleX(1.1);
            r.setScaleY(1.1);
            r.setEffect(surbrillance);
        });

        r.setOnMouseExited(event ->{
            r.setScaleX(1);
            r.setScaleY(1);
            r.setEffect(ombre);
        });
        
        r.setOnMouseClicked(event ->{
            selectionnerCouleur(Character.toUpperCase(r.getId().charAt(7)) + r.getId().substring(8).toLowerCase());
        });
    }
    
    /**
     * Définit la couleur à sélectionner et ferme la fenêtre.
     * @param c la couleur choisie.
     */
    private void selectionnerCouleur(String c){
        couleur = c;
        popupStage.close();
    }
    
    /**
     * Renvoie la couleur sélectionnée.
     * @return la couleur choisie.
     */
    public String getCouleur(){
        return this.couleur;
    }
    
    /**
     * Définit la fenêtre associée au popup.
     * @param s la fenêtre du popup.
     */
    public void setPopupStage(Stage s){
        this.popupStage = s;
    }
    
}
