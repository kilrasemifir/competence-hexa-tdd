package fr.kira.formation.spring.hexagonal.competences.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Personne extends Entity  {
    private String nom;
    private String prenom;
    private List<NiveauCompetence> niveauCompetences = new ArrayList<>();
}
