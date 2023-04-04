package fr.kira.formation.spring.hexagonal.competences.usecases;

public interface ValidationParLesTests {
    /**
     * Valide une compétence grâce aux tests.
     * @param idPersonne id de la personne qui a passé le test.
     * @param idCompetence id de la compétence validée.
     */
    void validerCompetence(String idPersonne, String idCompetence);
}
