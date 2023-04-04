package fr.kira.formation.spring.hexagonal.competences.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NiveauCompetence extends Entity{
    private Competence competence;
    private int niveau;
    private List<Validation> validations = new ArrayList<>();

    public NiveauCompetence(Competence competence, int niveau) {
        this.competence = competence;
        this.niveau = niveau;
    }

    public void setNiveau(int niveau){
        if (niveau < 0 || niveau >10){
            throw new RuntimeException("Le niveau doit être compris entre 0 et 10");
        }
        this.niveau = niveau;
    }

}
