package jeu.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import jeu.modele.Cartes.Carte;

public class CarteView {
    private final Carte carte;
    private final ImageView vue;

    public CarteView(Carte carte, int largeurCarte) {
        this.carte = carte;
        this.vue = new ImageView(new Image(carte.nomImage()));
        this.vue.setFitWidth(largeurCarte);
        this.vue.setPreserveRatio(true);
    }

    public Carte getCarte() {
        return carte;
    }

    public ImageView getImageView() {
        return vue;
    }
}

