package cartes;

import utils.Couleur;
import utils.TypeAction;

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
    public boolean estJouable(Carte carteTalon) {
        return this.couleur == carteTalon.getCouleur() ||
               (carteTalon instanceof CarteNumero && ((CarteNumero) carteTalon).getNumero() == this.numero);
    }

    @Override
    public TypeAction getTypeAction() {
        return TypeAction.AUCUNE;
    }

    @Override
    public String toString() {
        return couleur + " " + numero;
    }
}