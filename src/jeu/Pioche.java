package jeu;

import cartes.*;
import utils.Couleur;
import utils.TypeAction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pioche {
    private List<Carte> cartes;

    public Pioche() {
        this.cartes = new ArrayList<>();
        initialiserCartes();
        melanger();
    }

    private void initialiserCartes() {
        Couleur[] couleurs = {Couleur.ROUGE, Couleur.JAUNE, Couleur.VERT, Couleur.BLEU};

        for (Couleur couleur : couleurs) {
            for (int i = 0; i <= 9; i++) {
                cartes.add(new CarteNumero(couleur, i));
                if (i != 0) cartes.add(new CarteNumero(couleur, i)); // Doubles sauf 0
            }
            cartes.add(new CarteAction(couleur, TypeAction.PASSER));
            cartes.add(new CarteAction(couleur, TypeAction.PASSER));
            cartes.add(new CarteAction(couleur, TypeAction.INVERSION));
            cartes.add(new CarteAction(couleur, TypeAction.INVERSION));
            cartes.add(new CarteAction(couleur, TypeAction.PLUS_DEUX));
            cartes.add(new CarteAction(couleur, TypeAction.PLUS_DEUX));
        }

        for (int i = 0; i < 4; i++) {
            cartes.add(new CarteJoker(TypeAction.JOKER));
            cartes.add(new CarteJoker(TypeAction.PLUS_QUATRE));
        }
    }

    public void melanger() {
        Collections.shuffle(cartes);
    }

    public Carte piocher() {
        if (cartes.isEmpty()) {
            throw new IllegalStateException("La pioche est vide !");
        }
        return cartes.remove(0);
    }

    public boolean estVide() {
        return cartes.isEmpty();
    }
}
