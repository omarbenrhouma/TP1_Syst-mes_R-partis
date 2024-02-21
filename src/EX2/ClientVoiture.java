package EX2;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientVoiture {
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

            System.out.print("Entrez le type de voiture : ");
            String type = scanner.next();

            System.out.print("Entrez le modèle de voiture : ");
            String modele = scanner.next();

            System.out.print("Entrez la quantité de carburant : ");
            int carburant = scanner.nextInt();

            Socket socket = new Socket(serverAddress, serverPort);

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            // Crée un objet Voiture avec les paramètres saisis par le client
            Voiture voiture = new Voiture(type, modele);
            voiture.setCarburant(carburant);

            // Envoie l'objet au serveur
            out.writeObject(voiture);

            // Attend la réponse du serveur
            Voiture reponseVoiture = (Voiture) in.readObject();
            System.out.println("Carburant après mise à jour côté serveur : " + reponseVoiture.getCarburant());

            // Ferme les flux et la connexion
            out.close();
            in.close();
            socket.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
