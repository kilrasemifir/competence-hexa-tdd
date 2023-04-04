package fr.kira.formation.spring.hexagonal.competences.models;

import lombok.Data;

@Data
public class Competence extends Entity {
    private String nom;
    private String description;
}
