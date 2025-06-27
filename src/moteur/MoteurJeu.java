package moteur;

import cartes.Carte;
import cartes.CarteJoker;
import jeu.Jeu;
import joueur.Joueur;
import utils.Couleur;
import utils.TypeAction;

import java.util.List;
import java.util.Scanner;

/**
 * Classe principale qui fait tourner la partie UNO.
 * Gère la logique de chaque tour, effets spéciaux, et la fin de partie.
 */
public class MoteurJeu {
    private final Jeu jeu;
    private final GestionTour gestionTour;
    private final Scanner scanner;

    public MoteurJeu(List<Joueur> joueurs) {
        this.jeu = new Jeu(joueurs);
        this.gestionTour = new GestionTour(joueurs.size());
        this.scanner = new Scanner(System.in);
    }

    /**
     * Lance la partie et fait tourner les tours jusqu'à ce qu'un joueur gagne.
     */
    public void lancerPartie() {
        boolean partieTerminee = false;

        while (!partieTerminee) {
            Joueur joueur = jeu.getJoueur(gestionTour.getIndexActuel());
            Carte carteTalon = jeu.getTalon().getDerniereCarte();

            System.out.println("\n" + "=".repeat(50));
            System.out.println("Carte sur le talon : " + carteTalon);
            System.out.println("Tour de " + joueur.getNom() + " (" + joueur.getMain().size() + " cartes)");
            
            // Afficher les cartes du joueur
            afficherMainJoueur(joueur);
            
            // Demander au joueur ce qu'il veut faire
            boolean actionRealisee = false;
            while (!actionRealisee) {
                System.out.println("\nQue voulez-vous faire ?");
                System.out.println("1 - Jouer une carte");
                System.out.println("2 - Piocher une carte");
                System.out.print("Votre choix : ");
                
                try {
                    int choix = scanner.nextInt();
                    scanner.nextLine(); // consommer la ligne
                    
                    if (choix == 1) {
                        Carte carteJouee = choisirEtJouerCarte(joueur, carteTalon);
                        if (carteJouee != null) {
                            jeu.getTalon().ajouterCarte(carteJouee);
                            System.out.println(joueur.getNom() + " joue : " + carteJouee);
                            
                            // Gérer le choix de couleur pour les jokers
                            if (carteJouee instanceof CarteJoker) {
                                choisirCouleurJoker((CarteJoker) carteJouee);
                            }
                            
                            appliquerEffet(carteJouee);
                            actionRealisee = true;

                            if (joueur.aGagne()) {
                                System.out.println("\n🎉 " + joueur.getNom() + " a gagné ! 🎉");
                                partieTerminee = true;
                                continue;
                            } else if (joueur.getMain().size() == 1) {
                                System.out.println("🔥 " + joueur.getNom() + " dit UNO ! 🔥");
                            }
                        }
                    } else if (choix == 2) {
                        try {
                            Carte piochee = jeu.getPioche().piocher(jeu.getTalon());
                            joueur.ajouterCarte(piochee);
                            System.out.println(joueur.getNom() + " pioche une carte : " + piochee);
                            actionRealisee = true;
                        } catch (IllegalStateException e) {
                            System.out.println("❌ Plus de cartes à piocher !");
                        }
                    } else {
                        System.out.println("❌ Choix invalide ! Veuillez choisir 1 ou 2.");
                    }
                } catch (Exception e) {
                    System.out.println("❌ Entrée invalide ! Veuillez entrer un nombre.");
                    scanner.nextLine(); // vider le buffer
                }
            }

            if (!partieTerminee) {
                gestionTour.tourSuivant();
            }
        }
    }

    /**
     * Affiche toutes les cartes du joueur avec leurs indices.
     */
    private void afficherMainJoueur(Joueur joueur) {
        System.out.println("\nVos cartes :");
        List<Carte> main = joueur.getMain();
        for (int i = 0; i < main.size(); i++) {
            System.out.println((i + 1) + ". " + main.get(i));
        }
    }

