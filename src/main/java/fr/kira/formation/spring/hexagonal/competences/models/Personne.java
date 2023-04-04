package fr.kira.formation.spring.hexagonal.competences.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Personne extends Entity  {
    private String nom;
    private String prenom;
    private List<NiveauCompetence> niveauCompetences = new ArrayList<>();
}
