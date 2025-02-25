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
    
    /*
    La couleur en entrée correspond à l'attribut couleurActuelle de Partie.
    Permet d'adapter la couleur lorsqu'on en choisit une avec un joker.
    */
    public static boolean verifCouleur(Carte main,String couleur){
        return main.getCouleur().equals("Joker") || main.getCouleur().equals(couleur) || couleur.equals("Joker");
    }
    
    public static boolean verifNombre(Carte main,Carte table){
        if (main.getCouleur().equals("Joker")){
            return true;
        } else if (main instanceof CarteSimple c1 && table instanceof CarteSimple c2){
            return c1.getNumero() == c2.getNumero();
        }
        return false;
    }
    
    public boolean peutPoser(Carte c){
        return VerificationCarte.verifCouleur(c, partie.getCouleur()) || VerificationCarte.verifNombre(c, partie.getPaquet().voirCarteSup());
    }
}
