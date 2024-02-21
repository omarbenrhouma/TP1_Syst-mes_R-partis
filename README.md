# TP1_Syst-mes_R-partis

ceci concerne le travail réaliser du tp 1 concerne la création et la manipulation des sockets en mode TCP coté client et serveur. Le TP se divise en 3 exercices chaqu'un est réaliser dans un package indépendant.

            Exercice 1 :
L’exercice a pour but de développer un serveur TCCP/IP qui écoute sur le port 10000 et qui
accepte des connexions clientes. Le programme client se connecte au serveur, puis lui
envoie un message. Le message est analysé par le serveur, qui lui répond.
L’exemple suivant montre deux classes :
 L’un du coté serveur : SocketServeur
 L’autre du coté client : SocketClient
1. Commenter les lignes de code des classes : SocketClient , SocketServeur.
2. Testez ces deux programmes échangeant la phrase « ma première socket » dans les
deux cas suivants :
a. Les deux programmes se trouvant dans la même machine.
b. Les deux programmes dans deux machines distants.
Note : Il faut d’abord lancer le serveur pour qu’il soit à l’écoute.
3. Effectuer un schéma indiquant les flux de messages échangés

            Exercice 2 :
On souhaite échanger entre le client/serveur les objets d’une classe voiture via les sockets.
la classe voiture
Pour cela le client Crée un objet voiture et l’envoi au serveur pour lui fixer une quantité de
carburant avec la méthode setCarburant().
1. Ecrire la partie client de l’application et la partie serveur.
2. Tester cette application sur la même machine.
3. Tester cette application sur deux machines reliées par réseaux.


            Exercice 3 :
L'application distribuée à réaliser est basique. Elle est composée d'un client et d'un serveur.
La partie client envoie des données concernant des personnes au serveur. Ces données sont
composées de 2 informations : un entier (int) pour l'âge de la personne et une chaîne de
caractères (String) pour son nom. A chaque envoi d'informations sur une personne de la part
d'un client, le serveur retourne au client un entier correspondant à l'identificateur "unique et cryptée" du client
