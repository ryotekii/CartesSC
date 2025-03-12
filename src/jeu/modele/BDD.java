package jeu.modele;
import java.sql.*;
import java.util.LinkedHashMap;

public class BDD {
    private static Connection getConnexion() throws Exception {
        Connection connexion = null;
        try {
            connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/classementcognicards","root","");
        } catch (Exception e){
            System.out.println("erreur de connexion");
            e.printStackTrace();
        }
        return connexion;
    }
    
    public static void ajouterVictoire(String pseudo){
        String requete = "INSERT INTO joueurs(pseudo,victoires) VALUES (?,1)"
                + "ON DUPLICATE KEY UPDATE victoires = victoires + 1";
        try {
            Connection connexion = getConnexion();
            PreparedStatement ps = connexion.prepareStatement(requete);
            
            if (connexion != null) {
                ps.setString(1,pseudo);
                ps.executeUpdate();
                System.out.println("Victoire ajoutée !");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public static LinkedHashMap<String,Integer> recupererJoueurs(){
        String requete = "SELECT * FROM joueurs ORDER BY victoires DESC";
        
        LinkedHashMap<String,Integer> joueurs = new LinkedHashMap<>();
        try {
            Connection connexion = getConnexion();
            Statement st = connexion.createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()){
                String pseudo = rs.getString("pseudo");
                int victoires = rs.getInt("victoires");
                joueurs.put(pseudo, victoires);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return joueurs;
    }
    
}
