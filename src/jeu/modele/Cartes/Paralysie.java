package jeu.modele.Cartes;

import java.io.Serializable;

public class Paralysie extends CarteSpeciale implements Serializable {
    //Le joueur suivant pioche 4 cartes et celui qui pose la carte choisit la couleur.
    public Paralysie(){
        super("Joker");
    }
    
    @Override
    public String toString(){
        return "Paralysie du sommeil";
    }
}
