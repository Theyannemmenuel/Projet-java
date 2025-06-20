package moteur;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Pioche {
    private List<Carte> cartes;

    public Pioche() {
        cartes = new ArrayList<>();
        initialiserCartes();
        melanger();
    }

    // Initialisation des cartes dans la pioche
    private void initialiserCartes() {
        // Exemple simple : ajouter des cartes avec différents types
        for (int i = 0; i < 10; i++) {
            cartes.add(new Carte(Carte.PASSER));
            cartes.add(new Carte(Carte.INVERSION));
            cartes.add(new Carte(Carte.PLUS_DEUX));
        }
        // Ajout des cartes spéciales
        cartes.add(new Carte(Carte.PLUS_QUATRE));
        cartes.add(new Carte(Carte.JOKER));
    }

    // Mélanger la pioche
    public void melanger() {
        Collections.shuffle(cartes);
    }

    // Piocher une carte
    public Carte piocherCarte() {
        if (!cartes.isEmpty()) {
            return cartes.remove(cartes.size() - 1);
        }
        return null; // ou lever une exception si nécessaire
    }

    // Vérifier si la pioche est vide
    public boolean estVide() {
        return cartes.isEmpty();
    }

    // Nombre de cartes restantes dans la pioche
    public int taille() {
        return cartes.size();
    }
}