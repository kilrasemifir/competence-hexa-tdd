package fr.kira.formation.spring.hexagonal.competences.usecases;

import fr.kira.formation.spring.hexagonal.competences.models.Competence;

import java.util.List;

public interface CompetenceCRUD {

    /**
     * Retourne la liste de l'ensemble des compétences
     * @return liste des compétences
     */
    List<Competence> findAll();

    Competence findById(String id);
}
