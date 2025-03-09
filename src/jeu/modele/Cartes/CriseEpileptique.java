package jeu.modele.Cartes;

import java.io.Serializable;

public class CriseEpileptique extends CarteSpeciale implements Serializable {
    /*
    Chaque joueur pioche 1 carte sauf celui qui l’a posée,
    Le poseur peut choisir la couleur.
    */
    public CriseEpileptique(){
        super("Joker");
    }
    
    @Override
    public String toString(){
        return "Crise épileptique";
    }
}
