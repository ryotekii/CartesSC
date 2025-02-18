package jeu.console.Cartes;

public class AmnesieSelective extends CarteSpeciale {
    //Le prochain joueur doit jouer une carte au hasard.
    public AmnesieSelective(String c){
        super("Joker");
    } 
    
    @Override
    public String toString(){
        return "Amnésie sélective";
    }
}
