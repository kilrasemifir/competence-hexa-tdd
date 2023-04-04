package fr.kira.formation.spring.hexagonal.competences.models;

import lombok.Data;

@Data
public class Prerequis extends Entity {
    private Competence competence;
    private int niveau;
    private Competence competenceRequis;
    private int niveauRequis;
}
