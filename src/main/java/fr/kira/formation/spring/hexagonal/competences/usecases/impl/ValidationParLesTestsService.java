package fr.kira.formation.spring.hexagonal.competences.usecases.impl;

import fr.kira.formation.spring.hexagonal.competences.models.Competence;
import fr.kira.formation.spring.hexagonal.competences.models.NiveauCompetence;
import fr.kira.formation.spring.hexagonal.competences.models.Personne;
import fr.kira.formation.spring.hexagonal.competences.models.Validation;
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
    public void validerCompetence(String idPersonne, String idCompetence, String idValidateur) {
        Personne personne = this.personneCRUD.findById(idPersonne);
        Personne validateur = this.personneCRUD.findById(idValidateur);
        if (!validateur.isManager()) {
            throw new RuntimeException("Le valideur n'est pas un manager.");
        }
        Competence competence = this.competenceCRUD.findById(idCompetence);
        Optional<NiveauCompetence> niveau = personne.findNiveauCompetence(idCompetence);
        if (niveau.isPresent()){
            NiveauCompetence niveauCompetence = niveau.get();
            if (niveauCompetence.getNiveau() < 5) {
                niveauCompetence.setNiveau(5);
                niveauCompetence.getValidations().add(new Validation(validateur, idCompetence, 5));
            }
        } else {
            NiveauCompetence niveauCompetence = new NiveauCompetence(competence, 5);
            personne.getNiveauCompetences().add(niveauCompetence);
            niveauCompetence.getValidations().add(new Validation(validateur, idCompetence, 5));
        }
        this.personneCRUD.sauvegarder(personne);
    }
}
