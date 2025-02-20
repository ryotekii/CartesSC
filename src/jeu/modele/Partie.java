package jeu.modele;

import jeu.modele.Cartes.Carte;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Partie {
    private Joueur[] joueurs;
    private final Paquet paquet;
    private final Pioche pioche;
    private OrdreDeJeu ordre;
    private Carte carteSelectionnee;
    /*
    Pour garder en mémoire la couleur actuelle, en particulier avec
    l'utilisation de joker.
    */
    private String couleurActuelle;

    public Partie() {
        this.paquet = new Paquet(this);
        this.ajouterJoueurs(this.nbJoueurs());
        int i = 1;
        for (Joueur j:joueurs){
            j.setPseudo(i);
            i++;
        }
        this.ordre = new OrdreDeJeu(this);
        this.pioche = new Pioche(this);
        this.distribuer(7,null);
        this.carteSelectionnee=null;
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
        Scanner nombre = new Scanner(System.in);
        int nb = 0;

        do {
            System.out.print("Entrez le nombre de joueurs : ");
            while (!nombre.hasNextInt()) {  // Vérifie si l'entrée est un entier
                System.out.println("Erreur : Veuillez entrer un nombre valide !");
                nombre.next();  // Vide l'entrée incorrecte
            }
            nb = nombre.nextInt();
        } while (!nbJValide(nb));

        return nb;
    }

    
    public void setCarteSelectionnee(Carte carte){
        this.carteSelectionnee=carte;
    }
    
    public Carte getCarteSelectionnee(){
        return this.carteSelectionnee;
    }
    
    public boolean carteDifferente(Carte carte) {
        if (this.carteSelectionnee == null) {
            return true;
        }
        return !this.carteSelectionnee.equals(carte);
    }
    
    /*
    Distribue n cartes à tous les joueurs sauf j.
    */
    private void distribuer(int n,Joueur j){
        for (int i=0;i<n;i++){
            for (Joueur joueur:this.joueurs){
                if (joueur != j){
                    joueur.piocher();
                }
            }
        }
    }
    
    /*
    Ajoute n joueurs dans le paquet (demande leurs pseudos).
    */
    private void ajouterJoueurs(int n){
        Joueur j;
        joueurs = new Joueur[n];
        for (int i=0;i<n;i++){
            j = new Joueur(this);
            joueurs[i] = j;
        }
    }
    
    public Pioche getPioche(){
        return this.pioche;
    }
    
    private Joueur[] getListeJoueurs(){
        return joueurs;
    }
    
    public int getNombreJoueurs(){
        return joueurs.length;
    }
    
    public ArrayList<Carte> getListeCartesJoueur(int i){
        return this.joueurs[i].getPaquetJoueur().getListeCartes();
    }
    
    public int getNombreCartesJoueur(int i){
        return this.joueurs[i].getPaquetJoueur().getListeCartes().size();
    }
    
    /*
    Vérifie si le nombre de joueurs est bien compris entre 2 et 4.
    */
    private boolean nbJValide(int n){
        if (n<2){
            return false;
        } else return n <= 4;
    }
    
}
