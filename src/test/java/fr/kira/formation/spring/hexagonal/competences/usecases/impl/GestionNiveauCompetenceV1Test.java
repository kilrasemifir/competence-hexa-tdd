package fr.kira.formation.spring.hexagonal.competences.usecases.impl;

import fr.kira.formation.spring.hexagonal.competences.models.Competence;
import fr.kira.formation.spring.hexagonal.competences.models.NiveauCompetence;
import fr.kira.formation.spring.hexagonal.competences.models.Personne;
import fr.kira.formation.spring.hexagonal.competences.models.Prerequis;
import fr.kira.formation.spring.hexagonal.competences.usecases.CompetenceCRUD;
import fr.kira.formation.spring.hexagonal.competences.usecases.PersonneCRUD;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GestionNiveauCompetenceV1Test {
    static Competence JAVA = new Competence( "java", "poo", List.of() );

    static Competence SPRING = new Competence("spring","framework",new ArrayList<>());
    static Competence DOT_NET_CORE = new Competence(".NET CORE", "C#", new ArrayList<>());
    static Competence C_SHARP = new Competence("C#", "POO", new ArrayList<>());
    static Prerequis SPRING_JAVA = new Prerequis(SPRING, 5, JAVA, 5);
    static Prerequis DOT_NET_C_SHARP = new Prerequis(DOT_NET_CORE, 5, C_SHARP, 5);

    static {
        SPRING.getPrerequis().add(SPRING_JAVA);
        DOT_NET_CORE.getPrerequis().add(DOT_NET_C_SHARP);
    }

    static Personne personne = new Personne("Doe","John", List.of(
            new NiveauCompetence(JAVA, 6)
    ));

    static List<Competence> competences = List.of(
            JAVA,
            SPRING,
            C_SHARP,
            DOT_NET_CORE
    );
    @Mock
    CompetenceCRUD competenceCRUD;
    @Mock
    PersonneCRUD personneCRUD;

    GestionNiveauCompetenceV1 gestion; ;


    @Test
    @DisplayName("Doit retourner les competences qui n'ont aucun prerequis")
    void sansPrerequis(){
        // Given
        Mockito.when(competenceCRUD.findAll()).thenReturn(competences);
        Mockito.when(personneCRUD.findById("1")).thenReturn(personne);
        gestion = new GestionNiveauCompetenceV1(competenceCRUD, personneCRUD);
        // When
        List<Competence> results = gestion.competenceAccessibles("1");
        // Then
        assertTrue(results.contains(JAVA));
    }

    @Test
    @DisplayName("Doit retourner les competences dont la personne possède les prérequis")
    void competenceAvecPrerequis(){
        Mockito.when(competenceCRUD.findAll()).thenReturn(competences);
        Mockito.when(personneCRUD.findById("1")).thenReturn(personne);
        gestion = new GestionNiveauCompetenceV1(competenceCRUD, personneCRUD);
        List<Competence> results = gestion.competenceAccessibles("1");
        assertTrue(results.contains(SPRING));
    }

    @Test
    @DisplayName("Ne doit pas retourner les competences dont la personne ne possède pas les prèrequis")
    void competenceSansPrerequis(){
        Mockito.when(competenceCRUD.findAll()).thenReturn(competences);
        Mockito.when(personneCRUD.findById("1")).thenReturn(personne);
        gestion = new GestionNiveauCompetenceV1(competenceCRUD, personneCRUD);
        List<Competence> results = gestion.competenceAccessibles("1");
        assertFalse(results.contains(DOT_NET_CORE));
    }

}