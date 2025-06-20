package joueur;

import cartes.Carte;

import java.util.ArrayList;
import java.util.List;

public class Joueur {
    private final String nom;
    private final List<Carte> main;

    public Joueur(String nom) {
        this.nom = nom;
        this.main = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public void ajouterCarte(Carte carte) {
        main.add(carte);
    }

    public void retirerCarte(Carte carte) {
        main.remove(carte);
    }

    public List<Carte> getMain() {
        return main;
    }

    public boolean aGagne() {
        return main.isEmpty();
    }

    public Carte jouerCarte(Carte carteTalon) {
        for (Carte carte : main) {
            if (carte.estJouable(carteTalon)) {
                retirerCarte(carte);
                return carte;
            }
        }
        return null;
    }
}
