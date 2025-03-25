package jeu.modele.Cartes;

import java.io.Serializable;

public class TroubleEquilibre extends CarteSpeciale implements Serializable {
    // Changement de sens.
    public TroubleEquilibre(String c){
        super(c);
    }
    
    @Override
    public String toString(){
        return "Trouble de l'équilibre";
    }
}
