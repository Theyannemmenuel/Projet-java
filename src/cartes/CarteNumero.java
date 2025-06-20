package cartes;

import utils.Couleur;

public class CarteNumero extends Carte {
    private int numero;

    public CarteNumero(Couleur couleur, int numero) {
        super(couleur);
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    @Override
    public boolean estJouable(Carte carteDessus) {
        return this.couleur == carteDessus.getCouleur() ||
               (carteDessus instanceof CarteNumero && this.numero == ((CarteNumero) carteDessus).getNumero());
    }

    @Override
    public String toString() {
        return couleur + " " + numero;
    }
}