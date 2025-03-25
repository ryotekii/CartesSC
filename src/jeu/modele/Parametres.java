package jeu.modele;

import static java.lang.Math.random;
import java.util.Random;

/**
 * Constantes du jeu.
 */
public class Parametres {
    public static final String[] COULEURS = {"Rouge","Bleu","Vert"};
    public static String IMAGES = "/jeu/images/";
    
    /**
     * Retourne un entier aléatoire entre min et max inclus.
     * @param min le minimum.
     * @param max le maximum.
     * @return un entier aléatoire compris entre les deux bornes.
     */
    public static int entierAleatoire(int min, int max){
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}
