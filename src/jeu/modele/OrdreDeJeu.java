package jeu.modele;

/**
* Classe qui gère l'ordre de jeu, changements de sens, passages de tours.
*/
public class OrdreDeJeu {
    private boolean sens = true;
    private final Partie partie;
    
    /**
     * Le constructeur.
     * @param p la partie associée.
     */
    public OrdreDeJeu(Partie p){
        this.partie = p;
    }
    
    @Override
    public String toString(){
        if (!sens){
            return ("Le jeu tourne dans le sens inverse.");
        } else {
            return ("Le jeu tourne dans le sens horaire.");
        }
    }
    
    public Joueur getJoueurActuel(){
        return this.partie.getListeJoueurs()[0];
    }
    
    public void changerSens(){
        sens = !sens;
        partie.getController().retournerFleche();
    }
    
    public Joueur getJoueurSuivant(){
        if (sens){
            return this.partie.getListeJoueurs()[1];
        } else {
            return this.partie.getListeJoueurs()[this.partie.getListeJoueurs().length - 1];
        }
    }
    
    public void passerTourSuivant(){
        
    }
}

