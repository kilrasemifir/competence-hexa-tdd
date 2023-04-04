package fr.kira.formation.spring.hexagonal.competences.models;

import lombok.Data;

@Data
public class NiveauCompetence extends Entity{
    private Competence competence;
    private int niveau;

    public void setNiveau(int niveau){
        if (niveau < 0 || niveau >10){
            throw new RuntimeException("Le niveau doit être compris entre 0 et 10");
        }
        this.niveau = niveau;
    }

}
