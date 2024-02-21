package EX3;import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 0;
        Scanner keyb = new Scanner(System.in);


        // Demande à l'utilisateur le port du serveur
        System.out.print("Port du serveur : ");
        try {
            serverPort = keyb.nextInt();
        } catch (NumberFormatException e) {
            System.err.println("Le second paramètre n'est pas un entier.");
            System.exit(-1);
        }
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Entrez le nom de la personne : ");
            String nom = scanner.nextLine();

            System.out.print("Entrez l'âge de la personne : ");
            int age = scanner.nextInt();

            Socket socket = new Socket(serverAddress, serverPort);

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            // Crée un objet Personne avec les informations saisies par le client
            Personne personne = new Personne(age, nom);

            // Envoie l'objet au serveur
            out.writeObject(personne);
            out.flush();

            // Attend la réponse du serveur (identifiant crypté)
            String identifiantCrypte = (String) in.readObject();
            System.out.println("Identifiant crypté reçu du serveur : " + identifiantCrypte);

            // Fermer la connexion
            out.close();
            in.close();
            socket.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
