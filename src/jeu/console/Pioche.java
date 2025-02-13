package jeu.console;

import java.util.ArrayList;
import java.util.Collections;
import jeu.console.Cartes.AmnesieSelective;
import jeu.console.Cartes.BlocageMoteur;
import jeu.console.Cartes.Carte;
import jeu.console.Cartes.CarteSimple;
import jeu.console.Cartes.CriseEpileptique;
import jeu.console.Cartes.MainEtrangere;
import jeu.console.Cartes.Narcolepsie;
import jeu.console.Cartes.NeuropathieO;
import jeu.console.Cartes.Paralysie;
import jeu.console.Cartes.Tdah;
import jeu.console.Cartes.TroubleEquilibre;

public final class Pioche {
    private final ArrayList<Carte> pioche = new ArrayList<>();
    private final Partie partie;
    
    public Pioche(Partie p){
        this.init();
        this.melanger();
        this.partie = p;
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
            for (int i=0;i<2;i++){
                pioche.add(new AmnesieSelective(couleur));
                pioche.add(new BlocageMoteur(couleur));
                pioche.add(new MainEtrangere(couleur));
                pioche.add(new Narcolepsie(couleur));
                pioche.add(new TroubleEquilibre(couleur));
            }
            pioche.add(new CriseEpileptique());
            pioche.add(new NeuropathieO());
            pioche.add(new Paralysie());
            pioche.add(new Tdah());
        }
    }
    
    public void melanger(){
        Collections.shuffle(pioche);
    }
    
    public Carte piocher(){
        if (pioche.isEmpty()){
            refairePaquet();
        }
        return pioche.remove(0);
    }
    
    /*
    Faire une méthode pour distribuer le paquet en début de partie.
    */
    
    private void refairePaquet(){
        /*
        Modifier la fonction pour reprendre les cartes de la table de jeu (sauf
        la premiere) et les ajouter à la pioche.
        */
        ArrayList<Carte> provisoire = this.partie.getPaquet().viderPaquet();
        this.pioche.addAll(provisoire);
        this.melanger();
    }
    
}
