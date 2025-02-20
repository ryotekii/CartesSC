package jeu.modele;

import java.util.ArrayList;
import jeu.modele.Cartes.Carte;

/*
La main du joueur.
*/
public class PaquetJoueur {
    private Joueur joueur;
    private ArrayList<Carte> cartes = new ArrayList<>();
    
    public PaquetJoueur(Joueur j){
        this.joueur = j;
    }
    
    public void ajouterCarte(Carte c){
        this.cartes.add(c);
    }
    
    public Carte jouerCarte(int n){
        return cartes.remove(n);
    }
    
}
