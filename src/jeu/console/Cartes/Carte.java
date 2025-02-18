package jeu.console.Cartes;

import java.text.Normalizer;
import jeu.console.Parametres;

/*
Pour toutes les cartes du jeu.
*/

abstract public class Carte {
    protected final String couleur;
    
    protected Carte(String c){
        this.couleur=c;
    }
    
    public String getCouleur(){
        return this.couleur;
    }
    
    public String nomImage(){
        if (this.couleur.equals("Joker")){
            return Parametres.IMAGES+Carte.normaliser(this.toString())+".png";
        } else if (this instanceof CarteSimple cs){
            return Parametres.IMAGES+cs.getNumero()+"-"+cs.couleur+".png";
        } else {
            return Parametres.IMAGES+Carte.normaliser(this.toString())+"-"+this.couleur+".png";
        }
    }
    
    @Override
    abstract public String toString();
    
    /*
    Retire les accents, les espaces et les caractères spéciaux.
    */
    public static String normaliser(String s){
        return Normalizer.normalize(s, Normalizer.Form.NFD).replaceAll("\\p{M}", "").toLowerCase().replaceAll("[^a-z0-9]", "");
    }
}
