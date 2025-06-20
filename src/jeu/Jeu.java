package jeu;

import cartes.Carte;
import joueur.Joueur;

import java.util.List;

public class Jeu {
    private final List<Joueur> joueurs;
    private final Pioche pioche;
    private final Talon talon;

    public Jeu(List<Joueur> joueurs) {
        this.joueurs = joueurs;
        this.pioche = new Pioche();
        this.talon = new Talon();
        distribuerCartes();
        initialiserTalon();
    }

    private void distribuerCartes() {
        for (Joueur joueur : joueurs) {
            for (int i = 0; i < 7; i++) {
                joueur.ajouterCarte(pioche.piocher());
            }
        }
    }

    private void initialiserTalon() {
        Carte premiere = pioche.piocher();
        talon.ajouterCarte(premiere);
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
