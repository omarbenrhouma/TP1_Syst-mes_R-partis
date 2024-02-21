package EX1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SocketServeur {
    public static void main(String argv[]) {
        int port = 0;
        Scanner keyb = new Scanner(System.in);

        // Demande à l'utilisateur le port d'écoute du serveur
        System.out.print("Port d'écoute : ");
        try {
            port = keyb.nextInt();
        } catch (NumberFormatException e) {
            System.err.println("Le paramètre n'est pas un entier.");
            System.err.println("Usage : java ServeurUDP port-serveur");
            System.exit(-1);
        }

        try {
            // Crée un socket serveur écoutant le port spécifié
            ServerSocket serverSocket = new ServerSocket(port);

            // Attend la connexion d'un client et accepte la connexion
            Socket socket = serverSocket.accept();

            // Initialise les objets en entrées et sorties
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            // Lit la chaîne envoyée par le client
            String chaine = (String) input.readObject();
            System.out.println("Reçu : " + chaine);

            // Affiche l'adresse du client et le port
            System.out.println("Cela vient de : " + socket.getInetAddress() +
                    ":" + socket.getPort());

            // Envoie une réponse au client via le flux de sortie
            output.writeObject(new String("Bien reçu"));
        } catch (Exception e) {
            System.err.println("Erreur : " + e);
        }
    }
}
