package fr.kira.formation.spring.hexagonal.competences.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Equipe {
    private String id;
    private String nom;
    private List<Personne> personnes = new ArrayList<>();
}
