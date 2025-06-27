package joueur;

import cartes.Carte;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui représente un joueur du jeu UNO.
 * Chaque joueur possède un nom et une main de cartes.
 */
public class Joueur {
    private final String nom;
    private final List<Carte> main;  // La main du joueur (ses cartes)

    public Joueur(String nom) {
        this.nom = nom;
        this.main = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public List<Carte> getMain() {
        return main;
    }

    /**
     * Ajoute une carte dans la main du joueur.
     */
    public void ajouterCarte(Carte carte) {
        main.add(carte);
    }

    /**
     * Retire une carte spécifique de la main du joueur.
     */
    public void retirerCarte(Carte carte) {
        main.remove(carte);
    }

    /**
     * Retourne vrai si le joueur n'a plus de cartes (il a gagné).
     */
    public boolean aGagne() {
        return main.isEmpty();
    }
}