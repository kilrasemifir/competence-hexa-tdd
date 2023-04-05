package fr.kira.formation.spring.hexagonal.competences.usecases;

import fr.kira.formation.spring.hexagonal.competences.models.Competence;
import fr.kira.formation.spring.hexagonal.competences.models.NiveauCompetence;
import fr.kira.formation.spring.hexagonal.competences.models.Personne;
import fr.kira.formation.spring.hexagonal.competences.usecases.ports.sortie.CompetenceCRUD;
import fr.kira.formation.spring.hexagonal.competences.usecases.ports.sortie.PersonneCRUD;
import fr.kira.formation.spring.hexagonal.competences.usecases.ports.entree.PersonneConnaissantCompetence;

import java.util.ArrayList;
import java.util.List;

public class PersonneConnaissantCompetenceService implements PersonneConnaissantCompetence {

    private final PersonneCRUD personneCRUD;
    private final CompetenceCRUD competenceCRUD;

    public PersonneConnaissantCompetenceService(PersonneCRUD personneCRUD, CompetenceCRUD competenceCRUD) {
        this.personneCRUD = personneCRUD;
        this.competenceCRUD = competenceCRUD;
    }

    @Override
    public List<Personne> findPersonnesConnaissantCompetence(String competenceId) {
        Competence competence = competenceCRUD.findById(competenceId);
        List<Personne> personnes =  personneCRUD.findAll();
        List<Personne> results = new ArrayList<>();
        for (Personne personne: personnes){
            for (NiveauCompetence niveauCompetence: personne.getNiveauCompetences()){
                String idPersonneCompetence = niveauCompetence.getCompetence().getId();
                if (idPersonneCompetence.equals(competenceId) && niveauCompetence.getNiveau() >=5){
                    results.add(personne);
                    break;
                }
            }
        }
        return results;

    }
}
