package moteur;

import java.util.Stack;

public class Talon {
    private Stack<Carte> pile;

    public Talon() {
        pile = new Stack<>();
    }

    // Poser une carte sur le talon
    public void poserCarte(Carte carte) {
        pile.push(carte);
    }

    // Récupérer la carte du dessus du talon sans la retirer
    public Carte getCarteDuDessus() {
        if (!pile.isEmpty()) {
            return pile.peek();
        }
        return null;
    }

    // Retirer la carte du dessus (si besoin)
    public Carte retirerCarteDuDessus() {
        if (!pile.isEmpty()) {
            return pile.pop();
        }
        return null;
    }

    // Vérifier si le talon est vide
    public boolean estVide() {
        return pile.isEmpty();
    }

    // Taille du talon
    public int taille() {
        return pile.size();
    }
}