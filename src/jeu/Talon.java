package jeu;

import cartes.Carte;
import java.util.Stack;

// Classe qui représente le talon (pile des cartes jouées)
public class Talon {
    private Stack<Carte> pile;

    public Talon(Carte premiereCarte) {
        pile = new Stack<>();
        pile.push(premiereCarte); // On place la première carte
    }

    // Méthode pour ajouter une carte jouée
    public void poserCarte(Carte carte) {
        pile.push(carte);
    }

    // Méthode pour voir la dernière carte jouée (dessus du talon)
    public Carte getDerniereCarte() {
        return pile.peek();
    }
}
