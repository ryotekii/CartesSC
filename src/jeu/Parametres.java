package jeu;

public class Parametres {
    public static final String[] COULEURS = {"Rouge","Bleu","Vert","Jaune"};
    /* Variables statiques qui changent selon le mode de jeu.
    EMPILER permet de savoir si on a le droit de mettre un +2 après un +2
    ou un +4 après un +4. POSER_SI_POSSIBLE nous dit s'il faut jouer les cartes
    possibles obligatoirement ou si l'on peut piocher.
    */
    public static boolean EMPILER = false;
    public static boolean POSER_SI_POSSIBLE = true;
    
    /* A venir : 
    Ajouter des paramètres pour retirer certaines cartes spéciales.
    Configurer les paramètres selon le mode de jeu.
    */
    
}
