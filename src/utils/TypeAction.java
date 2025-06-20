package utils;

// Enumération des types d’action possibles pour les cartes spéciales
public enum TypeAction {
    PLUS_DEUX,   // +2 : le joueur suivant pioche deux cartes
    INVERSION,   // Inversion du sens
    PASSER,      // Le joueur suivant passe son tour
    JOKER,       // Joker simple (changement de couleur)
    PLUS_QUATRE  // +4 : pioche 4 et changement de couleur
}
