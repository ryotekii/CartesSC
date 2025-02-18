package jeu.console.Cartes;

public class Paralysie extends CarteSpeciale {
    //Le joueur suivant pioche 4 cartes et celui qui pose la carte choisit la couleur.
    public Paralysie(){
        super("Joker");
    }
    
    @Override
    public String toString(){
        return "Paralysie du sommeil";
    }
}
