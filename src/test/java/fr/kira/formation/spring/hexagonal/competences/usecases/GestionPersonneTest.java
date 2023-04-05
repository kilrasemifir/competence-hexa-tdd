package fr.kira.formation.spring.hexagonal.competences.usecases;

import fr.kira.formation.spring.hexagonal.competences.usecases.ports.entree.GestionPersonne;
import fr.kira.formation.spring.hexagonal.competences.usecases.ports.sortie.PersonneCRUD;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class GestionPersonneTest {

    PersonneCRUD personneCRUD = Mockito.mock(PersonneCRUD.class);
    GestionPersonne service;
    @BeforeEach
    void setUp() {
        service = new GestionPersonneService(personneCRUD);
    }

    @Test
    @DisplayName("Lors de la création d'une personne, la personne est sauvegardée")
    void creationPersonne() {
        service.creationPersonne("nom", "prenom");
        Mockito.verify(personneCRUD).sauvegarder(Mockito.any());
    }
}