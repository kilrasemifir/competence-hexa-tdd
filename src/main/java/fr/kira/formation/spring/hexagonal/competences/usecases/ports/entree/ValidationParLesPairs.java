package fr.kira.formation.spring.hexagonal.competences.usecases.ports.entree;

public interface ValidationParLesPairs {

    /**
     * Valide une compétence grâce aux pairs.
     * @param idPersonneCible id de la personne cible de la validation.
     * @param idPersonneValidateur id de la personne qui valide.
     * @param idCompetence id de la compétence validée.
     * @param niveau niveau de la compétence validée.
     */
    void validerCompetence(String idPersonneCible, String idPersonneValidateur,  String idCompetence, int niveau);
}
