package jeu.modele.Cartes;

public class BlocageMoteur extends CarteSpeciale {
    //Empêche le prochain joueur de jouer (équivalent passe ton tour).
    public BlocageMoteur(String c){
        super(c);
    }
    
        @Override
    public String toString(){
        return "Blocage moteur";
    }
}
