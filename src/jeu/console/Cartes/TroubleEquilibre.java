package jeu.console.Cartes;

public class TroubleEquilibre extends CarteSpeciale {
    // changement de sens 
    public TroubleEquilibre(String c){
        super(c);
    }
    
    @Override
    public String toString(){
        return "Trouble de l'équilibre";
    }
}
