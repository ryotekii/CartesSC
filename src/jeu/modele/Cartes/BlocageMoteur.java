package jeu.modele.Cartes;

import java.io.Serializable;

public class BlocageMoteur extends CarteSpeciale implements Serializable {
    //Empêche le prochain joueur de jouer (équivalent passe ton tour).
    public BlocageMoteur(String c){
        super(c);
    }
    
        @Override
    public String toString(){
        return "Blocage moteur";
    }
}
