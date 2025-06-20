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

    @Override
    public String toString() {
        return "JOKER " + typeAction;
    }
}
