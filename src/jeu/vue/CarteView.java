package jeu.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import jeu.modele.Cartes.Carte;
import jeu.modele.Parametres;

/**
 * Fait le lien entre Carte et les Imageview associées.
 */
public class CarteView {
    private final Carte carte;
    private final ImageView vue;

    /**
     * Le constructeur.
     * @param carte la carte.
     * @param largeurCarte la taille de l'image.
     */
    public CarteView(Carte carte, int largeurCarte) {
        this.carte = carte;
        System.out.println(Parametres.IMAGES+carte.nomImage());
        System.out.println(getClass().getResource(Parametres.IMAGES+carte.nomImage()).toExternalForm());
        this.vue = new ImageView(new Image(getClass().getResource(Parametres.IMAGES+carte.nomImage()).toExternalForm()));
  
        this.vue.setFitWidth(largeurCarte);
        this.vue.setPreserveRatio(true);
    }

    /**
     * Renvoie la carte associée.
     * @return la carte.
     */
    public Carte getCarte() {
        return carte;
    }

    /**
     * Renvoie l'image associée.
     * @return l'image.
     */
    public ImageView getImageView() {
        return vue;
    }
}

