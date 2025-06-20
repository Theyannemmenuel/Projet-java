package moteur;

import cartes.Carte;
import jeu.Jeu;
import joueur.Joueur;
import utils.TypeAction;

import java.util.List;

public class MoteurJeu {
    private final Jeu jeu;
    private final GestionTour gestionTour;

    public MoteurJeu(List<Joueur> joueurs) {
        this.jeu = new Jeu(joueurs);
        this.gestionTour = new GestionTour(joueurs.size());
    }

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
                if (joueur.getMain().isEmpty()) {
                    System.out.println(joueur.getNom() + " a gagné !");
                    partieTerminee = true;
                    continue;
                } else if (joueur.getMain().size() == 1) {
                    System.out.println(joueur.getNom() + " dit UNO !");
                }
            } else {
                Carte piochee = jeu.getPioche().piocher();
                System.out.println(joueur.getNom() + " pioche une carte.");
                if (piochee.estJouable(carteTalon)) {
                    System.out.println("Elle est jouable et posée !");
                    jeu.getTalon().ajouterCarte(piochee);
                    appliquerEffet(piochee);
                } else {
                    joueur.ajouterCarte(piochee);
                }
            }

            gestionTour.tourSuivant();
        }
    }

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
                suivant.ajouterCarte(jeu.getPioche().piocher());
                suivant.ajouterCarte(jeu.getPioche().piocher());
                gestionTour.tourSuivant(); // Il passe son tour
            }
            case PLUS_QUATRE -> {
                System.out.println("Le prochain joueur pioche 4 cartes !");
                Joueur suivant = jeu.getJoueur(gestionTour.getProchainIndex());
                for (int i = 0; i < 4; i++) {
                    suivant.ajouterCarte(jeu.getPioche().piocher());
                }
                gestionTour.tourSuivant(); // Il passe son tour
            }
            case JOKER -> {
                System.out.println("Carte joker jouée. Couleur à choisir par le joueur.");
                // À compléter si choix de couleur par joueur
            }
            default -> {}
        }
    }
}
