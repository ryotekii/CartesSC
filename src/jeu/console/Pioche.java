package jeu.console;

import java.util.ArrayList;
import java.util.Collections;
import jeu.console.Cartes.Carte;
import jeu.console.Cartes.CarteSimple;

public class Pioche {
    private static Pioche instance;
    private final static ArrayList<Carte> pioche = new ArrayList<>();
    
    public Pioche(){
        this.init();
        Pioche.melanger();
    }
    public static Pioche getInstance(){
        if (instance == null)
            instance = new Pioche();
        return instance;
    }
    
    /*
    Ajoute dans le paquet 2 cartes de chaque couleur.
    A modifier pour ajouter un nombre défini de cartes spéciales
    en fonction des parametres du mode de jeu.
    */
    private void init(){
        Carte temp;
        for (String couleur : Parametres.COULEURS) {
            for (int i=0;i<=9;i++){
                temp = new CarteSimple(i,couleur);
                pioche.add(temp);
                pioche.add(temp);
            }
        }
    }
    
    public static void melanger(){
        Collections.shuffle(pioche);
    }
    
    public static Carte piocher(){
        if (pioche.isEmpty()){
            refairePaquet();
        }
        return pioche.remove(0);
    }
    
    /*
    Faire une méthode pour distribuer le paquet en début de partie.
    */
    
    private static void refairePaquet(){
        /*
        Modifier la fonction pour reprendre les cartes de la table de jeu (sauf
        la premiere) et les ajouter à la pioche.
        */
        Pioche.melanger();
    }
    
}
