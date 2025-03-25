package jeu.modele;
import java.sql.*;
import java.util.LinkedHashMap;

public class BDD {
    
    /**
     * Se connecte à la base de données.
     * @return la connexion.
     * @throws Exception 
     */
    private static Connection getConnexion() throws Exception {
        Connection connexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/classementcognicards","root","");
        } catch (Exception e){
            System.out.println("erreur de connexion");
            e.printStackTrace();
        }
        return connexion;
    }
    
    /**
     * Se connecte à la base de données et ajoute un joueur lorsqu'il gagne.
     * Si son pseudo est déjà présent, incrémente le nombre de victoires.
     * @param pseudo le pseudo du gagnant.
     */
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
    
    /**
     * Récupère les joueurs de la base de données triés par victoires décroissantes.
     * @return la liste de joueurs.
     */
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
