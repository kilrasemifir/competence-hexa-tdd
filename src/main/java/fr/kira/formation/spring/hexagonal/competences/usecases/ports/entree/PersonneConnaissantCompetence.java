package fr.kira.formation.spring.hexagonal.competences.usecases.ports.entree;

import fr.kira.formation.spring.hexagonal.competences.models.Personne;

import java.util.List;

public interface PersonneConnaissantCompetence {

    /**
     * Retourne la liste des personnes connaissant la compétence. C'est-à-dire qui ont un niveau de compétence dans
     * ladite compétence supérieur à 5.
     * @param competenceId id de la compétence
     * @return liste des personnes connaissant la compétence
     */
    List<Personne> findPersonnesConnaissantCompetence(String competenceId);
}
