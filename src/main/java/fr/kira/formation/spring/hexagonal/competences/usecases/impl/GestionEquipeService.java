package fr.kira.formation.spring.hexagonal.competences.usecases.impl;

import fr.kira.formation.spring.hexagonal.competences.models.Equipe;
import fr.kira.formation.spring.hexagonal.competences.models.Personne;
import fr.kira.formation.spring.hexagonal.competences.ports.EquipeCRUD;
import fr.kira.formation.spring.hexagonal.competences.ports.PersonneCRUD;
import fr.kira.formation.spring.hexagonal.competences.usecases.GestionEquipe;

public class GestionEquipeService implements GestionEquipe {

    private final EquipeCRUD equipeCRUD;
    private final PersonneCRUD personneCRUD;

    public GestionEquipeService(EquipeCRUD equipeCRUD, PersonneCRUD personneCRUD) {
        this.equipeCRUD = equipeCRUD;
        this.personneCRUD = personneCRUD;
    }
    @Override
    public Equipe creationEquipe(String nomEquipe) {
        return this.equipeCRUD.sauvegarder(new Equipe(nomEquipe));
    }

    @Override
    public void ajouterPersonneAEquipe(String idPersonne, String idEquipe) {
        Personne membre = this.personneCRUD.findById(idPersonne);
        Equipe equipe = this.equipeCRUD.findById(idEquipe);
        equipe.getMembres().add(membre);
        this.equipeCRUD.sauvegarder(equipe);
    }
}
