package fr.kira.formation.spring.hexagonal.competences.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Personne extends Entity  {
    private String nom;
    private String prenom;
    private boolean isManager = false;
    private List<NiveauCompetence> niveauCompetences = new ArrayList<>();

    public Personne(String id, String nom, String prenom, List<NiveauCompetence> niveauCompetences) {
        super(id);
        this.nom = nom;
        this.prenom = prenom;
        this.niveauCompetences = niveauCompetences;
    }

    public Optional<NiveauCompetence> findNiveauCompetence(String idCompetence) {
        return niveauCompetences.stream()
                .filter(niveauCompetence -> niveauCompetence.getCompetence().getId().equals(idCompetence))
                .findFirst();
    }
}
