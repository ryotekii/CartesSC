package jeu.modele.Cartes;

import java.io.Serializable;

public class Tdah extends CarteSpeciale implements Serializable {
    //Le joueur joue une deuxième carte au choix après avoir posé celle-là.
    public Tdah(){
        super("Joker");
    }
    
        @Override
    public String toString(){
        return "TDAH";
    }
}
