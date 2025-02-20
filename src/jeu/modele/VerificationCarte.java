package jeu.modele;

import jeu.modele.Cartes.Carte;
import jeu.modele.Cartes.CarteSimple;

/*
Classe pour vérifier que les cartes peuvent être posées
(vérification couleur/numéro).
*/
public class VerificationCarte {
    private final Partie partie;
    
    public VerificationCarte(Partie p){
        this.partie = p;
    }
    
    public static boolean verifCouleur(Carte main,String couleur){
        if (main.getCouleur().equals("Joker")){
            return true;
        } else if (main.getCouleur().equals(couleur)){
            return true;
        /*
        Lorsqu'on joue le tdah, on peut poser une carte au choix sans
        avoir à choisir la couleur avant, la variable couleur prend
        alors pour valeur "Joker".
        */
        } else if (couleur.equals("Joker")){
            return true;
        }
        return false;
    }
    
    public static boolean verifNombre(Carte main,Carte table){
        if (main.getCouleur().equals("Joker")){
            return true;
        } else if (main instanceof CarteSimple c1 && table instanceof CarteSimple c2){
            return c1.getNumero() == c2.getNumero();
        }
        return false;
    }
    
}
