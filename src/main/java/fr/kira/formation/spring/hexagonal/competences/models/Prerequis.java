package fr.kira.formation.spring.hexagonal.competences.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prerequis extends Entity {
    private Competence competence;
    private int niveau;
    private Competence competenceRequis;
    private int niveauRequis;
}
