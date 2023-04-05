package fr.kira.formation.spring.hexagonal.competences.usecases;

import fr.kira.formation.spring.hexagonal.competences.CompetenceData;
import fr.kira.formation.spring.hexagonal.competences.models.NiveauCompetence;
import fr.kira.formation.spring.hexagonal.competences.models.Personne;
import fr.kira.formation.spring.hexagonal.competences.models.Validation;
import fr.kira.formation.spring.hexagonal.competences.ports.CompetenceCRUD;
import fr.kira.formation.spring.hexagonal.competences.ports.PersonneCRUD;
import fr.kira.formation.spring.hexagonal.competences.usecases.impl.ValidationParLesPairsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidationParLesPairsTest {

    ValidationParLesPairs service;
    Personne cible = new Personne("cible","","", new ArrayList<>());
    Personne validateur = new Personne("validateur","","", new ArrayList<>());
    PersonneCRUD personneCRUD;
    CompetenceCRUD competenceCRUD;

    @BeforeEach
    void setUp() {
        personneCRUD = Mockito.mock(PersonneCRUD.class);
        Mockito.when(personneCRUD.findById("cible")).thenReturn(cible);
        Mockito.when(personneCRUD.findById("validateur")).thenReturn(validateur);

        competenceCRUD = Mockito.mock(CompetenceCRUD.class);
        Mockito.when(competenceCRUD.findById("1")).thenReturn(CompetenceData.JAVA);

        service = new ValidationParLesPairsService(personneCRUD, competenceCRUD);
    }

    @Test
    @DisplayName("Si la cible n'a pas encore de niveau, le validateur a un niveau superieur a la demande, alors la cible gagne le niveau demandé")
    void validationCompetence() {
        validateur.getNiveauCompetences().add(new NiveauCompetence(CompetenceData.JAVA, 7));
        service.validerCompetence("cible", "validateur", "1", 6);
        NiveauCompetence niveau = cible.findNiveauCompetence(CompetenceData.JAVA.getId()).orElseThrow();
        assertEquals(6, niveau.getNiveau());
    }

    @Test
    @DisplayName("Si la cible possède un niveau inferieur a la demande, le validateur a un niveau superieur a la demande, alors la cible gagne le niveau demandé")
    void validationCompetenceInferieur() {
        validateur.getNiveauCompetences().add(new NiveauCompetence(CompetenceData.JAVA, 7));
        cible.getNiveauCompetences().add(new NiveauCompetence(CompetenceData.JAVA, 5));
        service.validerCompetence("cible", "validateur", "1", 6);
        NiveauCompetence niveau = cible.findNiveauCompetence(CompetenceData.JAVA.getId()).orElseThrow();
        assertEquals(6, niveau.getNiveau());
    }

    @Test
    @DisplayName("Si la cible possède déjà un niveau superieur a la demande, ne rien faire")
    void validationCompetenceSuperieur() {
        validateur.getNiveauCompetences().add(new NiveauCompetence(CompetenceData.JAVA, 7));
        cible.getNiveauCompetences().add(new NiveauCompetence(CompetenceData.JAVA, 8));
        service.validerCompetence("cible", "validateur", "1", 6);
        NiveauCompetence niveau = cible.findNiveauCompetence(CompetenceData.JAVA.getId()).orElseThrow();
        assertEquals(8, niveau.getNiveau());
        assertEquals(1, cible.getNiveauCompetences().size());
    }

    @Test
    @DisplayName("Si le validateur n'a pas le niveau demandé, retourné une erreur")
    void validationCompetenceValidateurInferieur() {
        validateur.getNiveauCompetences().add(new NiveauCompetence(CompetenceData.JAVA, 5));
        NiveauCompetence niveauCible = cible.findNiveauCompetence(CompetenceData.JAVA.getId()).orElse(null);
        assertThrows(RuntimeException.class, ()-> {
            service.validerCompetence("cible", "validateur", "1", 6);
            assertNull(niveauCible);
        });
    }

    @Test
    @DisplayName("Si le validateur n'a pas le niveau demandé, retourné une erreur")
    void validationCompetenceInferieur2() {
        validateur.setNiveauCompetences(List.of());
        NiveauCompetence niveauCible = cible.findNiveauCompetence(CompetenceData.JAVA.getId()).orElse(null);
        assertThrows(RuntimeException.class, ()-> {
            service.validerCompetence("cible", "validateur", "1", 6);
            assertNull(niveauCible);
        });
    }

    @Test
    @DisplayName("Lors ce que la validation est fini, le validateur est ajouté a la liste des validations a la compétence de la cible")
    void validationCompetenceAjoutListe() {
        validateur.getNiveauCompetences().add(new NiveauCompetence(CompetenceData.JAVA, 7));
        service.validerCompetence("cible", "validateur", "1", 6);
        NiveauCompetence niveau = cible.findNiveauCompetence(CompetenceData.JAVA.getId()).orElseThrow();
        Validation validation = niveau.getValidations().stream().filter(v -> v.getValidateur().equals(validateur)).findFirst().orElseThrow();
        assertEquals(CompetenceData.JAVA.getId(), validation.getIdCompetence());
        assertEquals(6, validation.getNiveau());
    }
}