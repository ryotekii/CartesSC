package jeu.modele.Cartes;

import java.io.Serializable;

/*
Les cartes avec des effets spéciaux.
*/

abstract public class CarteSpeciale extends Carte implements Serializable {
    public CarteSpeciale(String c){
        super(c);
    }
    
}
