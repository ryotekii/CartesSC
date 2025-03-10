package jeu.modele;

import java.io.IOException;
import java.io.Serializable;
import jeu.modele.Cartes.Carte;
import java.util.ArrayList;
import java.util.Random;
import jeu.controlleur.FXMLController;
import jeu.modele.Cartes.AmnesieSelective;
import jeu.modele.Cartes.CarteSpeciale;
import jeu.modele.Cartes.EffetsCartes;
import jeu.modele.Cartes.Tdah;

public class Partie implements Serializable {
    private Joueur[] joueurs;
    private final Paquet paquet;
    private final Pioche pioche;
    private OrdreDeJeu ordre;
    private Carte carteSelectionnee;
    private transient FXMLController controller;
    private VerificationCarte verification;
    /**
    * Pour garder en mémoire la couleur actuelle, en particulier avec
    * l'utilisation de joker.
    */
    private String couleurActuelle;
    private EffetsCartes effets;
    private boolean peutPoser = true;
    /**
     * Le constructeur. Créé le paquet, la pioche, l'ordre de jeu et
     * des variables qui gardent en mémoire la couleur de jeu et la prochaine
     * carte à poser.
     */
    public Partie() {
        this.paquet = new Paquet(this);
        this.ordre = new OrdreDeJeu(this);
        this.pioche = new Pioche(this);
        this.carteSelectionnee=null;
        this.effets=new EffetsCartes(this);
        this.verification = new VerificationCarte(this);
    }
    
    /**
     * Renvoie la joueur ayant le plus de cartes. En cas d'égalité, choisit au
     * hasard l'un des joueurs ayant le plus de cartes. Si le joueur ayant le plus de
     * cartes est celui est entré en paramètre, retourne le deuxième joueur ayant
     * le plus de cartes.
     * @param j
     * @return le joueur ayant le plus de cartes.
     */
    public Joueur getJoueurMax(Joueur j){
        ArrayList<Joueur> listePossible = new ArrayList<>();
        int maxCartes = -1;
        for (Joueur joueur : joueurs) {
            if (joueur == j){ 
                continue;
            }
            int nb = joueur.getPaquetJoueur().getListeCartes().size();
            if (nb > maxCartes) {
                maxCartes = nb;
                listePossible.clear();
                listePossible.add(joueur);
            } else if (nb == maxCartes) {
                listePossible.add(joueur);
            }
        }
        Random random = new Random();
        return listePossible.get(random.nextInt(listePossible.size()));
    }
    
    /**
     * Définit le controlleur associé.
     * @param c le controlleur.
     */
    public void setController(FXMLController c){
        this.controller=c;
    }
    
    public EffetsCartes getEffets(){
        return this.effets;
    }
    
    /**
     * Retourne le paquet associé à la partie.
     * @return le paquet.
     */
    public Paquet getPaquet(){
        return this.paquet;
    }
    
    public void setPeutPoser(Boolean b){
        peutPoser=b;
    }
    
    public boolean getPeutPoser(){
        return this.peutPoser;
    }
    
    /**
     * Définit la couleur de jeu.
     * @param c la couleur à définir.
     */
    public void setCouleur(String c){
        this.couleurActuelle=c;
    }
    
    /**
     * Retourne la couleur de jeu.
     * @return la couleur de jeu.
     */
    public String getCouleur(){
        return this.couleurActuelle;
    }
    
    public FXMLController getController(){
        return this.controller;
    }
    
    /**
     * Définit le pseudo du joueur i.
     * @param i l'index du joueur dans la liste des joueurs.
     * @param p la pseudo du joueur.
     */
    public void setPseudo(int i,String p){
        this.joueurs[i].setPseudo(p);
    }
    
    /**
     * Définit la carte en paramètre comme carte provisoire dans la partie.
     * @param carte la carte à mettre en mémoire.
     */
    public void setCarteSelectionnee(Carte carte){
        this.carteSelectionnee=carte;
    }
    
    /**
     * Renvoie la carte sélectionnée.
     * @return la carte sélectionnée.
     */
    public Carte getCarteSelectionnee(){
        return this.carteSelectionnee;
    }
    
