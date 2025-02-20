package jeu.modele.Cartes;

public class CriseEpileptique extends CarteSpeciale {
    /*
    Chaque joueur pioche 1 carte sauf celui qui l’a posée,
    Le poseur peut choisir la couleur.
    */
    public CriseEpileptique(){
        super("Joker");
    }
    
    @Override
    public String toString(){
        return "Crise épileptique";
    }
}
