package jeu.console.Cartes;

/*
Les cartes sans effet, de 0 à 9 dans chaque couleur.
*/

public class CarteSimple extends Carte {
    private final int numero;
    
    public CarteSimple(int i,String c){
        super(c);
        this.numero = i;
    }
    
    public int getNumero(){
        return this.numero;
    }
    
    @Override
    public String toString(){
        return this.numero+" "+this.couleur;
    }
}
