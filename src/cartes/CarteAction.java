package cartes;

import utils.Couleur;
import utils.TypeAction;

public class CarteAction extends Carte {
    private TypeAction action;

    public CarteAction(Couleur couleur, TypeAction action) {
        super(couleur);
        this.action = action;
    }

    public TypeAction getAction() {
        return action;
    }

    @Override
    public boolean estJouable(Carte carteDessus) {
        return this.couleur == carteDessus.getCouleur() ||
               (carteDessus instanceof CarteAction && this.action == ((CarteAction) carteDessus).getAction());
    }

    @Override
    public String toString() {
        return couleur + " " + action;
    }
}

