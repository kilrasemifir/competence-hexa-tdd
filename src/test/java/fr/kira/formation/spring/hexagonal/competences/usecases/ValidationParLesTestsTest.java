package fr.kira.formation.spring.hexagonal.competences.usecases;

import fr.kira.formation.spring.hexagonal.competences.CompetenceData;
import fr.kira.formation.spring.hexagonal.competences.models.Competence;
import fr.kira.formation.spring.hexagonal.competences.models.NiveauCompetence;
import fr.kira.formation.spring.hexagonal.competences.models.Personne;
import fr.kira.formation.spring.hexagonal.competences.models.Validation;
import fr.kira.formation.spring.hexagonal.competences.usecases.ports.entree.ValidationParLesTests;
import fr.kira.formation.spring.hexagonal.competences.usecases.ports.sortie.CompetenceCRUD;
import fr.kira.formation.spring.hexagonal.competences.usecases.ports.sortie.PersonneCRUD;
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
    Personne personne = new Personne("cible", "","", new ArrayList<>());
    Personne validateur = new Personne("validateur", "","", new ArrayList<>());

    @BeforeEach
    void setUp(){
        personneCRUD = Mockito.mock(PersonneCRUD.class);
        Mockito.when(personneCRUD.findById("cible")).thenReturn(personne);
        Mockito.when(personneCRUD.findById("validateur")).thenReturn(validateur);
        competenceCRUD = Mockito.mock(CompetenceCRUD.class);
        Mockito.when(competenceCRUD.findById("1")).thenReturn(CompetenceData.JAVA);
        service = new ValidationParLesTestsService(personneCRUD, competenceCRUD);
        validateur.setManager(true);
    }

    @Test
    @DisplayName("Si la personne ne possède pas encore le niveau de compétence, alors lui donne un niveau 5")
    void validationNiveau5(){
        Competence comp = CompetenceData.JAVA;
        service.validerCompetence("cible", comp.getId(), "validateur");
        NiveauCompetence niveau = personne.findNiveauCompetence(comp.getId()).orElseThrow();
        assertEquals(5,niveau.getNiveau());
    }

    @Test
    @DisplayName("Si la personne possède déjà le niveau de compétence inferieur a 5, alors lui donne un niveau 5")
    void validationNiveau5Inferieur(){
        Competence comp = CompetenceData.JAVA;
        personne.getNiveauCompetences().add(new NiveauCompetence(comp, 4));
        service.validerCompetence("cible", comp.getId(), "validateur");
        NiveauCompetence niveau = personne.findNiveauCompetence(comp.getId()).orElseThrow();
        assertEquals(5,niveau.getNiveau());
    }

    @Test
    @DisplayName("Si la personne possède déjà le niveau de compétence superieur a 5, alors ne rien faire")
    void validationNiveau5Superieur(){
        Competence comp = CompetenceData.JAVA;
        personne.getNiveauCompetences().add(new NiveauCompetence(comp, 6));
        service.validerCompetence("cible", comp.getId(), "validateur");
        NiveauCompetence niveau = personne.findNiveauCompetence(comp.getId()).orElseThrow();
        assertEquals(6,niveau.getNiveau());
    }

    @Test
    @DisplayName("Le validateur doit être ajouté à la liste des validateurs de la compétence")
    void validationAjoutValidateur(){
        Competence comp = CompetenceData.JAVA;
        service.validerCompetence("cible", comp.getId(), "validateur");
        NiveauCompetence niveau = personne.findNiveauCompetence(comp.getId()).orElseThrow();
        Validation validation = niveau.getValidations().stream().filter(v -> v.getValidateur().getId().equals("validateur")).findFirst().orElseThrow();
        assertEquals(CompetenceData.JAVA.getId(), validation.getIdCompetence());
    }

    @Test
    @DisplayName("Si la personne n'est pas un manager, alors retourné une erreur")
    void validationManager(){
        Competence comp = CompetenceData.JAVA;
        validateur.setManager(false);
        assertThrows(RuntimeException.class, () -> service.validerCompetence("cible", comp.getId(), "validateur"));
    }
}