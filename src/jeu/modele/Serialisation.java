package jeu.modele;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import jeu.modele.Partie;

public class Serialisation {
    public static void serialiser(Partie p, int n){
        ObjectOutputStream oos = null;
        
        try {
            final FileOutputStream fichier = new FileOutputStream("partie"+n+".ser");
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(p);
            oos.flush();
        } catch(final java.io.IOException e){
            e.printStackTrace();
        } finally {
            try {
                if (oos!=null){
                    oos.flush();
                    oos.close();
                }
            } catch(final IOException ex){
                ex.printStackTrace();
            }
        }
    }
    
    public static Partie recuperer(int n){
        Partie p = null;
        ObjectInputStream ois = null;
        try {
            final FileInputStream fichierIn = new FileInputStream("partie"+n+".ser");
            ois = new ObjectInputStream(fichierIn);
            p = (Partie) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if (ois !=null) {
                ois.close();
                }
            }catch(final IOException ex){
                ex.printStackTrace();
            }
        }
        return p;
    }
}