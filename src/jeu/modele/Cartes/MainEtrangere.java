package jeu.modele.Cartes;

import java.io.Serializable;

public class MainEtrangere extends CarteSpeciale implements Serializable {
    /*
    Le prochain joueur pioche une carte au hasard dans la main du joueur ayant
    le plus de cartes (ne peut pas poser de carte après ça).
    */
    public MainEtrangere(String c){
        super(c);
    }
    
        @Override
    public String toString(){
        return "Main étrangère";
    }
}
