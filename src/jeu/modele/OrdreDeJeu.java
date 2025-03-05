package jeu.modele;

/**
* Classe qui gère l'ordre de jeu, changements de sens, passages de tours.
*/
public class OrdreDeJeu {
    private boolean sens = true;
    private final Partie partie;
    private int n = 1;
    
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
    
    /**
     * Passe au joueur suivant.
     */
    public void passerSuivant(){
        Joueur[] joueurs = this.partie.getListeJoueurs();
    
        for (int j=0;j<n;j++){
            if (sens) {
                Joueur premier = joueurs[0];
                for (int i = 0; i < joueurs.length - 1; i++) {
                    joueurs[i] = joueurs[i + 1];
                }
                joueurs[joueurs.length - 1] = premier;
            } else {
                Joueur dernier = joueurs[joueurs.length - 1];
                for (int i = joueurs.length - 1; i > 0; i--) {
                    joueurs[i] = joueurs[i - 1];
                }
                joueurs[0] = dernier;
            }
        }
        n = 1;
    }
    
    public void sauterSuivant(){
        n = 2;
    }
}

