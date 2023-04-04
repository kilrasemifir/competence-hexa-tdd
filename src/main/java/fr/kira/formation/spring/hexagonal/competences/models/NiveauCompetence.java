package fr.kira.formation.spring.hexagonal.competences.models;

import lombok.Data;

@Data
public class NiveauCompetence {
    private Competence competence;
    private int niveau;
}
