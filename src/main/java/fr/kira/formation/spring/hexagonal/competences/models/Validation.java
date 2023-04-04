package fr.kira.formation.spring.hexagonal.competences.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Validation {
    private Personne validateur;
    private String idCompetence;
    private int niveau;
    private LocalDateTime dateValidation = LocalDateTime.now();

    public Validation(Personne validateur, String idCompetence, int niveau) {
        this.validateur = validateur;
        this.idCompetence = idCompetence;
        this.niveau = niveau;
    }
}
