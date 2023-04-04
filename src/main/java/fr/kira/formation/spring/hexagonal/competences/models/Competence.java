package fr.kira.formation.spring.hexagonal.competences.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Competence extends Entity {
    private String nom;
    private String description;
    private List<Prerequis> prerequis = new ArrayList<>();

    public Competence(String id, String nom, String description, List<Prerequis> prerequis) {
        super(id);
        this.nom = nom;
        this.description = description;
        this.prerequis = prerequis;
    }
}
