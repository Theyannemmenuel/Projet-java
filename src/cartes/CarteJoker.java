package cartes;

import utils.Couleur;
import utils.TypeAction;

public class CarteJoker extends Carte {
    private final TypeAction typeAction;

    public CarteJoker(TypeAction typeAction) {
        super(Couleur.AUCUNE); // Joker n'a pas de couleur définie au départ
        this.typeAction = typeAction;
    }

    @Override
    public boolean estJouable(Carte carteTalon) {
        return true; // Toujours jouable
    }

    @Override
    public TypeAction getTypeAction() {
        return typeAction;
    }

    /**
     * Permet de définir la couleur choisie par le joueur pour le joker.
     */
    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    @Override
    public String toString() {
        if (couleur == Couleur.AUCUNE) {
            return "JOKER " + typeAction;
        } else {
            return "JOKER " + typeAction + " (" + couleur + ")";
        }
    }
}