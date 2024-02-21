package EX3;
import java.io.Serializable;

public class Personne implements Serializable {
    private int age;
    private String nom;

    public Personne(int age, String nom) {
        this.age = age;
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public String getNom() {
        return nom;
    }

}
