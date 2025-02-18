package jeu.console;

import java.util.Scanner;

public class Joueur {
    private final String pseudo;
    private PaquetJoueur main;
    /*
    Fonction qui demande le pseudo à chaque joueur.
    */
    private final Partie partie;
    
    private String demanderPseudo() {
        String n;
        do {
            Scanner nom =new Scanner(System.in);
            System.out.println("Entrez le pseudo du joueur : ");
            n = nom.nextLine();
        } while (!partie.pseudoDispo(n));
        return n;
    }
   
    public Joueur(Partie p){
        this.pseudo = demanderPseudo();
        this.partie = p;
        this.main = new PaquetJoueur(this);
    }
    
    public void piocher(){
        main.ajouterCarte(partie.getPioche().piocher());
    }
    
    @Override
    public String toString(){
        return this.pseudo;
    }
}
