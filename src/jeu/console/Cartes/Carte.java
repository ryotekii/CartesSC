package jeu.console.Cartes;

abstract public class Carte {
    protected final String couleur;
    
    protected Carte(String c){
        this.couleur=c;
    }
    
    public String getCouleur(){
        return this.couleur;
    }
}
