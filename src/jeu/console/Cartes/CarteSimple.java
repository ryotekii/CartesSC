package jeu.console.Cartes;

public class CarteSimple extends Carte {
    private final int numero;
    
    public CarteSimple(int i,String c){
        super(c);
        this.numero = i;
    }
    
    public int getNumero(){
        return this.numero;
    }
}
