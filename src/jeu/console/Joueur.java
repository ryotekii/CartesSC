package jeu;

import java.util.Scanner;

public class Joueur {
    private final String pseudo;
    
    /*
    Fonction qui demande le pseudo à chaque joueur.
    A modifier plus tard pour vérifier qu'un pseudo n'est pas déjà pris.
    */
    private final Partie partie;
    public static String demanderPseudo() {
        String n;
        do {
            Scanner nom =new Scanner(System.in);
            System.out.println("Entrez le pseudo du joueur : ");
            n = nom.nextLine();
        } while (!partie.pseudoDispo(n));
        return n;
    }
   
    public Joueur(){
        this.pseudo = Joueur.demanderPseudo();
    }
    
    @Override
    public String toString(){
        return this.pseudo;
    }
}
