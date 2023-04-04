package fr.kira.formation.spring.hexagonal.competences.usecases.impl;

import fr.kira.formation.spring.hexagonal.competences.models.Competence;
import fr.kira.formation.spring.hexagonal.competences.models.NiveauCompetence;
import fr.kira.formation.spring.hexagonal.competences.models.Personne;
import fr.kira.formation.spring.hexagonal.competences.ports.CompetenceCRUD;
import fr.kira.formation.spring.hexagonal.competences.ports.PersonneCRUD;
import fr.kira.formation.spring.hexagonal.competences.usecases.ValidationParLesTests;

import java.util.Optional;

public class ValidationParLesTestsService implements ValidationParLesTests {

    private final PersonneCRUD personneCRUD;
    private final CompetenceCRUD competenceCRUD;

    public ValidationParLesTestsService(PersonneCRUD personneCRUD, CompetenceCRUD competenceCRUD) {
        this.personneCRUD = personneCRUD;
        this.competenceCRUD = competenceCRUD;
    }

    @Override
    public void validerCompetence(String idPersonne, String idCompetence) {
        Personne personne = this.personneCRUD.findById(idPersonne);
        Competence competence = this.competenceCRUD.findById(idCompetence);
        Optional<NiveauCompetence> niveau = personne.getNiveauCompetences()
                .stream()
                .filter(niveauCompetence -> niveauCompetence.getCompetence().getId().equals(idCompetence))
                .findFirst();
        if (niveau.isPresent()){
            if (niveau.get().getNiveau() < 5) {
                niveau.get().setNiveau(5);
            }
        } else {
            personne.getNiveauCompetences().add(new NiveauCompetence(competence, 5));
        }
        this.personneCRUD.save(personne);
    }
}
