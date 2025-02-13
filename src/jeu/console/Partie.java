package jeu.console;

import jeu.console.Cartes.Carte;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Partie {
    private Joueur[] joueurs;
    private final Paquet paquet;
    private final Pioche pioche;
    private OrdreDeJeu ordre;
    /*
    Pour garder en mémoire la couleur actuelle, en particulier avec
    l'utilisation de joker.
    */
    private String couleurActuelle;

    public Partie(Paquet p) {
        this.paquet = p;
        this.ajouterJoueurs(this.nbJoueurs());
        this.ordre = new OrdreDeJeu(this);
        this.pioche = new Pioche(this);
    }
    
    public boolean pseudoDispo(String s){
        for (Joueur j: this.joueurs){
            /*
            Vérifie si le pseudo n'existe pas déjà dans la liste des joueurs.
            */
            if (j.toString().equals(s)){
                System.out.println("Ce pseudo est déjà pris. Veuillez en choisir un autre.");
                return false;
            }   
        }
        return true;
    }
    
    public Paquet getPaquet(){
        return this.paquet;
    }
    
    private int nbJoueurs(){
        int nb = 0;
        Scanner nombre = new Scanner(System.in);
        do {
            try {
                System.out.println("Entrez le nombre de joueurs : ");
                nb = nombre.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Erreur : Veuillez entrer un nombre valide !");
                nombre.next();
            }
        } while (!this.nbJValide(nb));
        return nb;
    }
    
    /*
    Ajoute n joueurs dans le paquet (demande leurs pseudos).
    */
    private void ajouterJoueurs(int n){
        Joueur j;
        for (int i=0;i<n;i++){
            j = new Joueur(this);
            joueurs[i] = j;
        }
    }
    
    public Pioche getPioche(){
        return this.pioche;
    }
    
    private Joueur[] listeJoueurs(){
        return joueurs;
    }
    
    /*
    Vérifie si le nombre de joueurs est bien compris entre 2 et 6.
    */
    private boolean nbJValide(int n){
        if (n<2){
            return false;
        } else return n < 6;
    }
    
}
