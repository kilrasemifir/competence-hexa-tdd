package fr.kira.formation.spring.hexagonal.competences.usecases.impl;

import fr.kira.formation.spring.hexagonal.competences.models.Equipe;
import fr.kira.formation.spring.hexagonal.competences.ports.EquipeCRUD;
import fr.kira.formation.spring.hexagonal.competences.usecases.GestionEquipe;

public class GestionEquipeService implements GestionEquipe {

    private final EquipeCRUD equipeCRUD;

    public GestionEquipeService(EquipeCRUD equipeCRUD) {
        this.equipeCRUD = equipeCRUD;
    }
    @Override
    public Equipe creationEquipe(String nomEquipe) {
        return this.equipeCRUD.create(new Equipe(nomEquipe));
    }
}
