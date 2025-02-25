package jeu.modele;

public class Joueur {
    private String pseudo;
    private final PaquetJoueur main;
    /*
    Fonction qui demande le pseudo à chaque joueur.
    */
    private final Partie partie;
    
    /*private String demanderPseudo(int num) {
        String n;
        do {
            Scanner nom = new Scanner(System.in);
            System.out.println("Entrez le pseudo du joueur "+num+" : ");
            n = nom.nextLine();
        } while (!partie.pseudoDispo(n));
        return n;
    }*/
    
    public void setPseudo(String p){
        this.pseudo=p;
    }
   
    public Joueur(Partie p){
        this.partie = p;
        this.main = new PaquetJoueur(this);
    }
    
    public String getPseudo(){
        return this.pseudo;
    }
    
    public void piocher(){
        main.ajouterCarte(partie.getPioche().piocher());
    }
    
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
