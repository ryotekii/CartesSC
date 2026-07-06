package jeu.modele;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.scene.control.Label;
import jeu.modele.Partie;

public class Serialisation {
    /**
     * Sérialise une partie. Créé un fichier associé qui permettra de la reprendre ensuite.
     * @param p la partie à sérialiser.
     * @param n l'emplacement de sauvegarde souhaité.
     */
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
    
    /**
     * Renvoie la partie précédémment sauvegardée dans un emplacement <code>n</code>.
     * @param n le numéro de la sauvegarde.
     * @return l'objet Partie sauvegardé.
     */
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
    
    /**
     * Enregistre le contenu d'un tableau de texte sous la forme d'un fichier ser.
     * @param textes les textes à sérialiser.
     */
    public static void serialiserLabels(String[] textes){
        ObjectOutputStream oos = null;
        
        try {
            final FileOutputStream fichier = new FileOutputStream("nomsSauvegardes.ser");
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(textes);
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
    
    /**
     * Récupère les labels sérialisés et les renvoie sous la forme d'un tableau.
     * @return les labels récupérés.
     */
    public static String[] recupererLabels(){
        String[] textes = null;
        ObjectInputStream ois = null;
        try {
            final FileInputStream fichierIn = new FileInputStream("nomsSauvegardes.ser");
            ois = new ObjectInputStream(fichierIn);
            textes = (String[]) ois.readObject();
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
        return textes;
    }
}