package fr.kira.formation.spring.hexagonal.competences.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Equipe extends Entity  {
    private String nom;
    private List<Personne> personnes = new ArrayList<>();
}
