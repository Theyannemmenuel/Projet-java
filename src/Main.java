import joueur.Joueur;
import moteur.MoteurJeu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Joueur> joueurs = new ArrayList<>();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Nombre de joueurs : ");
            int nb = scanner.nextInt();
            scanner.nextLine(); // Flush

            for (int i = 1; i <= nb; i++) {
                System.out.print("Nom du joueur " + i + " : ");
                String nom = scanner.nextLine();
                joueurs.add(new Joueur(nom));
            }
        }

        MoteurJeu moteur = new MoteurJeu(joueurs);
        moteur.demarrerPartie();
    }
}
