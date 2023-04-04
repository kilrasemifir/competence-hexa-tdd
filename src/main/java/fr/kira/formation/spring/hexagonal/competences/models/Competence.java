package fr.kira.formation.spring.hexagonal.competences.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Competence extends Entity {
    private String nom;
    private String description;
    private List<Prerequis> prerequis = new ArrayList<>();
}
