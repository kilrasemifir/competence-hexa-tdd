package fr.kira.formation.spring.hexagonal.competences.usecases;

import fr.kira.formation.spring.hexagonal.competences.models.Equipe;
import fr.kira.formation.spring.hexagonal.competences.models.Personne;
import fr.kira.formation.spring.hexagonal.competences.usecases.ports.entree.GestionEquipe;
import fr.kira.formation.spring.hexagonal.competences.usecases.ports.sortie.EquipeCRUD;
import fr.kira.formation.spring.hexagonal.competences.usecases.ports.sortie.PersonneCRUD;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GestionEquipeTest {

    GestionEquipe service;
    EquipeCRUD equipeCRUD = Mockito.mock(EquipeCRUD.class);
    PersonneCRUD personneCRUD = Mockito.mock(PersonneCRUD.class);
    Personne membre = new Personne("membre", "","", List.of());
    Equipe equipe = new Equipe("Equipe 1");

    @BeforeEach
    void setUp() {
        Mockito.when(equipeCRUD.sauvegarder(Mockito.any(Equipe.class))).thenAnswer(invocation -> invocation.getArgument(0));
        Mockito.when(equipeCRUD.findById(equipe.getId())).thenReturn(equipe);
        Mockito.when(personneCRUD.findById(membre.getId())).thenReturn(membre);
        service = new GestionEquipeService(equipeCRUD, personneCRUD);
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
        Mockito.verify(equipeCRUD).sauvegarder(equipe);
    }

    @Test
    @DisplayName("Lors de l'ajout d'une personne a une équipe, la personne est dans la liste des membres")
    void ajouterPersonneAEquipe() {
        service.ajouterPersonneAEquipe(membre.getId(), equipe.getId());
        assertEquals(1, equipe.getMembres().size());
        assertEquals(membre.getPrenom(), equipe.getMembres().get(0).getPrenom());
    }

    @Test
    @DisplayName("Lors de l'ajout d'un membre, l'équipe doit être sauvegardée")
    void ajouterPersonneAEquipeSauvegarde() {
        service.ajouterPersonneAEquipe(membre.getId(), equipe.getId());
        Mockito.verify(equipeCRUD).sauvegarder(equipe);
    }

    @Test
    @DisplayName("Lors de la suppression d'un membre, le membres n'est plus dans la liste des membres")
    void supprimerPersonneDeEquipe() {
        equipe.getMembres().add(membre);
        service.supprimerPersonneDeEquipe(membre.getId(), equipe.getId());
        assertFalse(equipe.getMembres().contains(membre));
    }

    @Test
    @DisplayName("Lors de la suppression d'un membre, l'équipe doit être sauvegardée")
    void supprimerPersonneDeEquipeSauvegarde() {
        equipe.getMembres().add(membre);
        service.supprimerPersonneDeEquipe(membre.getId(), equipe.getId());
        Mockito.verify(equipeCRUD).sauvegarder(equipe);
    }

}