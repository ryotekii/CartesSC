package jeu.controlleur;

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
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
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
            selectionnerCouleur(r.getId().substring(7).toLowerCase());
        });
    }
    
    private void selectionnerCouleur(String c){
        couleur = c;
        popupStage.close();
    }
    
    public String getCouleur(){
        return this.couleur;
    }
    
    public void setPopupStage(Stage s){
        this.popupStage = s;
    }
    
}
