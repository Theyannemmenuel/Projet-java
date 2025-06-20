package cartes;

import utils.Couleur;

public abstract class Carte {
    protected Couleur couleur;

    public Carte(Couleur couleur) {
        this.couleur = couleur;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public abstract boolean estJouable(Carte carteDessus);

    public abstract String toString();
}