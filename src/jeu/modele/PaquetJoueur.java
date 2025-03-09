package jeu.modele;

import java.io.Serializable;
import java.util.ArrayList;
import jeu.modele.Cartes.Carte;

/**
* La main du joueur.
*/
public class PaquetJoueur implements Serializable {
    private final Joueur joueur;
    private final ArrayList<Carte> cartes = new ArrayList<>();
    
    /**
     * Le constructeur.
     * @param j le joueur associé au paquet.
     */
    public PaquetJoueur(Joueur j){
        this.joueur = j;
    }
    
    /**
     * Retourne la liste de cartes de la main du joueur.
     * @return les cartes du joueur.
     */
    public ArrayList<Carte> getListeCartes(){
        return this.cartes;
    }
    
    /**
     * Joue la carte donnée si elle est présente dans la main du joueur.
     * @param carte la carte à poser.
     */
    public void jouerCarte(Carte carte){
        cartes.removeIf(c -> c.equals(carte));
    }
    
    /**
     * Ajoute une parte dans la main du joueur.
     * @param c la carte.
     */
    public void ajouterCarte(Carte c){
        this.cartes.add(c);
    }
    
}
