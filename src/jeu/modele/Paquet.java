package jeu.modele;

import java.io.Serializable;
import java.util.ArrayList;
import jeu.modele.Cartes.Carte;

/**
 * Le paquet de cartes sur la table. Pile visible sur laquelle les cartes de la
 * partie sur laquelle les cartes sont empilées.
 * @author salom
 */
public class Paquet implements Serializable{
    private Partie partie;
    private ArrayList<Carte> paquet = new ArrayList<>();
    
    /**
     * Le constructeur.
     * @param p la aprtie associée.
     */
    public Paquet(Partie p){
        this.partie = p;
    }
    
    /**
     * Pose la carte sur le paquet.
     * @param c la carte à poser.
     */
    public void poserCarte(Carte c){
        this.paquet.addFirst(c);
    }
    
    /**
     * Retourne la carte sur le haut du paquet.
     * @return la première carte.
     */
    public Carte voirCarteSup(){
        return paquet.getFirst();
    }
    
    /**
     * Retourne l'intégralité du paquet.
     * @return la liste des cartes du paquet.
     */
    public ArrayList<Carte> getPaquet(){
        return this.paquet;
    }
    
    /**
     * Vide le paquet et retourne toutes les cartes sauf la première.
     * @return la liste des cartes du paquet en enlevant la première.
     */
    public ArrayList<Carte> viderPaquet(){
        ArrayList<Carte> provisoire = new ArrayList(this.paquet);
        this.paquet.clear();
        this.paquet.addFirst(provisoire.removeFirst());
        return provisoire;
    }
    
}
