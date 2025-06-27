package moteur;

public class GestionTour {
    private int indexActuel;
    private int sens;
    private final int nbJoueurs;

    public GestionTour(int nbJoueurs) {
        this.nbJoueurs = nbJoueurs;
        this.indexActuel = 0;
        this.sens = 1; // 1 = sens horaire, -1 = sens antihoraire
    }

    public int getIndexActuel() {
        return indexActuel;
    }

    public int getProchainIndex() {
        return (indexActuel + sens + nbJoueurs) % nbJoueurs;
    }

    public void tourSuivant() {
        indexActuel = (indexActuel + sens + nbJoueurs) % nbJoueurs;
    }

    public void inverserSens() {
        sens *= -1;
    }
}