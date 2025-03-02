package jeu.controlleur;

import java.io.File;
import java.io.IOException;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jeu.modele.Cartes.AmnesieSelective;
import jeu.modele.Cartes.BlocageMoteur;
import jeu.modele.Cartes.Carte;
import jeu.modele.Cartes.CarteSimple;
import jeu.modele.Cartes.CriseEpileptique;
import jeu.modele.Cartes.MainEtrangere;
import jeu.modele.Cartes.Narcolepsie;
import jeu.modele.Cartes.NeuropathieO;
import jeu.modele.Cartes.Paralysie;
import jeu.modele.Cartes.Tdah;
import jeu.modele.Cartes.TroubleEquilibre;
import jeu.modele.Parametres;
import jeu.modele.Partie;
import jeu.vue.CarteView;

/**
 * Controlleur de la page principale de la partie, affichant la table de jeu et
 * les mouvements possibles.
 */
public class FXMLController {
    private Partie partie;
    private ArrayList<Carte> cartesJoueurPrincipal;
    private int cartesJoueurDroit = 0;
    private int cartesJoueurDevant = 0;
    private int cartesJoueurGauche = 0;
    @FXML private ImageView imagePioche;
    private final DropShadow surbrillance = new DropShadow();
    private final DropShadow ombreCarte = new DropShadow();
    @FXML private HBox paquetJoueurPrincipal;
    @FXML private VBox paquetJoueurDroit;
    @FXML private VBox paquetJoueurGauche;
    @FXML private HBox paquetJoueurDevant;
    @FXML private HBox boxPaquet;
    @FXML private Rectangle carreCouleur;
    @FXML private Label pseudoDevant;
    @FXML private Label pseudoGauche;
    @FXML private Label pseudoDroit;
    @FXML private Label pseudoPrincipal;
    @FXML private Button boutonOptions;
    @FXML private Button boutonFinir;
    @FXML private Button boutonInfos;
    @FXML private Tooltip tooltipInfos;

    /**
     * Initialise la table de jeu. Créé les paquets à l'écran en fonction du
     * nombre de joueurs, ajoute des effets à la pioche et relie le bouton options
     * à la page correspondante.
     */
    public void init() {
        this.partie.setController(this);
        cartesJoueurPrincipal = partie.getListeCartesJoueur(0);
        switch (partie.getNombreJoueurs()) {
            case 4 -> {
                pseudoPrincipal.setText(partie.getListeJoueurs()[0].getPseudo());
                cartesJoueurDroit = partie.getNombreCartesJoueur(1);
                pseudoDroit.setText(partie.getListeJoueurs()[1].getPseudo());
                cartesJoueurDevant = partie.getNombreCartesJoueur(2);
                pseudoDevant.setText(partie.getListeJoueurs()[2].getPseudo());
                cartesJoueurGauche = partie.getNombreCartesJoueur(3);
                pseudoGauche.setText(partie.getListeJoueurs()[3].getPseudo());
            }
            case 3 -> {
                pseudoPrincipal.setText(partie.getListeJoueurs()[0].getPseudo());
                cartesJoueurDroit = partie.getNombreCartesJoueur(1);
                pseudoDroit.setText(partie.getListeJoueurs()[1].getPseudo());
                cartesJoueurGauche = partie.getNombreCartesJoueur(2);
                pseudoGauche.setText(partie.getListeJoueurs()[2].getPseudo());
            }
            case 2 -> {
                pseudoPrincipal.setText(partie.getListeJoueurs()[0].getPseudo());
                cartesJoueurDevant = partie.getNombreCartesJoueur(1);
                pseudoDevant.setText(partie.getListeJoueurs()[1].getPseudo());
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
            partie.getListeJoueurs()[0].piocher();
            this.mettreAJourAffichage();
        });
        mettreAJourAffichage();
        
        boutonOptions.setOnMouseClicked(event ->{
            try{
                ouvrirOptions();
            }catch(Exception e){
                System.out.println("erreur ouverture options");
            }
        });
        
        boutonInfos.setOnMouseEntered(event ->{
            Point2D p = boutonInfos.localToScreen(boutonInfos.getLayoutBounds().getMaxX(), boutonInfos.getLayoutBounds().getMaxY());
            tooltipInfos.show(boutonInfos, p.getX(), p.getY());
        });
        
        boutonInfos.setOnMouseExited(event ->{
            tooltipInfos.hide();
        });
        
    }
    
    /**
     * Définit la partie associée.
     * @param p la aprtie.
     */
    public void setPartie(Partie p){
        this.partie = p;
    }
    
