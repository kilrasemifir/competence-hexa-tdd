package fr.kira.formation.spring.hexagonal.competences.usecases.ports.sortie;

import fr.kira.formation.spring.hexagonal.competences.models.Equipe;

public interface EquipeCRUD {
    Equipe sauvegarder(Equipe equipe);
    Equipe findById(String id);
}
