package moteur;

public class GestionTour {
    private int indexActuel;
    private int sens = 1; // 1 = horaire, -1 = antihoraire
    private final int nbJoueurs;

    public GestionTour(int nbJoueurs) {
        this.nbJoueurs = nbJoueurs;
        this.indexActuel = 0;
    }

    public void tourSuivant() {
        indexActuel = (indexActuel + sens + nbJoueurs) % nbJoueurs;
    }

    public void inverserSens() {
        sens *= -1;
    }

    public int getIndexActuel() {
        return indexActuel;
    }

    public int getProchainIndex() {
        return (indexActuel + sens + nbJoueurs) % nbJoueurs;
    }
}
