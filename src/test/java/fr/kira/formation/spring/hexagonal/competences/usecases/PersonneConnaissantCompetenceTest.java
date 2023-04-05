package fr.kira.formation.spring.hexagonal.competences.usecases;

import fr.kira.formation.spring.hexagonal.competences.models.Personne;
import fr.kira.formation.spring.hexagonal.competences.usecases.ports.entree.PersonneConnaissantCompetence;
import fr.kira.formation.spring.hexagonal.competences.usecases.ports.sortie.CompetenceCRUD;
import fr.kira.formation.spring.hexagonal.competences.usecases.ports.sortie.PersonneCRUD;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static fr.kira.formation.spring.hexagonal.competences.CompetenceData.*;
import static fr.kira.formation.spring.hexagonal.competences.PersonneData.*;

class PersonneConnaissantCompetenceTest {

    PersonneConnaissantCompetence service;
    PersonneCRUD personneCRUD;
    CompetenceCRUD competenceCRUD;
    @BeforeEach
    void setUp() {
        personneCRUD = Mockito.mock(PersonneCRUD.class);
        Mockito.when(personneCRUD.findAll()).thenReturn(PERSONNES);
        competenceCRUD = Mockito.mock(CompetenceCRUD.class);
        Mockito.when(competenceCRUD.findById("1")).thenReturn(JAVA);
        service = new PersonneConnaissantCompetenceService(personneCRUD, competenceCRUD);
    }

    @Test
    @DisplayName("Ne retourne pas les personnes qui n'ont pas la compétence")
    void sansCompetenceTest(){
        List<Personne> results = service.findPersonnesConnaissantCompetence("1");
        assertFalse(results.contains(PERSONNE_VIDE));
    }

    @Test
    @DisplayName("Ne doit pas retourner la personne si elle a un niveau inferreur a 5")
    void niveauInferieur(){
        List<Personne> results = service.findPersonnesConnaissantCompetence("1");
        assertFalse(results.contains(PERSONNE_JAVA_INF));
    }

    @Test
    @DisplayName("Doit retourner les personnes qui ont un niveau sup a 5")
    void niveauSup(){
        List<Personne> results = service.findPersonnesConnaissantCompetence("1");
        assertTrue(results.contains(PERSONNE_JAVA));
    }

}