package jeu.modele;

import java.util.ArrayList;
import java.util.Collections;
import jeu.modele.Cartes.AmnesieSelective;
import jeu.modele.Cartes.BlocageMoteur;
import jeu.modele.Cartes.Carte;
import jeu.modele.Cartes.CarteSimple;
import jeu.modele.Cartes.CriseEpileptique;
import jeu.modele.Cartes.MainEtrangere;
import jeu.modele.Cartes.Narcolepsie;
import jeu.modele.Cartes.NeuropathieO;
import jeu.modele.Cartes.Paralysie;
import jeu.modele.Cartes.Tdah;
import jeu.modele.Cartes.TroubleEquilibre;

public final class Pioche {
    private final ArrayList<Carte> pioche = new ArrayList<>();
    private final Partie partie;
    
    public Pioche(Partie p){
        this.init();
        this.melanger();
        this.partie = p;
    }

    private void init(){
        Carte temp;
        for (String couleur : Parametres.COULEURS) {
            for (int i=0;i<=9;i++){
                temp = new CarteSimple(i,couleur);
                pioche.add(temp);
                pioche.add(temp);
            }
            for (int i=0;i<2;i++){
                pioche.add(new AmnesieSelective());
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
        Carte temp = pioche.remove(0);
        System.out.println(temp.nomImage());
        return temp;
    }
    
    /*
    OK - Reprend toutes les cartes du paquet (sauf la première) et les remet dans la pioche.
    */
    private void refairePaquet(){
        ArrayList<Carte> provisoire = this.partie.getPaquet().viderPaquet();
        this.pioche.addAll(provisoire);
        this.melanger();
        System.out.println("La pioche a été refaite.");
    }
    
}