    /**
     * Remplace l'image au-dessus du paquet par l'image de la carte la plus au-dessus du paquet.
     */
    public void mettreAJourViewPaquet() {
        CarteView nouvelleCarteView = new CarteView(partie.getPaquet().voirCarteSup(),500);
        boxPaquet.getChildren().clear();
        ImageView nouvelleImageView = nouvelleCarteView.getImageView();
        nouvelleImageView.setFitHeight(200);
        nouvelleImageView.setPreserveRatio(true);
        boxPaquet.setPrefWidth(141);
        boxPaquet.setPrefHeight(200);
        boxPaquet.setEffect(ombreCarte);
        boxPaquet.getChildren().add(nouvelleImageView);
        afficherInfosCarte();
    }
    
    /**
     * Ouvre la page des options.
     * @throws Exception si la page ne s'ouvre pas.
     */
    public void ouvrirOptions() throws Exception {
        FXMLLoader loader = new FXMLLoader(new File("src/jeu/controlleur/Options.fxml").toURI().toURL());
        Parent root = loader.load();
    
        OptionsController controller = loader.getController();
        controller.setPartie(this.partie);
        controller.setBoutonFinir(this.boutonFinir);

        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Options");
        popupStage.setScene(new Scene(root,800,600));
        popupStage.initStyle(StageStyle.UTILITY);
        popupStage.setOnCloseRequest(event -> event.consume());
    
        controller.setPopupStage(popupStage);
        popupStage.showAndWait();
    }
    
    /**
     * Met à jour l'affichage des paquets de carte et remet les effets qui disparaissent.
     */
    public void mettreAJourAffichage(){
        this.afficherCartesJoueurPrincipal();
        this.afficherCartesJoueurDroit();
        this.afficherCartesJoueurDevant();
        this.afficherCartesJoueurGauche();
        this.redefinirEffetsBox();
        this.mettreAJourCouleur();
        this.mettreAJourViewPaquet();
    }
    
    /**
     * Remet les effets visuels sur le paquet.
     */
    private void redefinirEffetsBox(){
        boxPaquet.setOnMouseEntered(null);
        boxPaquet.setOnMouseExited(null);
        boxPaquet.setOnMouseClicked(null);
        
        boxPaquet.setOnMouseEntered(event ->{
            boxPaquet.setScaleX(1.1);
            boxPaquet.setScaleY(1.1);
            boxPaquet.setEffect(surbrillance);
        });

        boxPaquet.setOnMouseExited(event ->{
            boxPaquet.setScaleX(1);
            boxPaquet.setScaleY(1);
            boxPaquet.setEffect(ombreCarte);
        });
        
        boxPaquet.setOnMouseClicked(event ->{
            try {
                partie.poserCarteSelectionnee();
                partie.setCarteSelectionnee(null);
                mettreAJourAffichage();
            } catch (IOException e) {
                System.out.println("erreur pose carte");
            }
        });    
    }
    
    /**
     * Change la couleur de fond du rectangle couleur en fonction de l'attribut
     * couleur de partie.
     */
    private void mettreAJourCouleur(){
        if (partie.getCouleur() == null || partie.getCouleur().isEmpty()) {
            carreCouleur.setFill(Color.WHITE);
        } else if (partie.getCouleur().equalsIgnoreCase("bleu")) {
            carreCouleur.setFill(Color.web("#0057cb"));
        } else if (partie.getCouleur().equalsIgnoreCase("rouge")) {
            carreCouleur.setFill(Color.web("#ff3131"));
        } else if (partie.getCouleur().equalsIgnoreCase("vert")) {
            carreCouleur.setFill(Color.web("#00bf63"));
        } else {
            carreCouleur.setFill(Color.WHITE);
        }
    }
    
    /**
     * Ouvre un popup pour choisir la couleur.
     * @throws IOException si le popup ne s'ouvre pas.
     */
    public void ouvrirPopupCouleur() throws IOException {
        FXMLLoader loader = new FXMLLoader(new File("src/jeu/controlleur/ChoisirCouleur.fxml").toURI().toURL());
        Parent root = loader.load();
    
        ChoisirCouleurController controller = loader.getController();
    
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Choisissez une couleur");
        popupStage.setScene(new Scene(root));
        popupStage.initStyle(StageStyle.UTILITY);
        popupStage.setOnCloseRequest(event -> event.consume());
    
        controller.setPopupStage(popupStage);
        popupStage.showAndWait();

        partie.setCouleur(controller.getCouleur());
    }
    
