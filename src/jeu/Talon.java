package jeu;

import cartes.Carte;

import java.util.ArrayList;
import java.util.List;

public class Talon {
    private List<Carte> cartes;

    public Talon() {
        this.cartes = new ArrayList<>();
    }

    public void ajouterCarte(Carte carte) {
        cartes.add(carte);
    }

    public Carte getDerniereCarte() {
        if (cartes.isEmpty()) {
            return null;
        }
        return cartes.get(cartes.size() - 1);
    }
}
