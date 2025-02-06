package jeu;

import java.util.ArrayList;
import java.util.Collections;
import jeu.Cartes.Carte;
import jeu.Cartes.CarteSimple;

public class Pioche {
    private static Pioche instance;
    private final static ArrayList pioche = new ArrayList<Carte>();
    
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
        return (Carte) pioche.remove(0);
    }
    
    private static void refairePaquet(){
        /*
        Modifier la fonction pour reprendre les cartes de la table de jeu (sauf
        la premiere) et les ajouter à la pioche.
        */
        Pioche.melanger();
    }
    
}
