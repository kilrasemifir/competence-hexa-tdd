package fr.kira.formation.spring.hexagonal.competences.usecases.impl;

import fr.kira.formation.spring.hexagonal.competences.models.Competence;
import fr.kira.formation.spring.hexagonal.competences.models.NiveauCompetence;
import fr.kira.formation.spring.hexagonal.competences.models.Personne;
import fr.kira.formation.spring.hexagonal.competences.models.Prerequis;
import fr.kira.formation.spring.hexagonal.competences.ports.CompetenceCRUD;
import fr.kira.formation.spring.hexagonal.competences.usecases.GestionNiveauCompetence;
import fr.kira.formation.spring.hexagonal.competences.ports.PersonneCRUD;

import java.util.ArrayList;
import java.util.List;

public class GestionNiveauCompetenceService implements GestionNiveauCompetence {

    private final CompetenceCRUD competenceCRUD;
    private final PersonneCRUD personneCRUD;

    public GestionNiveauCompetenceService(CompetenceCRUD competenceCRUD, PersonneCRUD personneCRUD) {
        this.competenceCRUD = competenceCRUD;
        this.personneCRUD = personneCRUD;
    }

    @Override
    public List<Competence> competenceAccessibles(String idPersonne) {
        List<Competence> competences = this.competenceCRUD.findAll();
        Personne personne = this.personneCRUD.findById(idPersonne);
        List<NiveauCompetence> niveaux = personne.getNiveauCompetences();
        List<Competence> result = constructionCompetencesAvecPrerequis(competences, niveaux);
        return result;

    }

    private static List<Competence> constructionCompetencesAvecPrerequis(List<Competence> competences, List<NiveauCompetence> niveaux) {
        List<Competence> result = new ArrayList<>();
        for(Competence comp: competences){
            if (comp.getPrerequis().size() == 0){
                result.add(comp);
            }
            else {
                verificationDesPrerequis(niveaux, result, comp);
            }

        }
        return result;
    }

    private static void verificationDesPrerequis(List<NiveauCompetence> niveaux, List<Competence> result, Competence comp) {
        boolean isAccessible = false;
        for (Prerequis pre : comp.getPrerequis()){
            for (NiveauCompetence niveau: niveaux){
                if (pre.getCompetenceRequis().equals(niveau.getCompetence())){
                    if (pre.getNiveauRequis() <= niveau.getNiveau()){
                        isAccessible = true;
                    }
                }
            }
        }
        if (isAccessible){
            result.add(comp);
        }
    }
}
