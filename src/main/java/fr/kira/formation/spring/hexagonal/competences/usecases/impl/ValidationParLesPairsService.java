package fr.kira.formation.spring.hexagonal.competences.usecases.impl;

import fr.kira.formation.spring.hexagonal.competences.models.Competence;
import fr.kira.formation.spring.hexagonal.competences.models.NiveauCompetence;
import fr.kira.formation.spring.hexagonal.competences.models.Personne;
import fr.kira.formation.spring.hexagonal.competences.models.Validation;
import fr.kira.formation.spring.hexagonal.competences.ports.CompetenceCRUD;
import fr.kira.formation.spring.hexagonal.competences.ports.PersonneCRUD;
import fr.kira.formation.spring.hexagonal.competences.usecases.ValidationParLesPairs;

import java.util.Optional;

public class ValidationParLesPairsService implements ValidationParLesPairs {

    private final PersonneCRUD personneCRUD;
    private final CompetenceCRUD competenceCRUD;

    public ValidationParLesPairsService(PersonneCRUD personneCRUD, CompetenceCRUD competenceCRUD) {
        this.personneCRUD = personneCRUD;
        this.competenceCRUD = competenceCRUD;
    }
    @Override
    public void validerCompetence(String idPersonneCible, String idPersonneValideur, String idCompetence, int niveau) {
        Personne cible = personneCRUD.findById(idPersonneCible);
        Personne validateur = personneCRUD.findById(idPersonneValideur);
        Competence competence = competenceCRUD.findById(idCompetence);
        verificationValideur(idCompetence, niveau, validateur);
        validationCible(idCompetence, niveau, cible, competence, validateur);
        personneCRUD.sauvegarder(cible);
    }

    private static void validationCible(String idCompetence, int niveau, Personne cible, Competence competence, Personne validateur) {
        Optional<NiveauCompetence> optionalNiveau = cible.findNiveauCompetence(idCompetence);
        if (optionalNiveau.isPresent()) {
            NiveauCompetence niveauComp = optionalNiveau.get();
            if (niveauComp.getNiveau() < niveau) {
                niveauComp.setNiveau(niveau);
                niveauComp.getValidations().add(new Validation(validateur, idCompetence, niveau));
            }
        } else {
            NiveauCompetence niveauComp = new NiveauCompetence(competence, niveau);
            cible.getNiveauCompetences().add(niveauComp);
            niveauComp.getValidations().add(new Validation(validateur, idCompetence, niveau));

        }
    }

    private static void verificationValideur(String idCompetence, int niveau, Personne validateur) {
        if (validateur.findNiveauCompetence(idCompetence).isPresent()) {
            NiveauCompetence niveauComp = validateur.findNiveauCompetence(idCompetence).get();
            if (niveauComp.getNiveau() < niveau) {
                throw new RuntimeException("Le valideur n'a pas le niveau requis pour valider cette compétence.");
            }
        } else {
            throw new RuntimeException("Le valideur n'a pas le niveau requis pour valider cette compétence.");
        }
    }
}