    /**
     * Permet au joueur de choisir et jouer une carte parmi celles qu'il possède.
     */
    private Carte choisirEtJouerCarte(Joueur joueur, Carte carteTalon) {
        List<Carte> main = joueur.getMain();
        List<Carte> cartesJouables = main.stream()
            .filter(carte -> carte.estJouable(carteTalon))
            .toList();

        if (cartesJouables.isEmpty()) {
            System.out.println("❌ Aucune carte jouable ! Vous devez piocher.");
            return null;
        }

        System.out.println("\nCartes jouables :");
        for (int i = 0; i < cartesJouables.size(); i++) {
            int indexDansMain = main.indexOf(cartesJouables.get(i)) + 1;
            System.out.println(indexDansMain + ". " + cartesJouables.get(i));
        }

        while (true) {
            System.out.print("Choisissez le numéro de la carte à jouer : ");
            try {
                int choix = scanner.nextInt();
                scanner.nextLine(); // consommer la ligne
                
                if (choix >= 1 && choix <= main.size()) {
                    Carte carteChoisie = main.get(choix - 1);
                    if (carteChoisie.estJouable(carteTalon)) {
                        joueur.retirerCarte(carteChoisie);
                        return carteChoisie;
                    } else {
                        System.out.println("❌ Cette carte n'est pas jouable !");
                    }
                } else {
                    System.out.println("❌ Numéro invalide !");
                }
            } catch (Exception e) {
                System.out.println("❌ Entrée invalide ! Veuillez entrer un nombre.");
                scanner.nextLine(); // vider le buffer
            }
        }
    }

    /**
     * Permet au joueur de choisir une couleur pour un joker.
     */
    private void choisirCouleurJoker(CarteJoker joker) {
        System.out.println("\nChoisissez une couleur :");
        System.out.println("1 - ROUGE");
        System.out.println("2 - JAUNE");
        System.out.println("3 - VERT");
        System.out.println("4 - BLEU");
        
        while (true) {
            System.out.print("Votre choix : ");
            try {
                int choix = scanner.nextInt();
                scanner.nextLine(); // consommer la ligne
                
                Couleur couleurChoisie = switch (choix) {
                    case 1 -> Couleur.ROUGE;
                    case 2 -> Couleur.JAUNE;
                    case 3 -> Couleur.VERT;
                    case 4 -> Couleur.BLEU;
                    default -> null;
                };
                
                if (couleurChoisie != null) {
                    joker.setCouleur(couleurChoisie);
                    System.out.println("Couleur choisie : " + couleurChoisie);
                    break;
                } else {
                    System.out.println("❌ Choix invalide ! Choisissez entre 1 et 4.");
                }
            } catch (Exception e) {
                System.out.println("❌ Entrée invalide ! Veuillez entrer un nombre.");
                scanner.nextLine(); // vider le buffer
            }
        }
    }

    /**
     * Applique les effets spéciaux d'une carte : +2, +4, passer, inversion, etc.
     */
    private void appliquerEffet(Carte carte) {
        switch (carte.getTypeAction()) {
            case PASSER -> {
                System.out.println("⏭️ Le prochain joueur passe son tour.");
                gestionTour.tourSuivant();
            }
            case INVERSION -> {
                System.out.println("🔄 Le sens du jeu est inversé !");
                gestionTour.inverserSens();
            }
            case PLUS_DEUX -> {
                System.out.println("📚 Le prochain joueur pioche 2 cartes !");
                Joueur suivant = jeu.getJoueur(gestionTour.getProchainIndex());
                for (int i = 0; i < 2; i++) {
                    try {
                        suivant.ajouterCarte(jeu.getPioche().piocher(jeu.getTalon()));
                    } catch (IllegalStateException e) {
                        System.out.println("⚠️ Pas assez de cartes pour compléter le +2.");
                        break;
                    }
                }
                gestionTour.tourSuivant();
            }
            case PLUS_QUATRE -> {
                System.out.println("📚📚 Le prochain joueur pioche 4 cartes !");
                Joueur suivant = jeu.getJoueur(gestionTour.getProchainIndex());
                for (int i = 0; i < 4; i++) {
                    try {
                        suivant.ajouterCarte(jeu.getPioche().piocher(jeu.getTalon()));
                    } catch (IllegalStateException e) {
                        System.out.println("⚠️ Pas assez de cartes pour compléter le +4.");
                        break;
                    }
                }
                gestionTour.tourSuivant();
            }
            case JOKER -> {
                System.out.println("🃏 Carte joker jouée !");
            }
            default -> {
                // Aucune action spéciale (carte normale)
            }
        }
    }
}