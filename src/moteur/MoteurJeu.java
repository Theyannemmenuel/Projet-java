package moteur;

import cartes.Carte;
import jeu.Jeu;
import joueur.Joueur;

import java.util.List;

/**
 * Classe principale qui fait tourner la partie UNO.
 * Gère la logique de chaque tour, effets spéciaux, et la fin de partie.
 */
public class MoteurJeu {
    private final Jeu jeu;
    private final GestionTour gestionTour;

    public MoteurJeu(List<Joueur> joueurs) {
        this.jeu = new Jeu(joueurs);
        this.gestionTour = new GestionTour(joueurs.size());
    }

    /**
     * Lance la partie et fait tourner les tours jusqu’à ce qu’un joueur gagne ou que les cartes soient épuisées.
     */
    public void demarrerPartie() {
        boolean partieTerminee = false;

        while (!partieTerminee) {
            Joueur joueur = jeu.getJoueur(gestionTour.getIndexActuel());
            Carte carteTalon = jeu.getTalon().getDerniereCarte();

            System.out.println("\nTalon : " + carteTalon);
            System.out.println("Tour de " + joueur.getNom());

            Carte carteJouee = joueur.jouerCarte(carteTalon);

            if (carteJouee != null) {
                jeu.getTalon().ajouterCarte(carteJouee);
                System.out.println(joueur.getNom() + " joue : " + carteJouee);
                appliquerEffet(carteJouee);

                if (joueur.aGagne()) {
                    System.out.println(joueur.getNom() + " a gagné !");
                    partieTerminee = true;
                    continue;
                } else if (joueur.getMain().size() == 1) {
                    System.out.println(joueur.getNom() + " dit UNO !");
                }
            } else {
                try {
                    Carte piochee = jeu.getPioche().piocher(jeu.getTalon());
                    System.out.println(joueur.getNom() + " pioche une carte.");

                    if (piochee.estJouable(carteTalon)) {
                        System.out.println("Elle est jouable et posée !");
                        jeu.getTalon().ajouterCarte(piochee);
                        appliquerEffet(piochee);
                    } else {
                        joueur.ajouterCarte(piochee);
                    }

                } catch (IllegalStateException e) {
                    System.out.println("Plus de cartes à piocher ou recycler !");
                    System.out.println("Fin du jeu : toutes les cartes ont été épuisées.");
                    partieTerminee = true;
                    continue;
                }
            }

            gestionTour.tourSuivant();
        }
    }

    /**
     * Applique les effets spéciaux d’une carte : +2, +4, passer, inversion, etc.
     */
    private void appliquerEffet(Carte carte) {
        switch (carte.getTypeAction()) {
            case PASSER -> {
                System.out.println("Le prochain joueur passe son tour.");
                gestionTour.tourSuivant();
            }
            case INVERSION -> {
                System.out.println("Le sens du jeu est inversé !");
                gestionTour.inverserSens();
            }
            case PLUS_DEUX -> {
                System.out.println("Le prochain joueur pioche 2 cartes !");
                Joueur suivant = jeu.getJoueur(gestionTour.getProchainIndex());
                for (int i = 0; i < 2; i++) {
                    try {
                        suivant.ajouterCarte(jeu.getPioche().piocher(jeu.getTalon()));
                    } catch (IllegalStateException e) {
                        System.out.println("Pas assez de cartes pour compléter le +2.");
                        break;
                    }
                }
                gestionTour.tourSuivant();
            }
            case PLUS_QUATRE -> {
                System.out.println("Le prochain joueur pioche 4 cartes !");
                Joueur suivant = jeu.getJoueur(gestionTour.getProchainIndex());
                for (int i = 0; i < 4; i++) {
                    try {
                        suivant.ajouterCarte(jeu.getPioche().piocher(jeu.getTalon()));
                    } catch (IllegalStateException e) {
                        System.out.println("Pas assez de cartes pour compléter le +4.");
                        break;
                    }
                }
                gestionTour.tourSuivant();
            }
            case JOKER -> {
                System.out.println("Carte joker jouée. Couleur à choisir (fonctionnalité à ajouter).");
                // ⚠️ À compléter si vous voulez que le joueur choisisse une couleur plus tard
            }
            default -> {
                // Aucune action spéciale (carte normale)
            }
        }
    }
}
