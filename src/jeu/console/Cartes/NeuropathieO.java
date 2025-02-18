package jeu.console.Cartes;

public class NeuropathieO extends CarteSpeciale {
    //Change la couleur en jeu.
    public NeuropathieO(){
        super("Joker");
    }
    
    @Override
    public String toString(){
        return "Neuropathie optique";
    }
}
