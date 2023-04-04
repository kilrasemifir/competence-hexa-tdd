package fr.kira.formation.spring.hexagonal.competences.usecases;

import fr.kira.formation.spring.hexagonal.competences.models.Personne;

import java.util.List;

public interface PersonneCRUD {
    Personne findById(String id);
    List<Personne> findAll();
}
