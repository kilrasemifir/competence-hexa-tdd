package fr.kira.formation.spring.hexagonal.competences.usecases;

import fr.kira.formation.spring.hexagonal.competences.CompetenceData;
import fr.kira.formation.spring.hexagonal.competences.PersonneData;
import fr.kira.formation.spring.hexagonal.competences.models.Competence;
import fr.kira.formation.spring.hexagonal.competences.models.NiveauCompetence;
import fr.kira.formation.spring.hexagonal.competences.models.Personne;
import fr.kira.formation.spring.hexagonal.competences.ports.CompetenceCRUD;
import fr.kira.formation.spring.hexagonal.competences.ports.PersonneCRUD;
import fr.kira.formation.spring.hexagonal.competences.usecases.impl.ValidationParLesTestsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Validation par les tests")
class ValidationParLesTestsTest {

    PersonneCRUD personneCRUD;
    CompetenceCRUD competenceCRUD;
    ValidationParLesTests service;
    Personne personne = new Personne("","", new ArrayList<>());

    @BeforeEach
    void setUp(){
        personneCRUD = Mockito.mock(PersonneCRUD.class);
        Mockito.when(personneCRUD.findById("1")).thenReturn(personne);
        competenceCRUD = Mockito.mock(CompetenceCRUD.class);
        Mockito.when(competenceCRUD.findById("1")).thenReturn(CompetenceData.JAVA);
        service = new ValidationParLesTestsService(personneCRUD, competenceCRUD);
    }

    @Test
    @DisplayName("Si la personne ne possède pas encore le niveau de compétence, alors lui donne un niveau 5")
    void validationNiveau5(){
        Competence comp = CompetenceData.JAVA;
        service.validerCompetence("1", comp.getId());
        NiveauCompetence niveau = personne.findNiveauCompetence(comp.getId()).orElseThrow();
        assertEquals(5,niveau.getNiveau());
    }

    @Test
    @DisplayName("Si la personne possède déjà le niveau de compétence inferieur a 5, alors lui donne un niveau 5")
    void validationNiveau5Inferieur(){
        Competence comp = CompetenceData.JAVA;
        personne.getNiveauCompetences().add(new NiveauCompetence(comp, 4));
        service.validerCompetence("1", comp.getId());
        NiveauCompetence niveau = personne.findNiveauCompetence(comp.getId()).orElseThrow();
        assertEquals(5,niveau.getNiveau());
    }

    @Test
    @DisplayName("Si la personne possède déjà le niveau de compétence superieur a 5, alors ne rien faire")
    void validationNiveau5Superieur(){
        Competence comp = CompetenceData.JAVA;
        personne.getNiveauCompetences().add(new NiveauCompetence(comp, 6));
        service.validerCompetence("1", comp.getId());
        NiveauCompetence niveau = personne.findNiveauCompetence(comp.getId()).orElseThrow();
        assertEquals(6,niveau.getNiveau());
    }
}