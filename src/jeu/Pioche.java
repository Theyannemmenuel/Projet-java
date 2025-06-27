package jeu;

import cartes.*;
import utils.Couleur;
import utils.TypeAction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe qui représente la pioche (tas de cartes à piocher).
 * Elle contient toutes les cartes qui ne sont pas dans les mains des joueurs ou dans le talon.
 */
public class Pioche {
    private List<Carte> cartes;  // Liste des cartes à piocher

    public Pioche() {
        this.cartes = new ArrayList<>();
        initialiserCartes();  // Remplit la pioche avec toutes les cartes UNO
        melanger();           // Mélange les cartes
    }

    /**
     * Ajoute toutes les cartes UNO à la pioche (numériques, actions, jokers).
     */
    private void initialiserCartes() {
        Couleur[] couleurs = {Couleur.ROUGE, Couleur.JAUNE, Couleur.VERT, Couleur.BLEU};

        for (Couleur couleur : couleurs) {
            // Cartes numériques : 0 à 9 (2 de chaque sauf 0)
            for (int i = 0; i <= 9; i++) {
                cartes.add(new CarteNumero(couleur, i));
                if (i != 0) cartes.add(new CarteNumero(couleur, i));
            }
            // Cartes Action : 2 de chaque type par couleur
            cartes.add(new CarteAction(couleur, TypeAction.PASSER));
            cartes.add(new CarteAction(couleur, TypeAction.PASSER));
            cartes.add(new CarteAction(couleur, TypeAction.INVERSION));
            cartes.add(new CarteAction(couleur, TypeAction.INVERSION));
            cartes.add(new CarteAction(couleur, TypeAction.PLUS_DEUX));
            cartes.add(new CarteAction(couleur, TypeAction.PLUS_DEUX));
        }

        // Jokers (sans couleur)
        for (int i = 0; i < 4; i++) {
            cartes.add(new CarteJoker(TypeAction.JOKER));
            cartes.add(new CarteJoker(TypeAction.PLUS_QUATRE));
        }
    }

    /**
     * Mélange aléatoirement les cartes de la pioche.
     */
    public void melanger() {
        Collections.shuffle(cartes);
    }

    /**
     * Permet à un joueur de piocher une carte. Si la pioche est vide,
     * on tente de la reconstituer depuis le talon.
     */
    public Carte piocher(Talon talon) {
        if (cartes.isEmpty()) {
            List<Carte> recharge = talon.recupererCartesPourRecycler();
            if (!recharge.isEmpty()) {
                cartes.addAll(recharge);
                melanger();
            } else {
                throw new IllegalStateException("La pioche et le talon sont vides !");
            }
        }
        return cartes.remove(0);
    }

    /**
     * Vérifie si la pioche est vide.
     */
    public boolean estVide() {
        return cartes.isEmpty();
    }
}