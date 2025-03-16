/*package jeu.modele;

import java.io.*;
import java.net.*;
import java.util.*;

public class Serveur {
    private static final int PORT = 5000;
    private static final int NBJOUEURS = 4;
    private static final List<Socket> sockets = new ArrayList<>();
    private static final List<Joueur> joueurs = new ArrayList<>();

    public static void creerServeur() {
        try (ServerSocket serveur = new ServerSocket(PORT)) { //Créé un serveur sur le port 5000.
            System.out.println("Serveur créé.");
            Partie partie = new Partie();
            
            while (joueurs.size()<NBJOUEURS) {
                Socket clientSocket = serveur.accept(); //Accepter les connexions.
                synchronized (sockets) {
                    Joueur joueur = new Joueur(partie);
                    joueur.setSocket(clientSocket);
                    joueurs.add(joueur);
                    sockets.add(clientSocket);
                    Connexion c = new Connexion(clientSocket);
                    Thread processusConnexion = new Thread(c);
                    envoyerMessageTous("Un nouveau joueur a rejoint la partie !");
                }
            }
            envoyerMessageTous("Démarrage en cours...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void gererJoueur(Joueur joueur) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(joueur.getSocket().getInputStream()));
            PrintWriter out = new PrintWriter(joueur.getSocket().getOutputStream(), true);
        } catch (IOException e) { 
            e.printStackTrace();
        }
    }

    public static void rejoindrePartie(Joueur joueur) {
        try {
            Socket clientSocket = new Socket ("localhost", PORT);
            joueur.setSocket(clientSocket);
            System.out.println("Connecté au serveur !");
            envoyerMessageTous(joueur.getPseudo() + " a rejoint la partie.");
        } catch (IOException e) {
            System.out.println("Impossible de rejoindre la partie !");
            e.printStackTrace();
        }
    }
    
    public static void quitterPartie(Joueur joueur) {
        try {
            synchronized (sockets) {
                if (joueur.getSocket() != null && !joueur.getSocket().isClosed()) {
                    joueur.getSocket().close();
                    joueurs.remove(joueur);
                    sockets.remove(joueur.getSocket());
                    envoyerMessageTous(joueur.getPseudo() + "  s'est déconnecté.");
                }
            }
            System.out.println("joueurs"+joueurs);
            System.out.println("sockets"+sockets);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void envoyerMessageTous(String message) {
        synchronized (sockets) {
            Iterator<Socket> iterator = sockets.iterator();
            while (iterator.hasNext()) {
                Socket socket = iterator.next();
                try {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println(message);
                } catch (IOException e) {
                    iterator.remove();
                }
            }
        }
    }
    
    
}*/

