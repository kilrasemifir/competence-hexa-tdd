package fr.kira.formation.spring.hexagonal.competences.usecases;

import fr.kira.formation.spring.hexagonal.competences.models.Competence;

import java.util.List;

public interface GestionNiveauCompetence {

    /**
     * Reourne la liste des compétences ou la personne portant idPersonne possède l'ensemble des prérequis.
     * @param idPersonne de la personne cible.
     * @return la liste des compétences.
     */
    List<Competence> competenceAccessibles(String idPersonne);
}
