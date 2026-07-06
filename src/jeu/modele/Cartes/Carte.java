package jeu.modele.Cartes;

import java.io.Serializable;
import java.text.Normalizer;
import jeu.modele.Parametres;

/**
* Toutes les cartes du jeu.
*/
abstract public class Carte implements Serializable {
    protected final String couleur;
    
    /**
     * Constructeur.
     * @param c 
     */
    protected Carte(String c){
        this.couleur=c;
    }
    
    /**
     * Renvoie la couleur de la carte.
     * @return la couleur.
     */
    public String getCouleur(){
        return this.couleur;
    }
    
    /**
     * Renvoie l'url associée à la carte.
     * @return l'url.
     */
    public String nomImage(){
        if (this.couleur.equals("Joker")){
            return Carte.normaliser(this.toString())+".png";
        } else if (this instanceof CarteSimple cs){
            return cs.getNumero()+"-"+cs.couleur.toLowerCase()+".png";
        } else {
            return Carte.normaliser(this.toString())+"-"+this.couleur.toLowerCase()+".png";
        }
    }
    
    @Override
    abstract public String toString();
    
    /**
    * Retire les accents, les espaces et les caractères spéciaux.
     * @param s la chaîne de caractères à normaliser.
     * @return la chaîne de caractères normalisée.
    */
    public static String normaliser(String s){
        return Normalizer.normalize(s, Normalizer.Form.NFD).replaceAll("\\p{M}", "").toLowerCase().replaceAll("[^a-z0-9]", "");
    }
}
