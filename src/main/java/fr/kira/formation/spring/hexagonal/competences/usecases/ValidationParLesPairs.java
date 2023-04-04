package fr.kira.formation.spring.hexagonal.competences.usecases;

public interface ValidationParLesPairs {

    /**
     * Valide une compétence grâce aux pairs.
     * @param idPersonneCible id de la personne cible de la validation.
     * @param idPersonneValideur id de la personne qui valide.
     * @param idCompetence id de la compétence validée.
     * @param niveau niveau de la compétence validée.
     */
    void validerCompetence(String idPersonneCible, String idPersonneValideur,  String idCompetence, int niveau);
}
