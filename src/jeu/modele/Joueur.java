package jeu.modele;

import java.io.Serializable;
import java.net.Socket;

public class Joueur implements Serializable {
    private String pseudo;
    private final PaquetJoueur main;
    /*
    Fonction qui demande le pseudo à chaque joueur.
    */
    private Partie partie;
    private Socket socket;
    
    /*private String demanderPseudo(int num) {
        String n;
        do {
            Scanner nom = new Scanner(System.in);
            System.out.println("Entrez le pseudo du joueur "+num+" : ");
            n = nom.nextLine();
        } while (!partie.pseudoDispo(n));
        return n;
    }*/
    
    /**
     * Définit le pseudo du joueur.
     * @param p le pseudo du joueur.
     */
    public void setPseudo(String p){
        this.pseudo=p;
    }
    
    public void setSocket(Socket s){
        this.socket=s;
    }
    
    public Socket getSocket(){
        return this.socket;
    }
    
    /**
     * Le constructeur.
     * @param p la partie associée.
     */
    public Joueur(Partie p){
        this.partie = p;
        this.main = new PaquetJoueur(this);
    }
    
    public Joueur(){
        this.main=new PaquetJoueur(this);
    }
    
    public void setPartie(Partie p){
        this.partie=p;
    }
    
    /**
     * Retourne le pseudo du joueur.
     * @return le pseudo.
     */
    public String getPseudo(){
        return this.pseudo;
    }
    
    /**
     * Ajoute la première carte de la pioche dans la main du joueur.
     */
    public void piocher(){
        main.ajouterCarte(partie.getPioche().piocher());
    }
    
    /**
     * Retourne la main du joueur.
     * @return la main.
     */
    public PaquetJoueur getPaquetJoueur(){
        return this.main;
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
