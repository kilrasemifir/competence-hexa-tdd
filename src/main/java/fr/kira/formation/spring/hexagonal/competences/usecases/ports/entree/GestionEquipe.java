package fr.kira.formation.spring.hexagonal.competences.usecases.ports.entree;

import fr.kira.formation.spring.hexagonal.competences.models.Equipe;

/**
 * Service de gestion des équipes.
 * C'est un port d'entrée de l'application.
 */
public interface GestionEquipe {
    Equipe creationEquipe(String nomEquipe);
    void ajouterPersonneAEquipe(String idPersonne, String idEquipe);

    void supprimerPersonneDeEquipe(String idPersonne, String idEquipe);
}
