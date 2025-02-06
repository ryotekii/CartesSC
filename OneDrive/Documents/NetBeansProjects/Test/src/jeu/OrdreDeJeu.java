package jeu;

/*
Classe qui gère l'ordre de jeu, changements de sens, passages de tours.
*/
public class OrdreDeJeu {
    private boolean inverse = false;
    
    @Override
    public String toString(){
        if (inverse){
            return ("Le jeu tourne dans le sens inverse.");
        } else {
            return ("Le jeu tourne dans le sens horaire.");
        }
    }
}
