package joueur;

import java.util.ArrayList;
import java.util.List;
import cartes.Carte;

// Classe qui représente un joueur dans le jeu UNO
public class Joueur {
    private String nom;
    private List<Carte> main;

    public Joueur(String nom) {
        this.nom = nom;
        this.main = new ArrayList<>(); // Liste des cartes du joueur
    }

    // Méthode pour obtenir le nom du joueur
    public String getNom() {
        return nom;
    }

    // Ajouter une carte à la main du joueur
    public void ajouterCarte(Carte carte) {
        main.add(carte);
    }

    // Retirer une carte de la main du joueur
    public void retirerCarte(Carte carte) {
        main.remove(carte);
    }

    // Retourner la liste des cartes du joueur
    public List<Carte> getMain() {
        return main;
    }

    // Vérifie si le joueur a fini ses cartes
    public boolean aGagne() {
        return main.isEmpty();
    }
}
