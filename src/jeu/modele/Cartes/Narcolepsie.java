package jeu.modele.Cartes;

import java.io.Serializable;

public class Narcolepsie extends CarteSpeciale implements Serializable {
    // Pioche 2 cartes.
    public Narcolepsie(String c){
        super(c);
    }
    
    @Override
    public String toString(){
        return "Narcolepsie";
    }
}
