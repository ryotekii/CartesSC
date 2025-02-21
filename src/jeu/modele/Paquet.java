package jeu.modele;

import java.util.ArrayList;
import jeu.modele.Cartes.Carte;

/*
La pile de cartes sur la table.
*/
public class Paquet {
    private Partie partie;
    private ArrayList<Carte> paquet = new ArrayList<>();
    
    public Paquet(Partie p){
        this.partie = p;
    }
    
    public void poserCarte(Carte c){
        this.paquet.addFirst(c);
    }
    
    public Carte voirCarteSup(){
        return paquet.getFirst();
    }
    
    public ArrayList<Carte> getPaquet(){
        return this.paquet;
    }
    
    public ArrayList<Carte> viderPaquet(){
        ArrayList<Carte> provisoire = new ArrayList(this.paquet);
        this.paquet.clear();
        this.paquet.addFirst(provisoire.removeFirst());
        return provisoire;
    }
    
}
