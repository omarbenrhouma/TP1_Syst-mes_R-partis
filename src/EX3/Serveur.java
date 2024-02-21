package EX3;
import java.io.*;
import java.net.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import java.util.Scanner;

public class Serveur {
    private static int identifiantClient = 1;

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
        } try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Serveur en attente de connexions...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connecté depuis : " + clientSocket.getInetAddress());

                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

                // Attend de recevoir un objet Personne du client
                Personne personne = (Personne) in.readObject();

                // Traite l'objet reçu (par exemple, affiche les informations de la personne)
                System.out.println("Nouvelle personne reçue - Nom: " + personne.getNom() + ", Age: " + personne.getAge());

                // Génère un identifiant crypté unique
                String identifiant = generateUniqueIdentifier(personne);
                System.out.println("Identifiant attribué par le serveur (crypté) : " + identifiant);

                // Envoie l'identifiant crypté au client
                out.writeObject(identifiant);
                out.flush();

                // Fermer la connexion
                out.close();
                in.close();
                clientSocket.close();
            }

        } catch (IOException | ClassNotFoundException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static String generateUniqueIdentifier(Personne personne) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        String dataToHash = personne.getNom() + personne.getAge() + System.currentTimeMillis();
        byte[] hashedBytes = md.digest(dataToHash.getBytes());

        return Base64.getEncoder().encodeToString(hashedBytes);
    }
}