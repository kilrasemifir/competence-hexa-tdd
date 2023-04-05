package fr.kira.formation.spring.hexagonal.competences.usecases;

import fr.kira.formation.spring.hexagonal.competences.models.Equipe;
import fr.kira.formation.spring.hexagonal.competences.ports.EquipeCRUD;
import fr.kira.formation.spring.hexagonal.competences.usecases.impl.GestionEquipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class GestionEquipeTest {

    GestionEquipe service;
    EquipeCRUD equipeCRUD;

    @BeforeEach
    void setUp() {
        equipeCRUD = Mockito.mock(EquipeCRUD.class);
        Mockito.when(equipeCRUD.create(Mockito.any(Equipe.class))).thenAnswer(invocation -> invocation.getArgument(0));
        service = new GestionEquipeService(equipeCRUD);
    }

    @Test
    @DisplayName("Création d'une équipe")
    void creationEquipe() {
        Equipe equipe = service.creationEquipe("Equipe 1");
        assertEquals("Equipe 1", equipe.getNom());
        assertTrue(equipe.getMembres().isEmpty());
    }

    @Test
    @DisplayName("La creation doit sauvegarder l'équipe")
    void creationEquipeSauvegarde() {
        Equipe equipe = service.creationEquipe("Equipe 1");
        Mockito.verify(equipeCRUD).create(equipe);
    }
}