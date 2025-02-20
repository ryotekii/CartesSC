package jeu.modele;

import java.util.Scanner;

public class Joueur {
    private String pseudo;
    private PaquetJoueur main;
    /*
    Fonction qui demande le pseudo à chaque joueur.
    */
    private final Partie partie;
    
    private String demanderPseudo(int num) {
        String n;
        do {
            Scanner nom = new Scanner(System.in);
            System.out.println("Entrez le pseudo du joueur "+num+" : ");
            n = nom.nextLine();
        } while (!partie.pseudoDispo(n));
        return n;
    }
    
    public void setPseudo(int n){
        this.pseudo=demanderPseudo(n);
    }
   
    public Joueur(Partie p){
        this.partie = p;
        this.main = new PaquetJoueur(this);
    }
    
    public void piocher(){
        main.ajouterCarte(partie.getPioche().piocher());
    }
    
    @Override
    public String toString(){
        if (this.pseudo != null){
            return this.pseudo;
        } else {
            return "";
        }
    }
}
