package jeu.modele.Cartes;

import java.io.Serializable;
import jeu.modele.Joueur;
import jeu.modele.PaquetJoueur;
import jeu.modele.Parametres;
import jeu.modele.Partie;

public class EffetsCartes implements Serializable {
    private Partie partie;
    
    public EffetsCartes(Partie p){
        this.partie = p;
    }
    
    public void appliquerEffets(Carte c){
        if (c instanceof CriseEpileptique){
            partie.distribuer(1, partie.getOrdreDeJeu().getJoueurActuel());
            System.out.println(partie.getOrdreDeJeu().getJoueurActuel());
            System.out.println(partie.getOrdreDeJeu().getJoueurSuivant());
        } else if (c instanceof MainEtrangere){
            Joueur donne = partie.getJoueurMax(partie.getOrdreDeJeu().getJoueurSuivant());
            Joueur recoit = partie.getOrdreDeJeu().getJoueurSuivant();
            Carte aleatoire;
            if (donne !=null){
                aleatoire = donne.getPaquetJoueur().getListeCartes().get(Parametres.entierAleatoire(0,donne.getPaquetJoueur().getListeCartes().size()-1));
                donne.getPaquetJoueur().jouerCarte(aleatoire);
            } else {
                aleatoire = partie.getPioche().piocher();
            }
            recoit.getPaquetJoueur().ajouterCarte(aleatoire);
        } else if (c instanceof TroubleEquilibre){
            partie.getOrdreDeJeu().changerSens();
        } else if (c instanceof AmnesieSelective){
            PaquetJoueur suivant = partie.getOrdreDeJeu().getJoueurSuivant().getPaquetJoueur();
            Carte aleatoire = suivant.getListeCartes().get(Parametres.entierAleatoire(0,suivant.getListeCartes().size()-1));
            suivant.jouerCarte(aleatoire);
            partie.getPaquet().poserCarte(aleatoire);
            partie.setCouleur(aleatoire.getCouleur());
            partie.getOrdreDeJeu().sauterSuivant();
            partie.getEffets().appliquerEffets(aleatoire);
//vérifier si possible ce qui se passe lorsque la carte jouée est à son tour amnésie sélective ?
        } else if (c instanceof BlocageMoteur){
            partie.getOrdreDeJeu().sauterSuivant();
        } else if (c instanceof Narcolepsie){
            partie.getOrdreDeJeu().getJoueurSuivant().piocher();
            partie.getOrdreDeJeu().getJoueurSuivant().piocher();
            partie.getOrdreDeJeu().sauterSuivant();
        } else if (c instanceof Paralysie){
            partie.getOrdreDeJeu().getJoueurSuivant().piocher();
            partie.getOrdreDeJeu().getJoueurSuivant().piocher();
            partie.getOrdreDeJeu().getJoueurSuivant().piocher();
            partie.getOrdreDeJeu().getJoueurSuivant().piocher();
            partie.getOrdreDeJeu().sauterSuivant();
        }
        
        /*
        Modifier main étrangère pour si elle est jouée en dernier ? Ou les rgèles d'effets ?
        */
        partie.getController().mettreAJourAffichage();
        if (!(c instanceof Tdah)){
            partie.getController().griserMain();
        }
    }
    
}
