package fr.kira.formation.spring.hexagonal.competences.configs;

import fr.kira.formation.spring.hexagonal.competences.usecases.GestionNiveauCompetenceService;
import fr.kira.formation.spring.hexagonal.competences.usecases.PersonneConnaissantCompetenceService;
import fr.kira.formation.spring.hexagonal.competences.usecases.ValidationParLesPairsService;
import fr.kira.formation.spring.hexagonal.competences.usecases.ValidationParLesTestsService;
import fr.kira.formation.spring.hexagonal.competences.usecases.ports.entree.GestionNiveauCompetence;
import fr.kira.formation.spring.hexagonal.competences.usecases.ports.entree.PersonneConnaissantCompetence;
import fr.kira.formation.spring.hexagonal.competences.usecases.ports.entree.ValidationParLesPairs;
import fr.kira.formation.spring.hexagonal.competences.usecases.ports.entree.ValidationParLesTests;
import fr.kira.formation.spring.hexagonal.competences.usecases.ports.sortie.CompetenceCRUD;
import fr.kira.formation.spring.hexagonal.competences.usecases.ports.sortie.PersonneCRUD;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CompetenceConfiguration {

    @Bean
    public GestionNiveauCompetence gestionNiveauCompetence(CompetenceCRUD competenceCRUD, PersonneCRUD personneCRUD) {
        return new GestionNiveauCompetenceService(
                competenceCRUD,
                personneCRUD
        );
    }

    @Bean
    public PersonneConnaissantCompetence personneConnaissantCompetence(CompetenceCRUD competenceCRUD, PersonneCRUD personneCRUD) {
        return new PersonneConnaissantCompetenceService(
                personneCRUD,
                competenceCRUD
        );
    }

    @Bean
    public ValidationParLesPairs validationParLesPairs(CompetenceCRUD competenceCRUD, PersonneCRUD personneCRUD) {
        return new ValidationParLesPairsService(
                personneCRUD,
                competenceCRUD
        );
    }

    @Bean
    public ValidationParLesTests validationParLesTests(CompetenceCRUD competenceCRUD, PersonneCRUD personneCRUD) {
        return new ValidationParLesTestsService(
                personneCRUD,
                competenceCRUD
        );
    }
}
