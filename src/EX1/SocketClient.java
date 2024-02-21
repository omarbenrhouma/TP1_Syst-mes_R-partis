package EX1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

class SocketClient {
    public static void main(String argv[]) {
        int port = 0;
        String host = "";
        Scanner keyb = new Scanner(System.in);

        // Demande à l'utilisateur le nom du serveur
        System.out.print("Nom du serveur : ");
        host = keyb.next();

        // Demande à l'utilisateur le port du serveur
        System.out.print("Port du serveur : ");
        try {
            port = keyb.nextInt();
        } catch (NumberFormatException e) {
            System.err.println("Le second paramètre n'est pas un entier.");
            System.exit(-1);
        }

        try {
            // Obtient l'adresse IP du serveur en utilisant le nom fourni
            InetAddress adr = InetAddress.getByName(host);

            // Crée un socket avec l'adresse IP et le port spécifiés
            Socket socket = new Socket(adr, port);

            // Initialise les flux de sortie et d'entrée d'objets
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            // Envoie une chaîne au serveur via le flux de sortie
            output.writeObject(new String("ma première socket"));

            // Lit la réponse du serveur depuis le flux d'entrée
            String chaine = (String) input.readObject();
            System.out.println("Reçu du serveur : " + chaine);
        } catch (Exception e) {
            System.err.println("Erreur : " + e);
        }
    }
}
