package jeu.modele.Cartes;

import java.io.Serializable;

public class AmnesieSelective extends CarteSpeciale implements Serializable {
    //Le prochain joueur doit jouer une carte au hasard.
    public AmnesieSelective(){
        super("Joker");
    } 
    
    @Override
    public String toString(){
        return "Amnésie sélective";
    }
}
