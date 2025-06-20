package cartes;

import utils.Couleur;
import utils.TypeAction;

public class CarteJoker extends Carte {
    private final TypeAction action; // JOKER ou PLUS_QUATRE

    public CarteJoker(TypeAction action) {
        super(Couleur.AUCUNE); // couleur définie par le joueur après avoir joué la carte
        this.action = action;
    }

    public TypeAction getAction() {
        return action;
    }

    @Override
    public boolean estJouable(Carte carteDessus) {
        return true; // Les jokers peuvent être joués sur n'importe quelle carte
    }

    @Override
    public String toString() {
        return action == TypeAction.PLUS_QUATRE ? "+4 (JOKER)" : "JOKER";
    }
}