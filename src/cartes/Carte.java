package cartes;

import utils.Couleur;
import utils.TypeAction;

public abstract class Carte {
    protected Couleur couleur;

    public Carte(Couleur couleur) {
        this.couleur = couleur;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public abstract boolean estJouable(Carte carteTalon);

    public abstract TypeAction getTypeAction();

    @Override
    public abstract String toString();
}