    /**
     * Affiche les cartes du joueur principal. Face visible, reliées à CarteView
     * avec des effets au survol et à la sélection.
     */
    private void afficherCartesJoueurPrincipal(){
        HBox hb = paquetJoueurPrincipal;
        int nb = cartesJoueurPrincipal.size();
        int largeurCarte = 85;
        int maxEcart = -15;
        double espace = Math.min(((hb.getWidth() - largeurCarte) / (nb - 1)) - largeurCarte, maxEcart);
        hb.setSpacing(espace); 

        hb.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            double espace2 = Math.min(((newWidth.doubleValue()-largeurCarte)/(nb-1))-largeurCarte,maxEcart);
            hb.setSpacing(espace2);
        });


        hb.getChildren().clear();
        hb.setAlignment(Pos.CENTER);
        for (Carte carte : cartesJoueurPrincipal) {
            CarteView carteView = new CarteView(carte, largeurCarte);
            ImageView image = carteView.getImageView();
    
            image.setUserData(carteView);
            image.setOnMouseClicked(event -> clickCarte(event));
            image.setEffect(ombreCarte);
            image.setCursor(Cursor.HAND);
            
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
    
    /**
     * Effet lorsqu'on clique sur une carte dans la main du joueur principal.
     * Sélectionne la carte pour l'envoyer à partie. Une seule carte à la fois, si
     * une carte est déjà sélectionnée, remplace l'ancienne.
     * @param event 
     */
    private void clickCarte(MouseEvent event) {
        ImageView imageView = (ImageView) event.getSource();
        CarteView carteView = (CarteView) imageView.getUserData();
        Carte carteCliquee = carteView.getCarte();

        if (partie.getCarteSelectionnee() == null) {
            partie.setCarteSelectionnee(carteCliquee);
            System.out.println(partie.getCarteSelectionnee());
            faireMonterCarte(imageView);
        } else {
            if (partie.carteDifferente(carteCliquee)) {
                remettreCarteEnPlace();
                partie.setCarteSelectionnee(carteCliquee);
                System.out.println(partie.getCarteSelectionnee());
                faireMonterCarte(imageView);
            } else {
                partie.setCarteSelectionnee(null);
                remettreCarteEnPlace();
            }
        }
        afficherInfosCarte();
    }

    /**
     * Effet visuel qui fait monter et grossir une carte lorsqu'on clique dessus.
     * @param iv l'image de la carte à faire monter.
     */
    private void faireMonterCarte(ImageView iv) {
        iv.setTranslateY(-20);
        iv.setScaleX(1.2);
        iv.setScaleY(1.2);
        iv.setEffect(surbrillance);
    }

    /**
     * Remet toutes les cartes au même niveau et supprime les effets visuels
     * de zoom ou de halo.
     */
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
        afficherInfosCarte();
    }
    
    /**
     * Affiche le dos des cartes du joueur devant en fonction de son nombre de cartes.
     */
    private void afficherCartesJoueurDevant(){
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
    
    /**
     * Affiche le dos des cartes du joueur à gauche en fonction de son nombre de cartes.
     */
    private void afficherCartesJoueurGauche(){
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
                
    /**
     * Affiche le dos des cartes du joueur à droite en fonction de son nombre de cartes.
     */
    private void afficherCartesJoueurDroit(){
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
    
    private void afficherInfosCarte(){
        if (partie.getCarteSelectionnee()==null){
            tooltipInfos.setText("Sélectionnez une carte pour voir son effet !");
        } else if (partie.getCarteSelectionnee() instanceof AmnesieSelective){
            tooltipInfos.setText("Faites jouer une carte au hasard au prochain joueur, ignorant couleur et le numéro.");
        } else if (partie.getCarteSelectionnee() instanceof BlocageMoteur){
            tooltipInfos.setText("Empêchez le prochain joueur de jouer.");
        } else if (partie.getCarteSelectionnee() instanceof CriseEpileptique){
            tooltipInfos.setText("Faites piocher une carte à tout le monde (sauf vous) et choisissez la couleur.");
        } else if (partie.getCarteSelectionnee() instanceof MainEtrangere){
            tooltipInfos.setText("Faites piocher une carte au hasard au prochain joueur dans la main du joueur ayant le plus de cartes, il ne pourra pas jouer après cela.");
        } else if (partie.getCarteSelectionnee() instanceof Narcolepsie){
            tooltipInfos.setText("Faites piocher 2 cartes au joueur suivant. Les cartes Narcolepsie peuvent s'additionner.");
        } else if (partie.getCarteSelectionnee() instanceof NeuropathieO){
            tooltipInfos.setText("Changez la couleur de jeu.");
        } else if (partie.getCarteSelectionnee() instanceof Paralysie){
            tooltipInfos.setText("Changez de couleur et faites piocher 4 cartes au joueur suivant. Les cartes Paralysie du sommeil peuvent s'additionner.");
        } else if (partie.getCarteSelectionnee() instanceof Tdah){
            tooltipInfos.setText("Posez cette carte et rejouez instantanément sans vous soucier de la couleur.");
        } else if (partie.getCarteSelectionnee() instanceof TroubleEquilibre){
            tooltipInfos.setText("Changez le sens de jeu.");
        } else if (partie.getCarteSelectionnee() instanceof CarteSimple){
            tooltipInfos.setText("Cette carte n'a pas d'effet. Posez-la pour faire diminuer la taille de votre main.");
        }
        
    }
}
