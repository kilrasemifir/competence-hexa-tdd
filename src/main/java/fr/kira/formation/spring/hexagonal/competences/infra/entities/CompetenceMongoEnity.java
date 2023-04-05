package fr.kira.formation.spring.hexagonal.competences.infra.entities;

import fr.kira.formation.spring.hexagonal.competences.models.Competence;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class CompetenceMongoEnity extends Competence {
    @Id
    private String id;
}
