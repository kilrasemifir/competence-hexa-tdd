package fr.kira.formation.spring.hexagonal.competences.ports;

import fr.kira.formation.spring.hexagonal.competences.models.Equipe;

public interface EquipeCRUD {
    Equipe sauvegarder(Equipe equipe);
    Equipe findById(String id);
}
