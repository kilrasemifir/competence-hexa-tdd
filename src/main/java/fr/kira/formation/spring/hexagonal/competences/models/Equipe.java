package fr.kira.formation.spring.hexagonal.competences.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Equipe extends Entity  {
    private String nom;
    private List<Personne> membres = new ArrayList<>();

    public Equipe(String nom) {
        this.nom = nom;
    }
}