    /**
     * Compare la carte entrée en paramètre avec carteSelectionnée.
     * @param carte la carte à comparer avec la carte gardée en mémoire dans la partie.
     * @return <code>true</code> si les deux cartes sont les mêmes et <code>false</code> sinon.
     */
    public boolean carteDifferente(Carte carte) {
        if (this.carteSelectionnee == null) {
            return true;
        }
        return !this.carteSelectionnee.equals(carte);
    }
    
    /**
     * Distribue n cartes à tous les joueurs sauf j.
     * @param n le nombre de cartes à distribuer.
     * @param j le joueur à exclure de la distribution.
     */
    public void distribuer(int n,Joueur j){
        for (int i=0;i<n;i++){
            for (Joueur joueur:this.joueurs){
                if (joueur != j){
                    joueur.piocher();
                }
            }
        }
    }
    
    /**
     * Ajoute n joueurs dans la liste de joueurs de la partie.
     * @param n le nombre de joueurs à ajouter.
     */
    public void ajouterJoueurs(int n){
        Joueur j;
        joueurs = new Joueur[n];
        for (int i=0;i<n;i++){
            j = new Joueur(this);
            joueurs[i] = j;
        }
    }
    
    /**
     * Pose la première carte sur le paquet après avoir distribué.
     * Continue jusqu'à ce que la carte ne soit pas une carte spéciale.
     */
    public void poserPremiereCarte(){
        Carte premiereCarte;
    
        do {
            premiereCarte = this.pioche.piocher();
            paquet.poserCarte(premiereCarte);
        } while (premiereCarte != null && (premiereCarte instanceof CarteSpeciale));

        if (premiereCarte != null) {
            this.couleurActuelle = premiereCarte.getCouleur();
            System.out.println(premiereCarte);
        }
    }
    
    /**
     * Renvoie la pioche.
     * @return la pioche associée à la partie.
     */
    public Pioche getPioche(){
        return this.pioche;
    }
    
    /**
     * Renvoie la liste de tous les joueurs.
     * @return les joueurs.
     */
    public Joueur[] getListeJoueurs(){
        return joueurs;
    }
    
    /**
     * Renvoie l'ordre de jeu associée à la partie.
     * @return l'ordre de jeu.
     */
    public OrdreDeJeu getOrdreDeJeu(){
        return this.ordre;
    }
    /**
     * Renvoie le nombre de joueurs.
     * @return le nombre de joueurs.
     */
    public int getNombreJoueurs(){
        return joueurs.length;
    }
    
    /**
     * Pose la carte sélectionnée par le joueur dans sa main sur le haut du paquet.
     * Vérifie que la carte respecte les règles puis pose. 
     * Si la carte permet de choisir une couleur, ouvre un popup. Met à jour
     * l'affichage des cartes et du paquet.
     * @throws IOException si le popup ne s'ouvre pas.
     */
    public void poserCarteSelectionnee() throws IOException{
        if (this.carteSelectionnee != null && this.verification.peutPoser(carteSelectionnee)){
            this.ordre.getJoueurActuel().getPaquetJoueur().jouerCarte(this.carteSelectionnee);
            this.paquet.poserCarte(this.carteSelectionnee);
            this.couleurActuelle=carteSelectionnee.getCouleur();
            if (this.carteSelectionnee.getCouleur().equals("Joker") && 
                    !(this.carteSelectionnee instanceof Tdah) && 
                    !(this.carteSelectionnee instanceof AmnesieSelective)){
                controller.ouvrirPopupCouleur();
            }
            effets.appliquerEffets(carteSelectionnee);
            System.out.println(this.couleurActuelle);
        }
    }
    
    /**
     * Renvoie la liste de cartes du joueur i.
     * @param i l'index du joueur dans la liste des joueurs.
     * @return la liste de cartes du joueur.
     */
    public ArrayList<Carte> getListeCartesJoueur(int i){
        return this.joueurs[i].getPaquetJoueur().getListeCartes();
    }
    
    /**
     * Renvoie le nombre de cartes du joueur i.
     * @param i l'index du joueur dans la liste des joueurs.
     * @return la liste de cartes du joueur.
     */
    public int getNombreCartesJoueur(int i){
        return this.joueurs[i].getPaquetJoueur().getListeCartes().size();
    }
}
