package jeu.controlleur;

import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
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
    private ArrayList<Carte> cartesJoueurPrincipal;
    private int cartesJoueurDroit = 0;
    private int cartesJoueurDevant = 0;
    private int cartesJoueurGauche = 0;
    @FXML private ImageView imagePaquet;
    @FXML private ImageView imagePioche;
    private final DropShadow surbrillance = new DropShadow();
    private final DropShadow ombreCarte = new DropShadow();
    @FXML private HBox paquetJoueurPrincipal;
    @FXML private VBox paquetJoueurDroit;
    @FXML private VBox paquetJoueurGauche;
    @FXML private HBox paquetJoueurDevant;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        partie = new Partie();
        // TODO
        cartesJoueurPrincipal = partie.getListeCartesJoueur(0);
        switch (partie.getNombreJoueurs()) {
            case 4 -> {
                cartesJoueurDroit = partie.getNombreCartesJoueur(1);
                cartesJoueurDevant = partie.getNombreCartesJoueur(2);
                cartesJoueurGauche = partie.getNombreCartesJoueur(3);
            }
            case 3 -> {
                cartesJoueurDroit = partie.getNombreCartesJoueur(1);
                cartesJoueurGauche = partie.getNombreCartesJoueur(2);
            }
            case 2 -> {
                cartesJoueurDevant = partie.getNombreCartesJoueur(1);
            }
        }
        
        ombreCarte.setColor(Color.BLACK);
        ombreCarte.setWidth(21);
        ombreCarte.setHeight(21);
        ombreCarte.setRadius(10);
        ombreCarte.setSpread(0);
        
        surbrillance.setColor(Color.CORAL);
        surbrillance.setRadius(20);
        surbrillance.setSpread(0.6);
        
        imagePaquet.setOnMouseEntered(event ->{
            imagePaquet.setScaleX(1.1);
            imagePaquet.setScaleY(1.1);
            imagePaquet.setEffect(surbrillance);
        });

        imagePaquet.setOnMouseExited(event ->{
            imagePaquet.setScaleX(1);
            imagePaquet.setScaleY(1);
            imagePaquet.setEffect(ombreCarte);
        });
        
        imagePioche.setOnMouseEntered(event ->{
            imagePioche.setScaleX(1.1);
            imagePioche.setScaleY(1.1);
            imagePioche.setEffect(surbrillance);
        });

        imagePioche.setOnMouseExited(event ->{
            imagePioche.setScaleX(1);
            imagePioche.setScaleY(1);
            imagePioche.setEffect(ombreCarte);
        });
        
        imagePioche.setOnMouseClicked(event ->{
            remettreCarteEnPlace();
        });
        
    }
    
    public void afficherCartesJoueurPrincipal(){
        HBox hb = paquetJoueurPrincipal;
        int nb = cartesJoueurPrincipal.size();
        int largeurCarte = 85;
        int maxEcart = -15;

        hb.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            double espace = Math.min(((newWidth.doubleValue()-largeurCarte)/(nb-1))-largeurCarte,maxEcart);
            hb.setSpacing(espace);
        });


        hb.getChildren().clear();
        hb.setAlignment(Pos.CENTER);
        for (Carte carte : cartesJoueurPrincipal) {
            CarteView carteView = new CarteView(carte, largeurCarte);
            ImageView image = carteView.getImageView();
    
            image.setUserData(carteView);
            image.setOnMouseClicked(event -> clickCarte(event));
            image.setEffect(ombreCarte);
            
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
    
    private void clickCarte(MouseEvent event) {
        ImageView imageView = (ImageView) event.getSource();
        CarteView carteView = (CarteView) imageView.getUserData();
        Carte carteCliquee = carteView.getCarte();

        if (partie.getCarteSelectionnee() == null) {
            partie.setCarteSelectionnee(carteCliquee);
            faireMonterCarte(imageView);
        } else {
            if (partie.carteDifferente(carteCliquee)) {
                    remettreCarteEnPlace();
                partie.setCarteSelectionnee(carteCliquee);
                faireMonterCarte(imageView);
            } else {
                partie.setCarteSelectionnee(null);
                remettreCarteEnPlace();
            }
        }
    }

    
    private void faireMonterCarte(ImageView iv) {
        iv.setTranslateY(-20);
        iv.setScaleX(1.2);
        iv.setScaleY(1.2);
        iv.setEffect(surbrillance);
    }

    private void remettreCarteEnPlace() {
        HBox hb = paquetJoueurPrincipal;
        for (Node node : hb.getChildren()) {
            if (node instanceof ImageView imageView) {
                imageView.setTranslateY(0);
                imageView.setScaleX(1);
                imageView.setScaleY(1);
                imageView.setEffect(ombreCarte);
            }
        }
    }
    
    
    public void afficherCartesJoueurDevant(){
        HBox hb = paquetJoueurDevant;
        int nb = cartesJoueurDevant;
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
            image.setEffect(ombreCarte);
            image.setViewOrder(i);
            hb.getChildren().add(image);
        }
    }
    
        public void afficherCartesJoueurGauche(){
            VBox vb = paquetJoueurGauche;
            int nb = cartesJoueurGauche;
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
                image.setEffect(ombreCarte);
                image.setRotate(90);
                image.setViewOrder(i);
                vb.getChildren().add(image);
            }
        }
        
            
        public void afficherCartesJoueurDroit(){
            VBox vb = paquetJoueurDroit;
            int nb = cartesJoueurDroit;
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
                image.setEffect(ombreCarte);
                image.setRotate(270);
                vb.getChildren().add(image);
            }
        }
    }
