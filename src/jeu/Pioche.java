package jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import cartes.Carte;

// Classe qui représente la pioche (le tas de cartes à piocher)
public class Pioche {
    private List<Carte> cartes;

    public Pioche(List<Carte> cartesMelangees) {
        // On crée une copie mélangée des cartes pour la pioche
        cartes = new ArrayList<>(cartesMelangees);
        Collections.shuffle(cartes); // Mélange
    }

    // Méthode pour piocher une carte
    public Carte piocher() {
        if (!cartes.isEmpty()) {
            return cartes.remove(0); // On prend la première carte
        } else {
            return null; // Pioche vide
        }
    }

    // Vérifie si la pioche est vide
    public boolean estVide() {
        return cartes.isEmpty();
    }
}
