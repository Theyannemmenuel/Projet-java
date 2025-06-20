package jeu;

import cartes.Carte;
import cartes.Pioche;
import cartes.Talon;
import joueur.Joueur;

import java.util.List;

public class Jeu {
    private List<Joueur> joueurs;
    private Pioche pioche;
    private Talon talon;

    public Jeu(List<Joueur> joueurs) {
        this.joueurs = joueurs;
        this.pioche = new Pioche();
        this.talon = new Talon();

        // Distribuer 7 cartes à chaque joueur
        for (Joueur joueur : joueurs) {
            for (int i = 0; i < 7; i++) {
                joueur.ajouterCarte(pioche.piocher());
            }
        }

        // Initialiser le talon avec une carte (non noire)
        Carte premiereCarte;
        do {
            premiereCarte = pioche.piocher();
        } while (premiereCarte.getCouleur().equals("noir"));
        talon.ajouterCarte(premiereCarte);
    }

    public List<Joueur> getJoueurs() {
        return joueurs;
    }

    public Joueur getJoueur(int index) {
        return joueurs.get(index);
    }

    public Pioche getPioche() {
        return pioche;
    }

    public Talon getTalon() {
        return talon;
    }
}
