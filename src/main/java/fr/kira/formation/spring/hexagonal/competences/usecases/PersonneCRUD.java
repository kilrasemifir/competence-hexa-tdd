package fr.kira.formation.spring.hexagonal.competences.usecases;

import fr.kira.formation.spring.hexagonal.competences.models.Personne;

public interface PersonneCRUD {
    Personne findById(String id);
}
