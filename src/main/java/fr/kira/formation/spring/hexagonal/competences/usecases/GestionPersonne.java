package fr.kira.formation.spring.hexagonal.competences.usecases;

import fr.kira.formation.spring.hexagonal.competences.models.Personne;

public interface GestionPersonne {
    Personne creationPersonne(String nom, String prenom);
}
