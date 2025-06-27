import joueur.Joueur;
import moteur.MoteurJeu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nombre de joueurs ?");
        int nbJoueurs = scanner.nextInt();
        scanner.nextLine(); // consomme la ligne

        List<Joueur> joueurs = new ArrayList<>();
        for (int i = 1; i <= nbJoueurs; i++) {
            System.out.print("Nom du joueur " + i + " : ");
            String nom = scanner.nextLine();
            joueurs.add(new Joueur(nom));
        }

        // On utilise ici le constructeur avec seulement la liste des joueurs
        MoteurJeu moteur = new MoteurJeu(joueurs);
        moteur.lancerPartie();

        scanner.close();
    }
}