package jeu.controlleur;

import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import jeu.modele.Cartes.Carte;
import jeu.modele.Parametres;
import jeu.modele.Partie;
import jeu.vue.CarteView;

public class FXMLController implements Initializable {
    private Partie partie;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        partie = new Partie();
        // TODO
    }

    public void afficherCartesJoueurPrincipal(HBox hb,ArrayList<Carte> cartes){
        int nb = cartes.size();
        int largeurCarte = 85;
        int maxEcart = -15;

        hb.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            double espace = Math.min(((newWidth.doubleValue()-largeurCarte)/(nb-1))-largeurCarte,maxEcart);
            hb.setSpacing(espace);
        });


        hb.getChildren().clear();
        hb.setAlignment(Pos.CENTER);
        for (Carte carte : cartes) {
            CarteView carteView = new CarteView(carte, largeurCarte);
            ImageView image = carteView.getImageView();
    
            image.setUserData(carteView);
            image.setOnMouseClicked(event -> clickCarte(hb, event));
            DropShadow ombre = new DropShadow();
            ombre.setOffsetX(-5);
            ombre.setColor(Color.gray(0.7));
            ombre.setRadius(15);
            image.setEffect(ombre);
            
            image.setOnMouseEntered(event -> {
                if (image.getScaleX()==1){
                    image.setScaleX(1.05);
                    image.setScaleY(1.05);
                }
            });

            image.setOnMouseExited(event -> {
                if (image.getScaleX()==1.05){
                    image.setScaleX(1.0);
                    image.setScaleY(1.0);
                }
            });
            hb.getChildren().add(image);
        }
    }
    
    private void clickCarte(HBox hb, MouseEvent event) {
        ImageView imageView = (ImageView) event.getSource();
        CarteView carteView = (CarteView) imageView.getUserData();
        Carte carteCliquee = carteView.getCarte();

        if (partie.getCarteSelectionnee() == null) {
            partie.setCarteSelectionnee(carteCliquee);
            faireMonterCarte(imageView);
        } else {
            if (partie.carteDifferente(carteCliquee)) {
                    remettreCarteEnPlace(hb);
                partie.setCarteSelectionnee(carteCliquee);
                faireMonterCarte(imageView);
            } else {
                partie.setCarteSelectionnee(null);
                remettreCarteEnPlace(hb);
            }
        }
    }
    
    private void faireMonterCarte(ImageView iv) {
        iv.setTranslateY(-20);
        iv.setScaleX(1.2);
        iv.setScaleY(1.2);
    }

    private void remettreCarteEnPlace(HBox hb) {
        for (Node node : hb.getChildren()) {
            if (node instanceof ImageView imageView) {
                imageView.setTranslateY(0);
                imageView.setScaleX(1);
                imageView.setScaleY(1);
            }
        }
    }
    
    
    public void afficherCartesJoueurDevant(HBox hb,int nb){
        int largeurCarte = 85;
        int maxEcart = -15;

        hb.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            double espace = Math.min(((newWidth.doubleValue()-largeurCarte)/(nb-1))-largeurCarte,maxEcart);
            hb.setSpacing(espace);
        });

        hb.getChildren().clear();
        hb.setAlignment(Pos.CENTER);
        for (int i=0;i<nb;i++) {
            ImageView image = new ImageView(new Image(Parametres.IMAGES+"dos.png"));
            image.setFitWidth(largeurCarte);
            image.setPreserveRatio(true);
            DropShadow ombre = new DropShadow();
            ombre.setOffsetY(3);
            ombre.setColor(Color.gray(0));
            ombre.setRadius(10);
            image.setEffect(ombre);
            hb.getChildren().add(image);
        }
    }
    
        public void afficherCartesJoueurGauche(VBox vb,int nb){
            int largeurCarte = 85;
            int maxEcart = -80;

            vb.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            double espace = Math.min(((newHeight.doubleValue()-largeurCarte)/(nb-1))-1.4*largeurCarte,maxEcart);
            vb.setSpacing(espace);
        });

            vb.getChildren().clear();
            vb.setAlignment(Pos.CENTER);
            for (int i=0;i<nb;i++) {
                ImageView image = new ImageView(new Image(Parametres.IMAGES+"dos.png"));
                image.setFitWidth(largeurCarte);
                image.setPreserveRatio(true);
                DropShadow ombre = new DropShadow();
                ombre.setOffsetX(-3);
                ombre.setColor(Color.gray(0));
                ombre.setRadius(10);
                image.setEffect(ombre);
                image.setRotate(90);
                vb.getChildren().add(image);
            }
        }
            
        public void afficherCartesJoueurDroit(VBox vb,int nb){
        int largeurCarte = 85;
        int maxEcart = -80;

        vb.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            double espace = Math.min(((newHeight.doubleValue()-largeurCarte)/(nb-1))-1.4*largeurCarte,maxEcart);
            vb.setSpacing(espace);
        });

        vb.getChildren().clear();
        vb.setAlignment(Pos.CENTER);
        for (int i=0;i<nb;i++) {
            ImageView image = new ImageView(new Image(Parametres.IMAGES+"dos.png"));
            image.setFitWidth(largeurCarte);
            image.setPreserveRatio(true);
            DropShadow ombre = new DropShadow();
            ombre.setOffsetY(3);
            ombre.setColor(Color.gray(0));
            ombre.setRadius(10);
            image.setEffect(ombre);
            image.setRotate(270);
            vb.getChildren().add(image);
        }
    }
}
