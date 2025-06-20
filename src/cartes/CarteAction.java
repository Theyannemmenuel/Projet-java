package cartes;

import utils.Couleur;
import utils.TypeAction;

public class CarteAction extends Carte {
    private final TypeAction typeAction;

    public CarteAction(Couleur couleur, TypeAction typeAction) {
        super(couleur);
        this.typeAction = typeAction;
    }

    @Override
    public boolean estJouable(Carte carteTalon) {
        return this.couleur == carteTalon.getCouleur() ||
               (carteTalon instanceof CarteAction && ((CarteAction) carteTalon).getTypeAction() == this.typeAction);
    }

    @Override
    public TypeAction getTypeAction() {
        return typeAction;
    }

    @Override
    public String toString() {
        return couleur + " " + typeAction;
    }
}