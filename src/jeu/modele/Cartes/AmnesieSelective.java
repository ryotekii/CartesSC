package jeu.modele.Cartes;

public class AmnesieSelective extends CarteSpeciale {
    //Le prochain joueur doit jouer une carte au hasard.
    public AmnesieSelective(){
        super("Joker");
    } 
    
    @Override
    public String toString(){
        return "Amnésie sélective";
    }
}
