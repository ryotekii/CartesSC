package jeu.modele;

import java.io.*;
import java.net.*;
import java.util.*;

public class Serveur {
    private static final int PORT = 5000;
    private static int nbJoueurs = 4;
    private static final List<Socket> sockets = new ArrayList<>();
    private static List<Joueur> joueurs = new ArrayList<>();

    public static void creerServeur() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) { //Créé un serveur sur le port 5000.
            System.out.println("Serveur créé.");
            while (joueurs.size()<nbJoueurs) {
                Socket clientSocket = serverSocket.accept(); //Accepter les connexions.
                System.out.println("Nouveau joueur connecté");
            }
            
            System.out.println("La partie commence.");
            for (Socket socket : sockets) {
                new PrintWriter(socket.getOutputStream(), true).println("Démarrage en cours...");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void gererJoueur(Joueur joueur) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(joueur.getSocket().getInputStream()));
            PrintWriter out = new PrintWriter(joueur.getSocket().getOutputStream(), true);

            synchronized (sockets) {
                if (joueurs.size() == nbJoueurs) {
                    System.out.println("Tous les joueurs sont connectés, la partie va commencer.");
                    for (Socket socket : sockets) {
                        new PrintWriter(socket.getOutputStream(), true).println("Démarrage en cours...");
                    }
                }
            }
        } catch (IOException e) { 
            e.printStackTrace();
        }
    }

    public static void rejoindrePartie(Joueur joueur) {
        try {
            Socket clientSocket = new Socket ("localhost", PORT);
            joueur.setSocket(clientSocket);
            
            synchronized(sockets){
                joueurs.add(joueur);
                sockets.add(clientSocket);
            }
            System.out.println("Connecté au serveur !");
            System.out.println(joueur.getPseudo() + " a rejoint la partie.");
            
            new Thread(() -> gererJoueur(joueur)).start();
        } catch (IOException e) {
            System.out.println("Impossible de rejoindre la partie !");
            e.printStackTrace();
        }
    }
    
    public static void quitterPartie(Joueur joueur) {
        try {
            System.out.println(joueur.getSocket());
            System.out.println(joueur.getSocket().isClosed());
            synchronized (sockets) {
                if (joueur.getSocket() != null && !joueur.getSocket().isClosed()) {
                    joueur.getSocket().close();
                    joueurs.remove(joueur);
                    sockets.remove(joueur.getSocket());
                    System.out.println("Joueur déconnecté.");
                }
            }
            System.out.println("joueurs"+joueurs);
            System.out.println("sockets"+sockets);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

