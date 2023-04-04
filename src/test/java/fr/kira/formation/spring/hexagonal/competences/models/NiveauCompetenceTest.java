package fr.kira.formation.spring.hexagonal.competences.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NiveauCompetenceTest {

    @Test
    @DisplayName("Le niveau ne doit pas être en dehors de 0 et 10")
    void setNiveau() {
        // Given
        NiveauCompetence niveauCompetence = new NiveauCompetence();
        // When Then
        assertThrows(RuntimeException.class, ()->niveauCompetence.setNiveau(-1));
    }
}