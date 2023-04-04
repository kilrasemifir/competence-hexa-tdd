package fr.kira.formation.spring.hexagonal.competences.usecases.impl;

import fr.kira.formation.spring.hexagonal.competences.models.Competence;
import fr.kira.formation.spring.hexagonal.competences.usecases.CompetenceCRUD;
import fr.kira.formation.spring.hexagonal.competences.usecases.PersonneCRUD;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static fr.kira.formation.spring.hexagonal.competences.CompetenceData.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Gestion des competences accessibles par une personne")
class CompetenceAccessiblesTest {

    CompetenceCRUD competenceCRUD;
    PersonneCRUD personneCRUD;
    GestionNiveauCompetenceV1 gestion;

    @BeforeAll
    void init(){
        competenceCRUD = Mockito.mock(CompetenceCRUD.class);
        personneCRUD = Mockito.mock(PersonneCRUD.class);
        Mockito.when(competenceCRUD.findAll()).thenReturn(competences);
        Mockito.when(personneCRUD.findById("1")).thenReturn(PERSONNE_JAVA);
        gestion = new GestionNiveauCompetenceV1(competenceCRUD, personneCRUD);
    }


    @Test
    @DisplayName("Doit retourner les competences qui n'ont aucun prerequis")
    void sansPrerequis(){
        List<Competence> results = gestion.competenceAccessibles("1");
        assertTrue(results.contains(JAVA));
    }

    @Test
    @DisplayName("Doit retourner les competences dont la personne possède les prérequis")
    void competenceAvecPrerequis(){
        List<Competence> results = gestion.competenceAccessibles("1");
        assertTrue(results.contains(SPRING));
    }

    @Test
    @DisplayName("Ne doit pas retourner les competences dont la personne ne possède pas les prèrequis")
    void competenceSansPrerequis(){
        List<Competence> results = gestion.competenceAccessibles("1");
        assertFalse(results.contains(DOT_NET_CORE));
    }

}