package fr.kira.formation.spring.hexagonal.competences.usecases.ports.entree;

import fr.kira.formation.spring.hexagonal.competences.models.Personne;

public interface ValidationParLesTests {
    /**
     * Valide une compétence grâce aux tests.
     * @param idPersonne id de la personne qui a passé le test.
     * @param idCompetence id de la compétence validée.
     * @param idValidateur personne qui valide la compétence.
     */
    void validerCompetence(String idPersonne, String idCompetence, String idValidateur);
}
