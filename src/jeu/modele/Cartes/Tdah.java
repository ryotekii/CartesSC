package jeu.modele.Cartes;

public class Tdah extends CarteSpeciale {
    //Le joueur joue une deuxième carte au choix après avoir posé celle-là.
    public Tdah(){
        super("Joker");
    }
    
        @Override
    public String toString(){
        return "TDAH";
    }
}
