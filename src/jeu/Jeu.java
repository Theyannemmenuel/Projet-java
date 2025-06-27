package jeu;

import cartes.Carte;
import joueur.Joueur;

import java.util.List;

/**
 * Classe principale qui représente une partie de UNO.
 * Elle gère les joueurs, la pioche, et le talon (pile de défausse).
 */
public class Jeu {
    private final List<Joueur> joueurs;  // Liste des joueurs en jeu
    private final Pioche pioche;         // La pioche du jeu
    private final Talon talon;           // Le talon (cartes jouées)

    public Jeu(List<Joueur> joueurs) {
        this.joueurs = joueurs;
        this.pioche = new Pioche();
        this.talon = new Talon();
        distribuerCartes();   // Donne les cartes à chaque joueur
        initialiserTalon();   // Pose la première carte du jeu
    }

    /**
     * Donne 7 cartes à chaque joueur au début de la partie.
     */
    private void distribuerCartes() {
        for (Joueur joueur : joueurs) {
            for (int i = 0; i < 7; i++) {
                joueur.ajouterCarte(pioche.piocher(talon));
            }
        }
    }

    /**
     * Pose une carte du dessus de la pioche sur le talon pour commencer la partie.
     */
    private void initialiserTalon() {
        Carte premiere = pioche.piocher(talon);
        talon.ajouterCarte(premiere);
    }

    // Accesseurs utiles pour récupérer les éléments de la partie

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