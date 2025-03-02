package jeu.modele;

import jeu.modele.Cartes.AmnesieSelective;
import jeu.modele.Cartes.BlocageMoteur;
import jeu.modele.Cartes.Carte;
import jeu.modele.Cartes.CarteSimple;
import jeu.modele.Cartes.MainEtrangere;
import jeu.modele.Cartes.Narcolepsie;
import jeu.modele.Cartes.TroubleEquilibre;

/**
* Classe pour vérifier que les cartes peuvent être posées
* (vérification couleur/numéro).
*/
public class VerificationCarte {
    private final Partie partie;
    
    /**
     * Le constructeur.
     * @param p la aprtie associée.
     */
    public VerificationCarte(Partie p){
        this.partie = p;
    }
    
    /**
     * Vérifie que la couleur de la carte à poser correspond à la couleur
     * de la partie.
     * @param main la carte à poser.
     * @param couleur la couleur de la partie.
     * @return <code>true</code> si la couleur est la même et <code>false</code> sinon.
     */
    public static boolean verifCouleur(Carte main,String couleur){
        return main.getCouleur().equals("Joker") || main.getCouleur().equals(couleur) || couleur.equals("Joker");
    }
    
    /**
     * Vérifie que le numéro de la carte à poser correspond au numéro de la carte
     * supérieure du paquet. Si l'une des cartes est un joker noir, les numéros correspondent.
     * Si les deux joker sont du même type, les numéros correspondent.
     * @param main la carte à poser.
     * @param table la carte au-dessus du paquet.
     * @return <code>true</code> si les nombres correspondent et <code>false</code> sinon.
     */
    public static boolean verifNombre(Carte main,Carte table){
        if (main.getCouleur().equals("Joker")){
            return true;
        } else if (main instanceof CarteSimple c1 && table instanceof CarteSimple c2){
            return c1.getNumero() == c2.getNumero();
        } else if ((main instanceof MainEtrangere && table instanceof MainEtrangere) || 
                (main instanceof Narcolepsie && table instanceof Narcolepsie) || 
                (main instanceof TroubleEquilibre && table instanceof TroubleEquilibre) || 
                (main instanceof AmnesieSelective && table instanceof AmnesieSelective) ||
                (main instanceof BlocageMoteur && table instanceof BlocageMoteur)){
            return true;
        }
        return false;
    }
    
    /**
     * Vérifie que la carte peut être posée par correspondance de couleur ou de numéro.
     * @param c la carte à poser.
     * @return <code>true</code> si la carte peut être posée, <code>false</code> sinon.
     */
    public boolean peutPoser(Carte c){
        return VerificationCarte.verifCouleur(c, partie.getCouleur()) || VerificationCarte.verifNombre(c, partie.getPaquet().voirCarteSup());
    }
}
