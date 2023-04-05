package fr.kira.formation.spring.hexagonal.competences.api.dto;

import lombok.Data;

@Data
public class ValidationParPairDTO {
    private String idPersonneCible;
    private String idPersonneValidateur;
    private String idCompetence;
    private int niveau;
}
