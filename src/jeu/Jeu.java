package moteur;

import java.util.List;
import java.util.ArrayList;

public class Jeu {
    private List<Joueur> joueurs;
    private Talon talon;
    private Pioche pioche;

    // Constructeur prenant une liste de joueurs
    public Jeu(List<Joueur> joueurs) {
        this.joueurs = joueurs;
        this.talon = new Talon();
        this.pioche = new Pioche();
    }

    public Joueur getJoueur(int index) {
        if(index >= 0 && index < joueurs.size()) {
            return joueurs.get(index);
        }
        return null;
    }

    public Talon getTalon() {
        return talon;
    }

    public Pioche getPioche() {
        return pioche;
    }
}