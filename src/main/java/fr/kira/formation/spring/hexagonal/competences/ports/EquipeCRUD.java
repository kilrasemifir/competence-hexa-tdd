package fr.kira.formation.spring.hexagonal.competences.ports;

import fr.kira.formation.spring.hexagonal.competences.models.Equipe;

import java.util.List;

public interface EquipeCRUD {
    Equipe create(Equipe equipe);
}
