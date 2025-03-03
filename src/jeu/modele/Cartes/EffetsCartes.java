package jeu.modele.Cartes;

import jeu.modele.Joueur;
import jeu.modele.PaquetJoueur;
import jeu.modele.Parametres;
import jeu.modele.Partie;

public class EffetsCartes {
    private Partie partie;
    
    public EffetsCartes(Partie p){
        this.partie = p;
    }
    
    public void appliquerEffets(Carte c){
        if (c instanceof CriseEpileptique){
            partie.distribuer(1, partie.getOrdreDeJeu().getJoueurActuel());
            System.out.println(partie.getOrdreDeJeu().getJoueurActuel());
            System.out.println(partie.getOrdreDeJeu().getJoueurSuivant());
            partie.getController().mettreAJourAffichage();
        } else if (c instanceof MainEtrangere){
            Joueur donne = partie.getJoueurMax(partie.getOrdreDeJeu().getJoueurSuivant());
            Joueur recoit = partie.getOrdreDeJeu().getJoueurSuivant();
            Carte aleatoire = donne.getPaquetJoueur().getListeCartes().get(Parametres.entierAleatoire(0,donne.getPaquetJoueur().getListeCartes().size()-1));
            recoit.getPaquetJoueur().ajouterCarte(aleatoire);
            donne.getPaquetJoueur().jouerCarte(aleatoire);
            partie.getController().mettreAJourAffichage();
        } else if (c instanceof TroubleEquilibre){
            partie.getOrdreDeJeu().changerSens();
            partie.getController().mettreAJourAffichage();
        } else if (c instanceof AmnesieSelective){
            PaquetJoueur suivant = partie.getOrdreDeJeu().getJoueurSuivant().getPaquetJoueur();
            Carte aleatoire = suivant.getListeCartes().get(Parametres.entierAleatoire(0,suivant.getListeCartes().size()-1));
            suivant.jouerCarte(aleatoire);
            partie.getPaquet().poserCarte(aleatoire);
            partie.setCouleur(aleatoire.getCouleur());
            partie.getOrdreDeJeu().passerTourSuivant();
            partie.getEffets().appliquerEffets(aleatoire);
//vérifier si possible ce qui se passe lorsque la carte jouée est à son tour amnésie sélective ?
        } else if (c instanceof BlocageMoteur){
            partie.getOrdreDeJeu().passerTourSuivant();
        } /* else if (c instanceof ){
            
        } else if (c instanceof ){
            
        } */
        
        /*
        Modifier main étrangère pour si elle est jouée en dernier ? Ou les rgèles d'effets ?
        */
    }
}
