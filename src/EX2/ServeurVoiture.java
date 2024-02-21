package EX2;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ServeurVoiture {
    public static void main(String[] args) {
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
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Serveur en attente de connexion...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connecté depuis : " + clientSocket.getInetAddress());

            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

            // Attend de recevoir un objet Voiture du client
            Voiture voiture = (Voiture) in.readObject();

            // Traite l'objet reçu (par exemple, met à jour le carburant)
            voiture.setCarburant(voiture.getCarburant() + 10);

            // Envoie une réponse au client
            out.writeObject(voiture);

            // Fermer la connexion
            out.close();
            in.close();
            clientSocket.close();
            serverSocket.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
