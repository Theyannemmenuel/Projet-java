package jeu;

import cartes.Carte;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui représente le talon, c'est-à-dire la pile de cartes jouées.
 */
public class Talon {
    private List<Carte> cartes;  // Cartes posées sur le talon

    public Talon() {
        this.cartes = new ArrayList<>();
    }

    /**
     * Ajoute une nouvelle carte sur le talon (carte jouée par un joueur).
     */
    public void ajouterCarte(Carte carte) {
        cartes.add(carte);
    }

    /**
     * Renvoie la carte actuellement visible sur le dessus du talon.
     */
    public Carte getDerniereCarte() {
        if (cartes.isEmpty()) {
            return null;
        }
        return cartes.get(cartes.size() - 1);
    }

    /**
     * Récupère toutes les cartes sauf la dernière pour les remettre dans la pioche.
     */
    public List<Carte> recupererCartesPourRecycler() {
        if (cartes.size() <= 1) return new ArrayList<>();
        List<Carte> aRecycler = new ArrayList<>(cartes.subList(0, cartes.size() - 1));
        Carte derniere = getDerniereCarte();
        cartes.clear();
        cartes.add(derniere);  // On garde la dernière sur le talon
        return aRecycler;
    }
}